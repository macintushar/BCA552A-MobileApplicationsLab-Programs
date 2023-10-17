package com.tusharselvakumar.resume;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    int themeCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button contactBtn = (Button)findViewById(R.id.contactButton);
        TextView EmailTextView = (TextView)findViewById(R.id.EmailTextView);
        FloatingActionButton FBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeCount++;
                if (themeCount<5) {
                    int rem = 5 - themeCount;
                    String msg = "Press the button " + String.valueOf(rem) + " more times to view Email ID";
                    Toast t = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                    t.show();
                    EmailTextView.setText("");
                }
                if (themeCount == 5) {
                    Toast t = Toast.makeText(getApplicationContext(), "You can email me at \ntusharkumar91111@gmail.com", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                    t.show();
                    themeCount = 0;
                    EmailTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    EmailTextView.setText("Email: tusharkumar91111@gmail.com");
                }
            }
        });

        FBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Do you want to exit ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                builder.setPositiveButton("Yes",(DialogInterface.OnClickListener) (dialog,which) -> {
                    finish();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}