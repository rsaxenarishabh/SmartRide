package com.smartrider24.Activity.DriverSide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smartrider24.Activity.DriverClosedRide;
import com.smartrider24.Activity.DriverPendingRide;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.driverconfirmride.DriverConfirmBookingResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class ClosedTrips extends AppCompatActivity {

    ImageView backButton;
    RelativeLayout relativeLayout;
    RecyclerView recyclerView;

    private static final String TAG = YourTrips.class.getSimpleName();
    DriverClosedRide driverPendingRide;
    String mobile = "", uniqId = "", lsessionId = "", rideType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_trips);
        recyclerView=findViewById(R.id.recyclerClosed);
        relativeLayout=findViewById(R.id.NotripsClose);

        backButton= findViewById(R.id.backc);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        getPendingRide();
    }

    private void getPendingRide() {



        mobile = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_MOBILE_NO);
        lsessionId = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_LSESSION_ID);
        uniqId = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_UNIQUE_ID);
        rideType = "Closed";
        Log.d(TAG, "getPendingRide: " + mobile);
        Log.d(TAG, "getPendingRide: " + lsessionId);
        Log.d(TAG, "getPendingRide: " + uniqId);
        Log.d(TAG, "getPendingRide: " + rideType);

        RequestBody cnfdrvbkrqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody cnfdrvbkreqlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody cnfdrvbkrqregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        RequestBody cnfdrvbkrqtypctrlmst = RequestBody.create(MediaType.parse("text/plain"), rideType);

        if (Utils.isInternetConnected(ClosedTrips.this)) {
            Utils.showProgressDialog(ClosedTrips.this);
            RestClient.driverRideClosed(cnfdrvbkrqlngunqid, cnfdrvbkreqlngsessid, cnfdrvbkrqregunqid, cnfdrvbkrqtypctrlmst, new Callback<DriverConfirmBookingResponse>() {
                @Override
                public void onResponse(Call<DriverConfirmBookingResponse> call, Response<DriverConfirmBookingResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            if (response.body().getResults().getDriverBookingDetails() != null &&
                                    response.body().getResults().getDriverBookingDetails().size() > 0) {
                                driverPendingRide = new DriverClosedRide(response.body().getResults().getDriverBookingDetails());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ClosedTrips.this);
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(driverPendingRide);
                                relativeLayout.setVisibility(GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            } else {
                                relativeLayout.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(GONE);
                            }

                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Log.d(TAG, "onResponse: " + response.body().getResults().getMsg());
                        }
                    }

                }

                @Override
                public void onFailure(Call<DriverConfirmBookingResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(ClosedTrips.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(ClosedTrips.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }


    }
}
