package com.example.alicia.dostudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alicia.dostudy.TimeTable.WeekActivity;
import com.example.alicia.dostudy.ToDoList.ToDoListActivity;

public class HomeActivity extends AppCompatActivity {

    private Button toDo, timeTable, calendar, notesAndGrades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        initClickListener();
    }

    private void initUI() {
        toDo = (Button) findViewById(R.id.home_todo);
        calendar = (Button) findViewById(R.id.home_calendar);
        timeTable = (Button) findViewById(R.id.home_timetable);
        notesAndGrades = (Button) findViewById(R.id.home_notes_grades);
    }

    private void initClickListener() {
        toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toToDo = new Intent(HomeActivity.this, ToDoListActivity.class);
                startActivity(toToDo);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCalendar = new Intent(HomeActivity.this, CalendarActivity.class);
                startActivity(toCalendar);
            }
        });
        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toTimeTable = new Intent(HomeActivity.this, WeekActivity.class);
                startActivity(toTimeTable);
            }
        });
        notesAndGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toNotesAndGrades = new Intent(HomeActivity.this, NotesGradesActivity.class);
                startActivity(toNotesAndGrades);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        checkNetworkConnection();
    }

    private void checkNetworkConnection() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean notification = sharedPreferences.getBoolean(getResources().getString(R.string.preferences_key_notifications), true);
        if(notification) {
            startService(new Intent(this, NotificationService.class));
        } else {
                stopService(new Intent(this, NotificationService.class));
        }
    }
}

