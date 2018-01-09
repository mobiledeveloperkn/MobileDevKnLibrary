package com.kalbe.mobiledevknlibs.ErrorReporting;

/**
 * Created by Dewi Oktaviani on 1/9/2018.
 */

public class ClsPathAcra {
    private static String paths;
    public static void pathAcra (String path){
        paths = path;
    }
    public static String finalPath(){
        return paths;
    }
}
