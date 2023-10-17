package com.tusharselvakumar.b99soundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {

    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();

        Button absolutelyBtn = (Button) findViewById(R.id.button);
        Button ahaBtn = (Button) findViewById(R.id.button2);
        Button coolBtn = (Button) findViewById(R.id.button3);
        Button damnBtn = (Button) findViewById(R.id.button4);
        Button huhBtn = (Button) findViewById(R.id.button5);
        Button noBtn = (Button) findViewById(R.id.button6);
        Button laughBtn = (Button) findViewById(R.id.button7);
        Button whatBtn = (Button) findViewById(R.id.button8);
        Button whyBtn = (Button) findViewById(R.id.button9);

        absolutelyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.absolutely);
            }
        });

        ahaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.aha);
            }
        });

        coolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.cool);
            }
        });

        damnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.damn);
            }
        });

        huhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.huh);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.no);
            }
        });

        laughBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.laugh);
            }
        });

        whatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.what);
            }
        });

        whyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.why);
            }
        });
    }

    private void playSound(int Sound) {
        mediaPlayer = MediaPlayer.create(this, Sound);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
    }
}