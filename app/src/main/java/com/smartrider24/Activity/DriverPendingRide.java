package com.smartrider24.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.Fragments.Pending;
import com.smartrider24.Interfaces.Datum;
import com.smartrider24.R;
import com.smartrider24.model.driverbookpending.TodayBookingHistory;
import com.smartrider24.model.driverconfirmride.DriverBookingDetail;

import java.util.List;

public class DriverPendingRide extends RecyclerView.Adapter<DriverPendingRide.MyViewHolder> {

    private List<DriverBookingDetail> todayBookingHistories;
    private onItemClickData onItemClickData;
    public void setOnItemClickData(DriverPendingRide.onItemClickData onItemClickData) {
        this.onItemClickData = onItemClickData;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rating, sourcePlace, sourceDistrict, destinationPlace, destinationDistrict, vehicleName, vehicleInfo, vehiTime,
                goodsInfo, totalDistance, totalcharge,textViewStartJob;

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
            textViewStartJob=view.findViewById(R.id.txtStartJob);


        }
    }


    public DriverPendingRide(List<DriverBookingDetail> historyList) {
        this.todayBookingHistories = historyList;
    }

    @Override
    public DriverPendingRide.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_data_trip, parent, false);

        return new DriverPendingRide.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DriverPendingRide.MyViewHolder holder, int position) {
        final DriverBookingDetail ddd = todayBookingHistories.get(position);
        //   holder.rating.setText(ddd.);
        holder.sourcePlace.setText(ddd.getBookingPickUpLocation());
        //   holder.sourceDistrict.setText(ddd.getsDistrict());
        holder.destinationPlace.setText(ddd.getBookingDeliveryLocation());
        // holder.destinationDistrict.setText(ddd.getdDistrict());
//        holder.vehicleName.setText(ddd.getVehicleName());
//        holder.vehicleInfo.setText(ddd.getVehicleDetails());
        holder.vehiTime.setText(ddd.getBookingDate());
        //     holder.goodsInfo.setText(ddd.getGoodsInfo());
        holder.totalDistance.setText(ddd.getBookingDistance()+"Km");
        holder.totalcharge.setText(ddd.getBookingFareAmt());
        if (ddd.getBook_ITEM_Remark()!=null){
            holder.goodsInfo.setText(""+ddd.getBook_ITEM_Remark());
        }
        if (ddd.getBooking_Vechile_Name()!=null){
            holder.vehicleName.setText(""+ddd.getBooking_Vechile_Name());
        }

        holder.textViewStartJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickData!=null){
                    onItemClickData.onClickConfirmData(ddd);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (todayBookingHistories!=null && todayBookingHistories.size()>0)
             return todayBookingHistories.size();
        return 0;
    }


    public interface onItemClickData{
         void onClickConfirmData(DriverBookingDetail ddd);
    }


}

