package com.smartrider24.Activity.DriverSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrider24.Activity.AmountDetailActivity;
import com.smartrider24.Activity.RiderSide.YourRides;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.accountStatus.AccountResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wallet extends AppCompatActivity {
    private static final String TAG = Wallet.class.getSimpleName();
    TextView backButton;
    TextView textViewMoney, textViewCash, textViewSmartCash;
    String uniqId = "", lsessionId = "", mobileNo = "";
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        textViewMoney = findViewById(R.id.txtMoney);
        textViewCash = findViewById(R.id.txtCash);
        textViewSmartCash = findViewById(R.id.txtSmartCash);
        linearLayout=findViewById(R.id.linearBankStatement);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Wallet.this,AmountDetailActivity.class);
                startActivity(intent);

            }
        });

        getAmountCash();

        backButton = findViewById(R.id.backw);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getAmountCash() {
        uniqId = SmartRidePref.getString(Wallet.this, Constants.CUSTOMER_UNIQUE_ID);
        mobileNo = SmartRidePref.getString(Wallet.this, Constants.CUSTOMER_MOBILE_NO);
        lsessionId = SmartRidePref.getString(Wallet.this, Constants.CUSTOMER_LSESSION_ID);
        Log.d(TAG, "YourRides Mobileno" + mobileNo);
        Log.d(TAG, "YourRides Lsessionid" + lsessionId);
        Log.d(TAG, "YourRides UniqId" + uniqId);

        RequestBody myacreqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody myacreqrgtmunqno = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        RequestBody myaclngtmsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);

        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.accountStatus(myacreqlngunqid, myacreqrgtmunqno, myaclngtmsessid, new Callback<AccountResponse>() {
                @Override
                public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                           if (response.body().getResults()!=null){
                               textViewMoney.setText(""+response.body().getResults().getAccountBalance());
                               textViewCash.setText(""+response.body().getResults().getAccountCashBalance());
                               textViewSmartCash.setText(""+response.body().getResults().getAccountSmartCashBalance());
                           }

                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(Wallet.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AccountResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(Wallet.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(Wallet.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }

    }
}
