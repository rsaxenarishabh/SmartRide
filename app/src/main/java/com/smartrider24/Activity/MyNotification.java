package com.smartrider24.Activity;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.smartrider24.Activity.DriverSide.YourTrips;
import com.smartrider24.Interfaces.Coupons;
import com.smartrider24.NotificationAdapter;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.notification.NotificationResponse;
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

public class MyNotification extends AppCompatActivity {
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final String TAG = MyNotification.class.getSimpleName();
    private TextView txtRegId, txtMessage;

    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    RelativeLayout relativeLayout;
    String loginType = "", mobileNo = "", uniqId = "", login = "";
    ImageView imageViewNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);
//        getSupportActionBar().setTitle("My Notifications");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        relativeLayout = findViewById(R.id.NoNotification);
        recyclerView = findViewById(R.id.recycler_notification);
        imageViewNotification=findViewById(R.id.backNotification);


        loginType = SmartRidePref.getString(getApplicationContext(), Constants.LOGIN_USER_ROLE);
        mobileNo = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_MOBILE_NO);
        uniqId = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_UNIQUE_ID);
        getNotification();
        imageViewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getNotification() {
        if (loginType.equalsIgnoreCase("Customer")) {
            login = "Customer";
        }
        if (loginType.equalsIgnoreCase("Driver")) {
            login = "Driver";
        }
        RequestBody usrnotfyreqtyp = RequestBody.create(MediaType.parse("text/plain"), login);
        RequestBody usrnotfyreqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody usrnotfyreqrqregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.notification(usrnotfyreqtyp, usrnotfyreqlngunqid, usrnotfyreqrqregunqid, new Callback<NotificationResponse>() {
                @Override
                public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            if (response.body().getResults().getNotificationDetails() != null && response.body().getResults().getNotificationDetails().size() > 0) {
                                Toast.makeText(MyNotification.this, "Success", Toast.LENGTH_SHORT).show();
                                notificationAdapter=new NotificationAdapter(response.body().getResults().getNotificationDetails());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MyNotification.this);
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(notificationAdapter);
                                relativeLayout.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            } else {
                                relativeLayout.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(MyNotification.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<NotificationResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(MyNotification.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(MyNotification.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}