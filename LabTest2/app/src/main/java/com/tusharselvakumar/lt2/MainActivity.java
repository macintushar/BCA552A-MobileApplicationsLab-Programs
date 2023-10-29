package com.tusharselvakumar.lt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String depCity;
    private String arrCity = "Delhi";
    private String departDate;
    private String arriveDate;
    private String name;
    private String email;
    private String phone;
    private String rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner departureCity = (Spinner) findViewById(R.id.departureCity);
        Spinner arrivalCity = (Spinner) findViewById(R.id.departureCity);
        Switch round = findViewById(R.id.roundTrip);
        Button depDate = findViewById(R.id.dateDep);
        Button arrDate = findViewById(R.id.dateArr);
        Button rateCalc = (Button) findViewById(R.id.rateCalcBtn);
        Button book = (Button) findViewById(R.id.ticketBookBtn);
        TextView priceView = (TextView) findViewById(R.id.tripPrice);

        EditText nameIn = findViewById(R.id.fullName);
        EditText phIn = findViewById(R.id.phoneNo);
        EditText emIn = findViewById(R.id.email);

        ArrayList<String> cityList = new ArrayList<>();
        cityList.add("Bangalore");
        cityList.add("Mumbai");
        cityList.add("Delhi");
        cityList.add("Bombay");
        cityList.add("Hyderabad");
        cityList.add("Chennai");
        cityList.add("Kochi");
        cityList.add("Kolkata");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayList<String> cityList1 = new ArrayList<>();
        cityList1.add("Bangalore");
        cityList1.add("Mumbai");
        cityList1.add("Delhi");
        cityList1.add("Bombay");
        cityList1.add("Hyderabad");
        cityList1.add("Chennai");
        cityList1.add("Kochi");
        cityList1.add("Kolkata");

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityList);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        departureCity.setAdapter(arrayAdapter);
        departureCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                depCity = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        arrivalCity.setAdapter(arrayAdapter);
        arrivalCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //arrCity = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        round.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    arrDate.setVisibility(View.VISIBLE);
                } else {
                    arrDate.setVisibility(View.INVISIBLE);
                }
            }
        });

        depDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                depDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                departDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        arrDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                arrDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                arriveDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        rateCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer price;
                if (round.isChecked()) {
                    price = 7500;
                }
                else {
                    price = 4000;
                }
                priceView.setText(price.toString());
                rate = price.toString();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameIn.getText().toString();
                phone = phIn.getText().toString();
                email = emIn.getText().toString();


                Intent ac2 = new Intent(v.getContext(), MainActivity2.class);

                ac2.putExtra("name",name);
                ac2.putExtra("email", email);
                ac2.putExtra("phone", phone);
                ac2.putExtra("rate", rate);
                ac2.putExtra("depDate", departDate);
                ac2.putExtra("retDate", arriveDate);
                ac2.putExtra("depCity", depCity);
                ac2.putExtra("arrCity", arrCity);

                startActivity(ac2);
            }
        });
    }
}