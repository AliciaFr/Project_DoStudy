package com.example.alicia.dostudy.TimeTable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alicia.dostudy.R;

public class WeekActivity extends AppCompatActivity implements View.OnClickListener{

    Button mondayButton, tuesdayButton, wednesdayButton, thursdayButton, fridayButton;
    Button moCourseOne, moCourseTwo, moCourseThree, moCourseFour, moCourseFive, moCourseSix;
    Button tuCourseOne, tuCourseTwo, tuCourseThree, tuCourseFour, tuCourseFive, tuCourseSix;
    Button weCourseOne, weCourseTwo, weCourseThree, weCourseFour, weCourseFive, weCourseSix;
    Button thCourseOne, thCourseTwo, thCourseThree, thCourseFour, thCourseFive, thCourseSix;
    Button frCourseOne, frCourseTwo, frCourseThree, frCourseFour, frCourseFive, frCourseSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setupDays();
        setupMondayCourses();
        setupTuesdayCourses();
        setupWednesdayCourses();
        setupThursdayCourses();
        setupFridayCourses();
    }

    private void setupDays(){
        mondayButton = (Button)findViewById(R.id.monday);
        tuesdayButton = (Button)findViewById(R.id.tuesday);
        wednesdayButton = (Button)findViewById(R.id.wednesday);
        thursdayButton = (Button)findViewById(R.id.thursday);
        fridayButton = (Button)findViewById(R.id.friday);

        mondayButton.setOnClickListener(this);
        tuesdayButton.setOnClickListener(this);
        wednesdayButton.setOnClickListener(this);
        thursdayButton.setOnClickListener(this);
        fridayButton.setOnClickListener(this);
    }

    private void setupMondayCourses(){
        moCourseOne = (Button)findViewById(R.id.mo_one);
        moCourseTwo = (Button)findViewById(R.id.mo_two);
        moCourseThree = (Button)findViewById(R.id.mo_three);
        moCourseFour = (Button)findViewById(R.id.mo_four);
        moCourseFive = (Button)findViewById(R.id.mo_five);
        moCourseSix = (Button)findViewById(R.id.mo_six);

        moCourseOne.setOnClickListener(this);
        moCourseTwo.setOnClickListener(this);
        moCourseThree.setOnClickListener(this);
        moCourseFour.setOnClickListener(this);
        moCourseFive.setOnClickListener(this);
        moCourseSix.setOnClickListener(this);
    }

    private void setupTuesdayCourses(){
        tuCourseOne = (Button)findViewById(R.id.tu_one);
        tuCourseTwo = (Button)findViewById(R.id.tu_two);
        tuCourseThree = (Button)findViewById(R.id.tu_three);
        tuCourseFour = (Button)findViewById(R.id.tu_four);
        tuCourseFive = (Button)findViewById(R.id.tu_five);
        tuCourseSix = (Button)findViewById(R.id.tu_six);

        tuCourseOne.setOnClickListener(this);
        tuCourseTwo.setOnClickListener(this);
        tuCourseThree.setOnClickListener(this);
        tuCourseFour.setOnClickListener(this);
        tuCourseFive.setOnClickListener(this);
        tuCourseSix.setOnClickListener(this);
    }

    private void setupWednesdayCourses(){
        weCourseOne = (Button)findViewById(R.id.we_one);
        weCourseTwo = (Button)findViewById(R.id.we_two);
        weCourseThree = (Button)findViewById(R.id.we_three);
        weCourseFour = (Button)findViewById(R.id.we_four);
        weCourseFive = (Button)findViewById(R.id.we_five);
        weCourseSix = (Button)findViewById(R.id.we_six);

        weCourseOne.setOnClickListener(this);
        weCourseTwo.setOnClickListener(this);
        weCourseThree.setOnClickListener(this);
        weCourseFour.setOnClickListener(this);
        weCourseFive.setOnClickListener(this);
        weCourseSix.setOnClickListener(this);
    }

    private void setupThursdayCourses(){
        thCourseOne = (Button)findViewById(R.id.th_one);
        thCourseTwo = (Button)findViewById(R.id.th_two);
        thCourseThree = (Button)findViewById(R.id.th_three);
        thCourseFour = (Button)findViewById(R.id.th_four);
        thCourseFive = (Button)findViewById(R.id.th_five);
        thCourseSix = (Button)findViewById(R.id.th_six);

        thCourseOne.setOnClickListener(this);
        thCourseTwo.setOnClickListener(this);
        thCourseThree.setOnClickListener(this);
        thCourseFour.setOnClickListener(this);
        thCourseFive.setOnClickListener(this);
        thCourseSix.setOnClickListener(this);
    }

    private void setupFridayCourses(){
        frCourseOne = (Button)findViewById(R.id.fr_one);
        frCourseTwo = (Button)findViewById(R.id.fr_two);
        frCourseThree = (Button)findViewById(R.id.fr_three);
        frCourseFour = (Button)findViewById(R.id.fr_four);
        frCourseFive = (Button)findViewById(R.id.fr_five);
        frCourseSix = (Button)findViewById(R.id.fr_six);

        frCourseOne.setOnClickListener(this);
        frCourseTwo.setOnClickListener(this);
        frCourseThree.setOnClickListener(this);
        frCourseFour.setOnClickListener(this);
        frCourseFive.setOnClickListener(this);
        frCourseSix.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.monday:
                switchToDay("Montag", moCourseOne, moCourseTwo, moCourseThree, moCourseFour, moCourseFive, moCourseSix);
                break;
            case R.id.tuesday:
                switchToDay("Dienstag", tuCourseOne, tuCourseTwo, tuCourseThree, tuCourseFour, tuCourseFive, tuCourseSix);
                break;
            case R.id.wednesday:
                switchToDay("Mittwoch", weCourseOne, weCourseTwo, weCourseThree, weCourseFour, weCourseFive, weCourseSix);
                break;
            case R.id.thursday:
                switchToDay("Donnerstag", thCourseOne, thCourseTwo, thCourseThree, thCourseFour, thCourseFive, thCourseSix);
                break;
            case R.id.friday:
                switchToDay("Freitag", frCourseOne, frCourseTwo, frCourseThree, frCourseFour, frCourseFive, frCourseSix);
                break;
            default:
                switchToCourse();
                break;
        }
    }

    private void switchToDay(String day, Button firstCourse, Button secondCourse, Button thirdCourse, Button fourthCourse, Button fifthCourse, Button sixthCourse){
        String courseOne = firstCourse.getText().toString();
        String courseTwo = secondCourse.getText().toString();
        String courseThree = thirdCourse.getText().toString();
        String courseFour = fourthCourse.getText().toString();
        String courseFive = fifthCourse.getText().toString();
        String courseSix = sixthCourse.getText().toString();

        Intent toDayIntent = new Intent(WeekActivity.this, DayActivity.class);
        toDayIntent.putExtra("dayName", day);
        toDayIntent.putExtra("Course1", courseOne);
        toDayIntent.putExtra("Course2", courseTwo);
        toDayIntent.putExtra("Course3", courseThree);
        toDayIntent.putExtra("Course4", courseFour);
        toDayIntent.putExtra("Course5", courseFive);
        toDayIntent.putExtra("Course6", courseSix);

        startActivity(toDayIntent);
    }

    private void switchToCourse(){
        Intent toCourseIntent = new Intent(WeekActivity.this, CourseActivity.class);
        startActivity(toCourseIntent);
    }
}
