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

        if (!value.equals("en")) {
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
            finish();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText) findViewById(R.id.userEmailAddress);
                EditText pwd = (EditText) findViewById(R.id.userPwd1);

                String em = email.getText().toString();
                String password = pwd.getText().toString();

                int EmailLength = em.length();
                int PwdLength = password.length();

                if (EmailLength > 0 && PwdLength > 0) {
                    DatabaseHelper dbHelper = new DatabaseHelper(view.getContext());
                    boolean isLoggedIn = dbHelper.checkLogin(em, password);
                    dbHelper.close();

                    if (isLoggedIn) {
                        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("userEmail", em);
                        editor.apply();

                        Intent intent = new Intent(view.getContext(), HomePage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Authentication failed
                        Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Enter a valid email address and password", Toast.LENGTH_SHORT).show();
                }
            }
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
