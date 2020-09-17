package com.smartrider24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.smartrider24.utils.Constants;

public class DriverWelcomeActivity extends AppCompatActivity {

    TextView textViewDriverRegistration,textViewDriverMessage;
    String message,regno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_welcome);
        textViewDriverMessage=findViewById(R.id.txtMessage);
        textViewDriverRegistration=findViewById(R.id.txtRegNo);



        if (getIntent().hasExtra(Constants.DRIVER_REG_NO)){
            regno=getIntent().getStringExtra(Constants.DRIVER_REG_NO);
            textViewDriverRegistration.setText(""+regno);
        }

        if (getIntent().hasExtra(Constants.DRIVER_MESSAGE)){
            message=getIntent().getStringExtra(Constants.DRIVER_MESSAGE);
            textViewDriverMessage.setText(""+message);
        }

    }
}