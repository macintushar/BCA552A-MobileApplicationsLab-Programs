package com.tusharselvakumar.kiacarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

public class RatePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_page);

        Button submitBtn = findViewById(R.id.submitbtn);
        RatingBar appRating = findViewById(R.id.ratingBar);
        CheckBox emailAlerts = findViewById(R.id.checkBox2);
        Switch data = findViewById(R.id.switch1);

        final String[] ratingMsg = new String[1];
        final String[] marketingConsent = new String[1];
        final String[] dataConsent = new String[1];

        data.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dataConsent[0] = "You are allowing Kia India to collect and use your data for research and marketing purposes.";

                } else {
                    dataConsent[0] = "You are NOT allowing Kia India to collect and use your data for research and marketing purposes.";
                }
            }
        });

        emailAlerts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    marketingConsent[0] = "You have signed up for Email Alerts.";
                } else {
                    marketingConsent[0] = "You have NOT signed up for Email Alerts.";
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingMsg[0] = "You have rated us: " + appRating.getRating() + "/" + appRating.getMax() + "\n" + dataConsent[0] + "\n"+ marketingConsent[0] + "\n\nRedirecting to Home Page....";

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RatePage.this);
                alertDialogBuilder.setTitle("Rating");
                alertDialogBuilder.setMessage(ratingMsg[0]);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(RatePage.this, HomePage.class);
                        startActivity(i);
                        finish();
                    }
                },5000);

            }
        });

    }
}