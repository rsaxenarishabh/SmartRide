package com.smartrider24.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrider24.Activity.DriverSide.KYC_Details.Address;
import com.smartrider24.Activity.DriverSide.KYC_Details.BankDetails;
import com.smartrider24.Activity.DriverSide.KYC_Details.OwnerShip;
import com.smartrider24.Activity.DriverSide.KYC_Details.PAN_card;
import com.smartrider24.Activity.RiderSide.ChangePassword;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.customerresponse.CusstomerProfileResponse;
import com.smartrider24.model.driverprofileresponse.DriverProfileResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    private static final String TAG = Profile.class.getSimpleName();
    TextView bankAccount, panCard, addressProoff, OWnerShip, edit, cancelEdit, txtmob, driverAadhar, driverRc, driverLicenc, driverinsurance;
    TextView tname, tmobile, temail;
    EditText eName, eMob, etEmail;
    ImageView backButton, BackButtonpR, editProf;
    String sName, mobile, emailPattern;
    LinearLayout profileDriver, profileRider, txt_infos, editInfos, chngePass;
    Button btn_update;
    String loginType = "";
    String uniqId, lsessionId, mobileNo;

    String driverAddressProof = "", driverLicence = "", vehicleRc = "", vehicleInsurance = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        edit = findViewById(R.id.nameEdit);
        backButton = findViewById(R.id.backp);
        BackButtonpR = findViewById(R.id.backpr);
        driverRc = findViewById(R.id.txt_rc);
        driverAadhar = findViewById(R.id.txt_aadhar);
        driverLicenc = findViewById(R.id.txt_licence);
        driverinsurance = findViewById(R.id.txt_insurance);
        profileDriver = findViewById(R.id.profileDriver);
        profileRider = findViewById(R.id.profileRider);
        editProf = findViewById(R.id.editProf);
        cancelEdit = findViewById(R.id.cancelEdit);
        txt_infos = findViewById(R.id.txt_infos);
        editInfos = findViewById(R.id.editInfos);
        btn_update = findViewById(R.id.btn_update);
        chngePass = findViewById(R.id.chngePass);
        txtmob = findViewById(R.id.txt_mobile);

        tname = findViewById(R.id.txtName);
        tmobile = findViewById(R.id.txt_mob);
        temail = findViewById(R.id.txt_email);
        eName = findViewById(R.id.etName);
        eMob = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        loginType = SmartRidePref.getString(Profile.this, Constants.LOGIN_USER_ROLE);
        // mobile=getIntent().getStringExtra("mob");
        if (loginType.equalsIgnoreCase("Driver")) {
            profileDriver.setVisibility(View.VISIBLE);
            profileRider.setVisibility(View.GONE);
            getDriverData();
        } else if (loginType.equalsIgnoreCase("Customer")) {
            profileDriver.setVisibility(View.GONE);
            profileRider.setVisibility(View.VISIBLE);
            profileData();
        }
//
//        editProf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editProf.setVisibility(View.GONE);
////                cancelEdit.setVisibility(View.VISIBLE);
//                editInfos.setVisibility(View.VISIBLE);
//                txt_infos.setVisibility(View.GONE);
//
//            }
//        });
        chngePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent);
                finish();

            }
        });

//        cancelEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editProf.setVisibility(View.VISIBLE);
//                cancelEdit.setVisibility(View.GONE);
//                editInfos.setVisibility(View.GONE);
//                txt_infos.setVisibility(View.VISIBLE);
//
//            }
//        });


//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                PopUp popUp = new PopUp();
//                popUp.showDialog(Profile.this);
//
//            }
//        });

        driverAadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (driverAddressProof != null && driverAddressProof.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), PAN_card.class);
                    intent.putExtra(Constants.DRIVER_AADHAR, driverAddressProof);
                    startActivity(intent);
                } else {
                    Toast.makeText(Profile.this, ""+"No file Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
        driverRc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vehicleRc != null && vehicleRc.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), BankDetails.class);
                    intent.putExtra(Constants.DRIVER_RC, vehicleRc);
                    startActivity(intent);
                } else {
                    Toast.makeText(Profile.this, ""+"No file Available", Toast.LENGTH_SHORT).show();
                }


            }
        });
        driverLicenc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (driverLicence != null && driverLicence.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), Address.class);
                    intent.putExtra(Constants.DRIVER_LICENCE, driverLicence);
                    startActivity(intent);
                } else {
                    Toast.makeText(Profile.this, ""+"No file Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
        driverinsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vehicleInsurance != null && vehicleInsurance.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), OwnerShip.class);
                    intent.putExtra(Constants.DRIVER_INSURANCE, vehicleInsurance);
                    startActivity(intent);
                } else {
                    Toast.makeText(Profile.this, ""+"No file Available", Toast.LENGTH_SHORT).show();
                }

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        BackButtonpR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editValdiation();
            }
        });

    }

    private void getDriverData() {
        uniqId = SmartRidePref.getString(Profile.this, Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(Profile.this, Constants.CUSTOMER_LSESSION_ID);
        mobileNo = SmartRidePref.getString(Profile.this, Constants.CUSTOMER_MOBILE_NO);


        Log.d(TAG, "ChangePassword Password" + uniqId);
        Log.d(TAG, "ChangePassword Password" + lsessionId);
        Log.d(TAG, "ChangePassword Password" + mobileNo);


        RequestBody drvprflngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody drvprfregtmunqno = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        RequestBody drvprflngtmsessunqid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);

        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.driverProfile(drvprflngunqid, drvprfregtmunqno, drvprflngtmsessunqid, new Callback<DriverProfileResponse>() {
                @Override
                public void onResponse(Call<DriverProfileResponse> call, Response<DriverProfileResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            if (response.body().getResults() != null) {

                                driverAddressProof = response.body().getResults().getDriverAddressProof();
                                driverLicence = response.body().getResults().getDriverLicence();
                                vehicleInsurance = response.body().getResults().getVehicleInsurance();
                                vehicleRc = response.body().getResults().getVehicleRC();
                                edit.setText("" + response.body().getResults().getDriverName());
                            }
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(Profile.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DriverProfileResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(Profile.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(Profile.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }

    }


    private void profileData() {
        uniqId = SmartRidePref.getString(Profile.this, Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(Profile.this, Constants.CUSTOMER_LSESSION_ID);
        mobileNo = SmartRidePref.getString(Profile.this, Constants.CUSTOMER_MOBILE_NO);


        Log.d(TAG, "ChangePassword Password" + uniqId);
        Log.d(TAG, "ChangePassword Password" + lsessionId);
        Log.d(TAG, "ChangePassword Password" + mobileNo);


        RequestBody smrtmapucmobilenod = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody smrtmapucustuiddt = RequestBody.create(MediaType.parse("text/plain"), uniqId);
        RequestBody smrtmapucustlngsunqid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);

        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.customerProfile(smrtmapucmobilenod, smrtmapucustuiddt, smrtmapucustlngsunqid, new Callback<CusstomerProfileResponse>() {
                @Override
                public void onResponse(Call<CusstomerProfileResponse> call, Response<CusstomerProfileResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            Log.d(TAG, "onResponse: " + response.body().getResults());
                            tname.setText("" + response.body().getResults().getCustomerNameval());
                            tmobile.setText("" + response.body().getResults().getCustomerMobNo());
                            temail.setText("" + response.body().getResults().getCustomerEmailId());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CusstomerProfileResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(Profile.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(Profile.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }


    }

    private void editValdiation() {

        if (eName.getText().toString().trim().equalsIgnoreCase("")) {
            eName.setError("Enter Name");
            eName.requestFocus();
            return;
        } else if (eMob.getText().toString().trim().equalsIgnoreCase("")) {
            eMob.setError("Enter Mobile No");
            eMob.requestFocus();
            return;
        } else if (eMob.getText().toString().trim().length() <= 9) {
            eMob.setError("Enter valid Mobile No");
            eMob.requestFocus();
            return;
        } else if (etEmail.getText().toString().trim().equalsIgnoreCase("")) {
            etEmail.setError("Enter Email");
            etEmail.requestFocus();
            return;
        } else if (!etEmail.getText().toString().trim().matches(emailPattern)) {
            etEmail.setError("Enter valid Email");
            etEmail.requestFocus();
            return;
        } else if (!isOnline()) {

            Toast.makeText(getApplicationContext(), "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
        } else {
            update_url();
        }
    }

    private void update_url() {

    }

    public class PopUp {
        EditText name, password;
        Button submit;

        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.layout_popup);

            name = dialog.findViewById(R.id.et_nameee);
            name.setText(sName);

            ImageButton icancel = dialog.findViewById(R.id.ib_close);

            final Button submit = dialog.findViewById(R.id.btn_Submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* hitUrlDrawTheMap(mobile.getText().toString(), empDate.getText().toString());
                    progressDialog.show();
                    dialog.dismiss();*/
                    submit.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                    checkValidation();
                }
            });

            icancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    // finish();
                }
            });

            dialog.show();

        }

        private void checkValidation() {

            if (name.getText().toString().trim().equalsIgnoreCase("")) {
                name.setError("Enter your name");
                name.requestFocus();
                return;
            } else if (!isOnline()) {
                Toast.makeText(getApplicationContext(), "Internet Unavailable! Please check your Internet..", Toast.LENGTH_SHORT).show();

            } else {

            }
        }

    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
