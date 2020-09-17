package com.smartrider24.Activity.DriverSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartrider24.Activity.DashBoard;
import com.smartrider24.R;
import com.smartrider24.Retrofit.RestClient;
import com.smartrider24.model.vehicle.VehicleListResponse;
import com.smartrider24.utils.Constants;
import com.smartrider24.utils.SmartRidePref;
import com.smartrider24.utils.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferEarn extends AppCompatActivity {

    private static final String TAG = ReferEarn.class.getSimpleName();
    TextView btn_ShareLink,txt_code;
    LinearLayout ll_referralCode;
    ImageView backButton;
    static String vehicleList;
    String vehicleuniqId, vehiclelsessionid;
String uniqId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);


        backButton= findViewById(R.id.back);
        btn_ShareLink= findViewById(R.id.btn_shareLink);
        txt_code= findViewById(R.id.txt_code);
        ll_referralCode=findViewById(R.id.ll_referralCode);

        uniqId = SmartRidePref.getString(ReferEarn.this, Constants.CUSTOMER_UNIQUE_ID);
        txt_code.setText(""+uniqId);

        btn_ShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareData();
            }
        });
        ll_referralCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Link",txt_code.getText().toString());
                Toast.makeText(getApplicationContext(),"Link Copied",Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    private void ShareData() {
        int applicationNameId = getApplicationContext().getApplicationInfo().labelRes;
        final String appPackageName = getApplicationContext().getPackageName();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, getApplicationContext().getString(applicationNameId));
        String text = "Invite your friends and family, when you refer a friend to try SmartRider24 Referal code";
        String link = "https://play.google.com/store/apps/details?id=" + appPackageName;
        i.putExtra(Intent.EXTRA_TEXT, text + " " +uniqId+" " + link);
        startActivity(Intent.createChooser(i, "Share link:"));
    }

}
