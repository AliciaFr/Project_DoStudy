package com.example.alicia.dostudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alicia.dostudy.TimeTable.WeekActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView toDo, timeTable, calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    private void initUI() {
        calendar = (TextView) findViewById(R.id.home_calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(i);
            }
        });

        timeTable = (TextView)findViewById(R.id.home_timetable);
        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toTimeTable = new Intent(HomeActivity.this, WeekActivity.class);
                startActivity(toTimeTable);
            }
        });
    }
    
}
