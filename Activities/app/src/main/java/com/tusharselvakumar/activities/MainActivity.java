package com.tusharselvakumar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
                String em = email.getText().toString();
                EditText pwd = (EditText) findViewById(R.id.editTextTextPassword);

                int EmailLength = em.length();
                int PwdLength = pwd.length();

                if (EmailLength>0 & PwdLength>0) {
                    Intent intent = new Intent(view.getContext(), MainActivity2.class);
                    intent.putExtra("email",em);
                    view.getContext().startActivity(intent);
                }

                else {
                    Toast.makeText(getApplicationContext(),"Enter a Valid Email Address or Password", Toast.LENGTH_SHORT).show();
                }
            };
        });
    }
}