package com.kalbe.mobiledevlibrary.activityTesting;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kalbe.mobiledevknlibs.Intent.IntentCustom;
import com.kalbe.mobiledevknlibs.ListView.CardAppAdapter;
import com.kalbe.mobiledevknlibs.ListView.ListViewCustom;
import com.kalbe.mobiledevknlibs.ToastAndSnackBar.SnackBar;
import com.kalbe.mobiledevlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class SanckbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanckbar);
        final CoordinatorLayout cl = (CoordinatorLayout) findViewById(R.id.cons_sb);
        final List<String> contentLibs = new ArrayList<>();
        contentLibs.add("Short");
        contentLibs.add("Long");
        contentLibs.add("Indefinite");

        ListView listView = findViewById(R.id.lv_snackbar);
        listView.setAdapter(new CardAppAdapter(getApplicationContext(), contentLibs, Color.WHITE));
        ListViewCustom.setListViewHeightBasedOnItems(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (contentLibs.get(position).equals("Short")) {
                    //ini kalo mau makek snacbar short
                    SnackBar.snackbarShort(cl, "Hello i'm snackbar short", R.color.red);
                } else if (contentLibs.get(position).equals("Long")) {
                    //ini kalo mau makek snacbar long
                    SnackBar.snackbarShort(cl, "Hello i'm snackbar Long", R.color.title_background);
                } else if (contentLibs.get(position).equals("Indefinite")) {
                    //ini kalo mau pakek snackbar indefinite
                    final Snackbar snackbar = SnackBar.snackbarIndefinite(cl, "Hello i'm snackbar Indefinite", R.color.red_bold);
                    snackbar.show();
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        IntentCustom.intentBackToFront(this);
    }
}
