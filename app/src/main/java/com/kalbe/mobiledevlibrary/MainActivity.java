package com.kalbe.mobiledevlibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kalbe.mobiledevknlibs.Connection.Connection;
import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.ListView.CardAppAdapter;
import com.kalbe.mobiledevknlibs.ListView.ListViewCustom;
import com.kalbe.mobiledevknlibs.ToastAndSnackBar.ToastCustom;
import com.kalbe.mobiledevlibrary.activityTesting.DatePickerActivity;
import com.kalbe.mobiledevlibrary.activityTesting.FileActivity;
import com.kalbe.mobiledevlibrary.activityTesting.ImageActivity;
import com.kalbe.mobiledevlibrary.activityTesting.InfoDeviceActivity;
import com.kalbe.mobiledevlibrary.activityTesting.InputFIlterActivity;
import com.kalbe.mobiledevlibrary.activityTesting.MapsActivity;
import com.kalbe.mobiledevlibrary.activityTesting.SanckbarActivity;
import com.kalbe.mobiledevlibrary.activityTesting.SpinnerActivity;
import com.kalbe.mobiledevlibrary.activityTesting.TableActivity;
import com.kalbe.mobiledevlibrary.activityTesting.ToastActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ImageView img;
    TextView tvFile;
    String txtPathUserData;
    Uri uri = null;

    @Override
    public void onBackPressed() {
        com.kalbe.mobiledevknlibs.AlertDialog.AlertDialog.alertDialogExit(MainActivity.this);
//        super.onBackPressed(); delete this one if you want to create alert dialog for exit

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color_theme));
        }
        setContentView(R.layout.activity_main);
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = Connection.checkConnectionMobile(conMan);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        Button button = (Button) findViewById(R.id.btnExit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.kalbe.mobiledevknlibs.AlertDialog.AlertDialog.alertDialogExit(MainActivity.this);
            }
        });
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
        contentLibs.add("Info Device");
        contentLibs.add("Snackbar");

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
                }else if (contentLibs.get(position).equals("Input Filter")){
                    IntentCustom.intentToActivity(MainActivity.this, InputFIlterActivity.class, null, null);
                }else if (contentLibs.get(position).equals("error reporting")){
                    ToastCustom.showToastDefault(getApplicationContext(), contentLibs.get(position) +" sudah ada di apps tinggal di modif");
                }else if (contentLibs.get(position).equals("permission Checkers")){
                    ToastCustom.showToastDefault(getApplicationContext(), "liat kodingan di class InformationCheckerActivity");
                }else if (contentLibs.get(position).equals("Info Device")){
                    IntentCustom.intentToActivity(MainActivity.this, InfoDeviceActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Snackbar")){
                    IntentCustom.intentToActivity(MainActivity.this, SanckbarActivity.class, null, null);
                }
                else {
                    ToastCustom.showToastDefault(getApplicationContext(), contentLibs.get(position) +" Belum di buatin contohnya");
                }
            }
        });
    }


}
