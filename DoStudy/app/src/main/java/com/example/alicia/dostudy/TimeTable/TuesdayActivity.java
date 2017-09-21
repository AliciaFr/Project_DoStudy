package com.example.alicia.dostudy.TimeTable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alicia.dostudy.R;

import java.util.ArrayList;

public class TuesdayActivity extends AppCompatActivity {

    TextView day;
    Button okayButton;
    EditText courseName;
    EditText courseTime;
    EditText courseTimeEnd;
    EditText courseRoom;
    EditText courseLecturer;
    EditText courseDate;
    EditText courseColour;

    private ArrayList<CourseItem> courseItems;
    private CourseItemAdapter item_adapter;
    private CourseItemDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        initList();
        initDB();
        setupUI();
        updateList();
    }

    private void initDB() {
        database = new CourseItemDatabase(this);
        database.open();
    }

    private void initList() {
        courseItems = new ArrayList<CourseItem>();
        ListView list = (ListView) findViewById(R.id.course_list);
        item_adapter = new CourseItemAdapter(this, courseItems);
        list.setAdapter(item_adapter);
    }

    private void setupUI(){
        day = (TextView)findViewById(R.id.day);
        day.setText(R.string.tuesday);
        setupButton();
        setupListView();
    }

    private void setupButton(){
        okayButton = (Button) findViewById(R.id.button);
        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputToList();
            }
        });
    }

    private void addInputToList() {
        courseName = (EditText) findViewById(R.id.course_input);
        courseTime = (EditText) findViewById(R.id.input_time);
        courseTimeEnd = (EditText) findViewById(R.id.input_time_end);
        courseRoom = (EditText) findViewById(R.id.input_room);
        courseLecturer = (EditText) findViewById(R.id.input_lecturer);
        courseDate = (EditText) findViewById(R.id.input_date);
        courseColour = (EditText) findViewById(R.id.input_colour);

        String name = courseName.getText().toString();
        String begin = courseTime.getText().toString();
        String end = courseTimeEnd.getText().toString();
        String room = courseRoom.getText().toString();
        String lecturer = courseLecturer.getText().toString();
        String date = courseDate.getText().toString();
        String colour = courseColour.getText().toString();

        if (!name.equals("") && !begin.equals("") && !end.equals("") && !room.equals("") && !lecturer.equals("") && !date.equals("") && !colour.equals("")) {
            courseName.setText("");
            courseTime.setText("");
            courseTimeEnd.setText("");
            courseRoom.setText("");
            courseLecturer.setText("");
            courseDate.setText("");
            courseColour.setText("");

            CourseItem newItem = new CourseItem(name, begin, end, room, lecturer, date, colour);
            database.insertCourseItem(newItem);
            updateList();
        }
    }

    private void setupListView() {
        ListView list = (ListView) findViewById(R.id.course_list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeTaskAtPosition(position);
                return true;
            }
        });
    }

    private void removeTaskAtPosition(int position) {
        if (courseItems.get(position) != null) {
            database.removeCourseItem(courseItems.get(position));
            updateList();
        }
    }

    private void updateList(){
        ArrayList tempList = database.getAllCourseItems();
        courseItems.clear();
        courseItems.addAll(tempList);
        item_adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

}
