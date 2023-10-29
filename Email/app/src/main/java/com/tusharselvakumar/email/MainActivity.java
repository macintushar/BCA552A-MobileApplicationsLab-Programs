package com.tusharselvakumar.email;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import io.github.cdimascio.dotenv.Dotenv;


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
                try {
                    EmailSender emailSender = new EmailSender("[EMAIL]", "[APP_PASSWORD]", emails, subject, message);
                    emailSender.execute();

                    Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_SHORT).show();

                    emailID.setText("");
                    emailSubject.setText("");
                    emailMessage.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}