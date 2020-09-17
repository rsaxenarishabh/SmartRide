package com.smartrider24.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.Dash;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.navigation.NavigationView;
import com.smartrider24.Activity.DriverSide.ReferEarn;
import com.smartrider24.Activity.DriverSide.TrackJourney;
import com.smartrider24.Activity.DriverSide.Wallet;
import com.smartrider24.Activity.DriverSide.YourTrips;
import com.smartrider24.Activity.RiderSide.FindLoads;
import com.smartrider24.Activity.RiderSide.YourRides;
import com.smartrider24.CommonHintArrayAdapter;
import com.smartrider24.DriverAcceptRideAdapter;
import com.smartrider24.Interfaces.Constant;
import com.smartrider24.Interfaces.Datum;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.RiderConfirmationActivity;
import com.smartrider24.model.bookingFare.BookingDetailFareResponse;
import com.smartrider24.model.bookingdone.BookingSuccessResponse;
import com.smartrider24.model.driveraccept.DriverAcceptBookingResponse;
import com.smartrider24.model.driverbookpending.DriverBookingPendingResponse;
import com.smartrider24.model.vehicle.VehicleList;
import com.smartrider24.model.vehicle.VehicleListResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class DashBoard extends AppCompatActivity {
    private static final String TAG = DashBoard.class.getSimpleName();
    int AUTOCOMPLETE_REQUEST_CODE_SOURCE = 1;
    int AUTOCOMPLETE_REQUEST_CODE_DEST = 2;
    private AppBarConfiguration mAppBarConfiguration;

    List<Datum> datumList = new ArrayList<>();
    ListAdapter listAdapter;
    RecyclerView recyclerView;
    ImageView sliderDriver, sliderRider, rNotic, dNotice;
    DrawerLayout drawerLayout;
    LinearLayout profileDataDriver, profileDataRider, wallet, trips, trackJourney, referEarn, Logout, walletR, Rides, bookNow, referEarnR, LogoutR;
    LinearLayout findLoads;
    String mobile = "";
    LinearLayout rider, driver, drawerDriver, drawerRider, ll_kmRs;
    Button btn_bookNow, btn_ConfirmBooking;
    TextView txtPickUp, txtdropOff, txtCar;
    String logintype = "";
    String pickingLocation = "", dropLocation = "";
    String uniqid = "", lsessionId = "", mobileNo = "";
    String refrence = "";
    TextView txtkilometer, txtFare;
    String service_booking_reference = "", Service_Vehicle_RefNo = "",
            Service_Vehicle_Name = "", Service_Total_Distance = "", Service_Fare = "",
            Service_Fare_Details = "", Service_Booking_RefNo = "";
    String refrenceno = "", trackno = "", fareamt = "";

    String vehicleList;
    String vehicleuniqId, vehiclelsessionid;
    Spinner spinnerVehicle;

    TextView txtProfilename, txtProfileMobile;
    TextView txtDriverProfileName, txtDriverProfileMobile;
    String customer_name, customer_phone;
    DriverAcceptRideAdapter driverAcceptRideAdapter;
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayoutNoTruck;
    EditText edtOptionPickup, edtOptionDrop, edtOptionMobile, edtRemarkItem;
    String optionPickUp = "", optionalDropUp = "", optionMobileno = "", optionItemRemark = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
       // Places.initialize(DashBoard.this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
      //  Places.initialize(DashBoard.this, "AIzaSyAO-nb7naIOYK0sRKib4nBsPYOHS-N6bxM");
        Places.initialize(DashBoard.this,"AIzaSyAO-nb7naIOYK0sRKib4nBsPYOHS-N6bxM");
        //   Places.initialize(getApplicationContext(), "AIzaSyAthMuML_fIysPX6wwrSyZ0EFZTTD2t258");
        //  Places.initialize(DashBoard.this, "AIzaSyDsfsZtDZ7buJkonvJdLtDbo2eeIaAY81o");


        if (!Places.isInitialized()) {
           // Places.initialize(DashBoard.this, "AIzaSyDRMI4wJHUfwtsX3zoNqVaTReXyHtIAT6U");
            Places.initialize(DashBoard.this,"AIzaSyAO-nb7naIOYK0sRKib4nBsPYOHS-N6bxM");
          //  Places.initialize(DashBoard.this, "AIzaSyAO-nb7naIOYK0sRKib4nBsPYOHS-N6bxM");
            //  Places.initialize(DashBoard.this, "AIzaSyDsfsZtDZ7buJkonvJdLtDbo2eeIaAY81o");
            //   Places.initialize(getApplicationContext(), "AIzaSyAthMuML_fIysPX6wwrSyZ0EFZTTD2t258");
        }
        edtOptionPickup = findViewById(R.id.edtOptionalPickupLocation);
        edtOptionDrop = findViewById(R.id.edtOptionalDropLocation);
        edtOptionMobile = findViewById(R.id.edtOptionalMobile);
        edtRemarkItem = findViewById(R.id.edtRemarkItem);


        relativeLayout = findViewById(R.id.NotripsDashboard);
        txtProfilename = findViewById(R.id.tRname);
        txtProfileMobile = findViewById(R.id.tRmob);
        relativeLayoutNoTruck = findViewById(R.id.NotripsDashboard);


        txtDriverProfileName = findViewById(R.id.tname);
        txtDriverProfileMobile = findViewById(R.id.tmob);
        spinnerVehicle = findViewById(R.id.spinnerVehicle);
        txtkilometer = findViewById(R.id.txtKilometer);
        txtFare = findViewById(R.id.txtFare);
        ll_kmRs = findViewById(R.id.ll_kmRs);
        btn_bookNow = findViewById(R.id.btn_bookNow);
        recyclerView = findViewById(R.id.recyclerView1);
        findLoads = findViewById(R.id.findLoads);
        wallet = findViewById(R.id.Wallet);
        profileDataDriver = findViewById(R.id.profileData);
        profileDataRider = findViewById(R.id.profileDataRider);
        trips = findViewById(R.id.trip);
        trackJourney = findViewById(R.id.trackJourney);
        referEarn = findViewById(R.id.referEarn);
        Logout = findViewById(R.id.logout);

        walletR = findViewById(R.id.Walletr);
        Rides = findViewById(R.id.tripr);
        bookNow = findViewById(R.id.BookNow);
        btn_ConfirmBooking = findViewById(R.id.btn_ConfirmBooking);
        referEarnR = findViewById(R.id.referEarnr);
        LogoutR = findViewById(R.id.logoutr);

        drawerLayout = findViewById(R.id.drawer_layout);
        sliderDriver = findViewById(R.id.slider);
        sliderRider = findViewById(R.id.slider1);
        drawerDriver = findViewById(R.id.drawerDriver);
        drawerRider = findViewById(R.id.riderDrawer);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        dyikdt();
        rider = findViewById(R.id.rider);
        rNotic = findViewById(R.id.rNotic);
        driver = findViewById(R.id.driver);
        dNotice = findViewById(R.id.dNotice);
        txtCar = findViewById(R.id.txtselectCar);
        txtdropOff = findViewById(R.id.txtDropoff);
        txtPickUp = findViewById(R.id.txtPickUp);
        if (getIntent().hasExtra("PICKUPLOCATION")) {
            pickingLocation = getIntent().getStringExtra("PICKUPLOCATION");
            txtPickUp.setText("" + pickingLocation);
            Log.d(TAG, "onCreate: " + pickingLocation);
        }
        if (getIntent().hasExtra("DROPLOCATION")) {
            dropLocation = getIntent().getStringExtra("DROPLOCATION");
            txtdropOff.setText("" + dropLocation);
        }
        logintype = SmartRidePref.getString(DashBoard.this, Constants.LOGIN_USER_ROLE);

        customer_name = SmartRidePref.getString(DashBoard.this, Constants.CUSTOMER_NAME);
        customer_phone = SmartRidePref.getString(DashBoard.this, Constants.CUSTOMER_MOBILE_NO);

        // mobile=getIntent().getStringExtra("mob");


        if (logintype.equalsIgnoreCase("Driver")) {
            rider.setVisibility(View.GONE);
            driver.setVisibility(View.VISIBLE);
            drawerDriver.setVisibility(View.VISIBLE);
            drawerRider.setVisibility(View.GONE);
            txtDriverProfileMobile.setText("" + customer_phone);
            txtDriverProfileName.setText("" + customer_name);
            getPendingRide();

        } else if (logintype.equalsIgnoreCase("Customer")) {
            rider.setVisibility(View.VISIBLE);
            driver.setVisibility(View.GONE);
            drawerRider.setVisibility(View.VISIBLE);
            drawerDriver.setVisibility(View.GONE);
            txtProfileMobile.setText("" + customer_phone);
            txtProfilename.setText("" + customer_name);
            getVehicleList();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();


        btn_bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validation();

            }
        });
        btn_ConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hitUrl();

            }
        });
        rNotic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyNotification.class);
                startActivity(intent);
            }
        });

        dNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MyNotification.class);
                startActivity(intent);
            }
        });

        findLoads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FindLoads.class);
                startActivity(intent);
            }
        });

        profileDataDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("mob", "1234567890");
                startActivity(intent);
            }
        });

        profileDataRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("mob", "9876543210");
                startActivity(intent);
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(intent);
                finish();
            }
        });
        trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YourTrips.class);
                startActivity(intent);
            }
        });
        referEarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ReferEarn.class);
                startActivity(intent);
            }
        });
        trackJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Wallet.class);
                startActivity(intent);
            }
        });

        txtPickUp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
        txtdropOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                checkPermissionDropLocation();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this); //Home is name of the activity
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intnt = new Intent(getApplicationContext(), Login.class);
                        SmartRidePref.putBoolean(getApplicationContext(), Constants.LOGIN_STATUS, false);
                        intnt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intnt);
                        finish();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        walletR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Wallet.class);
                startActivity(intent);
            }
        });
        Rides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), YourRides.class);
                startActivity(intent);
            }
        });
        referEarnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ReferEarn.class);
                startActivity(intent);
            }
        });
        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mobile=getIntent().getStringExtra("mob");
                if (logintype.equalsIgnoreCase("Driver")) {
                    rider.setVisibility(View.GONE);
                    driver.setVisibility(View.VISIBLE);
                    drawerDriver.setVisibility(View.VISIBLE);
                    drawerRider.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                    intent.putExtra("mob", "1234567890");
                    finish();
                    startActivity(intent);
                } else if (logintype.equalsIgnoreCase("Customer")) {
                    rider.setVisibility(View.VISIBLE);
                    driver.setVisibility(View.GONE);
                    drawerRider.setVisibility(View.VISIBLE);
                    drawerDriver.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                    intent.putExtra("mob", "9876543210");
                    finish();
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        });

        LogoutR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this); //Home is name of the activity
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intnt = new Intent(getApplicationContext(), Login.class);
                        SmartRidePref.putBoolean(getApplicationContext(), Constants.LOGIN_STATUS, false);
                        intnt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intnt);
                        finish();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        sliderDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });

        sliderRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });

    }

    private void getVehicleList() {

        vehicleList = "OPRVEHLIST";
        vehicleuniqId = SmartRidePref.getString(DashBoard.this, Constants.CUSTOMER_UNIQUE_ID);
        vehiclelsessionid = SmartRidePref.getString(DashBoard.this, Constants.CUSTOMER_LSESSION_ID);

        Log.d(TAG, "getVehicleList: vehicleList " + vehicleList);
        Log.d(TAG, "getVehicleList: UiqueId " + vehicleuniqId);
        Log.d(TAG, "getVehicleList: LsessionId " + vehiclelsessionid);


        RequestBody opvechlstmstreq = RequestBody.create(MediaType.parse("text/plain"), vehicleList);
        RequestBody oprvchrequsrmstunqid = RequestBody.create(MediaType.parse("text/plain"), vehicleuniqId);
        RequestBody oprvchrequsrlngsessid = RequestBody.create(MediaType.parse("text/plain"), vehiclelsessionid);


        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.vehicleList(opvechlstmstreq, oprvchrequsrmstunqid, oprvchrequsrlngsessid, new Callback<VehicleListResponse>() {
                @Override
                public void onResponse(Call<VehicleListResponse> call, final Response<VehicleListResponse> response) {
                    Log.d(TAG, "onResponse: First");
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Second");
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            Log.d(TAG, "onResponse: Third");
                            Log.d(TAG, "onResponse: " + response.body().getResults().getVehicleList());
                            adapterData(response.body().getResults().getVehicleList());
                            spinnerVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                    if (position != 0) {
                                        refrence = response.body().getResults().getVehicleList().get(position).getReqVehicleUid();
                                        if (refrence != null && refrence.length() > 0) {
                                            if (pickingLocation != null && pickingLocation.length() > 0) {
                                                if (dropLocation != null && dropLocation.length() > 0) {
                                                    //  validation();
                                                    btn_bookNow.setVisibility(View.VISIBLE);
                                                    ll_kmRs.setVisibility(View.GONE);
                                                    btn_ConfirmBooking.setVisibility(View.GONE);
                                                }
                                            }
                                        }

                                    }
                                    // Toast.makeText(DashBoard.this, "Selected item" + position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(DashBoard.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<VehicleListResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Log.d(TAG, "onResponse: Failure");
                    Toast.makeText(DashBoard.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Log.d(TAG, "onResponse: Five");
            Toast.makeText(DashBoard.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }
    }

    //Add response into string
    private void adapterData(List<VehicleList> data) {
        data.add(0, new VehicleList("Select Your Vehicle"));
        setAdapter(spinnerVehicle, data);
//        for (int i = 0; i < data.size(); i++) {
//            if (data.get(i).getId() != null && data.get(i).getId() == destinationId) {
//                spinnerVehicle.setSelection(i);
//                break;
//            }
//        }
    }

    public void setAdapter(Spinner spinner, List objectArrayList) {
        spinner.setDropDownVerticalOffset(spinnerVehicle.getHeight());
        spinner.setDropDownWidth(spinnerVehicle.getWidth());

        CommonHintArrayAdapter hintAdapter = new CommonHintArrayAdapter(
                this,
                R.layout.common_spinner_adapter,
                objectArrayList);
        spinner.setAdapter(hintAdapter);
    }


    private void selectPickUp() {

        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields).setTypeFilter(TypeFilter.ESTABLISHMENT)
                .setCountry("IN")
                .build(DashBoard.this);
//      List<Place.Field> placefiels= Collections.singletonList(Place.Field.NAME);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SOURCE);


    }

    private void selectDrop() {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields).setTypeFilter(TypeFilter.ESTABLISHMENT)
                .setCountry("IN")
                .build(DashBoard.this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_DEST);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void CheckPermission() {

        if (checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            // openSettings();
            showSettingsDialog();
        } else {
            selectPickUp();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissionDropLocation() {

        if (checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            // openSettings();
            showSettingsDialog();
        } else {
            selectDrop();
        }

    }


    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == AUTOCOMPLETE_REQUEST_CODE_SOURCE) {

                //  Place place=   Autocomplete.getStatusFromIntent(data);
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());

                if (!TextUtils.isEmpty(place.getName())) {
                    if (TextUtils.isEmpty(place.getAddress())) {
                        txtPickUp.setText("" + place.getName());
                        pickingLocation = place.getName();
                    } else {
                        pickingLocation = place.getName() + "" + place.getAddress();
                        txtPickUp.setText(place.getName() + "" + place.getAddress());
                    }
                }
                if (refrence != null && refrence.length() > 0) {
                    if (pickingLocation != null && pickingLocation.length() > 0) {
                        if (dropLocation != null && dropLocation.length() > 0) {
                            //validation();
                            btn_bookNow.setVisibility(View.VISIBLE);
                            ll_kmRs.setVisibility(View.GONE);
                            btn_ConfirmBooking.setVisibility(View.GONE);
                        }
                    }
                }
            } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_DEST) {
                Place placeDest = Autocomplete.getPlaceFromIntent(data);
                if (!TextUtils.isEmpty(placeDest.getName())) {

                    if (TextUtils.isEmpty(placeDest.getAddress())) {
                        dropLocation = placeDest.getName();
                        txtdropOff.setText("" + placeDest.getName());
                    } else {
                        dropLocation = placeDest.getName() + "" + placeDest.getAddress();
                        txtdropOff.setText(placeDest.getName() + "" + placeDest.getAddress());
                    }
                    if (refrence != null && refrence.length() > 0) {
                        if (pickingLocation != null && pickingLocation.length() > 0) {
                            if (dropLocation != null && dropLocation.length() > 0) {
                                //  validation();
                                btn_bookNow.setVisibility(View.VISIBLE);
                                ll_kmRs.setVisibility(View.GONE);
                                btn_ConfirmBooking.setVisibility(View.GONE);
                            }
                        }
                    }


                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("TAG", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: " + resultCode);
            }

        }

    }

    private void validation() {

        uniqid = SmartRidePref.getString(DashBoard.this, Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(DashBoard.this, Constants.CUSTOMER_LSESSION_ID);
        mobileNo = SmartRidePref.getString(DashBoard.this, Constants.CUSTOMER_MOBILE_NO);
        optionPickUp = edtOptionPickup.getText().toString().trim();
        optionalDropUp = edtOptionDrop.getText().toString().trim();
        optionMobileno = edtOptionMobile.getText().toString().trim();
        optionItemRemark = edtRemarkItem.getText().toString().trim();

        if (refrence.equalsIgnoreCase("") || refrence.length() == 0) {
            Toast.makeText(this, "Please Select Vehicle", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pickingLocation.equalsIgnoreCase("") || pickingLocation.length() == 0) {
            Toast.makeText(this, "Please Select Picking Location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dropLocation.equalsIgnoreCase("") && dropLocation.length() == 0) {
            Toast.makeText(this, "Please Select Drop Location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtOptionMobile.getText().toString().length() > 0 && edtOptionMobile.getText().toString().trim() != null) {
            if (!Utils.isValid(edtOptionMobile.getText().toString().trim())) {
                Toast.makeText(this, "Please Enter Valid Optional No", Toast.LENGTH_SHORT).show();
                return;
            }
        }
//
//        if (edtOptionMobile.getText().toString().trim().length() <= 9) {
//            Toast.makeText(this, "Please Enter Optional valid no", Toast.LENGTH_SHORT).show();
//        }

        if (optionMobileno.equalsIgnoreCase("")) {
            optionMobileno = "NA";
        }

        if (optionPickUp.equalsIgnoreCase("")) {
            optionPickUp = "NA";
        }

        if (optionalDropUp.equalsIgnoreCase("")) {
            optionalDropUp = "NA";
        }
        if (optionItemRemark.equalsIgnoreCase("")) {
            optionItemRemark = "NA";
        }


        RequestBody serbkreqvehrefno = RequestBody.create(MediaType.parse("text/plain"), refrence);
        RequestBody serbkreqpikuplocnm = RequestBody.create(MediaType.parse("text/plain"), pickingLocation);
        RequestBody serbkreqdellocnm = RequestBody.create(MediaType.parse("text/plain"), dropLocation);
        RequestBody serbkrequregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqid);
        RequestBody serbkrequlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody serbkrequlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);

        RequestBody serbkreqoptpikuploc = RequestBody.create(MediaType.parse("text/plain"), optionPickUp);
        RequestBody serbkreqoptdelloc = RequestBody.create(MediaType.parse("text/plain"), optionalDropUp);
        RequestBody serbkreqcntoptmobno = RequestBody.create(MediaType.parse("text/plain"), optionMobileno);
        RequestBody serbkreqitemremarkdt = RequestBody.create(MediaType.parse("text/plain"), optionItemRemark);


        Log.d(TAG, "Book Detail Reference " + refrence);
        Log.d(TAG, "Book Detail Picking " + pickingLocation);
        Log.d(TAG, "Book Detail Drop " + dropLocation);
        Log.d(TAG, "Book Detail Unique " + uniqid);
        Log.d(TAG, "Book Detail Mobile " + mobileNo);
        Log.d(TAG, "Book Detail lsession " + lsessionId);
        Log.d(TAG, "Book Detail PickUp " + optionPickUp);
        Log.d(TAG, "Book Detail Drop " + optionalDropUp);
        Log.d(TAG, "Book Detail Mobile" + optionMobileno);
        Log.d(TAG, "Book Detail Mobile" + optionItemRemark);


        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.bookingDetail(serbkreqvehrefno, serbkreqpikuplocnm,
                    serbkreqdellocnm, serbkrequregunqid, serbkrequlngunqid, serbkrequlngsessid, serbkreqoptpikuploc, serbkreqoptdelloc, serbkreqcntoptmobno, serbkreqitemremarkdt, new Callback<BookingDetailFareResponse>() {
                        @Override
                        public void onResponse(Call<BookingDetailFareResponse> call, Response<BookingDetailFareResponse> response) {
                            Utils.dismissProgressDialog();
                            if (response.body() != null) {
                                if (response.body().getStatus().equalsIgnoreCase("OK")) {
                                    Log.d(TAG, "onResponse: BookDetails" + response.body().getResults());
                                    Service_Booking_RefNo = String.valueOf(response.body().getResults().getServiceBookingRefNo());
                                    refrence = response.body().getResults().getServiceVehicleRefNo();
                                    Service_Vehicle_Name = response.body().getResults().getServiceVehicleName();
                                    pickingLocation = response.body().getResults().getServicePickupLocation();
                                    dropLocation = response.body().getResults().getServiceDeliveryLocation();
                                    Service_Total_Distance = String.valueOf(response.body().getResults().getServiceTotalDistance());
                                    Service_Fare = String.valueOf(response.body().getResults().getServiceFare());
                                    Service_Fare_Details = String.valueOf(response.body().getResults().getServiceFareDetails());
                                    optionPickUp = response.body().getResults().getService_Booking_Optional_PickupLoc();
                                    optionalDropUp = response.body().getResults().getService_Booking_Optional_DeliveryLoc();
                                    optionMobileno = response.body().getResults().getService_Booking_Optional_ContactNo();
                                    optionItemRemark = response.body().getResults().getService_Booking_ITEM_Remark();

                                    Log.d(TAG, "Booking Detail Response Booking ref: " + Service_Booking_RefNo);
                                    Log.d(TAG, "Booking Detail Response reference: " + refrence);
                                    Log.d(TAG, "Booking Detail Response Vehicle name: " + Service_Vehicle_Name);
                                    Log.d(TAG, "Booking Detail Response Picking Location: " + pickingLocation);
                                    Log.d(TAG, "Booking Detail Response DropLocation: " + dropLocation);
                                    Log.d(TAG, "Booking Detail Response Total Distance : " + Service_Total_Distance);
                                    Log.d(TAG, "Booking Detail Response Fare: " + Service_Fare);
                                    Log.d(TAG, "Booking Detail Response Fare Details: " + Service_Fare_Details);
                                    Log.d(TAG, "Booking Detail Response Optional PickUp " + optionPickUp);
                                    Log.d(TAG, "Booking Detail Response Optional Drop: " + optionalDropUp);
                                    Log.d(TAG, "Booking Detail Response Optional Mobile: " + optionMobileno);
                                    Log.d(TAG, "Booking Detail Response Optional Remark: " + optionItemRemark);

                                    btn_bookNow.setVisibility(View.GONE);
                                    ll_kmRs.setVisibility(View.VISIBLE);
                                    txtFare.setText("\u20B9" + " " + response.body().getResults().getServiceFare());
                                    txtkilometer.setText("" + response.body().getResults().getServiceTotalDistance());
                                    btn_ConfirmBooking.setVisibility(View.VISIBLE);

                                } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                                    Toast.makeText(DashBoard.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<BookingDetailFareResponse> call, Throwable t) {
                            Utils.dismissProgressDialog();
                            Toast.makeText(DashBoard.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Something went wrong!!");
                        }
                    });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(DashBoard.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }


    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void hitUrl() {
        Log.d(TAG, "Booking Detail Response Booking ref: " + Service_Booking_RefNo);
        Log.d(TAG, "Booking Detail Response reference: " + refrence);
        Log.d(TAG, "Booking Detail Response Vehicle name: " + Service_Vehicle_Name);
        Log.d(TAG, "Booking Detail Response Picking Location: " + pickingLocation);
        Log.d(TAG, "Booking Detail Response DropLocation: " + dropLocation);
        Log.d(TAG, "Booking Detail Response Total Distance : " + Service_Total_Distance);
        Log.d(TAG, "Booking Detail Response Fare: " + Service_Fare);
        Log.d(TAG, "Booking Detail Response Fare Details: " + Service_Fare_Details);
        Log.d(TAG, "Booking Detail Response Mobile no: " + mobileNo);
        Log.d(TAG, "Booking Detail Response UniquId: " + uniqid);
        Log.d(TAG, "Booking Detail Response LsessionId: " + lsessionId);
        Log.d(TAG, "Booking Detail Response PickUp: " + optionPickUp);
        Log.d(TAG, "Booking Detail Response DropUp: " + optionalDropUp);
        Log.d(TAG, "Booking Detail Response Mobile: " + optionMobileno);


        RequestBody nwbkingrefno = RequestBody.create(MediaType.parse("text/plain"), Service_Booking_RefNo);
        RequestBody nwbkreqvehrefno = RequestBody.create(MediaType.parse("text/plain"), refrence);
        RequestBody nwbkreqvehname = RequestBody.create(MediaType.parse("text/plain"), Service_Vehicle_Name);
        RequestBody nwbkreqpikuplocnm = RequestBody.create(MediaType.parse("text/plain"), pickingLocation);
        RequestBody nwbkreqdelivlocnm = RequestBody.create(MediaType.parse("text/plain"), dropLocation);
        RequestBody nwbkreqtotalkm = RequestBody.create(MediaType.parse("text/plain"), Service_Total_Distance);
        RequestBody nwbkreqkmfare = RequestBody.create(MediaType.parse("text/plain"), Service_Fare);
        RequestBody nwbkreqkmfaredetails = RequestBody.create(MediaType.parse("text/plain"), Service_Fare_Details);
        RequestBody nwbkrequsrlngtmunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody nwbkrequsrregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqid);
        RequestBody nwbkrequsrlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody nwbkreqoptpikuploc = RequestBody.create(MediaType.parse("text/plain"), optionPickUp);
        RequestBody nwbkreqoptdelloc = RequestBody.create(MediaType.parse("text/plain"), optionalDropUp);
        RequestBody nwbkreqcntoptmobno = RequestBody.create(MediaType.parse("text/plain"), optionMobileno);
        RequestBody nwbkreqitemremarkdt = RequestBody.create(MediaType.parse("text/plain"), optionItemRemark);

        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.bookingDone(nwbkingrefno, nwbkreqvehrefno,
                    nwbkreqvehname, nwbkreqpikuplocnm, nwbkreqdelivlocnm,
                    nwbkreqtotalkm, nwbkreqkmfare, nwbkreqkmfaredetails,
                    nwbkrequsrlngtmunqid, nwbkrequsrregunqid, nwbkrequsrlngsessid,
                    nwbkreqoptpikuploc, nwbkreqoptdelloc, nwbkreqcntoptmobno, nwbkreqitemremarkdt, new Callback<BookingSuccessResponse>() {
                        @Override
                        public void onResponse(Call<BookingSuccessResponse> call, Response<BookingSuccessResponse> response) {
                            Utils.dismissProgressDialog();
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: " + response.body().getResults().getBookingDateTime());
                                Log.d(TAG, "onResponse: " + response.body().getResults().getBookingReferenceNo());
                                Toast.makeText(DashBoard.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                                btn_bookNow.setVisibility(View.VISIBLE);
                                ll_kmRs.setVisibility(View.GONE);
                                btn_ConfirmBooking.setVisibility(View.GONE);

                                pickingLocation = "";
                                dropLocation = "";
                                txtPickUp.setText("");
                                txtdropOff.setText("");
                                edtOptionPickup.setText("");
                                edtOptionDrop.setText("");
                                edtOptionMobile.setText("");
                                spinnerVehicle.setSelection(0);
                                edtRemarkItem.setText("");
                                Intent intent = new Intent(DashBoard.this, RiderConfirmationActivity.class);
                                intent.putExtra(Constants.CUSTOMER_BOOKING_NO, response.body().getResults().getBookingReferenceNo());
                                intent.putExtra(Constants.CUSTOMER_BOOKING_DATE, response.body().getResults().getBookingDateTime());
                                startActivity(intent);


                            }
                        }

                        @Override
                        public void onFailure(Call<BookingSuccessResponse> call, Throwable t) {
                            Utils.dismissProgressDialog();
                            Toast.makeText(DashBoard.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Something went wrong!!");
                        }
                    }

            );
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(DashBoard.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }


    }

    private void dyikdt() {

        Datum datum = new Datum("1", "3.5", "Bangalore", "Bangalore", "Gurgaon", "Haryana", "BMW", "32 ft mxl", "02:00 PM,Today", "Eletronics Goods", "55 Km.", "3900");
        datumList.add(datum);
        datum = new Datum("2", "4.5", "Gurgaon", "Haryana", "Noida", "Delhi", "Duster", "32 ft mxl", "12:00 PM,Today", "Servicing Goods", "50 Km.", "3000");
        datumList.add(datum);
        datum = new Datum("3", "2.5", "Mumbai", "Maharastra", "Pune", "Maharastra", "BMW", "16 ft mxl", "05:00 PM,Tomorrow", "Furnitures", "15 Km.", "1100");
        datumList.add(datum);
        datum = new Datum("4", "3.0", "Ahemdabad", "Gujarat", "Surat", "Gujarat", "Maruti Suzuki", "36 fr mxl", "07:00 PM,Today", "Wires", "5 Km.", "3000");
        datumList.add(datum);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void getPendingRide() {

        mobileNo = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_MOBILE_NO);
        uniqid = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_LSESSION_ID);

        Log.d(TAG, "rideConfirm: " + mobileNo);
        Log.d(TAG, "rideConfirm: " + uniqid);
        Log.d(TAG, "rideConfirm: " + lsessionId);


        RequestBody drvbkhstreqlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody drvbkhstreqlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody drvbkhstregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqid);
        //  teamrecruit91@gmail.com
        if (Utils.isInternetConnected(DashBoard.this)) {
            Utils.showProgressDialog(DashBoard.this);
            RestClient.bookingPending(drvbkhstreqlngunqid, drvbkhstreqlngsessid, drvbkhstregunqid, new Callback<DriverBookingPendingResponse>() {
                @Override
                public void onResponse(Call<DriverBookingPendingResponse> call, Response<DriverBookingPendingResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            if (response.body().getResults().getTodayBookingHistory() != null &&
                                    response.body().getResults().getTodayBookingHistory().size() > 0) {
                                Log.d(TAG, "onResponse: " + response.body().getResults().getTodayBookingHistory().toString());
                                driverAcceptRideAdapter = new DriverAcceptRideAdapter(response.body().getResults().getTodayBookingHistory());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(driverAcceptRideAdapter);
                                driverAcceptRideAdapter.setOnItemClickData(new DriverAcceptRideAdapter.onItemClickData() {
                                    @Override
                                    public void onClickConfirmData(String ReferenceNo, String trackNo, String fareAmount) {
                                        rideConfirm(ReferenceNo, trackNo, fareAmount);
                                    }
                                });
                                relativeLayout.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            } else {
                                relativeLayout.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(DashBoard.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DriverBookingPendingResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });

        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(getApplicationContext(), "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }

    }

    private void rideConfirm(String referenceNo, String trackNo, String fareAmount) {
        mobileNo = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_MOBILE_NO);
        uniqid = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_UNIQUE_ID);
        lsessionId = SmartRidePref.getString(getApplicationContext(), Constants.CUSTOMER_LSESSION_ID);
        Log.d(TAG, "rideConfirm: " + referenceNo);
        Log.d(TAG, "rideConfirm: " + trackNo);
        Log.d(TAG, "rideConfirm: " + fareAmount);

        Log.d(TAG, "rideConfirm: " + mobileNo);
        Log.d(TAG, "rideConfirm: " + uniqid);
        Log.d(TAG, "rideConfirm: " + lsessionId);


        RequestBody drvacptservbkrefno = RequestBody.create(MediaType.parse("text/plain"), referenceNo);
        RequestBody drvacptservbktrkrefno = RequestBody.create(MediaType.parse("text/plain"), trackNo);
        RequestBody drvacpttmserbkamt = RequestBody.create(MediaType.parse("text/plain"), fareAmount);
        RequestBody drvacptbktmlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
        RequestBody drvacptbktmlngsessid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
        RequestBody drvacptbktmregunqid = RequestBody.create(MediaType.parse("text/plain"), uniqid);
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.driverBookingAccept(drvacptservbkrefno, drvacptservbktrkrefno, drvacpttmserbkamt, drvacptbktmlngunqid, drvacptbktmlngsessid, drvacptbktmregunqid, new Callback<DriverAcceptBookingResponse>() {
                @Override
                public void onResponse(Call<DriverAcceptBookingResponse> call, Response<DriverAcceptBookingResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            Log.d(TAG, "onResponse: " + response.body().getResults().getMsg());

                            driverAcceptRideAdapter.notifydata();
                            driverAcceptRideAdapter.notifyDataSetChanged();
                            //    DashBoard.this.notify();
                            //    DashBoard.this.notifyAll();
                            getPendingRide();
                            Toast.makeText(DashBoard.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(DashBoard.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DriverAcceptBookingResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(DashBoard.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });

        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(DashBoard.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }
    }


}