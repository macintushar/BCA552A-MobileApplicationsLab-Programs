package com.tusharselvakumar.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button) findViewById(R.id.button);
        EditText emailID = (EditText) findViewById(R.id.emailIDs);
        EditText emailSubject = (EditText) findViewById(R.id.emailSubject);
        EditText emailMessage = (EditText) findViewById(R.id.emailBody);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = emailID.getText().toString();
                String subject = emailSubject.getText().toString();
                String message = emailMessage.getText().toString();

//                Intent intent = new Intent(Intent.ACTION_SEND);
//
//                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emails});
//                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//                intent.putExtra(Intent.EXTRA_TEXT, message);
//
//                intent.setType("message/rfc822");
//                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

                EmailSender emailSender = new EmailSender("emailbycode@gmail.com","Tusharsk911",emails,subject,message);
                emailSender.execute();

            }
        });

    }

}