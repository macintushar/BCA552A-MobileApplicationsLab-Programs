package com.tusharselvakumar.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_SEND_SMS = 100;
    EditText phonenumber,message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send=findViewById(R.id.button);
        phonenumber=findViewById(R.id.editText);
        message=findViewById(R.id.editText2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
        }

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    String number=phonenumber.getText().toString();
                    String msg=message.getText().toString();
                    try {
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(number,null,msg,null,null);
                        Toast.makeText(getApplicationContext(),"Message Delivered",Toast.LENGTH_LONG).show();
                        message.setText("");
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Message not sent: " + e.toString(),Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "SMS permission not granted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "SMS permission granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}