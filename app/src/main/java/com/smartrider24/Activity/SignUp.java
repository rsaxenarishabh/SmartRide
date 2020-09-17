package com.smartrider24.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.FileUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrider24.Activity.image.ImageSelectActivity;
import com.smartrider24.Activity.otpview.OnOtpCompletionListener;
import com.smartrider24.Activity.otpview.OtpView;
import com.smartrider24.DriverWelcomeActivity;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.CustomerRegistration;
import com.smartrider24.model.Results;
import com.smartrider24.model.driver.DriverRegistrationResponse;
import com.smartrider24.model.drivermoile.DriverMobileVerifyResponse;
import com.smartrider24.model.driverotp.DriverOtpVerifyResponse;
import com.smartrider24.model.loginotp.MobileotpLoginResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.Utils;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static android.view.View.GONE;

public class SignUp extends AppCompatActivity {

    private static final String TAG = SignUp.class.getSimpleName();
    LinearLayout ll_driveInfo, ll_ownerInfo, ll_riderInfo;
    RadioGroup radioGroup;
    RadioButton radioRider, radioDriver;
    TextView txt_driverHeadinig, btn_login;
    Button btn_SignUp;
    EditText Dname, Dphone, DCurrentAddrsss, Dcity, Dstate, Dpan, Daadhaar, Dlicence, Dexperience,
            Oname, Ophone, Oaddress, Oemail, Ocity, Ostate, Orc, Oaadhaar, Oinsurance, Okyc, referralCode, Password, Rlocation, rGst;
    TextView uploadPAN, uploadLicence, DuploadAdhaar, OuploadAdhaar, uploadRc, uploadInsurance;
    String emailPattern;
    Calendar myCalendar;
    String uploadPanCard = "", uploadLicense = "", DriverAddharCard = "", OwnerAddharCard = "", owneruploadRc = "", ownerUploadInsurance = "";

    String riderFirstname = "", riderPhone = "", riderGstno = "", riderLocation = "", riderCity = "", riderState = "", riderReferal = "", riderPassword = "";

    String driverFullname = "", driverPhoneNumber = "", driverAddharCard = "", driverLicense = "", driverExperience = "", driverAddress = "",
            driverCity = "", driverState = "", driverReferalCode = "", driverPassword = "", ownerFullName = "", ownerPhoneNumber = "", ownerEmailAddress = "",
            ownerPanCard = "", ownerAddharCard = "", ownerRC = "", ownerInsurance = "", ownerKyc = "", ownerResidential = "", ownerCity = "", ownerState = "", referal = "";

    TextView textViewVerifyMobile;
    String mobile;
    boolean otpCheck = false;
    EditText editTextReferal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//
//        mAutocompleteDriver = findViewById(R.id.autocompleteAddressDriver);
//        mAutocompleteOwner = findViewById(R.id.autocompleteAddressEowner);
//        mAutocompleteCustomer = findViewById(R.id.autocompleteAddressCustomer);
        txt_driverHeadinig = findViewById(R.id.txt_driverHeadinig);
        ll_driveInfo = findViewById(R.id.driveInfo);
        ll_ownerInfo = findViewById(R.id.ownersDetail);
        ll_riderInfo = findViewById(R.id.riderInfo);
        radioDriver = findViewById(R.id.radioDriver);
        radioRider = findViewById(R.id.radioRider);
        radioGroup = findViewById(R.id.radiogrop);
        btn_login = findViewById(R.id.btn_Login);
        btn_SignUp = findViewById(R.id.btn_SignUp);
        editTextReferal = findViewById(R.id.eReferal);


        Dname = findViewById(R.id.etFullname);
        Dphone = findViewById(R.id.ePhone);
        // Demail=findViewById(R.id.etEmail);
        // DRaddress=findViewById(R.id.eResidentialAddress);
        DCurrentAddrsss = findViewById(R.id.eCurrentAddress);
        Dcity = findViewById(R.id.eCity);
        Dstate = findViewById(R.id.eState);
        // Dgender=findViewById(R.id.eGender);
        Dpan = findViewById(R.id.ePAN);
        Daadhaar = findViewById(R.id.eAdaahar);
        Dlicence = findViewById(R.id.eDrivingLicnc);
        Dexperience = findViewById(R.id.etDexpericne);

        Rlocation = findViewById(R.id.eRlocation);
        rGst = findViewById(R.id.eRgst);

        Oname = findViewById(R.id.eOwnerName);
        Ophone = findViewById(R.id.eownerPhone);
        Oaddress = findViewById(R.id.eOwnerAddress);
        Oemail = findViewById(R.id.eOwnerEmail);
        Ocity = findViewById(R.id.eOwnerCity);
        Ostate = findViewById(R.id.eOwnerState);
        Orc = findViewById(R.id.eRC);
        Oaadhaar = findViewById(R.id.eOwnerAdhaar);
        Oinsurance = findViewById(R.id.eInsurance);
        Okyc = findViewById(R.id.eKYC);
        referralCode = findViewById(R.id.eReferralCode);
        Password = findViewById(R.id.ePasswrd);
        textViewVerifyMobile = findViewById(R.id.txtVerifyMobile);


        //  Ddob=findViewById(R.id.txtDOB);
        uploadPAN = findViewById(R.id.uploadDriverPAN);
        uploadLicence = findViewById(R.id.uploadLicence);
        uploadInsurance = findViewById(R.id.uploadInsurance);
        DuploadAdhaar = findViewById(R.id.uploadDriverAadhaar);
        OuploadAdhaar = findViewById(R.id.uploadOwnerAadhaar);
        uploadRc = findViewById(R.id.uploadRC);

//
//        uploadPAN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SignUp.this, ImageSelectActivity.class);
//                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
//                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
//                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
//                startActivityForResult(intent, 1);
//            }
//        });
//        mAutocompleteCustomer.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
//            @Override
//            public void onPlaceSelected(final Place place) {
//                mAutocompleteCustomer.getDetailsFor(place, new DetailsCallback() {
//                    @Override
//                    public void onSuccess(final PlaceDetails details) {
//                        Log.d("test", "details " + details);
//                        // mStreet.setText(details.name);
//                        //  DCurrentAddrsss.setText(details.name);
//                        riderLocation = details.name;
//                        Log.d(TAG, "onSuccess: " + details.name);
//                        for (AddressComponent component : details.address_components) {
//                            for (AddressComponentType type : component.types) {
//                                switch (type) {
//                                    case STREET_NUMBER:
//                                        break;
//                                    case ROUTE:
//                                        break;
//                                    case NEIGHBORHOOD:
//                                        break;
//                                    case SUBLOCALITY_LEVEL_1:
//                                        break;
//                                    case SUBLOCALITY:
//                                        break;
//                                    case LOCALITY:
//                                        Dcity.setText(component.long_name);
//                                        riderCity = component.long_name;
//                                        break;
//                                    case ADMINISTRATIVE_AREA_LEVEL_1:
//                                        Dstate.setText(component.short_name);
//                                        riderState = component.short_name;
//                                        break;
//                                    case ADMINISTRATIVE_AREA_LEVEL_2:
//                                        break;
//                                    case COUNTRY:
//                                        break;
//                                    case POSTAL_CODE:
//                                        //  mZip.setText(component.long_name);
//                                        break;
//                                    case POLITICAL:
//                                        break;
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(final Throwable failure) {
//                        Log.d("test", "failure " + failure);
//                    }
//                });
//            }
//        });
//        mAutocompleteDriver.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
//            @Override
//            public void onPlaceSelected(final Place place) {
//                mAutocompleteDriver.getDetailsFor(place, new DetailsCallback() {
//                    @Override
//                    public void onSuccess(final PlaceDetails details) {
//                        Log.d("test", "details " + details);
//                        driverAddress = details.name;
//                        // mStreet.setText(details.name);
//                        for (AddressComponent component : details.address_components) {
//                            for (AddressComponentType type : component.types) {
//                                switch (type) {
//                                    case STREET_NUMBER:
//                                        break;
//                                    case ROUTE:
//                                        break;
//                                    case NEIGHBORHOOD:
//                                        break;
//                                    case SUBLOCALITY_LEVEL_1:
//                                        break;
//                                    case SUBLOCALITY:
//                                        break;
//                                    case LOCALITY:
//                                        Dcity.setText(component.long_name);
//                                        driverCity = component.long_name;
//                                        break;
//                                    case ADMINISTRATIVE_AREA_LEVEL_1:
//                                        Dstate.setText(component.short_name);
//                                        driverState = component.short_name;
//                                        break;
//                                    case ADMINISTRATIVE_AREA_LEVEL_2:
//                                        break;
//                                    case COUNTRY:
//                                        break;
//                                    case POSTAL_CODE:
//                                        //  mZip.setText(component.long_name);
//                                        break;
//                                    case POLITICAL:
//                                        break;
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(final Throwable failure) {
//                        Log.d("test", "failure " + failure);
//                    }
//                });
//            }
//        });
//        mAutocompleteOwner.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
//            @Override
//            public void onPlaceSelected(final Place place) {
//                mAutocompleteDriver.getDetailsFor(place, new DetailsCallback() {
//                    @Override
//                    public void onSuccess(final PlaceDetails details) {
//                        Log.d("test", "details " + details);
//                        ownerResidential = details.name;
//                        // mStreet.setText(details.name);
//                        for (AddressComponent component : details.address_components) {
//                            for (AddressComponentType type : component.types) {
//                                switch (type) {
//                                    case STREET_NUMBER:
//                                        break;
//                                    case ROUTE:
//                                        break;
//                                    case NEIGHBORHOOD:
//                                        break;
//                                    case SUBLOCALITY_LEVEL_1:
//                                        break;
//                                    case SUBLOCALITY:
//                                        break;
//                                    case LOCALITY:
//                                        Ocity.setText(component.long_name);
//                                        break;
//                                    case ADMINISTRATIVE_AREA_LEVEL_1:
//                                        Ostate.setText(component.short_name);
//                                        break;
//                                    case ADMINISTRATIVE_AREA_LEVEL_2:
//                                        break;
//                                    case COUNTRY:
//                                        break;
//                                    case POSTAL_CODE:
//                                        //  mZip.setText(component.long_name);
//                                        break;
//                                    case POLITICAL:
//                                        break;
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(final Throwable failure) {
//                        Log.d("test", "failure " + failure);
//                    }
//                });
//            }
//        });
        uploadLicence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 2);
            }
        });

        uploadInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 3);
            }
        });

        DuploadAdhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 4);
            }
        });

        OuploadAdhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 5);
            }
        });
        uploadRc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 6);
            }
        });

//
//        textViewVerifyMobile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mobileNoCheck();
//            }
//        });
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioRider.isChecked()) {
                    RiderValidation();
                    Dphone.setEnabled(true);


                }
                if (radioDriver.isChecked()) {
                    DriverValidation();
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        radioRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked) {
                    String textR = "Customer";
                    txt_driverHeadinig.setVisibility(View.GONE);
                    ll_driveInfo.setVisibility(View.GONE);
                    ll_ownerInfo.setVisibility(View.GONE);
                    ll_riderInfo.setVisibility(View.VISIBLE);
//                    textViewVerifyMobile.setVisibility(GONE);
                    Dname.setText("");
                    Dphone.setText("");
                    Dcity.setText("");
                    Dstate.setText("");

                } else {
                    // Do your coding
                }
            }
        });
        radioDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked) {
                    String textD = "Driver";
                    ll_riderInfo.setVisibility(View.GONE);
                    ll_driveInfo.setVisibility(View.VISIBLE);
                    ll_ownerInfo.setVisibility(View.VISIBLE);
//                    textViewVerifyMobile.setVisibility(View.VISIBLE);
                    Dname.setText("");
                    Dphone.setText("");
                    Dcity.setText("");
                    Dstate.setText("");

                } else {
                    // Do your coding
                }
            }
        });

        Dphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // Toast.makeText(SignUp.this, "Done"+i1 +"  "+i2+" "+i, Toast.LENGTH_SHORT).show();
                if (radioDriver.isChecked()) {
                    if (i == 9) {
                        if (Utils.isValid(Dphone.getText().toString().trim())) {
                            mobileNoCheck();
                        } else if (i == 8) {

                        } else {
                            ErrorAlertDialog("Please Enter Valid No");
                            Toast.makeText(SignUp.this, "Enter Valid No", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void mobileNoCheck() {
        if (Dphone.getText().toString().trim().equalsIgnoreCase("")) {
            Dphone.setError("Enter Phone No.");

            Dphone.requestFocus();
            return;
        } else if (Dphone.getText().toString().trim().length() <= 9) {
            Dphone.setError("Enter valid Phone No.");
            Dphone.requestFocus();
            return;
        }
        mobile = Dphone.getText().toString().trim();
        RequestBody chkanymobnounqctrl = RequestBody.create(MediaType.parse("text/plain"), mobile);
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.driverMobile(chkanymobnounqctrl, new Callback<DriverMobileVerifyResponse>() {
                @Override
                public void onResponse(Call<DriverMobileVerifyResponse> call, Response<DriverMobileVerifyResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            Log.d(TAG, "onResponse: " + response.body().getResults().getReqSessId());
                            Toast.makeText(SignUp.this, "Successfully Send Otp", Toast.LENGTH_SHORT).show();
                            alertDialogForDriver(response.body().getResults().getReqMobileNo(), response.body().getResults().getReqSessId(), response.body().getResults().getReqMobileNo());
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(SignUp.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<DriverMobileVerifyResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(SignUp.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(SignUp.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }
    }

    private void alertDialogForDriver(final String reqMobileNo, final String reqSessId, String mobile) {
        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and titl
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.otp_layout_view, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        //  OtpView otpView=findViewById(R.id.otp_view);
        final EditText edtOtp = dialogView.findViewById(R.id.edtOtp);
        TextView textView = dialogView.findViewById(R.id.txtPhone);
        // ImageView imageView = dialogView.findViewById(R.id.imgCancel);
        TextView textViewVerify = dialogView.findViewById(R.id.validate_button);
        dialog.setCancelable(false);
        textView.setText("+91-" + "" + mobile);
        textViewVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = edtOtp.getText().toString().trim();
                Log.d(TAG, "alertDialog: OTP " + otp);
                RequestBody chkunqmobnorgreq = RequestBody.create(MediaType.parse("text/plain"), reqMobileNo);
                RequestBody chkunqoptvlrgreq = RequestBody.create(MediaType.parse("text/plain"), otp);
                RequestBody chkunqreqsessid = RequestBody.create(MediaType.parse("text/plain"), reqSessId);
                if (Utils.isInternetConnected(SignUp.this)) {
                    Utils.showProgressDialog(SignUp.this);
                    RestClient.driverMobileOtpVerify(chkunqmobnorgreq, chkunqoptvlrgreq, chkunqreqsessid, new Callback<DriverOtpVerifyResponse>() {
                        @Override
                        public void onResponse(Call<DriverOtpVerifyResponse> call, Response<DriverOtpVerifyResponse> response) {
                            Utils.dismissProgressDialog();
                            if (response.body() != null) {
                                if (response.body().getStatus().equalsIgnoreCase("OK")) {
                                    Log.d(TAG, "onResponse: " + response.body().getResults().getMsg());
                                    Dphone.setEnabled(false);
                                    otpCheck = true;
                                    dialog.cancel();

                                } else {
                                    dialog.cancel();
                                    Toast.makeText(SignUp.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<DriverOtpVerifyResponse> call, Throwable t) {
                            Utils.dismissProgressDialog();
                            dialog.cancel();
                            Log.d(TAG, "onFailure: Something Went Wrong!!");
                            Toast.makeText(SignUp.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Utils.dismissProgressDialog();
                    dialog.cancel();
                    Toast.makeText(SignUp.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Internet Connection Failed!!");
                }
            }
        });

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });

        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            uploadPanCard = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            String filename = uploadPanCard.substring(uploadPanCard.lastIndexOf("/") + 1);
            Dpan.setText("" + filename);
            Log.d(TAG, "onActivityResult: Pan Card " + uploadPanCard);

        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            uploadLicense = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            String filename1 = uploadLicense.substring(uploadLicense.lastIndexOf("/") + 1);
            uploadLicence.setText("" + filename1);
            Log.d(TAG, "onActivityResult: License " + uploadLicense);


        } else if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
            ownerUploadInsurance = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Log.d(TAG, "onActivityResult: Insurance " + ownerUploadInsurance);
            String filename2 = ownerUploadInsurance.substring(ownerUploadInsurance.lastIndexOf("/") + 1);
            uploadInsurance.setText("" + filename2);
        } else if (requestCode == 4 && resultCode == Activity.RESULT_OK) {
            DriverAddharCard = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            String filename3 = DriverAddharCard.substring(DriverAddharCard.lastIndexOf("/") + 1);
            DuploadAdhaar.setText("" + filename3);
            Log.d(TAG, "onActivityResult: Addhar Card " + DriverAddharCard);
        } else if (requestCode == 5 && resultCode == Activity.RESULT_OK) {
            OwnerAddharCard = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            String filename4 = OwnerAddharCard.substring(OwnerAddharCard.lastIndexOf("/") + 1);
            Log.d(TAG, "onActivityResult: Owner Addhar Card " + OwnerAddharCard);
            OuploadAdhaar.setText("" + filename4);

        } else if (requestCode == 6 && resultCode == Activity.RESULT_OK) {
            owneruploadRc = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Log.d(TAG, "onActivityResult: Upload RC " + owneruploadRc);
            String filename5 = owneruploadRc.substring(owneruploadRc.lastIndexOf("/") + 1);
            uploadRc.setText("" + filename5);

        }
    }
/*
        private void updateLabel1() {
            String myFormat = "dd - MM - yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            Ddob.setText(sdf.format(myCalendar.getTime()));
        }*/

    private void DriverValidation() {
        if (Dname.getText().toString().trim().equalsIgnoreCase("")) {
            // Dname.setError("Enter Full Name");
            ErrorAlertDialog("Please Enter Full Name");
            Dname.requestFocus();
            return;
        } else if (Dphone.getText().toString().trim().equalsIgnoreCase("")) {
            // Dphone.setError("Enter Phone No.");
            ErrorAlertDialog("Please Enter Phone No");
            Dphone.requestFocus();
            return;
        } else if (Dphone.getText().toString().trim().length() <= 9) {
            // Dphone.setError("Enter valid Phone No.");
            ErrorAlertDialog("Please Enter Valid Phone No");
            Dphone.requestFocus();
            return;
        } else if (Daadhaar.getText().toString().trim().equalsIgnoreCase("")) {
            // Daadhaar.setError("Enter Aadhaar Details");
            ErrorAlertDialog("Please Enter Aadhaar No");
            Daadhaar.requestFocus();
            return;
        } else if (Dlicence.getText().toString().trim().equalsIgnoreCase("")) {
            //Dlicence.setError("Enter Driving Licence");
            ErrorAlertDialog("Please Enter Licence No");
            Dlicence.requestFocus();
            return;
        } else if (Dexperience.getText().toString().trim().equalsIgnoreCase("")) {
            // Dexperience.setError("Enter Driving Experience");
            ErrorAlertDialog("Please Enter Driving Experience");
            Dexperience.requestFocus();
            return;
        } else if (DCurrentAddrsss.getText().toString().trim().equalsIgnoreCase("")) {
            // DCurrentAddrsss.setError("Enter Address");
            ErrorAlertDialog("Please Enter Driver Current Address");
            DCurrentAddrsss.requestFocus();
            return;
        } else if (Dcity.getText().toString().trim().equalsIgnoreCase("")) {
            // Dcity.setError("Enter City");
            ErrorAlertDialog("Please Enter Driver City");
            Dcity.requestFocus();
            return;
        } else if (Dstate.getText().toString().trim().equalsIgnoreCase("")) {
            //  Dstate.setError("Enter State");
            ErrorAlertDialog("Please Enter Driver State");
            Dstate.requestFocus();
            return;
        } else if (Oname.getText().toString().trim().equalsIgnoreCase("")) {
            // Oname.setError("Enter Owner's Name");
            ErrorAlertDialog("Please Enter Owner Name");
            Oname.requestFocus();
            return;
        } else if (Ophone.getText().toString().trim().equalsIgnoreCase("")) {
            //  Ophone.setError("Enter Owner's Phone No");
            ErrorAlertDialog("Please Enter Owner Phone No");
            Ophone.requestFocus();
            return;
        } else if (!Utils.isValid(Ophone.getText().toString().trim())) {
            ErrorAlertDialog("Please Enter Valid Owner Phone No");
            Ophone.requestFocus();
            return;
        } else if (Ophone.getText().toString().trim().length() <= 9) {
            // Ophone.setError("Enter valid Owner's Phone No");
            ErrorAlertDialog("Please Enter Valid Owner Phone No");
            Ophone.requestFocus();
            return;
        } else if (Oaadhaar.getText().toString().trim().equalsIgnoreCase("")) {
            // Oaadhaar.setError("Enter Owner's Aadhaar");
            ErrorAlertDialog("Please Enter Owner Aadhar No");
            Oaadhaar.requestFocus();
            return;
        } else if (Orc.getText().toString().trim().equalsIgnoreCase("")) {
            //  Orc.setError("Enter RC");
            ErrorAlertDialog("Please Enter Owner RC No");
            Orc.requestFocus();
            return;
        } else if (Oinsurance.getText().toString().trim().equalsIgnoreCase("")) {
            // Oinsurance.setError("Enter Insurance");
            ErrorAlertDialog("Please Enter Owner Insurance No");
            Oinsurance.requestFocus();
            return;
        }

//        } else if (Okyc.getText().toString().trim().equalsIgnoreCase("")) {
//            Okyc.setError("Enter KYC");
//            Okyc.requestFocus();
//            return;
//        }
        else if (Oaddress.getText().toString().trim().equalsIgnoreCase("")) {
            // Oaddress.setError("Enter Owner's Address");
            ErrorAlertDialog("Please Enter Owner Address ");
            Oaddress.requestFocus();
            return;
        } else if (Ocity.getText().toString().trim().equalsIgnoreCase("")) {
            // Ocity.setError("Enter Owner's City");
            ErrorAlertDialog("Please Enter Owner City");
            Ocity.requestFocus();
            return;
        } else if (Ostate.getText().toString().trim().equalsIgnoreCase("")) {
            // Ostate.setError("Enter Owner's State");
            ErrorAlertDialog("Please Enter Owner State");
            Ostate.requestFocus();
            return;
        } else if (Password.getText().toString().trim().equalsIgnoreCase("")) {
            // Password.setError("Enter Password");
            ErrorAlertDialog("Please Enter Password");
            Password.requestFocus();
            return;
        } else if (Dpan.getText().toString().trim().equalsIgnoreCase("")) {
            // Dpan.setError("Enter Password");
            ErrorAlertDialog("Please Enter Driver PAN No");
            Dpan.requestFocus();
            return;
        } else if (uploadLicense.equalsIgnoreCase("") || uploadLicense.length() == 0) {
            ErrorAlertDialog("Please Upload Licence Photo");
            // Toast.makeText(this, "Please Select Licence ", Toast.LENGTH_SHORT).show();
            return;
        } else if (ownerUploadInsurance.equalsIgnoreCase("") || ownerUploadInsurance.length() == 0) {
            // Toast.makeText(this, "Please Select Insurance ", Toast.LENGTH_SHORT).show();
            ErrorAlertDialog("Please Upload Insurance Photo");
            return;


        } else if (DriverAddharCard.equalsIgnoreCase("") || DriverAddharCard.length() == 0) {
            //Toast.makeText(this, "Please Select Driver Aaddhar Card ", Toast.LENGTH_SHORT).show();
            ErrorAlertDialog("Please Upload Licence Photo");
            return;


        } else if (OwnerAddharCard.equalsIgnoreCase("") || OwnerAddharCard.length() == 0) {
            ErrorAlertDialog("Please Upload Owner Aadhar Photo");
            // Toast.makeText(this, "Please Select Owner Aaddhar Card ", Toast.LENGTH_SHORT).show();
            return;

        } else if (owneruploadRc.equalsIgnoreCase("") || owneruploadRc.length() == 0) {
            ErrorAlertDialog("Please Upload Owner RC Photo");
            // Toast.makeText(this, "Please Select  RC", Toast.LENGTH_SHORT).show();
            return;


        } else if (!otpCheck) {
            ErrorAlertDialog("Please Verify Driver Mobile No");
            // Toast.makeText(this, "Please Verify Driver Mobile No", Toast.LENGTH_SHORT).show();
            return;
        }

        driverFullname = Dname.getText().toString().trim();
        driverPhoneNumber = Dphone.getText().toString().trim();
        driverAddharCard = Daadhaar.getText().toString().trim();
        driverLicense = Dlicence.getText().toString().trim();
        driverExperience = Dexperience.getText().toString().trim();

        driverAddress = DCurrentAddrsss.getText().toString().trim();
        driverCity = Dcity.getText().toString().trim();
        driverState = Dstate.getText().toString().trim();
        ownerFullName = Oname.getText().toString().trim();
        ownerPhoneNumber = Ophone.getText().toString().trim();
        ownerEmailAddress = Oemail.getText().toString().trim();
        ownerPanCard = Oemail.getText().toString().trim();
        ownerAddharCard = Oaadhaar.getText().toString().trim();
        ownerRC = Orc.getText().toString().trim();
        ownerInsurance = Oinsurance.getText().toString().trim();
        ownerKyc = Okyc.getText().toString().trim();
        ownerCity = Ocity.getText().toString().trim();
        ownerState = Ostate.getText().toString().trim();
        driverPassword = Password.getText().toString().trim();
        ownerPanCard = Dpan.getText().toString().trim();
        ownerResidential = Oaddress.getText().toString().trim();
        referal = referralCode.getText().toString().trim();
        if (referal.equalsIgnoreCase("")) {
            referal = "NA";
        }
        Log.d(TAG, "DriverValidation: FullName " + driverFullname);
        Log.d(TAG, "DriverValidation: Mobile " + driverPhoneNumber);
        Log.d(TAG, "DriverValidation: AddharCard " + driverAddharCard);
        Log.d(TAG, "DriverValidation: Driver License " + driverLicense);
        Log.d(TAG, "DriverValidation: Driver Experience " + driverExperience);
        Log.d(TAG, "DriverValidation: Driver Address " + driverAddress);
        Log.d(TAG, "DriverValidation: Driver City " + driverCity);
        Log.d(TAG, "DriverValidation: Driver State" + driverState);
        Log.d(TAG, "DriverValidation: Owner Full Name " + ownerFullName);
        Log.d(TAG, "DriverValidation: Owner Phone No" + ownerPhoneNumber);
        Log.d(TAG, "DriverValidation: Owner Email No" + ownerEmailAddress);
        Log.d(TAG, "DriverValidation: Owner Addhar Card " + ownerAddharCard);
        Log.d(TAG, "DriverValidation: Owner RC" + ownerRC);

        Log.d(TAG, "DriverValidation: Owner Insurance" + ownerInsurance);
        Log.d(TAG, "DriverValidation: Owner KYC " + ownerKyc);
        Log.d(TAG, "DriverValidation: Owner Residential " + ownerResidential);
        Log.d(TAG, "DriverValidation: Owner City" + ownerCity);
        Log.d(TAG, "DriverValidation: Owner State" + ownerState);
        Log.d(TAG, "DriverValidation: Owner Password" + driverPassword);
        Log.d(TAG, "DriverValidation: Owner Password OTP CHECK" + otpCheck);


        Log.d(TAG, "DriverValidation: Referal" + referal);


        RequestBody drvrgname = RequestBody.create(MediaType.parse("text/plain"), driverFullname);
        RequestBody drvrgmobileno = RequestBody.create(MediaType.parse("text/plain"), driverPhoneNumber);
        RequestBody drvrgaadharno = RequestBody.create(MediaType.parse("text/plain"), driverAddharCard);

        RequestBody drvrgdrvglcence = RequestBody.create(MediaType.parse("text/plain"), driverLicense);
        RequestBody drvrgdrvgexp = RequestBody.create(MediaType.parse("text/plain"), driverExperience);
        RequestBody drvrgaddress = RequestBody.create(MediaType.parse("text/plain"), driverAddress);
        RequestBody drvrgcitynm = RequestBody.create(MediaType.parse("text/plain"), driverCity);
        RequestBody drvrgstatenm = RequestBody.create(MediaType.parse("text/plain"), driverState);
        RequestBody drvrgpasswrd = RequestBody.create(MediaType.parse("text/plain"), driverPassword);
        RequestBody ownrgfullname = RequestBody.create(MediaType.parse("text/plain"), ownerFullName);
        RequestBody ownrgmobno = RequestBody.create(MediaType.parse("text/plain"), ownerPhoneNumber);

        RequestBody ownrgemilid = RequestBody.create(MediaType.parse("text/plain"), ownerEmailAddress);
        RequestBody ownrgpancrdno = RequestBody.create(MediaType.parse("text/plain"), ownerPanCard);
        RequestBody ownrgaadharno = RequestBody.create(MediaType.parse("text/plain"), ownerAddharCard);
        RequestBody ownrgvechrcno = RequestBody.create(MediaType.parse("text/plain"), ownerRC);
        RequestBody ownrgvechinsno = RequestBody.create(MediaType.parse("text/plain"), ownerInsurance);
        RequestBody ownrgregaddress = RequestBody.create(MediaType.parse("text/plain"), ownerResidential);
        RequestBody ownrgcitynm = RequestBody.create(MediaType.parse("text/plain"), ownerCity);
        RequestBody ownrgstatenm = RequestBody.create(MediaType.parse("text/plain"), ownerState);
        RequestBody ownrgothrefnodt = RequestBody.create(MediaType.parse("text/plain"), referal);

        Log.d(TAG, "DriverValidation: Owner Password" + uploadLicense);
        Log.d(TAG, "DriverValidation: Owner Password" + ownerUploadInsurance);
        Log.d(TAG, "DriverValidation: Owner Password" + DriverAddharCard);
        Log.d(TAG, "DriverValidation: Owner Password" + OwnerAddharCard);
        Log.d(TAG, "DriverValidation: Owner Password" + owneruploadRc);


        File file = new File(uploadLicense);
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part drvrgupldrivilicecpy = MultipartBody.Part.createFormData("drvrgupldrivilicecpy", file.getName(), requestBody1);

        File file1 = new File(ownerUploadInsurance);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part ownrgvhcinsrncpy = MultipartBody.Part.createFormData("ownrgvhcinsrncpy", file1.getName(), requestBody2);

        File file2 = new File(DriverAddharCard);
        RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        MultipartBody.Part drvrgupldaadharcpy = MultipartBody.Part.createFormData("drvrgupldaadharcpy", file2.getName(), requestBody3);


        File file3 = new File(OwnerAddharCard);
        RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), file3);
        MultipartBody.Part ownregadhrcrdcpy = MultipartBody.Part.createFormData("ownregadhrcrdcpy", file3.getName(), requestBody4);


        File file4 = new File(owneruploadRc);
        RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), file4);
        MultipartBody.Part ownrgvhrccpy = MultipartBody.Part.createFormData("ownrgvhrccpy", file4.getName(), requestBody5);


        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(SignUp.this);
            RestClient.driverRegistration(drvrgname, drvrgmobileno, drvrgaadharno,
                    drvrgdrvglcence, drvrgdrvgexp, drvrgaddress, drvrgcitynm, drvrgstatenm, drvrgpasswrd, ownrgfullname, ownrgmobno
                    , ownrgemilid, ownrgpancrdno, ownrgaadharno, ownrgvechrcno, ownrgvechinsno, ownrgregaddress, ownrgcitynm, ownrgstatenm,
                    drvrgupldaadharcpy, drvrgupldrivilicecpy, ownregadhrcrdcpy, ownrgvhrccpy, ownrgvhcinsrncpy, ownrgothrefnodt
                    , new Callback<DriverRegistrationResponse>() {
                        @Override
                        public void onResponse(Call<DriverRegistrationResponse> call, Response<DriverRegistrationResponse> response) {
                            Utils.dismissProgressDialog();
                            if (response.body() != null) {
                                if (response.body().getStatus().equalsIgnoreCase("OK")) {
                                    Log.d(TAG, "onResponse: " + response.body().getResults().toString());
                                    Intent intent = new Intent(SignUp.this, DriverWelcomeActivity.class);
                                    intent.putExtra(Constants.DRIVER_REG_NO, response.body().getResults().getRegNo());
                                    intent.putExtra(Constants.DRIVER_MESSAGE, response.body().getResults().getMsg());
                                    startActivity(intent);
                                    finish();
                                } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                                    Toast.makeText(SignUp.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<DriverRegistrationResponse> call, Throwable t) {
                            Utils.dismissProgressDialog();
                            Toast.makeText(SignUp.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Something went wrong!!");
                        }
                    });


        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(SignUp.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }


    }

    private void RiderValidation() {

        if (Dname.getText().toString().trim().equalsIgnoreCase("")) {
            //Dname.setError("Enter Full Name");
            ErrorAlertDialog("Please Enter Full Name");
            Dname.requestFocus();
            return;
        } else if (Dphone.getText().toString().trim().equalsIgnoreCase("")) {
            //  Dphone.setError("Enter Phone No.");
            ErrorAlertDialog("Please Enter Phone No");
            Dphone.requestFocus();
            return;
        } else if (Dphone.getText().toString().trim().length() <= 9) {
            //  Dphone.setError("Enter valid Phone No.");
            ErrorAlertDialog("Please Enter Valid Phone No");
            Dphone.requestFocus();
            return;
        }
//        else if (rGst.getText().toString().trim().equalsIgnoreCase("")) {
//            // rGst.setError("Enter GST No.");
//            ErrorAlertDialog("Please Enter GST No");
//            rGst.requestFocus();
//            return;
//        }
        else if (Rlocation.getText().toString().trim().equalsIgnoreCase("")) {
            // Rlocation.setError("Enter Location");
            ErrorAlertDialog("Please Enter Location");
            //  Rlocation.requestFocus();
            return;
        } else if (Dcity.getText().toString().trim().equalsIgnoreCase("")) {
            // Dcity.setError("Enter City");
            ErrorAlertDialog("Please Enter City Name");
            Dcity.requestFocus();
            return;
        } else if (Dstate.getText().toString().trim().equalsIgnoreCase("")) {
            //  Dstate.setError("Enter State");
            ErrorAlertDialog("Please Enter State Name");
            Dstate.requestFocus();
            return;
        } else if (Password.getText().toString().trim().equalsIgnoreCase("")) {
            // Password.setError("Enter Password");
            ErrorAlertDialog("Please Enter Password");
            Password.requestFocus();
            return;
        } else if (Password.getText().toString().trim().length() < 8) {
            //  Password.setError("Password length should be greater than 8 digit");
            ErrorAlertDialog("Password length should be greater than 8 digit");
            Password.requestFocus();
            return;
        } else if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
        } else if (!Utils.isValid(Dphone.getText().toString().trim())) {
            ErrorAlertDialog("Please Enter Valid Phone no");
            return;
        } else {
            riderFirstname = Dname.getText().toString().trim();
            riderPhone = Dphone.getText().toString().trim();
            riderPassword = Password.getText().toString().trim();
            riderState = Dstate.getText().toString().trim();
            riderCity = Dcity.getText().toString().trim();
            riderGstno = rGst.getText().toString().trim();
            riderReferal = referralCode.getText().toString().trim();
            riderLocation = Rlocation.getText().toString().trim();
            if (rGst.getText().toString().trim().equalsIgnoreCase("")) {
                riderGstno = "NA";
            }
            if (referralCode.getText().toString().trim().equalsIgnoreCase("")) {
                riderReferal = "NA";
            }


            Log.d("SignUp", "Rider Name" + riderFirstname);
            Log.d("SignUp", "Rider Phone" + riderPhone);
            Log.d("SignUp", "Rider Password" + riderPassword);
            Log.d("SignUp", "Rider State" + riderState);
            Log.d("SignUp", "Rider City" + riderCity);
            Log.d("SignUp", "Rider GstNo" + riderGstno);
            Log.d("SignUp", "Rider Location" + riderLocation);
            Log.d("SignUp", "Rider Refer" + riderReferal);


            RequestBody smrtmaruacpername = RequestBody.create(MediaType.parse("text/plain"), riderFirstname);
            RequestBody smrtmaruacmobilenod = RequestBody.create(MediaType.parse("text/plain"), riderPhone);
            RequestBody smrtmaruacgstnod = RequestBody.create(MediaType.parse("text/plain"), riderGstno);
            RequestBody smrtmaruaclocnmd = RequestBody.create(MediaType.parse("text/plain"), riderLocation);
            RequestBody smrtmaruacstatenmd = RequestBody.create(MediaType.parse("text/plain"), riderState);
            RequestBody smrtmaruacreferralcded = RequestBody.create(MediaType.parse("text/plain"), riderReferal);
            RequestBody smrtmaruacpasswordd = RequestBody.create(MediaType.parse("text/plain"), riderPassword);
            RequestBody smrtmaruaccitynmd = RequestBody.create(MediaType.parse("text/plain"), riderCity);

            if (Utils.isInternetConnected(this)) {
                Utils.showProgressDialog(this);
                RestClient.customerRegistration(smrtmaruacpername, smrtmaruacmobilenod, smrtmaruacgstnod, smrtmaruaclocnmd, smrtmaruaccitynmd,
                        smrtmaruacstatenmd, smrtmaruacreferralcded, smrtmaruacpasswordd, new Callback<CustomerRegistration>() {
                            @Override
                            public void onResponse(Call<CustomerRegistration> call, Response<CustomerRegistration> response) {
                                Utils.dismissProgressDialog();
                                if (response.body() != null) {
                                    if (response.body().getStatus().equalsIgnoreCase("OK")) {
                                        alertDialog(response.body().getResults());
                                        Log.d(TAG, "onResponse: " + response.body().getResults());
                                        Toast.makeText(SignUp.this, "Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    if (response.body().getStatus().equalsIgnoreCase("Error")) {
                                        Toast.makeText(SignUp.this, "Mobile no is already register!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<CustomerRegistration> call, Throwable t) {
                                Utils.dismissProgressDialog();
                                Toast.makeText(SignUp.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Something went wrong!!");
                            }
                        });
            } else {
                Utils.dismissProgressDialog();
                Toast.makeText(SignUp.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Internet Connection Failed!!");
            }

        }
    }

    private void alertDialog(final Results results) {
        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and titl
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.otp_layout_view, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        final EditText edtOtp = dialogView.findViewById(R.id.edtOtp);

        TextView textView = dialogView.findViewById(R.id.txtPhone);
        textView.setText("91-" + results.getCustomerMobileNo());
        TextView textViewVerify = dialogView.findViewById(R.id.validate_button);
        dialog.setCancelable(false);
        textViewVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = edtOtp.getText().toString().trim();
                Log.d(TAG, "alertDialog: OTP " + otp);
                RequestBody smrtmaapvurmobno = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerMobileNo());
                RequestBody smrtmaapvuroptvc = RequestBody.create(MediaType.parse("text/plain"), otp);
                RequestBody smrtmaruacgstnod = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerGSTNO());
                RequestBody smrtmaruaclocnmd = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerAreaName());
                RequestBody smrtmaruaccitynmd = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerCityName());
                RequestBody smrtmaruacstatenmd = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerStateName());
                RequestBody smrtmaruacreferralcded = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerRefralCode());
                RequestBody smrtmauacpasswordd = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerPassword());
                RequestBody smrtmauacuanamed = RequestBody.create(MediaType.parse("text/plain"), results.getCustomerUName());
                if (Utils.isInternetConnected(getApplicationContext())) {
                    Utils.showProgressDialog(getApplicationContext());
                    RestClient.customerRegistrationUsingOtp(smrtmaapvurmobno, smrtmaapvuroptvc, smrtmaruacgstnod
                            , smrtmaruaclocnmd, smrtmaruaccitynmd, smrtmaruacstatenmd, smrtmaruacreferralcded, smrtmauacpasswordd, smrtmauacuanamed, new Callback<CustomerRegistration>() {
                                @Override
                                public void onResponse(Call<CustomerRegistration> call, Response<CustomerRegistration> response) {
                                    Utils.dismissProgressDialog();
                                    if (response.body() != null) {
                                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                                            Log.d(TAG, "onResponse: " + response.body().getResults());
                                            Toast.makeText(SignUp.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignUp.this, DriverWelcomeActivity.class);
                                            intent.putExtra(Constants.DRIVER_REG_NO, response.body().getResults().getMsg());
                                            intent.putExtra(Constants.DRIVER_MESSAGE, response.body().getResults().getMsg());
                                            startActivity(intent);
                                            finish();
                                            dialog.cancel();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<CustomerRegistration> call, Throwable t) {
                                    Utils.dismissProgressDialog();
                                    Log.d(TAG, "onFailure: Something Went Wrong!!");
                                    Toast.makeText(SignUp.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                }
                            });

                } else {
                    Utils.dismissProgressDialog();
                    Toast.makeText(SignUp.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Internet Connection Failed!!");

                }


            }
        });


        dialog.show();


    }

    private void hitDSignUp() {
        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
        intent.putExtra("mob", "1234567890");
        startActivity(intent);
        finish();
    }

    private void hitRSignUp() {
        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
        intent.putExtra("mob", "9876543210");
        startActivity(intent);
        finish();
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str = "";
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioDriver:
                if (checked)
                    str = "Driver";

                break;
            case R.id.radioRider:
                if (checked)

                    break;

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


}
