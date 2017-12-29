package com.kalbe.mobiledevknlibs.Toast;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by aan.junianto on 28/12/2017.
 */

public class ToastCustom extends Activity{
    public static void showToastStatic(Context ctx, String str) {
        Toast toast = Toast.makeText(ctx, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 25, 400);
        toast.show();
    }
}
