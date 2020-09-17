package com.smartrider24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.Interfaces.Datum;
import com.smartrider24.model.driverbookpending.TodayBookingHistory;

import java.util.List;

public class DriverAcceptRideAdapter extends RecyclerView.Adapter<DriverAcceptRideAdapter.MyViewHolder> {

    private List<TodayBookingHistory> todayBookingHistories;


    private onItemClickData onItemClickData;


    public void setOnItemClickData(DriverAcceptRideAdapter.onItemClickData onItemClickData) {
        this.onItemClickData = onItemClickData;
    }
    public void notifydata(){
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rating, sourcePlace, sourceDistrict, destinationPlace, destinationDistrict, vehicleName, vehicleInfo, vehiTime,
                goodsInfo, totalDistance, totalcharge, accept;

        public MyViewHolder(View view) {
            super(view);
            rating = (TextView) view.findViewById(R.id.rating);
            sourcePlace = (TextView) view.findViewById(R.id.txt_sName);
            sourceDistrict = (TextView) view.findViewById(R.id.txt_sDistrict);
            destinationPlace = (TextView) view.findViewById(R.id.txt_dName);
            destinationDistrict = (TextView) view.findViewById(R.id.txt_dDistrict);
            vehicleName = (TextView) view.findViewById(R.id.txt_vehicleName);
            vehicleInfo = (TextView) view.findViewById(R.id.vehicleInfo);
            vehiTime = (TextView) view.findViewById(R.id.time);
            goodsInfo = (TextView) view.findViewById(R.id.goodsInfo);
            totalDistance = (TextView) view.findViewById(R.id.distance);
            totalcharge = (TextView) view.findViewById(R.id.totalCharge);
            accept = view.findViewById(R.id.btnaccpt);
        }
    }


    public DriverAcceptRideAdapter(List<TodayBookingHistory> datumList) {
        this.todayBookingHistories = datumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_trips, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TodayBookingHistory ddd = todayBookingHistories.get(position);
//        holder.rating.setText(ddd);
        if (ddd.getBookingPickUpLocation() != null)
            holder.sourcePlace.setText(ddd.getBookingPickUpLocation());
        //  holder.sourceDistrict.setText(ddd.getsDistrict());
        if (ddd.getBookingDeliveryLocation() != null)
            holder.destinationPlace.setText(ddd.getBookingDeliveryLocation());
        //   holder.destinationDistrict.setText(ddd.getdDistrict());
//        holder.vehicleName.setText(ddd.());
        if (ddd.getBookingTrackNo() != null)
            holder.vehicleInfo.setText(ddd.getBookingTrackNo());
        if (ddd.getBookingDate() != null)
            holder.vehiTime.setText(ddd.getBookingDate());
        // holder.goodsInfo.setText(ddd.getGoodsInfo());
        if (ddd.getBookingDistance() != null)
            holder.totalDistance.setText(ddd.getBookingDistance() + " " + "KM");
        if (ddd.getBookingFareAmt() != null)
            holder.totalcharge.setText(ddd.getBookingFareAmt());
        if (ddd.getBookingStatus().equalsIgnoreCase("Booked")) {
            holder.accept.setVisibility(View.VISIBLE);
        } else {
            holder.accept.setVisibility(View.GONE);
        }
        if (ddd.getBooking_Vechile_Name()!=null){
            holder.vehicleName.setText(""+ddd.getBooking_Vechile_Name());
        }
        if (ddd.getBook_ITEM_Remark()!=null){
            holder.goodsInfo.setText(""+ddd.getBook_ITEM_Remark());
        }
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickData!=null){
                    onItemClickData.onClickConfirmData(ddd.getBookingRefNo(),ddd.getBookingTrackNo(),ddd.getBookingFareAmt());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (todayBookingHistories != null && todayBookingHistories.size() > 0)
            return todayBookingHistories.size();
        return 0;
    }

    public interface onItemClickData {
        void onClickConfirmData(String ReferenceNo, String trackNo, String fareAmount);
    }
}

