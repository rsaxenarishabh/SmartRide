 package com.smartrider24.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrider24.Interfaces.SmsListener;
import com.smartrider24.Interfaces.SmsReceiver;
import com.smartrider24.R;

public class OtpVerification extends AppCompatActivity {

    EditText ed;
    TextView tv;
    String otp_generated,contactNo,id1;
   // GlobalData gd = new GlobalData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        ed=(EditText)findViewById(R.id.otp);
        tv=(TextView) findViewById(R.id.verify_otp);
    /*This is important because this will be called every time you receive
     any sms */
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                ed.setText(messageText);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                } catch (Exception e) {
                }

                if (ed.getText().toString().equals(otp_generated)) {
                    Toast.makeText(OtpVerification.this,"OTP Verified Successfully !",Toast.LENGTH_SHORT).show();
                }

            }
            });
        }
    }

