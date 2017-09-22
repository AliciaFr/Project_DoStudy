package com.example.alicia.dostudy.Grades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.example.alicia.dostudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ninayazmin on 22.09.2017.
 */

public class GradeActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("");
        listDataHeader.add("");
        listDataHeader.add("");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("");
        top250.add("");
        top250.add("");
        top250.add("");
        top250.add("");
        top250.add("");
        top250.add("");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("");
        nowShowing.add("");
        nowShowing.add("");
        nowShowing.add("");
        nowShowing.add("");
        nowShowing.add("");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("");
        comingSoon.add("");
        comingSoon.add("");
        comingSoon.add("");
        comingSoon.add("");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}

