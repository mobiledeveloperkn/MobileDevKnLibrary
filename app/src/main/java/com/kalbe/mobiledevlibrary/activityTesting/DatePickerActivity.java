package com.kalbe.mobiledevlibrary.activityTesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.kalbe.mobiledevknlibs.AlertDialog.CustomDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsEnumDatePicker;
import com.kalbe.mobiledevknlibs.DrawableListener.DrawableClickListener;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevlibrary.R;

public class DatePickerActivity extends AppCompatActivity {

    EditText editText, editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        editText = (EditText)findViewById(R.id.txtDate);
        editText1 = (EditText) findViewById(R.id.txtDate2);
        CustomDatePicker.showHint(editText, clsEnumDatePicker.standard0);
        editText.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(editText) {
            @Override
            public boolean onDrawableClick() {
                CustomDatePicker.showDatePicker(DatePickerActivity.this, editText, "Date of Birth", clsEnumDatePicker.standard0);
                return false;
            }
        });

        clsDatePicker.showHint(editText1, clsEnumDatePicker.standard2);
        editText1.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(editText1) {
            @Override
            public boolean onDrawableClick() {
                clsDatePicker.showDatePicker(DatePickerActivity.this, editText1, clsEnumDatePicker.standard2);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        IntentCustom.intentBackToFront(this);
//        com.kalbe.mobiledevknlibs.AlertDialog.AlertDialog.alertDialogExit(this);

    }
}
