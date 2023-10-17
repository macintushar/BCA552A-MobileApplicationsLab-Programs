package com.tusharselvakumar.kiacarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = (Button) findViewById(R.id.button1);
        TextView signUpText = (TextView) findViewById(R.id.signUpText);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String value = sharedPreferences.getString("userEmail", "en");

        if (value != "en") {
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText) findViewById(R.id.userEmailAddress);
                String em = email.getText().toString();
                EditText pwd = (EditText) findViewById(R.id.userPwd1);

                int EmailLength = em.length();
                int PwdLength = pwd.length();

                if (EmailLength>0 & PwdLength>0) {
                    Intent intent = new Intent(view.getContext(), HomePage.class);

                    SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("userEmail", em);
                    editor.apply();

//                    intent.putExtra("email",em);
                    view.getContext().startActivity(intent);
                }

                else {
                    Toast.makeText(getApplicationContext(),"Enter a Valid Email Address or Password", Toast.LENGTH_SHORT).show();
                }
            };
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
}