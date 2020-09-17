package com.smartrider24.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.Activity.DriverPendingRide;
import com.smartrider24.Activity.Login;
import com.smartrider24.Activity.Profile;
import com.smartrider24.Interfaces.Datum;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.driverbookpending.DriverBookingPendingResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static android.view.View.GONE;

public class Pending extends Fragment {

    private static final String TAG = Pending.class.getSimpleName();
    RecyclerView recyclerView;
    DriverPendingRide driverPendingRide;
    String mobile="",uniqId="",lsessionId="";
    RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_fragment_pending, container, false);
        recyclerView = view.findViewById(R.id.recyclerPending);
        relativeLayout=view.findViewById(R.id.Notrips);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  getPendingRide();
    }

//    private void getPendingRide() {
//         mobile = SmartRidePref.getString(getActivity(), Constants.CUSTOMER_MOBILE_NO);
//         uniqId = SmartRidePref.getString(getActivity(), Constants.CUSTOMER_UNIQUE_ID);
//         lsessionId = SmartRidePref.getString(getActivity(), Constants.CUSTOMER_LSESSION_ID);
//
//
//        RequestBody drvbkhstreqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobile);
//        RequestBody drvbkhstreqlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
//        RequestBody drvbkhstregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);
//
//        if (Utils.isInternetConnected(getContext())) {
//            Utils.showProgressDialog(getContext());
//            RestClient.bookingPending(drvbkhstreqlngunqid, drvbkhstreqlngsessid, drvbkhstregunqid, new Callback<DriverBookingPendingResponse>() {
//                @Override
//                public void onResponse(Call<DriverBookingPendingResponse> call, Response<DriverBookingPendingResponse> response) {
//                    Utils.dismissProgressDialog();
//                    if (response.body() != null) {
//                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
//                            if (response.body().getResults().getTodayBookingHistory() != null &&
//                                    response.body().getResults().getTodayBookingHistory().size() > 0) {
//                                Log.d(TAG, "onResponse: " + response.body().getResults().getTodayBookingHistory().toString());
//                                driverPendingRide = new DriverPendingRide(response.body().getResults().getTodayBookingHistory());
//                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                                recyclerView.setLayoutManager(mLayoutManager);
//                                recyclerView.setAdapter(driverPendingRide);
//                                relativeLayout.setVisibility(GONE);
//                                recyclerView.setVisibility(View.VISIBLE);
//                                driverPendingRide.setOnItemClickData(new DriverPendingRide.onItemClickData() {
//                                    @Override
//                                    public void onClickConfirmData(String ReferenceNo, String trackNo, String fareAmount) {
//                                        rideConfirm(ReferenceNo, trackNo, fareAmount);
//                                    }
//                                });
//                            } else {
//                                relativeLayout.setVisibility(View.VISIBLE);
//                                recyclerView.setVisibility(GONE);
//
//                                Toast.makeText(getActivity(), "No Item", Toast.LENGTH_SHORT).show();
//                            }
//                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
//                            Toast.makeText(getActivity(), "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<DriverBookingPendingResponse> call, Throwable t) {
//                    Utils.dismissProgressDialog();
//                    Toast.makeText(getContext(), "Something went wrong!!", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Something went wrong!!");
//                }
//            });
//
//        } else {
//            Utils.dismissProgressDialog();
//            Toast.makeText(getContext(), "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "Internet Connection Failed!!");
//        }
//
//    }

    private void rideConfirm(String referenceNo, String trackNo, String fareAmount) {
        mobile = SmartRidePref.getString(getActivity(), Constants.CUSTOMER_MOBILE_NO);
        uniqId = SmartRidePref.getString(getActivity(), Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(getActivity(), Constants.CUSTOMER_LSESSION_ID);



        RequestBody drvacptservbkrefno = RequestBody.create(MediaType.parse("text/plain"), referenceNo);
        RequestBody drvacptservbktrkrefno = RequestBody.create(MediaType.parse("text/plain"), trackNo);
        RequestBody drvacpttmserbkamt = RequestBody.create(MediaType.parse("text/plain"), fareAmount);
        RequestBody drvacptbktmlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody drvacptbktmlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody drvacptbktmregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);


    }


}