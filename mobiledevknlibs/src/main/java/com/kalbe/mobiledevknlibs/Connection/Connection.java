package com.kalbe.mobiledevknlibs.Connection;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.CoordinatorLayout;

/**
 * Created by Dewi Oktaviani on 2/15/2018.
 */

public class Connection {
    public static NetworkInfo.State checkConnectionMobile(ConnectivityManager connectivityManager){
        //mobile
        NetworkInfo.State mobile = connectivityManager.getNetworkInfo(0).getState();
        return mobile;
    }
    public static NetworkInfo.State checkConnectionWifi(ConnectivityManager connectivityManager){
        //wifi
        NetworkInfo.State wifi = connectivityManager.getNetworkInfo(1).getState();

        return wifi;
    }
}
