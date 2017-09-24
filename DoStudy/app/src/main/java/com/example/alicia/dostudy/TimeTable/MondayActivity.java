package com.example.alicia.dostudy.TimeTable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alicia.dostudy.R;

import java.util.ArrayList;

public class MondayActivity extends AppCompatActivity{

    TextView day;
    Button okayButton;
    EditText courseName;
    EditText courseTime;
    EditText courseTimeEnd;
    EditText courseRoom;
    EditText courseLecturer;
    EditText courseDate;
    Spinner courseColour;

    private ArrayList<CourseItem> courseItems;
    private CourseItemAdapter item_adapter;
    private CourseItemDatabase database;

    /*
    the MondayActivity represents the courses for monday
    the other DayActivities ar similar, they only differ by the day name (in database, intents, toasts)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        initList();
        initDB();
        setupUI();
        updateList();
    }

    /*
    opens the database mondayCourses.db
     */

    private void initDB() {
        database = new CourseItemDatabase(this, "mondayCourses.db");
        database.open();
    }

    /*
    creates the ListView by using the CourseItemAdapter
     */

    private void initList() {
        courseItems = new ArrayList<CourseItem>();
        ListView list = (ListView) findViewById(R.id.course_list);
        item_adapter = new CourseItemAdapter(this, courseItems);
        list.setAdapter(item_adapter);
    }

    /*
    creates the layout by using the day name
    spinner, button and listView are initialised
     */

    private void setupUI(){
        day = (TextView)findViewById(R.id.day);
        day.setText(R.string.monday);
        courseColour = (Spinner) findViewById(R.id.input_colour);
        initSpinner();
        setupButton();
        setupListView();
    }

    /*
    If the button is clicked, the data is stored in the database
     */

    private void setupButton(){
        okayButton = (Button) findViewById(R.id.button);
        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputToDB();
            }
        });
    }

    /*
    the values are stored in variables
    the input fields are emptied and a new courseItem with the variables will be stored in the database
     */

    private void addInputToDB() {
        courseName = (EditText) findViewById(R.id.course_input);
        courseTime = (EditText) findViewById(R.id.input_time);
        courseTimeEnd = (EditText) findViewById(R.id.input_time_end);
        courseRoom = (EditText) findViewById(R.id.input_room);
        courseLecturer = (EditText) findViewById(R.id.input_lecturer);
        courseDate = (EditText) findViewById(R.id.input_date);

        String name = courseName.getText().toString();
        String begin = courseTime.getText().toString();
        String end = courseTimeEnd.getText().toString();
        String room = courseRoom.getText().toString();
        String lecturer = courseLecturer.getText().toString();
        String date = courseDate.getText().toString();
        int colour = courseColour.getSelectedItemPosition();

        if (!name.equals("") && !begin.equals("") && !end.equals("") && !room.equals("") && !lecturer.equals("") && !date.equals("")) {
            courseName.setText("");
            courseTime.setText("");
            courseTimeEnd.setText("");
            courseRoom.setText("");
            courseLecturer.setText("");
            courseDate.setText("");

            CourseItem newItem = new CourseItem(name, begin, end, room, lecturer, date, colour);
            database.insertCourseItem(newItem);
            updateList();
        }
    }

    /*
    if the spinner is clicked, the colourArray opens
     */

    private void initSpinner(){
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(MondayActivity.this, R.array.colourArray, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseColour.setAdapter(spinnerAdapter);

        courseColour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View v, int position, long arg3) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /*
    if an entry is clicked longer, it will be deleted and a toast appears
    if an entry is clicked, an intent is created, which gives the values to CourseDetailActivity
     */

    private void setupListView() {
        ListView list = (ListView) findViewById(R.id.course_list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeCourseAtPosition(position);
                makeToast();
                return true;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String name = courseItems.get(position).getCourseName();
                String begin = courseItems.get(position).getCourseBegin();
                String end = courseItems.get(position).getCourseEnd();
                String room = courseItems.get(position).getRoom();
                String lecturer = courseItems.get(position).getLecturer();
                String date = courseItems.get(position).getTestDate();
                int colour = courseItems.get(position).getColour();

                Intent getDetails = new Intent(MondayActivity.this, CourseDetailActivity.class);
                getDetails.putExtra("Name", name);
                getDetails.putExtra("Begin", begin);
                getDetails.putExtra("End", end);
                getDetails.putExtra("Room", room);
                getDetails.putExtra("Lecturer", lecturer);
                getDetails.putExtra("Date", date);
                getDetails.putExtra("Colour", colour);

                startActivity(getDetails);
            }
        });
    }

    /*
    removes the entry at the given position and updates the list
     */

    private void removeCourseAtPosition(int position) {
        if (courseItems.get(position) != null) {
            database.removeCourseItem(courseItems.get(position));
            updateList();
        }
    }

    /*
    creates a toast
     */

    private void makeToast(){
        String deleted = "Kurs gelöscht!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(MondayActivity.this, deleted, duration);
        toast.show();
    }

    /*
    updates the list by removing all entries and saving the entries of the database
     */

    private void updateList(){
        ArrayList tempList = database.getAllCourseItems();
        courseItems.clear();
        courseItems.addAll(tempList);
        item_adapter.notifyDataSetChanged();
    }

    /*
    closes the database
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}
