package com.tusharselvakumar.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    private int[] nos;
    private int n;
    private String op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] calculation = {""};
        final int[] final_answer = {0};
        TextView resultText = findViewById(R.id.textView);

        Button acBtn = (Button) findViewById(R.id.acBtn);
        Button modulusBtn = (Button) findViewById(R.id.modulusBtn);
        Button divideBtn = (Button) findViewById(R.id.divideBtn);
        Button multiplyBtn = (Button) findViewById(R.id.multiplyBtn);
        Button minusBtn = (Button) findViewById(R.id.minusBtn);
        Button additionBtn = (Button) findViewById(R.id.additionBtn);
        Button equalBtn = (Button) findViewById(R.id.equalBtn);
        Button backspaceBtn = (Button) findViewById(R.id.backspaceBtn);
        
        Button dotBtn = (Button) findViewById(R.id.dotBtn);
        Button oneBtn = (Button) findViewById(R.id.oneBtn);
        Button twoBtn = (Button) findViewById(R.id.twoBtn);
        Button threeBtn = (Button) findViewById(R.id.threeBtn);
        Button fourBtn = (Button) findViewById(R.id.fourBtn);
        Button fiveBtn = (Button) findViewById(R.id.fiveBtn);
        Button sixBtn = (Button) findViewById(R.id.sixBtn);
        Button sevenBtn = (Button) findViewById(R.id.sevenBtn);
        Button eightBtn = (Button) findViewById(R.id.eightBtn);
        Button nineBtn = (Button) findViewById(R.id.nineBtn);

        acBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = "";
                final_answer[0] = 0;
                setResultText(resultText,calculation[0]);
            }
        });

        modulusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "%";
                setResultText(resultText,calculation[0]);
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "/";
                setResultText(resultText,calculation[0]);
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "*";
                setResultText(resultText,calculation[0]);
            }
        });

        additionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "+";
                setResultText(resultText,calculation[0]);
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "-";
                setResultText(resultText,calculation[0]);
            }
        });

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "1";
                setResultText(resultText,calculation[0]);
            }
        });

        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "2";
                setResultText(resultText,calculation[0]);
            }
        });

        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "3";
                setResultText(resultText,calculation[0]);
            }
        });

        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "4";
                setResultText(resultText,calculation[0]);
            }
        });

        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "5";
                setResultText(resultText,calculation[0]);
            }
        });

        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "6";
                setResultText(resultText,calculation[0]);
            }
        });

        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "7";
                setResultText(resultText,calculation[0]);
            }
        });

        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "8";
                setResultText(resultText,calculation[0]);
            }
        });

        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + "9";
                setResultText(resultText,calculation[0]);
            }
        });

        dotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = calculation[0] + ".";
                setResultText(resultText,calculation[0]);
            }
        });

        backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculation[0] = backspaceBtn(calculation[0]);
                setResultText(resultText,calculation[0]);
            }
        });

        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalsBtn(calculation);
            }
        });
    }

    public String backspaceBtn(String calculation) {
        calculation = calculation.substring(0,calculation.length()-1);
        return calculation;
    }

    public void setResultText(TextView resultText, String textToSet) {
        String displayText = "";

        for (int i = 0; i < textToSet.length(); i++) {
            char ex = textToSet.charAt(i);
            String currentItem = String.valueOf(ex);
            if (currentItem.equals("_")) {
                displayText = displayText + "";
            }

            else {
                displayText = displayText + textToSet.charAt(i);
            }
        }
        resultText.setText(displayText);
    }

    public void equalsBtn(String[] calculation) {
        String[] values = new String[100];
        String[] operators = new String[100];
        String num = values[0] + operators[0] + values[1] + calculation[0];
        Integer cur_num = 0;
        Integer cur_op = 0;

        for(int x = 0; x<calculation[0].length(); x++) {
            if (calculation[0].charAt(x) != '+' || calculation[0].charAt(x) != '-' || calculation[0].charAt(x) != '*' || calculation[0].charAt(x) != '/') {
                values[cur_num] = values[cur_num] + calculation[0].charAt(x);
            }
            if (calculation[0].charAt(x) != '+'){
                cur_num += 1;
                operators[cur_op] = "+";
                cur_op+=1;
            }
        }

        Toast.makeText(getApplicationContext(),num, Toast.LENGTH_SHORT).show();
    }

    public Integer getNumAtChar(Character num) {
        Integer val = null;
        if (num.equals("1")){
            val = 1;
        } else if (num.equals("2")){
            val = 2;
        } else if (num.equals("3")){
            val = 3;
        } else if (num.equals("4")){
            val = 4;
        } else if (num.equals("5")){
            val = 5;
        } else if (num.equals("6")){
            val = 6;
        } else if (num.equals("7")){
            val = 7;
        } else if (num.equals("8")){
            val = 8;
        } else if (num.equals("9")){
            val = 9;
        } else if (num.equals("0")){
            val = 0;
        }
        return val;
    }
}