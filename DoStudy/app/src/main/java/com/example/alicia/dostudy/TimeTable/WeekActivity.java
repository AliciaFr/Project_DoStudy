package com.example.alicia.dostudy.TimeTable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alicia.dostudy.R;

public class WeekActivity extends AppCompatActivity implements View.OnClickListener{

    Button monday, tuesday, wednesday, thursday, friday;
    Button moOne, moTwo, moThree, moFour, moFive, moSix;
    Button tuOne, tuTwo, tuThree, tuFour, tuFive, tuSix;
    Button weOne, weTwo, weThree, weFour, weFive, weSix;
    Button thOne, thTwo, thThree, thFour, thFive, thSix;
    Button frOne, frTwo, frThree, frFour, frFive, frSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setupDayFunctionality();
        setupMondayCourses();
        setupTuesdayCourses();
        setupWednesdayCourses();
        setupThursdayCourses();
        setupFridayCourses();
    }

    private void setupDayFunctionality(){
        monday = (Button)findViewById(R.id.monday);
        tuesday = (Button)findViewById(R.id.tuesday);
        wednesday = (Button)findViewById(R.id.wednesday);
        thursday = (Button)findViewById(R.id.thursday);
        friday = (Button)findViewById(R.id.friday);

        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wednesday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);
    }

    private void setupMondayCourses(){
        moOne = (Button)findViewById(R.id.mo_one);
        moTwo = (Button)findViewById(R.id.mo_two);
        moThree = (Button)findViewById(R.id.mo_three);
        moFour = (Button)findViewById(R.id.mo_four);
        moFive = (Button)findViewById(R.id.mo_five);
        moSix = (Button)findViewById(R.id.mo_six);

        moOne.setOnClickListener(this);
        moTwo.setOnClickListener(this);
        moThree.setOnClickListener(this);
        moFour.setOnClickListener(this);
        moFive.setOnClickListener(this);
        moSix.setOnClickListener(this);
    }

    private void setupTuesdayCourses(){
        tuOne = (Button)findViewById(R.id.tu_one);
        tuTwo = (Button)findViewById(R.id.tu_two);
        tuThree = (Button)findViewById(R.id.tu_three);
        tuFour = (Button)findViewById(R.id.tu_four);
        tuFive = (Button)findViewById(R.id.tu_five);
        tuSix = (Button)findViewById(R.id.tu_six);

        tuOne.setOnClickListener(this);
        tuTwo.setOnClickListener(this);
        tuThree.setOnClickListener(this);
        tuFour.setOnClickListener(this);
        tuFive.setOnClickListener(this);
        tuSix.setOnClickListener(this);
    }

    private void setupWednesdayCourses(){
        weOne = (Button)findViewById(R.id.we_one);
        weTwo = (Button)findViewById(R.id.we_two);
        weThree = (Button)findViewById(R.id.we_three);
        weFour = (Button)findViewById(R.id.we_four);
        weFive = (Button)findViewById(R.id.we_five);
        weSix = (Button)findViewById(R.id.we_six);

        weOne.setOnClickListener(this);
        weTwo.setOnClickListener(this);
        weThree.setOnClickListener(this);
        weFour.setOnClickListener(this);
        weFive.setOnClickListener(this);
        weSix.setOnClickListener(this);
    }

    private void setupThursdayCourses(){
        thOne = (Button)findViewById(R.id.th_one);
        thTwo = (Button)findViewById(R.id.th_two);
        thThree = (Button)findViewById(R.id.th_three);
        thFour = (Button)findViewById(R.id.th_four);
        thFive = (Button)findViewById(R.id.th_five);
        thSix = (Button)findViewById(R.id.th_six);

        thOne.setOnClickListener(this);
        thTwo.setOnClickListener(this);
        thThree.setOnClickListener(this);
        thFour.setOnClickListener(this);
        thFive.setOnClickListener(this);
        thSix.setOnClickListener(this);
    }

    private void setupFridayCourses(){
        frOne = (Button)findViewById(R.id.fr_one);
        frTwo = (Button)findViewById(R.id.fr_two);
        frThree = (Button)findViewById(R.id.fr_three);
        frFour = (Button)findViewById(R.id.fr_four);
        frFive = (Button)findViewById(R.id.fr_five);
        frSix = (Button)findViewById(R.id.fr_six);

        frOne.setOnClickListener(this);
        frTwo.setOnClickListener(this);
        frThree.setOnClickListener(this);
        frFour.setOnClickListener(this);
        frFive.setOnClickListener(this);
        frSix.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.monday:
                switchToDay("Montag", moOne, moTwo, moThree, moFour, moFive, moSix);
                break;
            case R.id.tuesday:
                switchToDay("Dienstag", tuOne, tuTwo, tuThree, tuFour, tuFive, tuSix);
                break;
            case R.id.wednesday:
                switchToDay("Mittwoch", weOne, weTwo, weThree, weFour, weFive, weSix);
                break;
            case R.id.thursday:
                switchToDay("Donnerstag", thOne, thTwo, thThree, thFour, thFive, thSix);
                break;
            case R.id.friday:
                switchToDay("Freitag", frOne, frTwo, frThree, frFour, frFive, frSix);
                break;
            default:
                switchToCourse();
                break;
        }
    }

    private void switchToDay(String day, Button one, Button two, Button three, Button four, Button five, Button six){
        String courseOne = one.getText().toString();
        String courseTwo = two.getText().toString();
        String courseThree = three.getText().toString();
        String courseFour = four.getText().toString();
        String courseFive = five.getText().toString();
        String courseSix = six.getText().toString();

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
