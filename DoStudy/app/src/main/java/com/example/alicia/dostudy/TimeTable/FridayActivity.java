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

public class FridayActivity extends AppCompatActivity {

    TextView day;
    Button okayButton;
    EditText courseName;
    EditText courseTime;

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
        day.setText(R.string.friday);
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
        String name = courseName.getText().toString();
        String begin = courseTime.getText().toString();

        if (!name.equals("") && !begin.equals("")) {
            courseName.setText("");
            courseTime.setText("");

            CourseItem newItem = new CourseItem(name,begin);
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
