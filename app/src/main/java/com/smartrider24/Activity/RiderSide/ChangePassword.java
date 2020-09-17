package com.smartrider24.Activity.RiderSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.smartrider24.Activity.DashBoard;
import com.smartrider24.Activity.Login;
import com.smartrider24.Activity.SignUp;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.passwordupdate.PasswordUpdateResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class ChangePassword extends AppCompatActivity {
    private static final String TAG = Login.class.getSimpleName();
    ImageView backCp;
    EditText newPasswrd,confrmpaswrd;
    Button btn_Save;
    String newPassword,confirmPassword,uniqId,lsessionId,mobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        init();
    }

    private void init() {
        backCp= findViewById(R.id.backCp);
        newPasswrd= findViewById(R.id.newPass);
        confrmpaswrd= findViewById(R.id.confrmPass);
        btn_Save=findViewById(R.id.btn_Save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validation();
            }
        });
        backCp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void validation() {

        if(newPasswrd.getText().toString().trim().equalsIgnoreCase("")){
            newPasswrd.setError("Enter New Password");
            newPasswrd.requestFocus();
            return;
        }
        else if(confrmpaswrd.getText().toString().trim().equalsIgnoreCase("")){
            confrmpaswrd.setError("Re-enter Password");
            confrmpaswrd.requestFocus();
            return;
        }
        else if(!confrmpaswrd.getText().toString().trim().matches(newPasswrd.getText().toString().trim())){
            confrmpaswrd.setError("Password Mismatch");
            confrmpaswrd.requestFocus();
            return;
        } else if (confrmpaswrd.getText().toString().trim().length() < 8) {
            confrmpaswrd.setError("Password length should be greater than 8 digit");
            confrmpaswrd.requestFocus();
            return;
        }
        else if(!isOnline()){
            Toast.makeText(getApplicationContext(),"Please check your Internet Connection!",Toast.LENGTH_SHORT).show();
            return;
        }
        uniqId= SmartRidePref.getString(ChangePassword.this, Constants.CUSTOMER_UNIQUE_ID);
        lsessionId=SmartRidePref.getString(ChangePassword.this,Constants.CUSTOMER_LSESSION_ID);
        mobileNo=SmartRidePref.getString(ChangePassword.this,Constants.CUSTOMER_MOBILE_NO);

        newPassword=newPasswrd.getText().toString().trim();
        confirmPassword=confrmpaswrd.getText().toString().trim();

        Log.d(TAG, "ChangePassword Mobile" + newPassword);
        Log.d(TAG, "ChangePassword Password" + confirmPassword);
        Log.d(TAG, "ChangePassword Password" + uniqId);
        Log.d(TAG, "ChangePassword Password" + lsessionId);
        Log.d(TAG, "ChangePassword Password" + mobileNo);

        RequestBody smrtmaunpsconfmnctrlval = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody smrtmaunpsconfnewpsval = RequestBody.create(MediaType.parse("text/plain"), confirmPassword);
        RequestBody smrtmaunpsreqsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody smrtmaucustmstunqid = RequestBody.create(MediaType.parse("text/plain"), uniqId);

        if (Utils.isInternetConnected(this)){
            Utils.showProgressDialog(this);
            RestClient.updatePassword(smrtmaunpsconfmnctrlval, smrtmaunpsconfnewpsval, smrtmaunpsreqsessid, smrtmaucustmstunqid, new Callback<PasswordUpdateResponse>() {
                @Override
                public void onResponse(Call<PasswordUpdateResponse> call, Response<PasswordUpdateResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body()!=null){
                        if (response.body().getStatus().equalsIgnoreCase("OK")){
                            Log.d(TAG, "onResponse: "+response.body().getResults());
                            Toast.makeText(ChangePassword.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(ChangePassword.this, DashBoard.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PasswordUpdateResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(ChangePassword.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        }else {
            Utils.dismissProgressDialog();
            Toast.makeText(ChangePassword.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }



    }



    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
