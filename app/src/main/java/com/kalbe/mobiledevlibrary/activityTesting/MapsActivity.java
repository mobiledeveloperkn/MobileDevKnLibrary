package com.kalbe.mobiledevlibrary.activityTesting;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.Maps.PopUpMaps;
import com.kalbe.mobiledevlibrary.R;

public class MapsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button button = (Button)findViewById(R.id.btnMaps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PopUpMaps().popUpMaps(MapsActivity.this, R.layout.popup_map);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        IntentCustom.intentBackToFront(this);
    }
}
