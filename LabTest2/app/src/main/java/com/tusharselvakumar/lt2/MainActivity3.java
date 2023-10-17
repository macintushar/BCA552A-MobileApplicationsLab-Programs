package com.tusharselvakumar.lt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String rate = getIntent().getStringExtra("rate");
        String depDate = getIntent().getStringExtra("depDate");
        String retDate = getIntent().getStringExtra("retDate");
        String depCity = getIntent().getStringExtra("depCity");
        String arrCity = getIntent().getStringExtra("arrCity");
        String seat = getIntent().getStringExtra("seat");

        String passenger = "Name: " + name + "\nEmail:" + email + "\nPhone: " + phone;
        String flight = "Depart from: " + depCity + " on " + depDate + "\nArrive in: " + arrCity + " and leave on " + retDate + "\n\nPrice: " + rate + "\nSeat Option: " + seat;

        TextView tv1 = findViewById(R.id.passengerDetails);
        TextView tv2 = findViewById(R.id.passengerDetails2);

        tv1.setText(passenger);
        tv2.setText(flight);

        Button confirm = (Button) findViewById(R.id.button2);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Confirmed Ticket", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}