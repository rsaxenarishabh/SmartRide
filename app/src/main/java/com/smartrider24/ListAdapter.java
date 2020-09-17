package com.smartrider24;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.model.bookinghistory.BookingHistorySuccessResponse;
import com.smartrider24.model.bookinghistory.MyBookingHistory;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<MyBookingHistory> bookingHistorySuccessResponses;
    onItemClick onItemClick;

    public void setOnCallItemclick(ListAdapter.onCallItemclick onCallItemclick) {
        this.onCallItemclick = onCallItemclick;
    }

    onCallItemclick onCallItemclick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rating, sourcePlace, sourceDistrict, destinationPlace, destinationDistrict, vehicleName, vehicleInfo, vehiTime,
                goodsInfo, totalDistance, totalcharge, tracking, callDriver;
        LinearLayout linearLayoutConfirm;

        public MyViewHolder(View view) {
            super(view);
            //   rating = (TextView) view.findViewById(R.id.rating);
            sourcePlace = (TextView) view.findViewById(R.id.txt_sName);
            sourceDistrict = (TextView) view.findViewById(R.id.txt_sDistrict);
            destinationPlace = (TextView) view.findViewById(R.id.txt_dName);
            destinationDistrict = (TextView) view.findViewById(R.id.txt_dDistrict);
            vehicleName = (TextView) view.findViewById(R.id.txt_vehicleName);
            // vehicleInfo = (TextView) view.findViewById(R.id.vehicleInfo);
            vehiTime = (TextView) view.findViewById(R.id.time);
            // goodsInfo = (TextView) view.findViewById(R.id.goodsInfo);
            totalDistance = (TextView) view.findViewById(R.id.distance);
            totalcharge = (TextView) view.findViewById(R.id.totalCharge);
            linearLayoutConfirm = view.findViewById(R.id.linearConfirmPending);
            tracking = view.findViewById(R.id.tracking);
            callDriver = view.findViewById(R.id.CallDriver);
//                tracking.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent=new Intent(getApplicationContext(), TrackRides.class);
//                        startActivity(intent);
//                    }
//                });
//
//                callDriver.setOnClickListener(new View.OnClickListener() {
//                    @SuppressLint("MissingPermission")
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent=new Intent(Intent.ACTION_DIAL);
//                        startActivity(intent);
//                    }
//                });
        }
    }
    public void setOnItemClick(ListAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


    public ListAdapter(List<MyBookingHistory> bookingHistorySuccessResponses) {
        this.bookingHistorySuccessResponses = bookingHistorySuccessResponses;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_data_rides, parent, false);

        return new ListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, int position) {
        final MyBookingHistory bookingreesponse = bookingHistorySuccessResponses.get(position);
        //  holder.rating.setText(ddd.getRating());

        if (bookingreesponse.getBookingPickUpLocation() != null) {
            holder.sourcePlace.setText("" + bookingreesponse.getBookingPickUpLocation());
            //   holder.sourceDistrict.setText(ddd.getsDistrict());
        }
        if (bookingreesponse.getBookingDeliveryLocation() != null) {
            holder.destinationPlace.setText("" + "" + bookingreesponse.getBookingDeliveryLocation());
        }


        //  holder.destinationDistrict.setText(ddd.getdDistrict());
        if (bookingreesponse.getBookingVechileName() != null) {
            holder.vehicleName.setText("" + bookingreesponse.getBookingVechileName());
        }
        //    holder.vehicleInfo.setText(ddd.getVehicleDetails());
        //holder.vehiTime.setText(""+bookingreesponse.getResults().getMyBookingHistory().get(position).getBookingPickUpLocation());
        //holder.goodsInfo.setText(ddd.getGoodsInfo());
        if (bookingreesponse.getBookingDistance() != null) {
            holder.totalDistance.setText("" + bookingreesponse.getBookingDistance() + "KM");
        }
        if (bookingreesponse.getBookingFareAmt() != null) {
            holder.totalcharge.setText("" + "" + bookingreesponse.getBookingFareAmt());
        }

        if (bookingreesponse.getBookingStatus().equalsIgnoreCase("Confirmed")) {
            holder.linearLayoutConfirm.setVisibility(View.VISIBLE);
        }
        if (bookingreesponse.getBookingStatus().equalsIgnoreCase("Booked")) {
            holder.linearLayoutConfirm.setVisibility(View.GONE);
        }
        holder.tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick!=null){
                    onItemClick.onTrackItems(bookingreesponse.getBookingRefNo(),bookingreesponse.getBookingTrackNo());
                }
            }
        });
        holder.callDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (onCallItemclick!=null){
                 onCallItemclick.onCallItems(bookingreesponse.getDriverMobileNo());
             }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (bookingHistorySuccessResponses != null && bookingHistorySuccessResponses.size() > 0) {
            return bookingHistorySuccessResponses.size();
        } else {
            return 0;
        }

    }


    public interface onItemClick {
        void onTrackItems(String bookrefernceNo, String trackNo);

    }
    public interface  onCallItemclick{
        void onCallItems(String callNo);
    }
}