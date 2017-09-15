package com.example.alicia.dostudy.TimeTable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alicia.dostudy.R;

public class DayActivity extends AppCompatActivity implements View.OnClickListener {

    Button courseOne, courseTwo, courseThree, courseFour, courseFive, courseSix;
    TextView day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        setupUI();
    }

    private void setupUI(){
        day = (TextView)findViewById(R.id.day);
        String nameOfDay = getIntent().getExtras().getString("dayName");
        day.setText(nameOfDay);

        courseOne = (Button)findViewById(R.id.course_one);
        String firstCourse = getIntent().getExtras().getString("Course1");
        courseOne.setText(firstCourse);
        checkContent(firstCourse, courseOne);
        courseOne.setOnClickListener(this);

        courseTwo = (Button)findViewById(R.id.course_two);
        String secondCourse = getIntent().getExtras().getString("Course2");
        courseTwo.setText(secondCourse);
        checkContent(secondCourse, courseTwo);
        courseTwo.setOnClickListener(this);

        courseThree = (Button)findViewById(R.id.course_three);
        String thirdCourse = getIntent().getExtras().getString("Course3");
        courseThree.setText(thirdCourse);
        checkContent(thirdCourse, courseThree);
        courseThree.setOnClickListener(this);

        courseFour = (Button)findViewById(R.id.course_four);
        String fourthCourse = getIntent().getExtras().getString("Course4");
        courseFour.setText(fourthCourse);
        checkContent(fourthCourse, courseFour);
        courseFour.setOnClickListener(this);

        courseFive = (Button)findViewById(R.id.course_five);
        String fifthCourse = getIntent().getExtras().getString("Course5");
        courseFive.setText(fifthCourse);
        checkContent(fifthCourse, courseFive);
        courseFive.setOnClickListener(this);

        courseSix = (Button)findViewById(R.id.course_six);
        String sixthCourse = getIntent().getExtras().getString("Course6");
        courseSix.setText(sixthCourse);
        checkContent(sixthCourse, courseSix);
        courseSix.setOnClickListener(this);
    }

    private void checkContent(String courseName, Button course){
        if (courseName.isEmpty()){
            course.setText(R.string.pause);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.course_one:
                switchToCourse();
                break;
            case R.id.course_two:
                switchToCourse();
                break;
            case R.id.course_three:
                switchToCourse();
                break;
            case R.id.course_four:
                switchToCourse();
                break;
            case R.id.course_five:
                switchToCourse();
                break;
            case R.id.course_six:
                switchToCourse();
                break;
        }
    }

    private void switchToCourse(){
        Intent toCourseIntent = new Intent(DayActivity.this, CourseActivity.class);
        startActivity(toCourseIntent);
    }
}
