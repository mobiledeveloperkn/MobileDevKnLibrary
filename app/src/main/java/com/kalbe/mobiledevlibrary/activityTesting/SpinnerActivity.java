package com.kalbe.mobiledevlibrary.activityTesting;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.kalbe.mobiledevknlibs.Spinner.SpinnerCustom;
import com.kalbe.mobiledevlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Spinner spinner = (Spinner) findViewById(R.id.spnSelectedItem);
        Spinner spinner1 = (Spinner) findViewById(R.id.spnDefault);

        List<String> categories = new ArrayList<String>();
        categories.add("--Select One--");
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");
        SpinnerCustom.setAdapterSpinner(spinner, getApplicationContext(), R.layout.custom_spinner, categories);
        spinner1.setAdapter(new SpinnerCustom.MySpinnerAdapter(getApplicationContext(), R.layout.custom_spinner, categories));
        SpinnerCustom.selectedItemByText(getApplicationContext(), spinner, categories, "Travel");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(parentIntent);
        finish();
    }
}
