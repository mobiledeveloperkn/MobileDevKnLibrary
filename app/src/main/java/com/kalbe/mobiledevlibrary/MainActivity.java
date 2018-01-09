package com.kalbe.mobiledevlibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.kalbe.mobiledevknlibs.AlertDialog.CustomDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsEnumDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickFile;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImage;
import com.kalbe.mobiledevknlibs.PickImageAndFile.UriData;
import com.kalbe.mobiledevknlibs.Spinner.SpinnerCustom;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    TextView tvFile;
    String txtPathUserData;
    Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
//        ToastCustom.showToasty(getApplicationContext(), "tes");
        Spinner spn = (Spinner) findViewById(R.id.tesSpinner);
        img = (ImageView) findViewById(R.id.tesImg);
        Button btn = (Button) findViewById(R.id.btntes);
        Button btnFile = (Button)findViewById(R.id.btnFile);
        tvFile = (TextView) findViewById(R.id.tvFile);
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickFile.intentPickFile(MainActivity.this, 500);
            }
        });
        final EditText date = (EditText) findViewById(R.id.txtDate);
        
//        clsDatePicker.showDatePicker(MainActivity.this, date);

//        CustomDatePicker.showHint(date, clsEnumDatePicker.standard2);
        clsDatePicker.showHint(date, clsEnumDatePicker.standard0);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CustomDatePicker.showDatePicker(MainActivity.this, date, "Set Date", 0);
                clsDatePicker.showDatePicker(MainActivity.this, date, clsEnumDatePicker.standard0);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file = new File(txtPathUserData, "tes" );
//                byte[] tes = IntentCustom.getByteImageToSave(getApplicationContext(), uri);
                byte[] tesFile = null;
                try {
                     tesFile = PickFile.getByteArrayFileToSave(uri, getApplicationContext());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ToastCustom.showToastDefault(getApplicationContext(), String.valueOf(tesFile));
//                TesFragment fragmentDownloadData = new TesFragment();
//                FragmentTransaction fragmentTransactionDownloadData = getSupportFragmentManager().beginTransaction();
//                fragmentTransactionDownloadData.replace(R.id.frame, fragmentDownloadData);
//                fragmentTransactionDownloadData.commit();
            }
        });

         txtPathUserData= Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator;

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage.CaptureImage(MainActivity.this, txtPathUserData, "tes", 400);
            }
        });



        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");
        SpinnerCustom.setAdapterSpinner(spn, getApplicationContext(), R.layout.custom_spinner, categories);
//        spn.setAdapter(new SpinnerCustom.MySpinnerAdapter(getApplicationContext(), R.layout.custom_spinner, categories));
        SpinnerCustom.selectedItemByText(getApplicationContext(), spn, categories, "Travel");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 400) {
            if (resultCode == -1) {
                try {
                    uri = UriData.getOutputMediaFileUri(getApplicationContext(),txtPathUserData, "tes" );
                    Bitmap bitmap = PickImage.decodeStreamReturnBitmap(getApplicationContext(), uri);
                    PickImage.previewCapturedImage(img, bitmap, 150, 150);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 500){
            if (resultCode == -1){
                try {
                    String tes = PickFile.getFileName(getApplicationContext(), resultCode, data);
                    uri = UriData.getOutputMediaFileUri(txtPathUserData, tes );
                    PickFile.moveFileToSpecificUri(getApplicationContext(),data, uri);
                    tvFile.setText(tes);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
