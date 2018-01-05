package com.kalbe.mobiledevknlibs.Converter;

/**
 * Created by Robert on 03/01/2018.
 */

public class Converter {
    public static double StringToDouble(String txtNumber){
        if (txtNumber != null && !txtNumber.equals("")){
            return Double.parseDouble(txtNumber);
        }else{
            return 0;
        }
    }
//    public static int
}
