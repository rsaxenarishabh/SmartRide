package com.smartrider24.Activity.DriverSide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.smartrider24.Activity.DriverClosedRide;
import com.smartrider24.Activity.DriverPendingRide;
import com.smartrider24.Activity.Profile;
import com.smartrider24.Fragments.Confirmed;
import com.smartrider24.Fragments.Pending;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.driverbookpending.DriverBookingPendingResponse;
import com.smartrider24.model.driverconfirmride.DriverBookingDetail;
import com.smartrider24.model.driverconfirmride.DriverConfirmBookingResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static android.view.View.GONE;

public class YourTrips extends AppCompatActivity {

    ImageView backButton;
    LinearLayout closedTrips;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private static final String TAG = YourTrips.class.getSimpleName();
    RecyclerView recyclerView;
    DriverPendingRide driverPendingRide;
    String mobile = "", uniqId = "", lsessionId = "", rideType = "";
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_trips);
        closedTrips = findViewById(R.id.closedTrips);
        relativeLayout=findViewById(R.id.NotripsDriver);

        recyclerView=findViewById(R.id.recyclerDriver);
        backButton = findViewById(R.id.backt);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        closedTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClosedTrips.class);
                startActivity(intent);
            }
        });
        getPendingRide();
    }

    private void getPendingRide() {
        mobile = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_MOBILE_NO);
        lsessionId = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_LSESSION_ID);
        uniqId = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_UNIQUE_ID);
        rideType = "Confirmed";
        Log.d(TAG, "getPendingRide: " + mobile);
        Log.d(TAG, "getPendingRide: " + lsessionId);
        Log.d(TAG, "getPendingRide: " + uniqId);
        Log.d(TAG, "getPendingRide: " + rideType);


        RequestBody cnfdrvbkrqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody cnfdrvbkreqlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody cnfdrvbkrqregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        RequestBody cnfdrvbkrqtypctrlmst = RequestBody.create(MediaType.parse("text/plain"), rideType);

        if (Utils.isInternetConnected(YourTrips.this)) {
            Utils.showProgressDialog(YourTrips.this);
            RestClient.driverConfirm(cnfdrvbkrqlngunqid, cnfdrvbkreqlngsessid, cnfdrvbkrqregunqid, cnfdrvbkrqtypctrlmst, new Callback<DriverConfirmBookingResponse>() {
                @Override
                public void onResponse(Call<DriverConfirmBookingResponse> call, Response<DriverConfirmBookingResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            if (response.body().getResults().getDriverBookingDetails() != null &&
                                    response.body().getResults().getDriverBookingDetails().size() > 0) {
                                driverPendingRide = new DriverPendingRide(response.body().getResults().getDriverBookingDetails());
                                driverPendingRide.setOnItemClickData(new DriverPendingRide.onItemClickData() {
                                    @Override
                                    public void onClickConfirmData(DriverBookingDetail ddd) {

                                        SmartRidePref.putString(getApplicationContext(),"BOOKINGREF",ddd.getBookingRefNo());
                                        SmartRidePref.putString(getApplicationContext(),"BOOKINGTRACKNO",ddd.getBookingTrackNo());
                                        SmartRidePref.putString(getApplicationContext(),"DRIVERREFRENCE",ddd.getDriverRefNo());
                                        SmartRidePref.putString(getApplicationContext(),"DRIVERUNQLGNo",ddd.getDriverUNQLGNo());
                                        SmartRidePref.putString(getApplicationContext(),"PICKING",ddd.getBookingPickUpLocation());
                                        SmartRidePref.putString(getApplicationContext(),"DROP",ddd.getBookingDeliveryLocation());
                                        SmartRidePref.putString(getApplicationContext(),"FARE",ddd.getBookingFareAmt());
                                        SmartRidePref.putString(getApplicationContext(),"DATE",ddd.getBookingDate());
                                        SmartRidePref.putString(getApplicationContext(),"DISTANCE",ddd.getBookingDistance());
                                        SmartRidePref.putString(getApplicationContext(),"TIME", String.valueOf(ddd.getTrackTmdt()));
                                        SmartRidePref.putString(getApplicationContext(),"OPTIONPICKUP",ddd.getBook_OPPICKUP_Loc());
                                        SmartRidePref.putString(getApplicationContext(),"OPTIONDELILOC",ddd.getBook_OPPDEL_Loc());
                                        SmartRidePref.putString(getApplicationContext(),"OPTIONMOBILE",ddd.getBook_OPT_ContactNo());
                                        SmartRidePref.putString(getApplicationContext(),"OPTIONITEMREMARK",ddd.getBook_ITEM_Remark());
                                        SmartRidePref.putString(getApplicationContext(),"VEHICLENAME",ddd.getBooking_Vechile_Name());
                                        Intent intent=new Intent(YourTrips.this,RideStartActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(YourTrips.this);
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
                    Toast.makeText(YourTrips.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(YourTrips.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }
    }
}

