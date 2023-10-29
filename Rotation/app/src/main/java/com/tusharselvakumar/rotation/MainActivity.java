package com.tusharselvakumar.rotation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public class Orientation extends OrientationEventListener {
        private Context context;

        public Orientation(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public void onOrientationChanged(int orientation) {
            // Check if the orientation is valid (not flat or undefined)
            if (orientation == ORIENTATION_UNKNOWN) {
                return;
            }
            // Determine the orientation description based on the angle
            String orientationText;
            if (orientation >= 45 && orientation < 135 || orientation >= 225 && orientation < 315) {
                orientationText = "Landscape";
            } else {
                orientationText = "Portrait";
            }

            // Show a toast with the orientation description
            TextView TView = (TextView) findViewById(R.id.TView);
            TView.setText(orientationText);
        }
    }
    private Orientation orientationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orientationEventListener = new Orientation(this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (orientationEventListener.canDetectOrientation()) {
            orientationEventListener.enable();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        orientationEventListener.disable();
    }

}