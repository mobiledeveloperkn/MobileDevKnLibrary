package com.kalbe.mobiledevlibrary.activityTesting;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.kalbe.mobiledevlibrary.R;
import com.wooplr.spotlight.utils.SpotlightSequence;

public class TourGuideActivity extends AppCompatActivity {

    private static final String INTRO_REPEAT = "fab_intro";
    private static final String INTRO_SWITCH = "switch_intro";
    private static final String INTRO_RESET = "reset_intro";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide);
        final Button button1 = (Button)findViewById(R.id.btn_tg1);
        final Button button2 = (Button)findViewById(R.id.btn_tg2);
        final Button button3 = (Button)findViewById(R.id.btn_tg3);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                SpotlightSequence.getInstance(TourGuideActivity.this,null)
                        .addSpotlight(button1, "Hello", "Click to Create Greeting", INTRO_SWITCH)
                        .addSpotlight(button2, "Welcome ", "Click here to Add Greating", INTRO_RESET)
                        .addSpotlight(button3, "My Friend", "Click here to Add Friend", INTRO_REPEAT)
                        .startSequence();
            }
        },400);
    }
}
