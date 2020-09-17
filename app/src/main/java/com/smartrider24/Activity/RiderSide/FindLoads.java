package com.smartrider24.Activity.RiderSide;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.smartrider24.Activity.DashBoard;
import com.smartrider24.R;

import java.util.Arrays;
import java.util.List;

public class FindLoads extends AppCompatActivity {
    private static final String TAG = DashBoard.class.getSimpleName();
    int AUTOCOMPLETE_REQUEST_CODE_SOURCE = 1;
    int AUTOCOMPLETE_REQUEST_CODE_DEST = 2;
    ImageView backf;
    TextView ePick, eDrop;
    TextView btn_submit;
    String pickuplocation,droplocation;
    LinearLayout linearLayoutSource,linearLayoutDrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_loads);
        linearLayoutDrop=findViewById(R.id.linearDrop);
        linearLayoutSource=findViewById(R.id.linearSource);

//        Places.initialize(FindLoads.this, "AIzaSyD5_rhp3p2VZeYIKOG5a4pVeW5vf6AiESk");
//
//        if (!Places.isInitialized()) {
//            Places.initialize(FindLoads.this, "AIzaSyD5_rhp3p2VZeYIKOG5a4pVeW5vf6AiESk");
//        }

        init();

    }

    private void init() {
        backf = findViewById(R.id.backf);
        ePick = findViewById(R.id.etPickup);
        eDrop = findViewById(R.id.etDropOff);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   validation();
            }
        });

        backf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayoutSource.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });

        linearLayoutDrop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                checkPermissionDropLocation();
            }
        });
    }
    private void selectPickUp() {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .setCountry("IN")
                .build(FindLoads.this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SOURCE);
    }

    private void selectDrop() {
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .setCountry("IN")
                .build(FindLoads.this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_DEST);
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FindLoads.this);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void CheckPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == AUTOCOMPLETE_REQUEST_CODE_SOURCE) {

                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());

                if (!TextUtils.isEmpty(place.getName())) {
                    if (TextUtils.isEmpty(place.getAddress())) {
                        pickuplocation=place.getName();
                        ePick.setText("" + place.getName());
                        Log.d(TAG, "onActivityResult: "+pickuplocation);
                    } else {
                        pickuplocation=place.getName()+""+place.getAddress();
                        Log.d(TAG, "onActivityResult: "+pickuplocation);
                        ePick.setText(place.getName() + "" + place.getAddress());
                    }
                }
            } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_DEST) {

                Place placeDest = Autocomplete.getPlaceFromIntent(data);

                if (!TextUtils.isEmpty(placeDest.getName())) {

                    if (TextUtils.isEmpty(placeDest.getAddress())) {
                        droplocation=placeDest.getName();
                        eDrop.setText("" + placeDest.getName());
                        Log.d(TAG, "onActivityResult: "+droplocation);

                    } else {
                        droplocation=placeDest.getName()+" "+placeDest.getAddress();
                        Log.d(TAG, "onActivityResult: "+droplocation);
                        eDrop.setText(placeDest.getName() + "" + placeDest.getAddress());
                    }


                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("TAG", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
            }

        }

    }

    private void validation() {
        if (ePick.getText().toString().trim().equalsIgnoreCase("")) {
            ePick.setError("Enter Pick Up Location");
            ePick.requestFocus();
            return;
        } else if (eDrop.getText().toString().trim().equalsIgnoreCase("")) {
            eDrop.setError("Enter Drop Off Location");
            eDrop.requestFocus();
            return;
        } else if (!isOnline()) {

            Toast.makeText(getApplicationContext(), "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent=new Intent(FindLoads.this,DashBoard.class);
        intent.putExtra("PICKUPLOCATION",pickuplocation);
        intent.putExtra("DROPLOCATION",droplocation);
        startActivity(intent);
        finish();


    }

    private void submitUrl() {

    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
