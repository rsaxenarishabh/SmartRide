package com.smartrider24.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.Fragments.Confirmed;
import com.smartrider24.Interfaces.Datum;
import com.smartrider24.R;

import java.util.List;

public class DriverConfirmRIde extends RecyclerView.Adapter<DriverConfirmRIde.MyViewHolder> {

    private List<Datum> datumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rating, sourcePlace,sourceDistrict,destinationPlace,destinationDistrict,vehicleName,vehicleInfo,vehiTime,
                goodsInfo,totalDistance,totalcharge;
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
        }
    }


    public DriverConfirmRIde(List<Datum> datumList) {
        this.datumList = datumList;
    }

    @Override
    public DriverConfirmRIde.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_data_trip, parent, false);

        return new DriverConfirmRIde.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DriverConfirmRIde.MyViewHolder holder, int position) {
        Datum ddd = datumList.get(position);
        holder.rating.setText(ddd.getRating());
        holder.sourcePlace.setText(ddd.getSourceName());
        holder.sourceDistrict.setText(ddd.getsDistrict());

        holder.destinationPlace.setText(ddd.getDestinationName());
        holder.destinationDistrict.setText(ddd.getdDistrict());
        holder.vehicleName.setText(ddd.getVehicleName());
        holder.vehicleInfo.setText(ddd.getVehicleDetails());
        holder.vehiTime.setText(ddd.getTiming());
        holder.goodsInfo.setText(ddd.getGoodsInfo());
        holder.totalDistance.setText(ddd.getDistance());
        holder.totalcharge.setText(ddd.getChargeFees());

    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }
}



