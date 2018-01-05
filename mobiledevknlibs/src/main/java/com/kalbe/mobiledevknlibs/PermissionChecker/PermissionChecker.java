package com.kalbe.mobiledevknlibs.PermissionChecker;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Robert on 05/01/2018.
 */

public class PermissionChecker extends Activity {
    final private static int REQUEST_CODE_ASK_PERMISSIONS = 123;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static void getpermissionSPGAll(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("You need to allow access. . .");
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        &&!ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        &&!ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.CAMERA)
                        &&!ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.READ_PHONE_STATE)){
                    ActivityCompat.requestPermissions(activity,
                            new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    dialog.dismiss();

                }
                ActivityCompat.requestPermissions(activity,
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public static void getPermission(Activity activity, int permissionId){
        if(enumPermissionChecker.CAMERA.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.CAMERA},
                    enumPermissionChecker.CAMERA.getIdPermision());
        }
        if(enumPermissionChecker.CALENDAR.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR},
                    enumPermissionChecker.CALENDAR.getIdPermision());
        }
        if(enumPermissionChecker.CONTACTS.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS},
                    enumPermissionChecker.CONTACTS.getIdPermision());
        }
        if(enumPermissionChecker.LOCATION.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                    enumPermissionChecker.CAMERA.getIdPermision());
        }
        if(enumPermissionChecker.PHONE.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_PHONE_NUMBERS,Manifest.permission.ANSWER_PHONE_CALLS},
                    enumPermissionChecker.PHONE.getIdPermision());
        }
        if(enumPermissionChecker.MICROPHONE.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.RECORD_AUDIO},
                    enumPermissionChecker.MICROPHONE.getIdPermision());
        }
        if(enumPermissionChecker.SENSORS.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.BODY_SENSORS},
                    enumPermissionChecker.SENSORS.getIdPermision());
        }
        if(enumPermissionChecker.SMS.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.SEND_SMS,Manifest.permission.BROADCAST_SMS,Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS},
                    enumPermissionChecker.SMS.getIdPermision());
        }
        if(enumPermissionChecker.STORAGE.getIdPermision() == permissionId){
            ActivityCompat.requestPermissions(activity,
                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    enumPermissionChecker.STORAGE.getIdPermision());
        }

    }
}
