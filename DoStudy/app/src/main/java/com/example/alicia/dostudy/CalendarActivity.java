package com.example.alicia.dostudy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ListIterator;
import java.util.Locale;

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
        showDateToEntry();
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
        initListAdapter();
        initListView();
        currMonth = (TextView) findViewById(R.id.calendar_month);
        addEntry = (ImageView) findViewById(R.id.calendar_add_entry);
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalendarActivity.this, AddCalendarEntryActivity.class);
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
                for (ListIterator<CalendarEntry> iterator = arrayList.listIterator(); iterator.hasNext();) {
                    int position = 0;
                    String stringDate = DateFormatter.dateToString(iterator.next().getDate());
                    Date entryDate = DateFormatter.stringToDate(stringDate);
                    System.out.println("EntryDate: " + entryDate + ", DateClicked: " + dateClicked);

                    if (dateClicked.toString().equals(entryDate.toString())) {
                        Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                        listview.setSelection(position);
                    }
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

    private void showDateToEntry() {
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int firstVisibleRow = listview.getFirstVisiblePosition();
                System.out.println(firstVisibleRow + "firstRow");
                int currDateInInt = adapter.getItem(firstVisibleItem).getDate();
                System.out.println(currDateInInt + "view");
                String currDateInString = DateFormatter.dateToString(currDateInInt);
                System.out.println("currDate" + currDateInString);
                Date currDate = DateFormatter.stringToDate(currDateInString);
                calendarView.setCurrentDate(currDate);
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
            Event event = new Event(Color.rgb(0, 153, 204), timestampDate);
            calendarView.addEvent(event, true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
        }
        return true;
    }
}