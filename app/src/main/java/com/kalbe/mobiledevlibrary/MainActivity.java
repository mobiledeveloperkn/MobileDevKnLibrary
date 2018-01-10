package com.kalbe.mobiledevlibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.kalbe.mobiledevknlibs.AlertDialog.clsEnumDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;
import com.kalbe.mobiledevknlibs.InputFilter.InputFilter;
import com.kalbe.mobiledevknlibs.Maps.PopUpMaps;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickFile;
import com.kalbe.mobiledevknlibs.PickImageAndFile.PickImage;
import com.kalbe.mobiledevknlibs.PickImageAndFile.UriData;
import com.kalbe.mobiledevknlibs.Spinner.SpinnerCustom;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;

import java.io.File;
import java.io.FileNotFoundException;
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
        setContentView(R.layout.activity_main);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        txtPathUserData= Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + "com.mobiledevknlibs" + File.separator + "user_data" + File.separator + "tes" + File.separator;
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
        EditText etInputFilter = (EditText) findViewById(R.id.txt_input_filter);
//        InputFilter.filterCapsAll(etInputFilter, 20);
        InputFilter.filterSpaceAtoZ0to9(etInputFilter);

        ToastCustom.showToasty(getApplicationContext(), "tes", 2);
//        clsDatePicker.showDatePicker(MainActivity.this, date);

//        CustomDatePicker.showHint(date, clsEnumDatePicker.standard2);
        clsDatePicker.showHint(date, clsEnumDatePicker.standard0);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CustomDatePicker.showDatePicker(MainActivity.this, date, "Set Date", 0);
//                clsDatePicker.showDatePicker(MainActivity.this, date, clsEnumDatePicker.standard0);
                ToastCustom.showToasty(getApplicationContext(), "tes", 2);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file = new File(txtPathUserData, "tes" );
//                byte[] tes = IntentCustom.getByteImageToSave(getApplicationContext(), uri);
                String[] colTextHeader = {"Nama", "Qty", "ED"};
//                new PopUpMaps().popUpMaps(MainActivity.this, R.layout.popup_map);
//                byte[] tesFile = null;
//                try {
//                     tesFile = PickFile.getByteArrayFileToSave(uri, getApplicationContext());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                ToastCustom.showToastDefault(getApplicationContext(), String.valueOf(tesFile));
//                TesFragment fragmentDownloadData = new TesFragment();
//                FragmentTransaction fragmentTransactionDownloadData = getSupportFragmentManager().beginTransaction();
//                fragmentTransactionDownloadData.replace(R.id.frame, fragmentDownloadData);
//                fragmentTransactionDownloadData.commit();
            }
        });

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

    private void viewList(Context ctx, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        final View promptView = layoutInflater.inflate(R.layout., null);

        final TextView _tvNoSO = (TextView) promptView.findViewById(R.id.tvnoSOtbl);
        final TextView _tvKet = (TextView) promptView.findViewById(R.id.tvkettbl);
        _tvNoSO.setText(dt.get(position).get_txtQuantityStock());
        _tvKet.setText(dt.get(position).get_txtKeterangan());
        final TextView tv_item = (TextView) promptView.findViewById(R.id.tvItemtbl);
        tv_item.setTypeface(null, Typeface.BOLD);
        tv_item.setText(String.valueOf(dt.get(position).get_intSumItem()));
        final  TextView tv_amount = (TextView) promptView.findViewById(R.id.tvSumAmount) ;
        tv_amount.setTypeface(null, Typeface.BOLD);
        tv_amount.setText(String.valueOf(dt.get(position).get_intSumAmount()));
        final  TextView tv_status = (TextView) promptView.findViewById(R.id.tvStatus);
        tv_status.setTypeface(null, Typeface.BOLD);

        final TableRow tr_neared = (TableRow) promptView.findViewById(R.id.tr_neared);
        final TableRow tr_keterangan = (TableRow) promptView.findViewById(R.id.tr_keterangan);
        tr_keterangan.setVisibility(View.GONE);
        tr_neared.setVisibility(View.GONE);

        if (dt.get(position).get_intSubmit().equals("1")&&dt.get(position).get_intSync().equals("0")){
            tv_status.setText("submit");
        } else if (dt.get(position).get_intSubmit().equals("1")&&dt.get(position).get_intSync().equals("1")){
            tv_status.setText("Sync");
        }

        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProductQty);
        tlb.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(getContext());

        TableLayout tl = new TableLayout(getContext());

        String[] colTextHeader = {"Nama", "Qty", "ED"};

        for (String text : colTextHeader) {
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tr.addView(tv,params);
        }
        tl.addView(tr);

        data = new tSalesProductQuantityDetailBL().GetDataNoQuantityStock(dt.get(position).get_txtQuantityStock());

        double qtySum=0;
        double qtyNum;
        for(tSalesProductQuantityDetailData dat : data){
            tr = new TableRow(getContext());
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);

            TextView product = new TextView(getContext());
            product.setTextSize(12);
            product.setWidth(400);
            product.setPadding(10, 10, 10, 10);
            product.setBackgroundColor(Color.parseColor("#f0f0f0"));
            product.setTextColor(Color.BLACK);
            product.setText(dat.getTxtProduct());
            tr.addView(product,params);

            TextView qty = new TextView(getContext());
            qty.setTextSize(12);
            qty.setPadding(10, 10, 10, 10);
            qty.setBackgroundColor(Color.parseColor("#f0f0f0"));
            qty.setTextColor(Color.BLACK);
            qty.setGravity(Gravity.RIGHT);
            qty.setText(dat.getTxtQuantity() +" pcs");
            tr.addView(qty,params);

            TextView price = new TextView(getContext());
            price.setTextSize(12);
            price.setPadding(10, 10, 10, 10);
            price.setBackgroundColor(Color.parseColor("#f0f0f0"));
            price.setTextColor(Color.BLACK);
            price.setGravity(Gravity.RIGHT);
            price.setText(new clsMainActivity().giveFormatDate2(dat.getTxtExpireDate()));
            tr.addView(price,params);
//
//            TextView amount = new TextView(getContext());
//            amount.setTextSize(12);
//            amount.setWidth(200);
//            amount.setPadding(10, 10, 10, 10);
//            amount.setBackgroundColor(Color.parseColor("#f0f0f0"));
//            amount.setTextColor(Color.BLACK);
//            amount.setGravity(Gravity.RIGHT);
//            double prc = Double.valueOf(dat.get_intPrice());
//            double itm = Double.valueOf(dat.getTxtQuantity());
//            qtyNum = prc * itm;
//            qtySum += qtyNum;
//            amount.setText(new clsMainActivity().convertNumberDec(qtyNum));
//            tr.addView(amount,params);

            tl.addView(tr, tableRowParams);
        }

        tlb.addView(tl);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
}
