package com.smartrider24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartrider24.utils.Constants;

public class RiderConfirmationActivity extends AppCompatActivity {

    TextView textViewBookingNo, textViewDate, backButtp;
    String bookingNo = "", bookingDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_confirmation);

        textViewBookingNo = findViewById(R.id.txtBookingNo);
        textViewDate = findViewById(R.id.txtBookingDate);
        backButtp = findViewById(R.id.backButton);
        if (getIntent().hasExtra(Constants.CUSTOMER_BOOKING_DATE)) {
            bookingDate = getIntent().getStringExtra(Constants.CUSTOMER_BOOKING_DATE);
            textViewDate.setText("Booking Date: " + bookingDate);

        }
        if (getIntent().hasExtra(Constants.CUSTOMER_BOOKING_NO)) {
            bookingNo = getIntent().getStringExtra(Constants.CUSTOMER_BOOKING_NO);
            textViewBookingNo.setText("Booking No: " + bookingNo);
        }
        backButtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}