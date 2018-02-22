package com.kalbe.mobiledevlibrary.activityTesting;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.Maps.PopUpMaps;
import com.kalbe.mobiledevknlibs.PermissionChecker.PermissionMaps;
import com.kalbe.mobiledevlibrary.MainActivity;
import com.kalbe.mobiledevlibrary.R;

public class MapsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button button = (Button)findViewById(R.id.btnMaps);

        Button button2 = (Button)findViewById(R.id.btnMaps2); 
        //ini buat mengaktifkan gps secara otomatis
        new PermissionMaps().locationActivation(MapsActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PopUpMaps().popUpMapsTwoCoordinates(MapsActivity.this, R.layout.popup_map, "-6.300641", "106.814095");

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
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
