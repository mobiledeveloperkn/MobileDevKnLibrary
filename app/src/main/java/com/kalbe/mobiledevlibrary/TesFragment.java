package com.kalbe.mobiledevlibrary;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import com.kalbe.mobiledevknlibs.AlertDialog.CustomDatePicker;
import com.kalbe.mobiledevknlibs.AlertDialog.clsDatePicker;
import com.kalbe.mobiledevknlibs.Maps.PopUpMaps;
import com.kalbe.mobiledevknlibs.Table.Table;

import java.util.ArrayList;

/**
 * Created by Dewi Oktaviani on 1/7/2018.
 */

public class TesFragment extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tes, container, false);
        final EditText editText = (EditText) v.findViewById(R.id.txtDatefragment);
//        clsDatePicker.showDatePicker(getContext(), editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDatePicker.showDatePicker(getContext(), editText, "Set Date", 1);
            }
        });
//        editText.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etTglLhr) {
//            @Override
//            public boolean onDrawableClick() {
//                showDatePicker();
//                return false;
//            }
//        });
        Button tes = (Button) v.findViewById(R.id.btn);
        tes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             viewList(getContext());
//                                new PopUpMaps().popUpMaps(getContext(), R.layout.popup_map);
            }
        });
        return v;
    }

    private void viewList(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View promptView = layoutInflater.inflate(R.layout.popup_tables, null);
        TableLayout tlb = (TableLayout) promptView.findViewById(R.id.tlProductQty);

        String[] colTextHeader = {"Nama", "Qty", "ED"};
        ArrayList<String[]> data = new ArrayList<>();
        String tes = "gula";
        String tes2 = "1 kg";
        String tes3 = "Rp. 25000";
        String[] datas = {tes, tes2, tes3};
        data.add(datas);
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
}
