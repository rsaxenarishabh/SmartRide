package com.smartrider24.Activity.DriverSide.KYC_Details;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.smartrider24.R;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.Utils;

public class BankDetails extends AppCompatActivity {

    ImageView backButton;
    String driverRc;
    ImageView imageViewRC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        backButton= findViewById(R.id.backbank);
        imageViewRC=findViewById(R.id.img_RC);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (getIntent().hasExtra(Constants.DRIVER_RC)){
            driverRc=getIntent().getStringExtra(Constants.DRIVER_RC);
        }
        if (driverRc!=null){
            Utils.showProgressDialog(this);
            Glide.with(this).load(Uri.parse("https://www.smartrider24.com/drvrsecdoc/"+driverRc)).addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                  Utils.dismissProgressDialog();
                  return false;
                }
            }).into(imageViewRC);
        }
    }

    }
