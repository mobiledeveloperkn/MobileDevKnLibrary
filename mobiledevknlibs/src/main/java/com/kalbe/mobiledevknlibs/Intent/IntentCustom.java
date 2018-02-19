package com.kalbe.mobiledevknlibs.Intent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NavUtils;

import com.kalbe.mobiledevknlibs.PDFView.PDFViewer;
import com.kalbe.mobiledevknlibs.PermissionChecker.PermissionChecker;
import com.kalbe.mobiledevknlibs.ToastAndSnackBar.ToastCustom;

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

    public static void intentActionView(Context context, Uri uri, String typeData, Boolean backToFront){
        boolean result= PermissionChecker.Utility.checkPermission(context);
        if (result){
            try {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                intent.setDataAndType(uri, typeData);
                ((Activity)context).finish();
                context.startActivity(intent);
            }catch (ActivityNotFoundException e){
                if (backToFront){
                    IntentCustom.intentBackToFront(context);
                }
                ToastCustom.showToasty(context, "You haven't app for open this file", 3);
            }
        }
    }

    public static void intentBackToFront(Context context){
        Intent parentIntent = NavUtils.getParentActivityIntent((Activity) context);
        parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(parentIntent);
        ((Activity)context).finish();
    }
}
