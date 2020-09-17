package com.smartrider24.Activity.DriverSide;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.location.LocationListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.smartrider24.Activity.DashBoard;
import com.smartrider24.Activity.Profile;
import com.smartrider24.Activity.SignUp;
import com.smartrider24.Activity.image.ImageSelectActivity;
import com.smartrider24.BuildConfig;
import com.smartrider24.DriverWelcomeActivity;
import com.smartrider24.LocationUpdatesService;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.Uti;
import com.smartrider24.model.CustomerRegistration;
import com.smartrider24.model.Results;
import com.smartrider24.model.driverconfirmride.DriverBookingDetail;
import com.smartrider24.model.paymentreceipt.PaymentReceiptResponse;
import com.smartrider24.model.ridestatus.RideStatusResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class RideStartActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener, LocationListener {

    private TextView txtstartRide;
    private TextView txtCompleteRide;
    private TextView ridetime;
    private TextView ridedistance;
    private TextView ridefare;
    DriverBookingDetail detail;
    String receive = "", receipt = "";
    EditText txtReceive;
    TextView txtReceipt;
    TextView optionalPick,optionalDrop,optionalMObile,txtVehiclename,txtGoodsInfo;
    ImageView imageViewPic,imageViewDrop;
    int time;
    String bookingRefNo = "", bookingTrackNo = "", currentAddress = "",
            driverReferenceNo = "", driverUNQLGno = "", driverLsessionId = "", currentLat = "", currentLot = "", status = "";

    String fareAmount = "", paymentReceive = "";
    private static final String TAG = RideStartActivity.class.getSimpleName();

    // Used in checking for runtime permissions.
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    // The BroadcastReceiver used to listen from broadcasts from the service.
    private MyReceiver myReceiver;

    // A reference to the service used to get location updates.
    private LocationUpdatesService mService = null;

    // Tracks the bound state of the service.
    private boolean mBound = false;

    LocationManager locationManager;

    // UI elements.
    private Button mRequestLocationUpdatesButton;
    private Button mRemoveLocationUpdatesButton;

    Location location;

    String uniqId = "", lsessionId = "", mobileNo = "";

    // Monitors the state of the connection to the service.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationUpdatesService.LocalBinder binder = (LocationUpdatesService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_start);
        txtstartRide = (TextView) findViewById(R.id.txtstartRide);
        txtCompleteRide = (TextView) findViewById(R.id.txtCompleteRide);
        ridetime = (TextView) findViewById(R.id.ridetime);
        ridedistance = (TextView) findViewById(R.id.ridedistance);
        ridefare = (TextView) findViewById(R.id.ridefare);
        optionalPick=findViewById(R.id.txtOptionPickup);
        optionalDrop=findViewById(R.id.txtOptionalDrop);
        optionalMObile=findViewById(R.id.txtOptionalMobile);
        txtVehiclename=findViewById(R.id.txt_vehicleName);
        txtGoodsInfo=findViewById(R.id.goodsInfo);
        imageViewPic=findViewById(R.id.imgPickupGoogle);
        imageViewDrop=findViewById(R.id.imgDropGoogle);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        myReceiver = new MyReceiver();
        // Check that the user hasn't revoked permissions by going to Settings.
        if (Uti.requestingLocationUpdates(this)) {
            if (!checkPermissions()) {
                requestPermissions();
            }
        }
        time = Integer.parseInt(SmartRidePref.getString(RideStartActivity.this, "TIME"));

    }


    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);

        mRequestLocationUpdatesButton = (Button) findViewById(R.id.btnStartRide);
        mRemoveLocationUpdatesButton = (Button) findViewById(R.id.btnCompleteRide);

        mRequestLocationUpdatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkPermissions()) {
                    requestPermissions();
                } else {
                    status = "Start";
                    SmartRidePref.putBoolean(getApplicationContext(),"DRIVERSTATUS",true);
                    mService.createLocationRequest(time);
                    mService.requestLocationUpdates();
                }
            }
        });

        mRemoveLocationUpdatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "Close";
                onLocationChanged(location);

                mService.removeLocationUpdates();
            }
        });

        // Restore the state of the buttons when the activity (re)launches.
        setButtonsState(Uti.requestingLocationUpdates(this));

        // Bind to the service. If the service is in foreground mode, this signals to the service
        // that since this activity is in the foreground, the service can exit foreground mode.
        bindService(new Intent(this, LocationUpdatesService.class), mServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtstartRide.setText("" + SmartRidePref.getString(RideStartActivity.this, "PICKING"));
        txtCompleteRide.setText("" + SmartRidePref.getString(RideStartActivity.this, "DROP"));
        ridefare.setText("" + SmartRidePref.getString(RideStartActivity.this, "FARE"));
        ridedistance.setText("" + SmartRidePref.getString(RideStartActivity.this, "DISTANCE") + " KM");
        ridetime.setText("" + SmartRidePref.getString(RideStartActivity.this, "DATE"));

        optionalMObile.setText(""+SmartRidePref.getString(RideStartActivity.this,"OPTIONMOBILE"));
         optionalPick.setText(""+SmartRidePref.getString(RideStartActivity.this,"OPTIONPICKUP"));
         optionalDrop.setText(""+SmartRidePref.getString(RideStartActivity.this,"OPTIONDELILOC"));
        txtGoodsInfo.setText(""+SmartRidePref.getString(RideStartActivity.this,"OPTIONITEMREMARK"));
        txtVehiclename.setText(""+SmartRidePref.getString(RideStartActivity.this,"VEHICLENAME"));

        imageViewPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://maps.google.co.in/maps?q=" + SmartRidePref.getString(RideStartActivity.this,"PICKING")));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        imageViewDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://maps.google.co.in/maps?q=" + SmartRidePref.getString(RideStartActivity.this,"DROP")));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
                new IntentFilter(LocationUpdatesService.ACTION_BROADCAST));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (mBound) {
            // Unbind from the service. This signals to the service that this activity is no longer
            // in the foreground, and the service can respond by promoting itself to a foreground
            // service.
            unbindService(mServiceConnection);
            mBound = false;
        }
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }

    /**
     * Returns the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            Snackbar.make(
                    findViewById(R.id.activity_main),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(RideStartActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(RideStartActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted.
                mService.createLocationRequest(time);
                mService.requestLocationUpdates();
            } else {
                // Permission denied.
                setButtonsState(false);
                Snackbar.make(
                        findViewById(R.id.activity_main),
                        R.string.permission_denied_explanation,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.settings, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentAddress = Uti.getAddress(RideStartActivity.this, location.getLatitude(), location.getLongitude());
        sendLocation(currentAddress, location.getLongitude(), location.getLatitude());
    }

    private void sendLocationClose() {

    }

    /**
     * Receiver for broadcasts sent by {@link LocationUpdatesService}.
     */
    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
            if (location != null) {
                location.getLatitude();
                location.getLongitude();
                currentAddress = Uti.getAddress(RideStartActivity.this, location.getLatitude(), location.getLongitude());
                sendLocation(currentAddress, location.getLongitude(), location.getLatitude());
//                Toast.makeText(MainActivity.this, Utils.getLocationText(location),
//                        Toast.LENGTH_SHORT).show();
                Toast.makeText(RideStartActivity.this, "" + currentAddress,
                        Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "onReceive: " + currentAddress);
            }
        }
    }

    private void sendLocation(String currentadd, double longitude, double latitude) {

        bookingRefNo = SmartRidePref.getString(RideStartActivity.this, "BOOKINGREF");
        bookingTrackNo = SmartRidePref.getString(RideStartActivity.this, "BOOKINGTRACKNO");
        driverReferenceNo = SmartRidePref.getString(RideStartActivity.this, "DRIVERREFRENCE");
        driverUNQLGno = SmartRidePref.getString(RideStartActivity.this, "DRIVERUNQLGNo");
        driverLsessionId = SmartRidePref.getString(RideStartActivity.this, Constants.CUSTOMER_LSESSION_ID);
        currentLat = String.valueOf(latitude);
        currentLot = String.valueOf(longitude);
        currentAddress = currentadd;
        Log.d(TAG, "sendLocation: Reference no " + bookingRefNo);
        Log.d(TAG, "sendLocation: Track no" + bookingTrackNo);
        Log.d(TAG, "sendLocation: Latitude " + currentLat);
        Log.d(TAG, "sendLocation: Longitude " + currentLot);
        Log.d(TAG, "sendLocation: Longitude " + currentAddress);
        Log.d(TAG, "sendLocation: Driver Reference No" + driverReferenceNo);
        Log.d(TAG, "sendLocation: Driver UNQLGNo " + driverUNQLGno);
        Log.d(TAG, "sendLocation: Driver SessionId " + driverLsessionId);
        Log.d(TAG, "sendLocation: Ride Status " + status);


        RequestBody drsndjbrefno = RequestBody.create(MediaType.parse("text/plain"), bookingRefNo);
        RequestBody drsndjbtrkunqno = RequestBody.create(MediaType.parse("text/plain"), bookingTrackNo);
        RequestBody drsndjbtklatdt = RequestBody.create(MediaType.parse("text/plain"), currentLat);
        RequestBody drsndjbtklngdt = RequestBody.create(MediaType.parse("text/plain"), currentLot);
        RequestBody drsndjbtkcrntaddrss = RequestBody.create(MediaType.parse("text/plain"), currentAddress);
        RequestBody drrgrefnosndtrk = RequestBody.create(MediaType.parse("text/plain"), driverReferenceNo);
        RequestBody drlngunqnotrk = RequestBody.create(MediaType.parse("text/plain"), driverUNQLGno);
        RequestBody drlgsessunqidtrk = RequestBody.create(MediaType.parse("text/plain"), driverLsessionId);
        RequestBody drtrpcurntstatusdt = RequestBody.create(MediaType.parse("text/plain"), status);

        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.rideCall(drsndjbrefno, drsndjbtrkunqno, drsndjbtklatdt, drsndjbtklngdt, drsndjbtkcrntaddrss, drrgrefnosndtrk, drlngunqnotrk, drlgsessunqidtrk, drtrpcurntstatusdt, new Callback<RideStatusResponse>() {
                @Override
                public void onResponse(Call<RideStatusResponse> call, Response<RideStatusResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                            if (status.equalsIgnoreCase("Close")) {
                                sendDataAlertDialog();
                                Toast.makeText(RideStartActivity.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                            Toast.makeText(RideStartActivity.this, "" + response.body().getResults().getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }

                }

                @Override
                public void onFailure(Call<RideStatusResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(RideStartActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Something went wrong!!");
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(RideStartActivity.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Internet Connection Failed!!");
        }


    }

    private void sendDataAlertDialog() {
        final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and titl
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.close_ride_layout, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog dialog = dialogBuilder.create();
        final EditText edtFare = dialogView.findViewById(R.id.edtFareAmount);
        Button buttonRide = dialogView.findViewById(R.id.btnDoneRide);
        txtReceive = dialogView.findViewById(R.id.txtReceivepayment);
        txtReceipt = dialogView.findViewById(R.id.txtReceiptPayment);


        txtReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RideStartActivity.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 102);
            }
        });

        buttonRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtFare.getText().toString().trim().equalsIgnoreCase("")
                        && edtFare.getText().toString().trim().length() == 0) {
                    Toast.makeText(RideStartActivity.this, "Enter Fare Amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtReceive.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(RideStartActivity.this, "Enter Receive Amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (receipt != null && receipt.length() == 0) {
                    Toast.makeText(RideStartActivity.this, "Select Receipt Payment", Toast.LENGTH_SHORT).show();
                    return;
                }
                fareAmount = edtFare.getText().toString().trim();
                paymentReceive = txtReceive.getText().toString().trim();
                uniqId = SmartRidePref.getString(RideStartActivity.this, Constants.CUSTOMER_UNIQUE_ID);
                lsessionId = SmartRidePref.getString(RideStartActivity.this, Constants.CUSTOMER_LSESSION_ID);
                mobileNo = SmartRidePref.getString(RideStartActivity.this, Constants.CUSTOMER_MOBILE_NO);
                RequestBody drvupddeldtbkrefno = RequestBody.create(MediaType.parse("text/plain"), bookingRefNo);
                RequestBody drvupddeldtbkunqno = RequestBody.create(MediaType.parse("text/plain"), bookingTrackNo);
                RequestBody drvupddeldtfareamt = RequestBody.create(MediaType.parse("text/plain"), fareAmount);
                RequestBody drvupddeldtrecfreamt = RequestBody.create(MediaType.parse("text/plain"), paymentReceive);


                RequestBody drvupddeldtlngunqid = RequestBody.create(MediaType.parse("text/plain"), mobileNo);
                RequestBody drvupddeldtregtmunqno = RequestBody.create(MediaType.parse("text/plain"), uniqId);
                RequestBody drvupddeldtlngtmsessunqid = RequestBody.create(MediaType.parse("text/plain"), lsessionId);
                File file1 = new File(receipt);
                RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                MultipartBody.Part drvupddeldtrecptdoc = MultipartBody.Part.createFormData("drvupddeldtrecptdoc", file1.getName(), requestBody2);

                Log.d(TAG, "onClick: Fare " + bookingRefNo);
                Log.d(TAG, "onClick: Fare " + bookingTrackNo);
                Log.d(TAG, "onClick: Fare " + fareAmount);
                Log.d(TAG, "onClick: Receipt " + receipt);
                Log.d(TAG, "onClick: Receive " + paymentReceive);
                Log.d(TAG, "ChangePassword Mobile no" + mobileNo);
                Log.d(TAG, "ChangePassword uniqId" + uniqId);
                Log.d(TAG, "ChangePassword lsessionId" + lsessionId);
                if (Utils.isInternetConnected(RideStartActivity.this)) {
                    Utils.showProgressDialog(RideStartActivity.this);
                    RestClient.paymentCall(drvupddeldtbkrefno, drvupddeldtbkunqno, drvupddeldtfareamt, drvupddeldtrecfreamt, drvupddeldtrecptdoc, drvupddeldtlngunqid,
                            drvupddeldtregtmunqno, drvupddeldtlngtmsessunqid, new Callback<PaymentReceiptResponse>() {
                                @Override
                                public void onResponse(Call<PaymentReceiptResponse> call, Response<PaymentReceiptResponse> response) {
                                    Utils.dismissProgressDialog();
                                    if (response.body() != null) {
                                        if (response.body().getStatus().equalsIgnoreCase("OK")) {
                                            Log.d(TAG, "onResponse: " + response.body().getResults().getMsg());
                                            SmartRidePref.putBoolean(getApplicationContext(),"DRIVERSTATUS",false);
                                            Intent intent=new Intent(RideStartActivity.this, DashBoard.class);
                                            startActivity(intent);
                                            finish();
                                            dialog.cancel();
                                        } else if (response.body().getStatus().equalsIgnoreCase("Error")) {
                                            Log.d(TAG, "onResponse: " + response.body().getResults().getMsg());
                                        }
                                    }

                                }

                                @Override
                                public void onFailure(Call<PaymentReceiptResponse> call, Throwable t) {
                                    Utils.dismissProgressDialog();
                                    Toast.makeText(RideStartActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Something went wrong!!");
                                }
                            });

                } else {
                    Utils.dismissProgressDialog();
                    Toast.makeText(RideStartActivity.this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Internet Connection Failed!!");
                }

            }
        });


        dialog.setCancelable(false);
        dialog.show();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            receipt = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            String filename1 = receipt.substring(receipt.lastIndexOf("/") + 1);
            txtReceipt.setText("" + filename1);
            Log.d(TAG, "onActivityResult: License " + receipt);


        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        // Update the buttons state depending on whether location updates are being requested.
        if (s.equals(Uti.KEY_REQUESTING_LOCATION_UPDATES)) {
            setButtonsState(sharedPreferences.getBoolean(Uti.KEY_REQUESTING_LOCATION_UPDATES,
                    false));
        }
    }

    private void setButtonsState(boolean requestingLocationUpdates) {
        if (requestingLocationUpdates) {
            mRequestLocationUpdatesButton.setEnabled(false);
            mRequestLocationUpdatesButton.setVisibility(View.GONE);
            mRemoveLocationUpdatesButton.setVisibility(View.VISIBLE);
            mRemoveLocationUpdatesButton.setEnabled(true);
        } else {
            mRequestLocationUpdatesButton.setEnabled(true);
            mRequestLocationUpdatesButton.setVisibility(View.VISIBLE);
            mRemoveLocationUpdatesButton.setVisibility(View.GONE);
            mRemoveLocationUpdatesButton.setEnabled(false);
        }
    }

}