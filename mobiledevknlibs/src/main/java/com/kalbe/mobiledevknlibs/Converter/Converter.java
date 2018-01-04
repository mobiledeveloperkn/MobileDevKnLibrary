package com.kalbe.mobiledevknlibs.Converter;

/**
 * Created by Robert on 03/01/2018.
 */

public class Converter {
    public static double StringToGeotagging(String txtGeo){
        if (txtGeo != null && !txtGeo.equals("")){
            return Double.parseDouble(txtGeo);
        }else{
            return 0;
        }
    }
//    public static int
}
