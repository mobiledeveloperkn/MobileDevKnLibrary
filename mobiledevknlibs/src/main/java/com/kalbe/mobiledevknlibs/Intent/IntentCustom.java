package com.kalbe.mobiledevknlibs.Intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
