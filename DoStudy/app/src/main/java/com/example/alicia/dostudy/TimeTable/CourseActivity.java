package com.example.alicia.dostudy.TimeTable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alicia.dostudy.R;

public class CourseActivity extends AppCompatActivity {

    EditText title, timeBegin, timeEnd, room, lecturer, testDate;
    Spinner colourSpinner;
    Button saveAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setupUI();
    }

    private void setupUI(){

        title = (EditText)findViewById(R.id.title);
        timeBegin = (EditText)findViewById(R.id.time_begin);
        timeEnd = (EditText)findViewById(R.id.time_end);
        room = (EditText)findViewById(R.id.room);
        lecturer = (EditText)findViewById(R.id.lecturer);
        testDate = (EditText)findViewById(R.id.test);
        colourSpinner = (Spinner) findViewById(R.id.colour);

        initButton();
        initSpinner();
    }

    private void initButton(){
        saveAll = (Button)findViewById(R.id.button_save);
        saveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initSpinner(){
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(CourseActivity.this, R.array.colourArray, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colourSpinner.setAdapter(spinnerAdapter);

        colourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long arg3) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addInputToDB(){

        String courseTitle = title.getText().toString();
        String timeFrom = timeBegin.getText().toString();
        String timeTo = timeEnd.getText().toString();
        String courseRoom = room.getText().toString();
        String lecturerName = lecturer.getText().toString();
        String test = testDate.getText().toString();

    }
}
