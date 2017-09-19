package com.example.alicia.dostudy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ListIterator;
import java.util.Locale;
import java.util.TimeZone;


public class CalendarActivity extends AppCompatActivity {

    private ImageView addEntry;
    private TextView currMonth;
    private CompactCalendarView calendarView;
    private ListView listview;
    private ActionBar actionBar;

    private ArrayList<CalendarEntry> arrayList = new ArrayList<>();
    private InternDatabase database;
    private CalendarEntryAdapter adapter;

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMM yyyy", Locale.GERMAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_portrait);
        initDB();
        initUI();
        updateList();
        addEntryToCalendar();
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
        actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);
        currMonth = (TextView) findViewById(R.id.calendar_month);
        addEntry = (ImageView) findViewById(R.id.calendar_add_entry);
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalendarActivity.this, CalendarAddEntryActivity.class);
                startActivity(i);
            }
        });
        calendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        initCalendar();
    }

    private void initCalendar() {
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        currMonth.setText(dateFormatMonth.format(calendarView.getFirstDayOfCurrentMonth()));
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                if (dateClicked.toString().compareTo("20.09.2017") == 0) {
                        Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                } else {
                        Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                currMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
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
        ListIterator<CalendarEntry> iterator = arrayList.listIterator();
        while(iterator.hasNext()) {
            String stringDate = DateFormatter.dateToString(iterator.next().getDate());
            long timestampDate = DateFormatter.stringToDate(stringDate).getTime();
            Event event = new Event(Color.RED, timestampDate);
            calendarView.addEvent(event, true);
        }
    }
}
