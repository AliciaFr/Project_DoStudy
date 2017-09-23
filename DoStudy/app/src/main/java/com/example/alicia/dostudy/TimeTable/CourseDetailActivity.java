package com.example.alicia.dostudy.TimeTable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alicia.dostudy.R;

public class CourseDetailActivity extends AppCompatActivity {

    TextView name, begin, end, room, lecturer, date, colour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        setupUI();
    }

    private void setupUI(){
        name = (TextView)findViewById(R.id.title_result);
        name.setText(getIntent().getExtras().getString("Name"));

        begin = (TextView)findViewById(R.id.time_begin_result);
        begin.setText(getIntent().getExtras().getString("Begin"));

        end = (TextView)findViewById(R.id.time_end_result);
        end.setText(getIntent().getExtras().getString("End"));

        room = (TextView)findViewById(R.id.room_result);
        room.setText(getIntent().getExtras().getString("Room"));

        lecturer = (TextView)findViewById(R.id.lecturer_result);
        lecturer.setText(getIntent().getExtras().getString("Lecturer"));

        date = (TextView)findViewById(R.id.test_result);
        date.setText(getIntent().getExtras().getString("Date"));

        colour = (TextView)findViewById(R.id.colour_result);

        switch (getIntent().getExtras().getInt("Colour")){
            case 0:
                colour.setText("hellblau");
                break;
            case 1:
                colour.setText("dunkelblau");
                break;
            case 2:
                colour.setText("türkis");
                break;
            case 3:
                colour.setText("hellgrün");
                break;
            case 4:
                colour.setText("dunkelgrün");
                break;
            case 5:
                colour.setText("gelb");
                break;
            case 6:
                colour.setText("keine");
                break;
            case 7:
                colour.setText("rot");
        }
    }
}
