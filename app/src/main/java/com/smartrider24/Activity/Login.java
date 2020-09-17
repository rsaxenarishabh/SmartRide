package com.smartrider24.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.login.LoginResponse;
import com.smartrider24.model.loginotp.MobileotpLoginResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class Login extends AppCompatActivity {
    private static final String TAG = Login.class.getSimpleName();
    EditText et_mobile, et_pswrd;
    Button btn_next;
    TextView btn_register, btnGEtOTP;
    String mobileno, mobilePassword, loginType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_mobile = findViewById(R.id.et_mobile);
        et_pswrd = findViewById(R.id.et_pswrd);
        btn_next = findViewById(R.id.btn_next);
        btn_register = findViewById(R.id.btn_register);
        btnGEtOTP = findViewById(R.id.btn_getOTP);
        locationPermission();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_mobile.getText().toString().equalsIgnoreCase("")) {
                   // et_mobile.setError("Enter Mobile No.");
                    ErrorAlertDialog("Enter Mobile No");
                    et_mobile.requestFocus();
                    return;

                } else if (et_mobile.getText().toString().length() <= 9) {
                    //et_mobile.setError("Enter valid Mobile No.");
                    ErrorAlertDialog("Enter Password/OTP");
                    et_mobile.requestFocus();
                    return;

                } else if (et_pswrd.getText().toString().equalsIgnoreCase("")) {
                   // et_pswrd.setError("Enter Password / OTP");
                    ErrorAlertDialog("Enter Password/OTP");
                    et_pswrd.requestFocus();
                    return;
                }else if (!Utils.isValid(et_mobile.getText().toString().trim())){
                    ErrorAlertDialog("Enter Valid Mobile No");
                    et_mobile.requestFocus();
                    return;
                }

                mobileno = et_mobile.getText().toString().trim();
                mobilePassword = et_pswrd.getText().toString().trim();
                if (mobilePassword.length() == 6) {
                    loginType = "REQOTP";
                } else {
                    loginType = "REQPASS";
                }

                Log.d(TAG, "Login Mobile" + mobileno);
                Log.d(TAG, "Login Password" + mobilePassword);
                Log.d(TAG, "Login Login Type" + loginType);
                RequestBody smrtmaplogin = RequestBody.create(MediaType.parse("text/plain"), mobileno);
                RequestBody smrtmappass = RequestBody.create(MediaType.parse("text/plain"), mobilePassword);
                RequestBody smrtmlngrqtyp = RequestBody.create(MediaType.parse("text/plain"), loginType);

                if (Utils.isInternetConnected(Login.this)) {
                    Utils.showProgressDialog(Login.this);
                    RestClient.loginData(smrtmaplogin, smrtmappass, smrtmlngrqtyp, new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            Utils.dismissProgressDialog();
                            if (response.body() != null) {
                                if (response.body().getStatus().equalsIgnoreCase("OK")) {
                                    Log.d(TAG, "Login onResponse: " + response.body().getResults());
                                    SmartRidePref.putString(Login.this, Constants.CUSTOMER_UNIQUE_ID, response.body().getResults().getCustomerUnqid());
                                    //SmartRidePref.putString(Login.this, Constants.CUSTOMER_UNIQUE_ID, "SRCUST206");
                                    SmartRidePref.putString(Login.this, Constants.CUSTOMER_LSESSION_ID, response.body().getResults().getCustomerLSessId());
                                    //SmartRidePref.putString(Login.this, Constants.CUSTOMER_LSESSION_ID, "5ed889439ab91");
                                    SmartRidePref.putString(Login.this, Constants.CUSTOMER_MOBILE_NO, response.body().getResults().getCustomerMobNo());
                                    //SmartRidePref.putString(Login.this, Constants.CUSTOMER_MOBILE_NO, "9311311012");
                                    SmartRidePref.putString(Login.this, Constants.LOGIN_USER_ROLE, response.body().getResults().getLoginUserRole());
                                    //SmartRidePref.putString(Login.this, Constants.LOGIN_USER_ROLE, "Driver");
                                    SmartRidePref.putString(Login.this, Constants.CUSTOMER_STATUS, response.body().getResults().getCustomerStatus());
                                    SmartRidePref.putString(Login.this, Constants.CUSTOMER_NAME, response.body().getResults().getCustomerUName());
                                    SmartRidePref.putBoolean(Login.this, Constants.LOGIN_STATUS, true);
                                    Intent intent = new Intent(Login.this, DashBoard.class);
                                    startActivity(intent);
                                    finish();
                                } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                                    Toast.makeText(Login.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Utils.dismissProgressDialog();
                            Toast.makeText(Login.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Something went wrong!!");
                        }
                    });

                } else {
                    Utils.dismissProgressDialog();
                    Toast.makeText(Login.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Internet Connection Failed!!");
                }
//                } else if (et_mobile.getText().toString().equalsIgnoreCase("9876543210")) {
//                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
//                    intent.putExtra("mob", "9876543210");
//                    startActivity(intent);
//                    finish();
//
//                } else if (et_mobile.getText().toString().equalsIgnoreCase("1234567890")) {
//                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
//                    intent.putExtra("mob", "1234567890");
//                    startActivity(intent);
//                    finish();
//                }


            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        btnGEtOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpURL();

            }
        });
    }

    private void locationPermission() {
        Dexter.withActivity(this).
                withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA).
                withListener(new MultiplePermissionsListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {


                            // do you work now
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown
                            (List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();


    }

    private void otpURL() {
        if (et_mobile.getText().toString().equalsIgnoreCase("")) {
            et_mobile.setError("Enter Mobile No.");
            et_mobile.requestFocus();
            return;

        } else if (et_mobile.getText().toString().length() <= 9) {
            et_mobile.setError("Enter valid Mobile No.");
            et_mobile.requestFocus();
            return;

        }
        mobileno = et_mobile.getText().toString().trim();
        RequestBody smrtmauchpsreqmnoval = RequestBody.create(MediaType.parse("text/plain"), mobileno);
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.mobileotpcall(smrtmauchpsreqmnoval, new Callback<MobileotpLoginResponse>() {
                @Override
                public void onResponse(Call<MobileotpLoginResponse> call, Response<MobileotpLoginResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            Log.d(TAG, "onResponse: " + response.body().getResults());
                            Toast.makeText(Login.this, "Successfully Send Otp", Toast.LENGTH_SHORT).show();
                            btnGEtOTP.setVisibility(GONE);
                            timerInOtp();
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(Login.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }

                @Override
                public void onFailure(Call<MobileotpLoginResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(Login.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(Login.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }
    }

    public void ErrorAlertDialog(String Error) {
        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and titl
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView texterror = dialogView.findViewById(R.id.txtError);
        ImageView txtOk = dialogView.findViewById(R.id.imagecancel);
        dialog.setCancelable(false);
        texterror.setText("" + Error);
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();


    }


    public void timerInOtp() {
        new CountDownTimer(60000, 3000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                btnGEtOTP.setText("Resend Otp");
                btnGEtOTP.setVisibility(View.VISIBLE);
            }
        }.start();

    }


}

