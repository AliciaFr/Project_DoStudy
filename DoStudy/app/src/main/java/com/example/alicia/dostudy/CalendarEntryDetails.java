package com.example.alicia.dostudy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CalendarEntryDetails extends AppCompatActivity {

    private ImageView close;
    private TextView tvTitle, tvDescription, tvDate, tvTime, tvAlarm, tvLocation;

    private String title, description, date, time;
    private InternDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_entry_details);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        informationInBundle(extras);
        initUI();
        initDB();
    }

    private void initDB() {
        database = new InternDatabase(this);
    }

    private void initUI() {
        ImageView close = (ImageView) findViewById(R.id.calendar_entry_details_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CalendarEntryDetails.this, CalendarActivity.class);
                startActivity(i);
            }
        });
        TextView tvTitle = (TextView) findViewById(R.id.calendar_entry_details_title);
        TextView tvDescription = (TextView) findViewById(R.id.calendar_entry_details_description);
        TextView tvDate = (TextView) findViewById(R.id.calendar_entry_details_date);
        TextView tvTime = (TextView) findViewById(R.id.calendar_entry_details_time);

        tvTitle.setText(title);
        tvDescription.setText(description);
        tvDate.setText(date);
        tvTime.setText(time);
    }

    private void informationInBundle(Bundle extras) {
        CalendarEntry entry = (CalendarEntry) extras.get(getResources().getString(R.string.calendar_entry_intent));
        title = entry.getTitle();
        description = entry.getDescription();
        date = DateFormatter.dateToString(entry.getDate());
        time = entry.getTime();
    }
}
