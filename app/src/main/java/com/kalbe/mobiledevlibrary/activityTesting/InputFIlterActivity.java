package com.kalbe.mobiledevlibrary.activityTesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.kalbe.mobiledevknlibs.InputFilter.InputFilters;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevlibrary.R;

public class InputFIlterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_filter);

        EditText editText = (EditText)findViewById(R.id.txtFilter1);
        TextView textView = (TextView)findViewById(R.id.textFilter1);
        textView.setText("hanya boleh letter dan digit ex.sp.char dan length di batasi 5 char");
        new InputFilters().etTextWatcher(editText, 5);

        EditText editText1 = (EditText)findViewById(R.id.txtFilter2);
        TextView textView1 = (TextView)findViewById(R.id.textFilter2);
        textView1.setText("hanya boleh Caps letter dan digit ex.sp.char dan length di batasi 5 char");
        new InputFilters().etCapsTextWatcher(editText1, 5);

        EditText editText2 = (EditText)findViewById(R.id.txtFilter3);
        TextView textView2 = (TextView)findViewById(R.id.textFilter3);
        textView2.setText("hanya boleh letter dan digit inc.sp.char dan length di batasi 5 char");
        char[] chars = {',','.'};
        new InputFilters().etTextWatcherSPChar(editText2, 5, chars);

        EditText editText3 = (EditText)findViewById(R.id.txtFilter4);
        TextView textView3 = (TextView)findViewById(R.id.textFilter4);
        textView3.setText("hanya boleh caps letter dan digit inc.sp.char dan length di batasi 5 char");
        new InputFilters().etCapsTextWatcherSPChar(editText3, 5, chars);

        EditText editText4 = (EditText)findViewById(R.id.txtFilter5);
        TextView textView4 = (TextView)findViewById(R.id.textFilter5);
        textView4.setText("hanya boleh letter dan digit inc.sp.char dan length di batasi 5 char and no space");
        new InputFilters().etCapsTextWatcherNoSpace(editText4, 5, chars);

        EditText editText5 = (EditText)findViewById(R.id.txtFilter6);
        TextView textView5 = (TextView)findViewById(R.id.textFilter6);
        textView5.setText("hanya boleh letter dan digit inc.sp.char dan length di batasi 5 char and no space at first char");
        new InputFilters().etTextWatcherNoSpaceAtFirst(editText5, 5, chars);

        EditText editText6 = (EditText)findViewById(R.id.txtFilter7);
        TextView textView6 = (TextView)findViewById(R.id.textFilter7);
        textView6.setText("hanya boleh caps letter dan digit inc.sp.char dan length di batasi 5 char and no space at first char");
        new InputFilters().etCapsTextWatcherNoSpaceAtFirst(editText6, 5, chars);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        new IntentCustom().intentBackToFront(this);
    }
}
