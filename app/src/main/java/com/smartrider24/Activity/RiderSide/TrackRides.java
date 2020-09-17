package com.smartrider24.Activity.RiderSide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrider24.Activity.Profile;
import com.smartrider24.Activity.Tracklistadapter;
import com.smartrider24.Interfaces.trackDataRides;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.customertracking.CustomerTrackingResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class TrackRides extends AppCompatActivity {

    private static final String TAG = TrackRides.class.getSimpleName();
    ArrayList<trackDataRides> dataRides;
    ListView listView;
    ImageView backTD;
    TextView driverMobile;
    String uniqId = "", lsessionId = "", mobileNo = "";
    String bookingReference = "", trackNo = "";
    TextView textViewMobile, textViewDriverName, textViewVehicle;
    Tracklistadapter tracklistadapter;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_rides);
        textViewDriverName = findViewById(R.id.driverName);
        textViewVehicle = findViewById(R.id.driverVehicleNo);
        recyclerView=findViewById(R.id.recyclerview);
        textViewMobile = findViewById(R.id.driverMobile);

        if (getIntent().hasExtra("BOOKREFERENCENO")) {
            bookingReference = getIntent().getStringExtra("BOOKREFERENCENO");
        }
        if (getIntent().hasExtra("TRACKNO")) {
            trackNo = getIntent().getStringExtra("TRACKNO");
        }

        trackingData(bookingReference, trackNo);
      //  listView = findViewById(R.id.listData);
        backTD = findViewById(R.id.backTD);
        driverMobile = findViewById(R.id.driverMobile);
        driverMobile.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9638527410"));
                startActivity(intent);
            }
        });
        backTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void trackingData(String bookingReference, String trackNo) {

        uniqId = SmartRidePref.getString(TrackRides.this, Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(TrackRides.this, Constants.CUSTOMER_LSESSION_ID);
        mobileNo = SmartRidePref.getString(TrackRides.this, Constants.CUSTOMER_MOBILE_NO);


        Log.d(TAG, "ChangePassword Password" + uniqId);
        Log.d(TAG, "ChangePassword Password" + lsessionId);
        Log.d(TAG, "ChangePassword Password" + mobileNo);

        RequestBody bktrkrefno = RequestBody.create(MediaType.parse("text/plain"), bookingReference);
        RequestBody bktrkunqno = RequestBody.create(MediaType.parse("text/plain"), trackNo);
        RequestBody bktrkcstlngid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody bktrkcstregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        RequestBody bktrkcstlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.customerTrack(bktrkrefno, bktrkunqno, bktrkcstlngid, bktrkcstregunqid, bktrkcstlngsessid, new Callback<CustomerTrackingResponse>() {
                @Override
                public void onResponse(Call<CustomerTrackingResponse> call, Response<CustomerTrackingResponse> response) {
                   Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("Ok")) {
                            if (response.body().getResults().getDriverDetails() != null &&
                                    response.body().getResults().getDriverDetails().getTrackBookingHistory().size() > 0) {
                                textViewMobile.setText(""+response.body().getResults().getDriverDetails().getDriverMobileNo());
                                textViewVehicle.setText(""+response.body().getResults().getDriverDetails().getDriverVechNo());
                                textViewDriverName.setText(""+response.body().getResults().getDriverDetails().getDriverName());
                                tracklistadapter=new Tracklistadapter(response.body().getResults().getDriverDetails().getTrackBookingHistory());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(tracklistadapter);

                                Log.d(TAG, "onResponse: " + response.body().getResults().getDriverDetails().getTrackBookingHistory().toString());
                            }
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(TrackRides.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<CustomerTrackingResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(TrackRides.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(TrackRides.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }
    }
}
