package com.smartrider24.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.smartrider24.R;
import com.smartrider24.model.customertracking.TrackBookingHistory;

import java.util.List;

public class Tracklistadapter extends RecyclerView.Adapter<Tracklistadapter.MyViewHolder> {
    private List<TrackBookingHistory> todayBookingHistories;

    public Tracklistadapter(List<TrackBookingHistory> todayBookingHistories) {
        this.todayBookingHistories = todayBookingHistories;
    }

    public void setTodayBookingHistories(List<TrackBookingHistory> todayBookingHistories) {
        this.todayBookingHistories = todayBookingHistories;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView placeName, placeTime, status;

        public MyViewHolder(View view) {
            super(view);
            placeName = view.findViewById(R.id.placeName);
            placeTime = view.findViewById(R.id.dateTime);
            status = view.findViewById(R.id.txtStatus);
        }
    }
    @Override
    public Tracklistadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_trackhistory, parent, false);

        return new Tracklistadapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Tracklistadapter.MyViewHolder holder, int position) {
        TrackBookingHistory trackBookingHistory = todayBookingHistories.get(position);
        if (trackBookingHistory.getTarckDateTime()!=null) {
            holder.placeTime.setText("" + trackBookingHistory.getTarckDateTime());
        }
        if (trackBookingHistory.getTarckStatus()!=null) {
            holder.status.setText("" + trackBookingHistory.getTarckStatus());
        }
        if (trackBookingHistory.getTarckLocation()!=null) {
            holder.placeName.setText("" + trackBookingHistory.getTarckLocation());
        }

    }

    @Override
    public int getItemCount() {
        if (todayBookingHistories != null && todayBookingHistories.size() > 0)
            return todayBookingHistories.size();
        return 0;
    }


}

