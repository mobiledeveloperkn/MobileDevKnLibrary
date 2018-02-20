package com.kalbe.mobiledevlibrary.activityTesting;

import android.graphics.Color;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.kalbe.mobiledevknlibs.DeviceInformation.DeviceInformation;
import com.kalbe.mobiledevknlibs.DeviceInformation.ModelDevice;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.ListView.AppAdapter;
import com.kalbe.mobiledevknlibs.ListView.CardAppAdapter;
import com.kalbe.mobiledevknlibs.ListView.ListViewCustom;
import com.kalbe.mobiledevlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class InfoDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_device);

        final List<String> contentLibs = new ArrayList<>();
        ModelDevice modelDevice = DeviceInformation.getDeviceInformation();

        contentLibs.add("SDK" + modelDevice.getVersionSDK());
        contentLibs.add(modelDevice.getProduct());
        contentLibs.add(modelDevice.getOsVersion());
        contentLibs.add(modelDevice.getModel());
        contentLibs.add(modelDevice.getDevice());



        contentLibs.add("SENSOR");

        List<Sensor> sensors = new DeviceInformation().getSensorInformation(this);
        if (sensors!=null&&sensors.size()>0){
            for (Sensor sensor : sensors){
                contentLibs.add(sensor.getVendor());
                contentLibs.add(sensor.getName());
                contentLibs.add(String.valueOf(sensor.getVersion()));
                contentLibs.add(String.valueOf(sensor.getPower()));
                contentLibs.add(String.valueOf(sensor.getType()));

            }
        }

        ListView listView = (ListView)findViewById(R.id.lv_infoDevice);
        listView.setAdapter(new AppAdapter(getApplicationContext(), contentLibs));
        ListViewCustom.setListViewHeightBasedOnItems(listView);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        IntentCustom.intentBackToFront(this);
    }
}
