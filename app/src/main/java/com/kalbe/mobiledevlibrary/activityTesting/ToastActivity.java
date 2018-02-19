package com.kalbe.mobiledevlibrary.activityTesting;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kalbe.mobiledevknlibs.ListView.ListViewCustom;
import com.kalbe.mobiledevknlibs.ListView.clsSwipeList;
import com.kalbe.mobiledevknlibs.ToastAndSnackBar.ToastCustom;
import com.kalbe.mobiledevlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        ListView listView = (ListView) findViewById(R.id.lv_toast);
        final List<clsSwipeList> swipeLists = new ArrayList<>();
        final List<String> contentLibs = new ArrayList<>();
        contentLibs.add("Succes");
        contentLibs.add("Error");
        contentLibs.add("Info");
        contentLibs.add("Warning");
        contentLibs.add("Normal");
        contentLibs.add("Toast custom spg mobile true");
        contentLibs.add("Toast custom spg mobile false");
        contentLibs.add("show Toasty Custom");
        for (String content : contentLibs){
            clsSwipeList _clsSwipeList = new clsSwipeList();
            _clsSwipeList.set_txtTitle(content);
            swipeLists.add(_clsSwipeList);
        }
        listView.setAdapter(ListViewCustom.setCardList(this, swipeLists, Color.WHITE, 0));
        ListViewCustom.setListViewHeightBasedOnItems(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (swipeLists.get(position).get_txtTitle().equals("Toast custom spg mobile true")){
                   ToastCustom.showToastSPGMobile(getApplicationContext(), swipeLists.get(position).get_txtTitle(), true);
               } else if (swipeLists.get(position).get_txtTitle().equals("Toast custom spg mobile false")){
                   ToastCustom.showToastSPGMobile(getApplicationContext(), swipeLists.get(position).get_txtTitle(), false);
               }else if (swipeLists.get(position).get_txtTitle().equals("show Toasty Custom")){
                   Drawable icon = getResources().getDrawable(R.drawable.ic_edit);
                   ToastCustom.showToastyCustom(getApplicationContext(), swipeLists.get(position).get_txtTitle(), icon, Color.BLUE, 5, false, true);
               }
               else {
                   ToastCustom.showToasty(getApplicationContext(), swipeLists.get(position).get_txtTitle(), position+1);
               }
            }
        });
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
