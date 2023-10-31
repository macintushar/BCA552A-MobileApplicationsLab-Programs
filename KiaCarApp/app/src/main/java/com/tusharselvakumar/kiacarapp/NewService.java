package com.tusharselvakumar.kiacarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NewService extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);

        CalendarView serviceDate = findViewById(R.id.calendarView);
        Spinner serviceCenterOpt = findViewById(R.id.serviceCenterSelector);
        Button submitBtn = findViewById(R.id.submitButton);
        RadioGroup serviceTypeGroup = findViewById(R.id.radioGroup);
        ToggleButton waterWashToggle = findViewById(R.id.waterWashToggle);
        final String[] serviceCenterName = {""};
        final String[] selectedDate = {""};
        final String[] waterWashConfirmation = {""};

        serviceDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate[0] = dayOfMonth + "-" + (month + 1) + "-" + year;
            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Yelahanka");
        arrayList.add("Lalbagh");
        arrayList.add("Hennur");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        serviceCenterOpt.setAdapter(arrayAdapter);
        serviceCenterOpt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                serviceCenterName[0] = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        waterWashToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    waterWashConfirmation[0] = "with Water Wash";
                } else {
                    waterWashConfirmation[0] = "without Water Wash";
                }
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String selectedServiceType =((RadioButton)findViewById(serviceTypeGroup.getCheckedRadioButtonId())).getText().toString();
                String confirmationMsg = "You have selected \nService Center: " + serviceCenterName[0] + "\nfor a " + selectedServiceType + "\non: " + selectedDate[0] + "\n" + waterWashConfirmation[0];

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NewService.this);
                alertDialogBuilder.setTitle("Confirm Service?");
                alertDialogBuilder.setMessage(confirmationMsg);
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(v,"Service Booked",Snackbar.LENGTH_LONG).show();

                        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        String userEmail = sharedPreferences.getString("userEmail", "en");

                        String emailTo = userEmail;
                        String subject = "Kia Service | Service Scheduled on " + selectedDate[0];
                        String msg = "You have scheduled a service at " + serviceCenterName[0] + " for a " + selectedServiceType + " on " + selectedDate[0] + " " + waterWashConfirmation[0];

                        try {
                            EmailSender emailSender = new EmailSender("emailbycode@gmail.com", "edmfcwnnsabnpbap", emailTo, subject, msg);
                            emailSender.execute();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
                        }

                        Intent serviceConfirm = new Intent(NewService.this, ServiceConfirmSplash.class);
                        startActivity(serviceConfirm);
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(v,"Service not Booked",Snackbar.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
