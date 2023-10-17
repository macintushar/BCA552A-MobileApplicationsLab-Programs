package com.tusharselvakumar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn = (Button) findViewById(R.id.button2);

        String value = getIntent().getStringExtra("email");
        Toast t = Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT);
        t.show();

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Hello, " + value);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(getApplicationContext(),"Logging Out", Toast.LENGTH_SHORT);
                t.show();
                Intent logout = new Intent(view.getContext(), MainActivity.class);
                startActivity(logout);

            };
        });

        ImageButton imgBtn = (ImageButton) findViewById(R.id.imgButton);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openVideo = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=boYtpG5Ifg8"));
                startActivity(openVideo);
            }
        });
    }
}