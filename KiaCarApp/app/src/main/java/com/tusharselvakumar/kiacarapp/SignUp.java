package com.tusharselvakumar.kiacarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.google.android.material.snackbar.Snackbar;

public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText email = (EditText) findViewById(R.id.userEmailAddress);
        EditText name = (EditText) findViewById(R.id.userFullName);
        EditText VIN = (EditText) findViewById(R.id.userVINNumber);
        EditText engineNumber = (EditText) findViewById(R.id.userEngineNumber);
        EditText password1 = (EditText) findViewById(R.id.userPwd1);
        EditText password2 = (EditText) findViewById(R.id.userPwd2);
        TextView loginText = (TextView) findViewById(R.id.loginText);

        Button submitBtn = (Button) findViewById(R.id.button1);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Login.class);
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String nameText = name.getText().toString();
                String VINText = VIN.getText().toString();
                String engineNumberText = engineNumber.getText().toString();
                String pwd1 = password1.getText().toString();
                String pwd2 = password2.getText().toString();

                if (Validations(emailText,nameText,VINText,engineNumberText,pwd1,pwd2)) {
                    try{
                        // Create a DatabaseHelper instance
                        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                        SQLiteDatabase database = dbHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put("Email", emailText);
                        values.put("Name", nameText);
                        values.put("VIN", VINText);
                        values.put("EngineNumber", engineNumberText);
                        values.put("Password", pwd1);

                        database.insert("login", null, values);
                        database.close();

                        Snackbar.make(v,"Signed Up. Redirecting to Login..",Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(SignUp.this, Login.class);
                                startActivity(i);
                                finish();
                            }
                        },3000);
                    }
                    catch (Exception  e) {
                        Snackbar.make(v,"Unable to Sign Up. Please try again.",Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public boolean Validations(String email, String name, String vin, String engineNumber, String pwd1, String pwd2) {
        int isValidFlag = 0;
        if (email.length() < 1 || name.length() < 1 || vin.length() < 1 || engineNumber.length() < 1 || pwd1.length() < 1 || pwd2.length() < 1)
        {
            Toast.makeText(getApplicationContext(),"Fill out all the fields",Toast.LENGTH_LONG).show();
            isValidFlag = isValidFlag + 1;
        }
        if (!pwd1.equals(pwd2)) {
            Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_LONG).show();
            isValidFlag = isValidFlag + 1;
        }

        if (isValidFlag > 0)
        {
            return false;
        }
        return true;
    }

}