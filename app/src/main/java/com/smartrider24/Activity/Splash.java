package com.smartrider24.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.smartrider24.Activity.DriverSide.RideStartActivity;
import com.smartrider24.R;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (SmartRidePref.getBoolean(Splash.this,"DRIVERSTATUS")){
                    Intent intent=new Intent(Splash.this, RideStartActivity.class);
                    startActivity(intent);
                    finish();
                }
               else if (SmartRidePref.getBoolean(Splash.this, Constants.LOGIN_STATUS)){
                    Intent mainIntent = new Intent(Splash.this,DashBoard.class);
                    Log.d("One", "run: First");
                    startActivity(mainIntent);
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(Splash.this,Login.class);
                    Log.d("One", "run: Two");
                    startActivity(mainIntent);
                    finish();
                }
                /* Create an Intent that will start the Menu-Activity. */

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
