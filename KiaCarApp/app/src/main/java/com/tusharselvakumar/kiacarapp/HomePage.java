package com.tusharselvakumar.kiacarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomePage extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String value = sharedPreferences.getString("userEmail", "en");
//        Toast t = Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT);
//        t.show();

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Hello, " + value);

        WebView webView = (WebView) findViewById(R.id.viewCarWebsite);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.kia.com/in/our-vehicles/seltos/showroom.html");

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> showPopupMenu(view));


        ImageButton imgBtn = (ImageButton) findViewById(R.id.imgButton);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openVideo = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=boYtpG5Ifg8"));
                startActivity(openVideo);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.fab_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String itemValue = item.toString();

                switch (itemValue) {
                    case "Book A Service":
                        Snackbar.make(view, itemValue, Snackbar.LENGTH_LONG).show();
                        Intent bookService = new Intent(view.getContext(), NewService.class);
                        startActivity(bookService);
                        return true;

                    case "View Service History":
                        Snackbar.make(view, "Viewing Service History", Snackbar.LENGTH_LONG).show();
                        //Intent viewService;
                        return true;

                    case "View Vehicle Details":
                        Snackbar.make(view, "Viewing Vehicle Details", Snackbar.LENGTH_LONG).show();
                        //Intent viewVehicle;
                        return true;

                    case "Logout":
                        Snackbar.make(view, itemValue, Snackbar.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Logging Out", Toast.LENGTH_SHORT).show();
                        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.remove("userEmail");
                        editor.apply();
                        Intent logout = new Intent(view.getContext(), Login.class);
                        startActivity(logout);
                        return true;

                    case "Rate Us":
                        Intent rateApp = new Intent(view.getContext(), RatePage.class);
                        startActivity(rateApp);
                        return true;

                    case "Quiz":
                        Intent quizPage = new Intent(view.getContext(), Quiz.class);
                        startActivity(quizPage);
                        return true;

                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

}