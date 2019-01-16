package com.kalbe.mobiledevlibrary.activityTesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.library.badgeall.viewbadger.ShortcutBadger;
import com.kalbe.mobiledevlibrary.R;

public class NotificationReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);
        ShortcutBadger.removeCount(this);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        new IntentCustom().intentBackToFront(this);
    }
}
