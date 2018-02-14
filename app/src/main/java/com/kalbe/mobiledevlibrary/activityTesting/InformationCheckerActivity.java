package com.kalbe.mobiledevlibrary.activityTesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kalbe.mobiledevknlibs.PermissionChecker.IntPermissionChecker;
import com.kalbe.mobiledevknlibs.PermissionChecker.PermissionChecker;
import com.kalbe.mobiledevknlibs.PermissionChecker.enumPermissionChecker;
import com.kalbe.mobiledevlibrary.R;

public class InformationCheckerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_checker);
        //untuk mendapatkan semua permission (muncul alert dialog)
//        PermissionChecker.getpermissionSPGAll(this);

        //untuk mendapatkan permission namun satu persatu sesuai dengan enum yang di inginkan
        PermissionChecker.getPermission(this, IntPermissionChecker.CALENDAR);

        //method utility untuk secara manual minta user memberikan akses read and write storage
        PermissionChecker.Utility.checkPermission(InformationCheckerActivity.this);
    }
}
