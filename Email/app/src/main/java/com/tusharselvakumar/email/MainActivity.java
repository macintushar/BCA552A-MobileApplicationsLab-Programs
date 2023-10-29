package com.tusharselvakumar.email;

import androidx.appcompat.app.AppCompatActivity;

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

        Button send = (Button) findViewById(R.id.button);
        EditText emailIDto = (EditText) findViewById(R.id.toEmail);
        EditText emailIDcc = (EditText) findViewById(R.id.ccEmail);
        EditText emailIDbcc = (EditText) findViewById(R.id.bccEmail);
        EditText emailSubject = (EditText) findViewById(R.id.emailSubject);
        EditText emailMessage = (EditText) findViewById(R.id.emailBody);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTo = emailIDto.getText().toString();
                String emailCC = emailIDcc.getText().toString();
                String emailBCC = emailIDbcc.getText().toString();
                String subject = emailSubject.getText().toString();
                String message = emailMessage.getText().toString();

                try {
                    EmailSender emailSender = new EmailSender("EMAIL", "PASSWORD", emailTo, emailCC, emailBCC, subject, message);
                    emailSender.execute();

                    Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_SHORT).show();

                    emailIDto.setText("");
                    emailIDcc.setText("");
                    emailIDbcc.setText("");

                    emailSubject.setText("");
                    emailMessage.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}