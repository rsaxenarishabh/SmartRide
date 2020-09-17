package com.smartrider24.Activity.RiderSide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smartrider24.Activity.Login;
import com.smartrider24.Interfaces.Datum;
import com.smartrider24.ListAdapter;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.bookinghistory.BookingHistorySuccessResponse;
import com.smartrider24.model.bookinghistory.MyBookingHistory;
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

public class YourRides extends AppCompatActivity {
    private static final String TAG = YourRides.class.getSimpleName();

    RecyclerView recyclerView;
    ImageView backButton;
    ListAdapter listAdapterR;
    List<Datum>datumListR=new ArrayList<>();
    String uniqId,lsessionId,mobileNo;
    RelativeLayout relativeLayout;
    List<MyBookingHistory> bookingHistories=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_rides);

        recyclerView=findViewById(R.id.recyclerRides);
        backButton= findViewById(R.id.backR);
        relativeLayout=findViewById(R.id.Notrips);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ridesUrl();
    }

    private void ridesUrl() {

        uniqId= SmartRidePref.getString(YourRides.this, Constants.CUSTOMER_UNIQUE_ID);
        lsessionId=SmartRidePref.getString(YourRides.this,Constants.CUSTOMER_LSESSION_ID);
        mobileNo=SmartRidePref.getString(YourRides.this,Constants.CUSTOMER_MOBILE_NO);

        Log.d(TAG, "YourRides Mobileno" + mobileNo);
        Log.d(TAG, "YourRides Lsessionid" + lsessionId);
        Log.d(TAG, "YourRides UniqId" + uniqId);



        RequestBody mybkhstreqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody mybkhstreqlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody mybkhstregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);

        if (Utils.isInternetConnected(this)){
            Utils.showProgressDialog(this);
            RestClient.bookingHistory(mybkhstreqlngunqid, mybkhstreqlngsessid, mybkhstregunqid, new Callback<BookingHistorySuccessResponse>() {
                @Override
                public void onResponse(Call<BookingHistorySuccessResponse> call, Response<BookingHistorySuccessResponse> response) {
                   Utils.dismissProgressDialog();
                    if (response.body()!=null){
                        if (response.body().getStatus().equalsIgnoreCase("OK")){
                            if (response.body().getResults()!=null  && response.body().getResults().getMyBookingHistory().size()>0){
                                listAdapterR = new ListAdapter(response.body().getResults().getMyBookingHistory());
                                listAdapterR.setOnItemClick(new ListAdapter.onItemClick() {
                                    @Override
                                    public void onTrackItems(String bookrefernceNo, String trackNo) {
                                        Intent intent=new Intent(YourRides.this,TrackRides.class);
                                        intent.putExtra("BOOKREFERENCENO",bookrefernceNo);
                                        intent.putExtra("TRACKNO",trackNo);
                                        Log.d(TAG, "onTrackItems: "+bookrefernceNo);
                                        Log.d(TAG, "onTrackItems: "+trackNo);
                                        startActivity(intent);
                                    }

                                });
                                listAdapterR.setOnCallItemclick(new ListAdapter.onCallItemclick() {
                                    @Override
                                    public void onCallItems(String callNo) {
                                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                                        callIntent.setData(Uri.parse("tel:"+callNo));//change the number
                                        startActivity(callIntent);
                                    }
                                });
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(listAdapterR);
                                relativeLayout.setVisibility(View.GONE);
                            }else {
                                recyclerView.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("Error")){
                            Toast.makeText(YourRides.this, ""+response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<BookingHistorySuccessResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(YourRides.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        }else {
            Utils.dismissProgressDialog();
            Toast.makeText(YourRides.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }





    }


}
