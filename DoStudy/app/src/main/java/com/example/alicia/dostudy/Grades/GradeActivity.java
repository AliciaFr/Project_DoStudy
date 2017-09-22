package com.example.alicia.dostudy.Grades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

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

    // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v,
        int groupPosition, long id) {
            return false;
        }
    });

    // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

        @Override
        public void onGroupExpand(int groupPosition) {
            Toast.makeText(getApplicationContext(),
                    listDataHeader.get(groupPosition) + "Gruppe aufgeklappt",
                    Toast.LENGTH_SHORT).show();
        }
    });

    // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

        @Override
        public void onGroupCollapse(int groupPosition) {
            Toast.makeText(getApplicationContext(),
                    listDataHeader.get(groupPosition) + "Gruppe zusammengeklappt",
                    Toast.LENGTH_SHORT).show();

        }
    });

    // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
        int groupPosition, int childPosition, long id) {
            // TODO Auto-generated method stub
            Toast.makeText(
                    getApplicationContext(),
                    listDataHeader.get(groupPosition)
                            + " : "
                            + listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition), Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
    });
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

