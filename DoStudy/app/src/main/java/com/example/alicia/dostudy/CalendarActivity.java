package com.example.alicia.dostudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.TimeZone;


public class CalendarActivity extends AppCompatActivity {

    private ImageView addEntry;
    private CompactCalendarView calendarView;
    private ListView listview;

    private ArrayList<CalendarEntry> arrayList = new ArrayList<>();
    private InternDatabase database;
    private CalendarEntryAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_portrait);
        initDB();
        initUI();
        updateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        arrayList.clear();
        arrayList.addAll(database.getAllEntries());
        listview.setAdapter(adapter);
        sortData();
    }

    private void sortData() {
        Collections.sort(arrayList);
        adapter.notifyDataSetChanged();
    }

    private void initUI() {
        initListView();
        initListAdapter();
        addEntry = (ImageView) findViewById(R.id.calendar_add_entry);
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalendarActivity.this, CalendarAddEntryActivity.class);
                startActivity(i);
            }
        });
        calendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setLocale(TimeZone.getDefault(), Locale.GERMAN);
    }

    private void initListAdapter() {
        adapter = new CalendarEntryAdapter(this, arrayList);
    }

    private void initListView() {
        listview = (ListView) findViewById(R.id.listview_calendar_entries);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(CalendarActivity.this, CalendarEntryDetails.class);
                intent.putExtra(getResources().getString(R.string.calendar_entry_intent),
                        arrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initDB() {
        database = new InternDatabase(this);
    }


    private void addEntryToCalendar() {

    }
}
