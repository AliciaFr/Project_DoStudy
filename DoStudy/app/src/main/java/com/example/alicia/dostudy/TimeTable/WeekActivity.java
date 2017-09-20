package com.example.alicia.dostudy.TimeTable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alicia.dostudy.R;

public class WeekActivity extends AppCompatActivity implements View.OnClickListener{

    Button monday, tuesday, wednesday, thursday, friday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setupUI();
    }

    private void setupUI(){
        monday = (Button)findViewById(R.id.monday_button);
        monday.setOnClickListener(this);

        tuesday = (Button)findViewById(R.id.tuesday_button);
        tuesday.setOnClickListener(this);

        wednesday = (Button)findViewById(R.id.wednesday_button);
        wednesday.setOnClickListener(this);

        thursday = (Button)findViewById(R.id.thursday_button);
        thursday.setOnClickListener(this);

        friday = (Button)findViewById(R.id.friday_button);
        friday.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.monday_button:
                Intent openMonday = new Intent(WeekActivity.this, MondayActivity.class);
                startActivity(openMonday);
                break;
            case R.id.tuesday_button:
                Intent openTuesday = new Intent(WeekActivity.this, TuesdayActivity.class);
                startActivity(openTuesday);
                break;
            case R.id.wednesday_button:
                Intent openWednesday = new Intent(WeekActivity.this, WednesdayActivity.class);
                startActivity(openWednesday);
                break;
            case R.id.thursday_button:
                Intent openThursday = new Intent(WeekActivity.this, ThursdayActivity.class);
                startActivity(openThursday);
                break;
            case R.id.friday_button:
                Intent openFriday = new Intent(WeekActivity.this, FridayActivity.class);
                startActivity(openFriday);
                break;
        }
    }
}
