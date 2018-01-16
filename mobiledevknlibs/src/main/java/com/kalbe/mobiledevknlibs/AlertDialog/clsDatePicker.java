package com.kalbe.mobiledevknlibs.AlertDialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.kalbe.mobiledevknlibs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Robert on 05/01/2018.
 */

public class clsDatePicker {
    private static Calendar calendar;
    private static EditText dateView;
    private static int year,month, day;
    private static DatePickerDialog dialog;
    private static int frm;
    public static void showHint(EditText editText, int format){
        calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        editText.setHint(formatSimpleDate(calendar.getTime(), format));
    }
    public static void showDatePicker(final Context context, EditText editText, int format) {
        frm = format;
        dateView = editText;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //tambah style baru
                    dialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Dialog, myDateListener, year, month, day);
                } else {
                    dialog = new DatePickerDialog(context,  myDateListener, year, month, day);
                }
                dialog.setTitle("Select Date");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
    }

    private static DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    year= arg1;
                    month = arg2;
                    day = arg3;
                    displayDate(dateView, frm);
                    dateView.setEnabled(true);
                }
            };
    private static void displayDate(EditText editText, int format){
        GregorianCalendar c = new GregorianCalendar(year, month, day);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        editText.setText(simpleDateFormat.format(c.getTime()));
        String date = formatSimpleDate(c.getTime(), format);
        editText.setText(date);
    }

    public static String formatSimpleDate(Date dates, int format){
        String date = "";
        SimpleDateFormat simpleDateFormat;
        switch (format){
            case 0:
                simpleDateFormat = new SimpleDateFormat("dd - MMM - yyyy");
                break;
            case 1:
                simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
                break;
            case 2:
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                break;
            case 3:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            default:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        date = simpleDateFormat.format(dates);
        return date;
    }
    private static void showDate(EditText editText, int year, int month, int day) {
        editText.setHint(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
