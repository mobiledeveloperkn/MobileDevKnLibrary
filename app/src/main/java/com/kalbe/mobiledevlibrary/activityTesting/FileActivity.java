package com.kalbe.mobiledevlibrary.activityTesting;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.Intent.TypeDataIntent;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickFile;
import com.kalbe.mobiledevknlibs.PickImageAndFile.UriData;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;
import com.kalbe.mobiledevlibrary.R;

import java.io.File;
import java.io.FileNotFoundException;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Button button = (Button)findViewById(R.id.btnPickFile);
        Button button1 = (Button)findViewById(R.id.btnsaveFile);
        Button button2 = (Button) findViewById(R.id.btnmoveFile);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take filenya dulu
                String[] mimetypes = {"application/pdf"};
                PickFile.intentPickFile(FileActivity.this, 510, mimetypes);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ini untuk pick file biasa aja
                String[] mimetypes = {"application/pdf" , "application/msword" , "application/vnd.ms-excel"};
                PickFile.intentPickFile(FileActivity.this, 310, mimetypes);

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ini untuk pick file biasa aja
                String[] mimetypes = {"application/pdf" , "application/msword" , "application/vnd.ms-excel"};
                PickFile.intentPickFile(FileActivity.this, 410, mimetypes);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==310){
            if (resultCode==-1){
                //intent untuk view pdf menggunakan pdf viewer
                String fileName = PickFile.getFileName(this, data.getData());
                final String fileExtension = fileName.substring(fileName.lastIndexOf("."));

                    if (fileExtension.contains(".pdf")){
                        IntentCustom.intentPDViewer(FileActivity.this, data.getData(), false);
                    } else if (fileExtension.contains(".doc")){
                        IntentCustom.intentActionView(this, data.getData(), TypeDataIntent.DOC, true);
                    } else if (fileExtension.contains(".xls")){
                        IntentCustom.intentActionView(this, data.getData(), TypeDataIntent.EXCEL, true);
                    }
            }
        } else if (requestCode==410){
            if (resultCode==-1){
                try {
                    //get byte array
                    byte[] save = PickFile.getByteArrayFileToSave(data.getData(), this);
//                    ToastCustom.showToasty(this, "data yang tersimpan : " + save, 1);
                    String fileName = PickFile.getFileName(this, data.getData());
                    final String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                    String file = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator ;
                    //decode file buat create temp file
                    //bisa pakek ini atau
//                    if (fileExtension.contains(".pdf")){
//                        PickFile.decodeByteArraytoTempFilePDF(save,file );
//                        ToastCustom.showToasty(this, "succes create temp file", 1);
//                    }

                    //atau pakek ini

                    PickFile.decodeByteArraytoTempFile(save, file, fileExtension);
                    ToastCustom.showToasty(this, "succes create temp file", 1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode==510){
            if (resultCode==-1){
                //get nama filenya dulu
                String fileName = PickFile.getFileName(this, data.getData());
                //trus definisikan path destinasinya
                String file = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator + fileName ;
                //convert ke dalam bentuk uri
                Uri uri = UriData.getOutputMediaUrifromFile(this, new File(file));
                PickFile.moveFileToSpecificUri(this, data, uri);
                ToastCustom.showToasty(this, "Move up successfully, look at your destination file", 1);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
