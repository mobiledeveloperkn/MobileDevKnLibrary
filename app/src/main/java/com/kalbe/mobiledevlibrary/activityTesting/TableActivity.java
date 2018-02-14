package com.kalbe.mobiledevlibrary.activityTesting;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.Table.Table;
import com.kalbe.mobiledevlibrary.R;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Button button = (Button)findViewById(R.id.btnTable);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList(TableActivity.this);
            }
        });

    }

    private void viewList(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //pengaturan layoutnya biar tampilannya di tengah
        final View promptView = layoutInflater.inflate(R.layout.popup_tables, null);
        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProductQty);

        String[] colTextHeader = {"Nama", "Qty", "ED"};
        ArrayList<String[]> data = new ArrayList<>();
        String tes = "gula";
        String tes2 = "1 kg";
        String tes3 = "Rp. 25000";
        String[] datas = {tes, tes2, tes3};
        data.add(datas);
        //cara menggunakan table library ini dengan memasukkan parameter context, nama tableLayoutnya, string array nama headernya, list string array datanya
        Table.setTableLayout(context, tlb, colTextHeader, data);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        IntentCustom.intentBackToFront(this);
    }
}
