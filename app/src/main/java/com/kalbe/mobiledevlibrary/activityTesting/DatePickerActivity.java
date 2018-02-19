package com.kalbe.mobiledevlibrary.activityTesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.kalbe.mobiledevknlibs.AlertDialog.CustomDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;
import com.kalbe.mobiledevknlibs.DrawableListener.DrawableClickListener;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevlibrary.R;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {

    EditText editText, editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        editText = (EditText)findViewById(R.id.txtDate);
        editText1 = (EditText) findViewById(R.id.txtDate2);

        //set bundle
        Calendar c = Calendar.getInstance();
        final Bundle args = new Bundle();
        args.putInt(CustomDatePicker.YEAR, c.get(Calendar.YEAR));
        args.putInt(CustomDatePicker.MONTH, 0);
        args.putInt(CustomDatePicker.DAY_OF_MONTH, 1);
        //ini untuk set max date, bisa pakek calender bisa juga di set dgn yang lain
        c.add(Calendar.YEAR, 2);
        args.putLong(CustomDatePicker.DATE_MAX, c.getTimeInMillis());
//                args.putLong(CustomDatePicker.DATE_MAX, System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        //ini untuk set min date, bisa pakek calender bisa juga di set dgn yang lain
        c.add(Calendar.YEAR, -4);
        args.putLong(CustomDatePicker.DATE_MIN, c.getTimeInMillis());

        //set hint for date
        CustomDatePicker.showHint(editText, args, CustomDatePicker.format.standard0);


        editText.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(editText) {
            @Override
            public boolean onDrawableClick() {
                CustomDatePicker.showDatePicker(DatePickerActivity.this, editText, "Date of Birth", CustomDatePicker.format.standard0, args);
                return false;
            }
        });

        //set bundle for hint
        final  Bundle argg = new Bundle();
        argg.putInt(clsDatePicker.YEAR, 2018);
        argg.putInt(clsDatePicker.MONTH, 0);
        argg.putInt(clsDatePicker.DAY_OF_MONTH, 1);
        //set hint date
        clsDatePicker.showHint(editText1, argg, clsDatePicker.format.standard2);


        //set bundle for date picker if we want to have different setting between hint and datepicker
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 0, 1);
        final  Bundle arg = new Bundle();
        arg.putInt(clsDatePicker.YEAR, 2000);
        arg.putInt(clsDatePicker.MONTH, 0);
        arg.putInt(clsDatePicker.DAY_OF_MONTH, 1);
        cal.add(Calendar.YEAR, 2);//maximum 2 tahun dari tahun yang di set
        arg.putLong(clsDatePicker.DATE_MAX, cal.getTimeInMillis());
//        args.putLong(clsDatePicker.DATE_MIN, c.getTimeInMillis());
        editText1.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(editText1) {
            @Override
            public boolean onDrawableClick() {
                clsDatePicker.showDatePicker(DatePickerActivity.this, editText1, "Select Date", arg, clsDatePicker.format.standard2, clsDatePicker.style.Theme_DeviceDefault_Dialog);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        IntentCustom.intentBackToFront(this);
//        com.kalbe.mobiledevknlibs.AlertDialog.AlertDialog.alertDialogExit(DatePickerActivity.this);

    }
}
