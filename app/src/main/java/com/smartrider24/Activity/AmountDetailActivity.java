package com.smartrider24.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.accountdetailstatement.AccountDetailStatementResponse;
import com.smartrider24.model.accountdetailstatement.AccountDetails;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmountDetailActivity extends AppCompatActivity {
    private static final String TAG = AmountDetailActivity.class.getSimpleName();
    private TextView txtTotalBalance;
    private TextView txtCash1;
    private TextView txtSmartCash1;
    private RecyclerView recyclerAmount;
    private RelativeLayout relativeLayout;
    String uniqId, lsessionId, mobileNo;
    AcountDetailAdapter acountDetailAdapter;
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_detail);
        relativeLayout = findViewById(R.id.noTransaction);
        txtTotalBalance = (TextView) findViewById(R.id.txtTotalBalance);
        txtCash1 = (TextView) findViewById(R.id.txtCash1);
        txtSmartCash1 = (TextView) findViewById(R.id.txtSmartCash1);
        recyclerAmount = (RecyclerView) findViewById(R.id.recyclerAmount);
        imageViewBack=findViewById(R.id.txtaccount);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        getAccountDetails();
    }

    private void getAccountDetails() {
        uniqId = SmartRidePref.getString(AmountDetailActivity.this, Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(AmountDetailActivity.this, Constants.CUSTOMER_LSESSION_ID);
        mobileNo = SmartRidePref.getString(AmountDetailActivity.this, Constants.CUSTOMER_MOBILE_NO);


        Log.d(TAG, "ChangePassword Password" + uniqId);
        Log.d(TAG, "ChangePassword Password" + lsessionId);
        Log.d(TAG, "ChangePassword Password" + mobileNo);


        RequestBody myacstmtreqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody myacstmtreqrgtmunqno = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        RequestBody myacstmtlngtmsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.accountDetail(myacstmtreqlngunqid, myacstmtreqrgtmunqno, myacstmtlngtmsessid, new Callback<AccountDetailStatementResponse>() {
                @Override
                public void onResponse(Call<AccountDetailStatementResponse> call, Response<AccountDetailStatementResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body().getStatus().equalsIgnoreCase("OK")) {
                        if (response.body().getResults().getAccountDetails() != null) {
                            accountDetailData(response.body().getResults().getAccountDetails());
                        }
                        if (response.body().getResults().getAccountStatement() != null
                                && response.body().getResults().getAccountStatement().size() > 0) {
                            acountDetailAdapter = new AcountDetailAdapter(response.body().getResults().getAccountStatement());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerAmount.setLayoutManager(mLayoutManager);
                            recyclerAmount.setAdapter(acountDetailAdapter);
                        } else {
                            relativeLayout.setVisibility(View.VISIBLE);
                            recyclerAmount.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(AmountDetailActivity.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AccountDetailStatementResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(AmountDetailActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });

        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(AmountDetailActivity.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }


    }

    private void accountDetailData(AccountDetails accountDetails) {
        if (accountDetails.getAccountBalance()!=null){
            txtTotalBalance.setText("\u20B9 "+accountDetails.getAccountBalance());
        }
        if (accountDetails.getAccountCashBalance()!=null){
            txtCash1.setText("\u20B9 "+accountDetails.getAccountCashBalance());
        }
        if (accountDetails.getAccountSmartCashBalance()!=null){
            txtSmartCash1.setText("\u20B9 "+accountDetails.getAccountSmartCashBalance());
        }

    }
}