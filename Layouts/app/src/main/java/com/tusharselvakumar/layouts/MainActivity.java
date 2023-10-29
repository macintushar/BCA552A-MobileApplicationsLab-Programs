package com.tusharselvakumar.layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout bg_lyt = (ConstraintLayout) findViewById(R.id.bg);
        Switch darkMode = (Switch) findViewById(R.id.switch1);
        Button submit = (Button) findViewById(R.id.submit);

        RadioButton good = (RadioButton) findViewById(R.id.good);
        RadioButton great = (RadioButton) findViewById(R.id.great);
        RadioButton okay = (RadioButton) findViewById(R.id.okay);
        RadioButton bad = (RadioButton) findViewById(R.id.bad);

        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( darkMode.isChecked() ){
                    bg_lyt.setBackgroundColor(Color.GRAY);
                }
                else
                {
                    bg_lyt.setBackgroundColor(Color.WHITE);
                }

            }

        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                    RadioGroup rg = (RadioGroup) findViewById(R.id.feelings);
                    String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                    Toast t = Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                    t.show();
            }
        });

    }
}