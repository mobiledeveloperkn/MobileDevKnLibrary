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
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import com.kalbe.mobiledevknlibs.PermissionChecker.PermissionMaps;
import com.kalbe.mobiledevknlibs.ToastAndSnackBar.SnackBar;
import com.kalbe.mobiledevknlibs.ToastAndSnackBar.ToastCustom;
import com.kalbe.mobiledevlibrary.activityTesting.CreateNotificationActivity;
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
import com.kalbe.mobiledevlibrary.activityTesting.TourGuideActivity;

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
        String mobile = Connection.checkConnectionMobile(conMan);
        String wifi = Connection.checkConnectionWifi(conMan);
        String connection = null;
        if (mobile=="connected"||wifi=="connected"){
            connection = "connected";
        } else {
            connection = "no conectivity";
        }
        CoordinatorLayout cl = (CoordinatorLayout)findViewById(R.id.cl_main);
        final Snackbar snackbar =  new SnackBar().snackbarIndefinite(cl, connection, R.color.red);
        snackbar.show();
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setAction("Close", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

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
        contentLibs.add("Badger");
        contentLibs.add("Tour Guide");

        ListView listView = findViewById(R.id.lvContent);
        listView.setAdapter(new CardAppAdapter(getApplicationContext(), contentLibs, Color.WHITE));
        ListViewCustom.setListViewHeightBasedOnItems(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (contentLibs.get(position).equals("Spinner")){
                    new IntentCustom().intentToActivity(MainActivity.this, SpinnerActivity.class, null, null);
                } else if (contentLibs.get(position).equals("File & pdfViewer")){
                    new IntentCustom().intentToActivity(MainActivity.this, FileActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Toast")){
                    new IntentCustom().intentToActivity(MainActivity.this, ToastActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Image")){
                    new IntentCustom().intentToActivity(MainActivity.this, ImageActivity.class, null, null);
                }else if (contentLibs.get(position).equals("DatePicker")){
                    new IntentCustom().intentToActivity(MainActivity.this, DatePickerActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Maps")){
                    new IntentCustom().intentToActivity(MainActivity.this, MapsActivity.class, null, null);
                }else if (contentLibs.get(position).equals("table")){
                    new IntentCustom().intentToActivity(MainActivity.this, TableActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Input Filter")){
                    new IntentCustom().intentToActivity(MainActivity.this, InputFIlterActivity.class, null, null);
                }else if (contentLibs.get(position).equals("error reporting")){
                    new ToastCustom().showToastDefault(getApplicationContext(), contentLibs.get(position) +" sudah ada di apps tinggal di modif");
                }else if (contentLibs.get(position).equals("permission Checkers")){
                    new ToastCustom().showToastDefault(getApplicationContext(), "liat kodingan di class InformationCheckerActivity");
                }else if (contentLibs.get(position).equals("Info Device")){
                    new IntentCustom().intentToActivity(MainActivity.this, InfoDeviceActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Snackbar")){
                    new IntentCustom().intentToActivity(MainActivity.this, SanckbarActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Badger")){
                    new IntentCustom().intentToActivity(MainActivity.this, CreateNotificationActivity.class, null, null);
                }else if (contentLibs.get(position).equals("Tour Guide")){
                    new IntentCustom().intentToActivity(MainActivity.this, TourGuideActivity.class, null, null);
                }
                else {
                    new ToastCustom().showToastDefault(getApplicationContext(), contentLibs.get(position) +" Belum di buatin contohnya");
                }
            }
        });
        String[] tes = new String[]{"ansgdhasgdhhsjkckldsjck"};
    }


}
