package com.kalbe.mobiledevknlibs.Intent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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

import com.kalbe.mobiledevknlibs.PDFView.PDFViewer;
import com.kalbe.mobiledevknlibs.PermissionChecker.PermissionChecker;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;

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

    public static void intentToActivity(Context context, Class<?> cls, String keyPutExtra, String[] value) {
        Intent myIntent = new Intent(context, cls);
        myIntent.putExtra(keyPutExtra, value);
        Activity activity = (Activity) context;
        activity.finish();
        context.startActivity(myIntent);
    }

    public static void intentPDViewer(Context context, Uri uri, Boolean swipeHorizontal){
        boolean result= PermissionChecker.Utility.checkPermission(context);
        if (result){
            Intent intent =  new Intent(context, PDFViewer.class);
            intent.putExtra("pdf", uri);
            intent.putExtra("swipeHorizontal", swipeHorizontal);
            ((Activity)context).finish();
            context.startActivity(intent);
        }
    }

    public static void intentActionView(Context context, Uri uri, String typeData){
        boolean result= PermissionChecker.Utility.checkPermission(context);
        if (result){
            try {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, typeData);
                ((Activity)context).finish();
                context.startActivity(intent);
            }catch (ActivityNotFoundException e){
                ToastCustom.showToasty(context, "You haven't app for open this file", 4);
            }
        }
    }
}
