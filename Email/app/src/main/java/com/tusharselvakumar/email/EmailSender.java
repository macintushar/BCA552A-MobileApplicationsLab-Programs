package com.tusharselvakumar.email;
import android.os.AsyncTask;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender extends AsyncTask<Void, Void, Boolean> {

    private String username;
    private String password;
    private String emailID;
    private String emailBody;
    private String emailSubject;

    public EmailSender(String username, String password, String emailAddress, String emailSubject, String emailBody) {
        this.username = username;
        this.password = password;
        this.emailID = emailAddress;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailID));
            message.setSubject(emailSubject);
            message.setText(emailBody);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
