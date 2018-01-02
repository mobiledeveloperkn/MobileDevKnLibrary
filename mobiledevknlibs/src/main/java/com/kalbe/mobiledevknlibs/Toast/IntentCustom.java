package com.kalbe.mobiledevknlibs.Toast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dewi Oktaviani on 1/2/2018.
 */

public class IntentCustom {
    public static void intentViewFragment(Context context, Class<?> cls, String keyPutExtra, String value) {
        Intent myIntent = new Intent(context, cls);
        myIntent.putExtra(keyPutExtra, value);
        Activity activity = (Activity) context;
        activity.finish();
        context.startActivity(myIntent);
    }
}
