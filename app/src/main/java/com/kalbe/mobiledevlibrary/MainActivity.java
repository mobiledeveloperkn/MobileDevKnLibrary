package com.kalbe.mobiledevlibrary;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;
import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsEnumDatePicker.*;
import com.kalbe.mobiledevknlibs.Converter.Converter;
import com.kalbe.mobiledevknlibs.DrawableListener.DrawableClickListener;
import com.kalbe.mobiledevknlibs.InputFilter.InputFilter;
import com.kalbe.mobiledevknlibs.InputFilter.InputFilters;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.Intent.TypeDataIntent;
import com.kalbe.mobiledevknlibs.ListView.CardAppAdapter;
import com.kalbe.mobiledevknlibs.ListView.ListViewCustom;
import com.kalbe.mobiledevknlibs.PDFView.PDFViewer;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickFile;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImage;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImageCustom;
import com.kalbe.mobiledevknlibs.PickImageAndFile.UriData;
import com.kalbe.mobiledevknlibs.Spinner.SpinnerCustom;
import com.kalbe.mobiledevknlibs.Table.Table;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;
import com.kalbe.mobiledevlibrary.activityTesting.DatePickerActivity;
import com.kalbe.mobiledevlibrary.activityTesting.FileActivity;
import com.kalbe.mobiledevlibrary.activityTesting.ImageActivity;
import com.kalbe.mobiledevlibrary.activityTesting.MapsActivity;
import com.kalbe.mobiledevlibrary.activityTesting.SpinnerActivity;
import com.kalbe.mobiledevlibrary.activityTesting.TableActivity;
import com.kalbe.mobiledevlibrary.activityTesting.ToastActivity;
import com.kalbe.mobiledevlibrary.common.clsItem;
import com.kalbe.mobiledevlibrary.repo.clsItemRepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ImageView img;
    TextView tvFile;
    String txtPathUserData;
    Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color_theme));
        }
        setContentView(R.layout.activity_main);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        txtPathUserData= Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator;

        final List<String> contentLibs = new ArrayList<>();
        contentLibs.add("Spinner");
        contentLibs.add("File & pdfViewer");
        contentLibs.add("Image");
        contentLibs.add("Maps");
        contentLibs.add("error reporting");
        contentLibs.add("Toast");
        contentLibs.add("table");
        contentLibs.add("permission Checkers");
        contentLibs.add("Input Filter");
        contentLibs.add("DatePicker");

        ListView listView = findViewById(R.id.lvContent);
        listView.setAdapter(new CardAppAdapter(getApplicationContext(), contentLibs, Color.WHITE));
        ListViewCustom.setListViewHeightBasedOnItems(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (contentLibs.get(position).equals("Spinner")){
                    IntentCustom.intentToActivity(MainActivity.this, SpinnerActivity.class, null, null);
                } else if (contentLibs.get(position).equals("File & pdfViewer")){
                    IntentCustom.intentToActivity(MainActivity.this, FileActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Toast")){
                    IntentCustom.intentToActivity(MainActivity.this, ToastActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Image")){
                    IntentCustom.intentToActivity(MainActivity.this, ImageActivity.class, null, null);
                }else if (contentLibs.get(position).equals("DatePicker")){
                    IntentCustom.intentToActivity(MainActivity.this, DatePickerActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Maps")){
                    IntentCustom.intentToActivity(MainActivity.this, MapsActivity.class, null, null);
                }else if (contentLibs.get(position).equals("table")){
                    IntentCustom.intentToActivity(MainActivity.this, TableActivity.class, null, null);
                }
                else {
                    ToastCustom.showToastDefault(getApplicationContext(), contentLibs.get(position) +" Belum di buatin contohnya");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
