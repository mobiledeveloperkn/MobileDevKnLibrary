package com.kalbe.mobiledevlibrary;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.kalbe.mobiledevknlibs.ListView.ListViewCustom;
import com.kalbe.mobiledevknlibs.ListView.clsSwipeList;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = (ListView) findViewById(R.id.lv_languages);
        String[] languages = new String[] { "SQL",
                "JAVA",
                "JAVA SCRIPT",
                "C#",
                "PYTHON",
                "C++",
                "PHP",
                "IOS",
                "ANDROID"
        };
        List<clsSwipeList> lists = new ArrayList<>();
        lists.clear();

        for (int i = 0; i < languages.length; i++){
            clsSwipeList clsSwipeList = new clsSwipeList();
            clsSwipeList.set_txtTitle(languages[i]);
            lists.add(clsSwipeList);
        }
        listView.setAdapter(ListViewCustom.setCardList(Main2Activity.this, lists, R.color.white, 0));
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
