package com.kalbe.mobiledevknlibs.Intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dewi Oktaviani on 1/2/2018.
 */

public class IntentCustom {
    public static void intentViewFragment(Context context, Class<?> cls, String keyPutExtra, String value) {
        Intent myIntent = new Intent(context, cls);
        myIntent.putExtra(keyPutExtra, value);
        Activity activity = (Activity) context;
        activity.finish();
        context.startActivity(myIntent);
    }

    public static void intentCaptureImage(Context context, String folderName, String fileName, final int REQUEST_CODE) {
        Uri uriImage = getOutputMediaFileUri(context, folderName, fileName);
        Intent intentCamera1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera1.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        Activity activity = (Activity) context;
        activity.startActivityForResult(intentCamera1, REQUEST_CODE);
    }

    //get location file to save tmp
    public static Uri getOutputMediaFileUri(Context context, String folderName, String fileName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //use this if Lollipop_Mr1 (API 22) or above
            return FileProvider.getUriForFile(context.getApplicationContext(), context.getApplicationContext().getPackageName()+".provider", getOutputMediaFile(folderName, fileName));
        } else {
            return Uri.fromFile(getOutputMediaFile(folderName, fileName));
        }
//        return Uri.fromFile(getOutputMediaFile());
    }

    //create path file
    private static File getOutputMediaFile(String folderName, String fileName) {
        // External sdcard location

        File mediaStorageDir = new File(folderName + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
//                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
//                Log.d("Failed create " + fileName + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("_yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName + timeStamp + ".png");
        return mediaFile;
    }

    private static Bitmap resizeImageForBlob(Bitmap photo){
        int width = photo.getWidth();
        int height = photo.getHeight();

        int maxHeight = 800;
        int maxWidth = 800;

        Bitmap bitmap;

        if(height > width){
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int)(width / ratio);
        }
        else if(height < width){
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int)(height / ratio);
        }
        else{
            width = maxWidth;
            height = maxHeight;
        }

        bitmap = Bitmap.createScaledBitmap(photo, width, height, true);

        return bitmap;
    }

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private byte[] imgPhoto = null;

    private void previewCapturedImageBefore1(ImageView imageView, Bitmap photo) {
        Bitmap bitmap = resizeImageForBlob(photo);

        try {
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

            imgPhoto = output.toByteArray();

            imageView.setImageBitmap(photo_view);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getByteImageToSave(Bitmap photo){
         byte[] imgPhoto = null;
        try {
            Bitmap bitmap = resizeImageForBlob(photo);
            ByteArrayOutputStream output = null;
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output); // bmp is your Bitmap instance
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imgPhoto = output.toByteArray();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }return imgPhoto ;
    }
}
