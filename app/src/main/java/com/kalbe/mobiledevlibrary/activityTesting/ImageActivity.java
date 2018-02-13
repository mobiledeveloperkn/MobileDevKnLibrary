package com.kalbe.mobiledevlibrary.activityTesting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImage;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImageCustom;
import com.kalbe.mobiledevknlibs.PickImageAndFile.UriData;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;
import com.kalbe.mobiledevlibrary.R;

import java.io.File;
import java.net.URI;

public class ImageActivity extends AppCompatActivity {
    ImageView imageView1, imageView2;
    String file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView1 = (ImageView)findViewById(R.id.tesImg);
        imageView2 = (ImageView)findViewById(R.id.tesImg2);
         file = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator ;
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickImage.CaptureImage(ImageActivity.this, file, "img", 100 );
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageCustom.selectImageProfile(ImageActivity.this, file, 600, 700);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==100){
            if (resultCode==-1){
                Uri uri = UriData.getOutputMediaImageUri(this, file, "img");
                //untuk mendapatkan bitmap bisa menggunakan decode stream
                Bitmap bitmap = PickImage.decodeStreamReturnBitmap(this, uri);
                //atau decode file yang di definisikan dulu urinya
                Bitmap bitmap1 = PickImage.decodeFileReturnBitmap(this, uri);
                //get byte array
                byte[] save = PickImage.getByteImageToSave(this, uri);
                ToastCustom.showToastDefault(this, "data yang tersimpan : " + save);
                PickImage.previewCapturedImage(imageView1, bitmap1, 100, 100);
            }
        }else if (requestCode==600){
            if (resultCode==-1){
                //untuk uri yang di belakangnya ada cons itu tandanya dia sudah ada mana filenya tinggal masukkin path foldernya aja
                Uri uri = UriData.getOutputMediaImageUriCons(this, file);
                //ini kalau ingin cropnya di atur sendiri panjang dan lebarnya
               PickImageCustom.performCropCustom(uri, this, 800 );
               //ini kalau ingin di buat dp jadi panjang dan lebarnya sama (persegi)
//               PickImageCustom.performCropProfile(uri, this, 800);
            }
        }else if (requestCode==700){
            if (resultCode==-1){
//               PickImageCustom.performCropCustom(data.getData(), this, 800);
               PickImageCustom.performCropProfile(data.getData(), this, 800);
            }
        }else if (requestCode==800){
            if (resultCode==-1){
                //ini untuk get bitmap dari hasil cropping, dengan memasukkan request code yang sama baik gambar yang di ambil dari kamera maupun dari gallery
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data"); //key "data" setting defaultnya
                PickImage.previewCapturedImage(imageView2, thePic, 150, 150);
            }
        }
    }
}
