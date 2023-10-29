package com.tusharselvakumar.lt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private String seatOption;
    private String depCity;
    private String arrCity;
    private String departDate;
    private String arriveDate;
    private String name;
    private String email;
    private String phone;
    private String rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        rate = getIntent().getStringExtra("rate");
        departDate = getIntent().getStringExtra("depDate");
        arriveDate = getIntent().getStringExtra("retDate");
        depCity = getIntent().getStringExtra("depCity");
        arrCity = getIntent().getStringExtra("arrCity");

        Button menu = (Button) findViewById(R.id.button);
        TextView tv = (TextView) findViewById(R.id.seatType);

        ImageView iv = findViewById(R.id.imageView);

        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.context_menu_item2) {
            // Handle context menu item 2 click
            Toast.makeText(this, "Selected Economy XL", Toast.LENGTH_SHORT).show();
            seatOption = "Economy XL";
            return true;
        } else {
            Toast.makeText(this, "Selected Economy", Toast.LENGTH_SHORT).show();
            seatOption = "Economy";
            return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.popup_menu_item1) {
                    Toast.makeText(getApplicationContext(), "Viewing Image", Toast.LENGTH_SHORT).show();

                    ImageView iv = findViewById(R.id.imageView);
                    iv.setVisibility(View.VISIBLE);

                    return true;
                } if (itemId == R.id.popup_menu_item2) {
                    Intent ac3 = new Intent(getApplicationContext(), MainActivity3.class);

                    ac3.putExtra("name",name);
                    ac3.putExtra("email", email);
                    ac3.putExtra("phone", phone);
                    ac3.putExtra("rate", rate);
                    ac3.putExtra("depDate", departDate);
                    ac3.putExtra("retDate", arriveDate);
                    ac3.putExtra("depCity", depCity);
                    ac3.putExtra("arrCity", arrCity);
                    ac3.putExtra("seat", seatOption);

                    startActivity(ac3);
                    return true;
                } if (itemId == R.id.popup_menu_item3) {
                    String passenger = "Name: " + name + "Email:" + email + "Phone: " + phone + " Depart from: " + depCity + " on " + departDate + " Arrive in: " + arrCity + " and leave on " + arriveDate + " Price: " + rate + " Seat Option: " + seatOption;
                    Toast.makeText(getApplicationContext(), passenger, Toast.LENGTH_LONG).show();
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });

        popupMenu.show();
    }
}