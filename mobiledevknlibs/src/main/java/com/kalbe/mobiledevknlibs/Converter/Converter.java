package com.kalbe.mobiledevknlibs.Converter;


import android.content.Context;
import android.support.v4.app.Fragment;

import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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


    public static Fragment FragmentStringToClas(Context context, String name){
        Fragment fragment = null;
        try {
            Class<?> fragmentClass = Class.forName(context.getPackageName() + "." +name);
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return fragment;
    }
    public static JSONArray ResultJsonArray(String dt) throws ParseException{
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(dt);
        JSONArray lang= (JSONArray) obj;
        return lang;
    }
}
