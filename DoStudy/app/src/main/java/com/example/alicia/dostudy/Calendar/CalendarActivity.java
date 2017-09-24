package com.example.alicia.dostudy.Calendar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.alicia.dostudy.DateFormatter;
import com.example.alicia.dostudy.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ListIterator;
import java.util.Locale;

/* This activity sets up a calendarView in which all Entries are represented and a ListView with
    information to the saved events
* */

public class CalendarActivity extends AppCompatActivity {

    private ImageView addEntry;
    private TextView currMonth;
    private CompactCalendarView calendarView;
    private ListView listview;

    private ArrayList<CalendarEntry> arrayList = new ArrayList<>();
    private CalendarDatabase database;
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
            }

            // sets the textView to the current scrolled month
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

    /* when the listView is scrolled the pointer for the current position in the calendar jumps to
        the date of the first ListView element that is shown
     */
    private void showDateToEntry() {
        if (arrayList.size() != 0) {
            listview.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    CalendarEntry firstVisible = adapter.getItem(firstVisibleItem);
                    if (firstVisible != null) {
                        DateFormat dateFormat = new SimpleDateFormat("MMM yyyy", Locale.GERMAN);
                        int currDateInInt = firstVisible.getDate();
                        String currDateInString = DateFormatter.dateToString(currDateInInt);
                        Date currDate = DateFormatter.stringToDate(currDateInString);
                        String currMonthDate = dateFormat.format(currDate);
                        calendarView.setCurrentDate(currDate);
                        currMonth.setText(currMonthDate);
                    }
                }
            });
        }
    }

    private void initDB() {
        database = new CalendarDatabase(this);
    }

    /* adds the entries from the arrayList to the calendar. Shown as a colored circle in calendar*/
    private void addEntryToCalendar() {
        ListIterator<CalendarEntry> iterator = arrayList.listIterator();
        while(iterator.hasNext()) {
            String stringDate = DateFormatter.dateToString(iterator.next().getDate());
            long timestampDate = DateFormatter.stringToDate(stringDate).getTime();
                Event event = new Event(Color.rgb(0, 153, 204), timestampDate);
                calendarView.addEvent(event, true);

        }
    }
}