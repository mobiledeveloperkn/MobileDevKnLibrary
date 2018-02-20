package com.kalbe.mobiledevlibrary.activityTesting;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kalbe.mobiledevknlibs.library.badgeall.viewbadger.ShortcutBadger;
import com.kalbe.mobiledevlibrary.R;

public class CreateNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);
        Button button = (Button)findViewById(R.id.btn_notif);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void createNotification(){
        Intent i = new Intent(getApplicationContext(), NotificationReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),(int)System.currentTimeMillis(), i, PendingIntent.FLAG_ONE_SHOT);

        int icon = R.drawable.ic_notif;
        String tickerText = "어서 오십시오";
        long when = System.currentTimeMillis();
        Notification tnotification = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setContentTitle("안녕하세요")
                .setContentText("내 가장 친한 친구")
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.mipmap.ic_launcher))
                .setWhen(when)
                .setTicker(tickerText)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setNumber(1)
                .setDefaults(Notification.DEFAULT_ALL | Notification.FLAG_SHOW_LIGHTS | Notification.PRIORITY_DEFAULT)
                .build();
        NotificationManager tnotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        tnotification.flags |= Notification.FLAG_AUTO_CANCEL;
        tnotification.defaults=Notification.DEFAULT_ALL;
        tnotificationManager.notify(0,tnotification);
        ShortcutBadger.applyCount(this, 1);
    }
}
