package com.example.alicia.dostudy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class CalendarAddEntryActivity extends Activity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextView startText, endText, startValue, endValue;
    private TextView reminder, repetition;


    private int day, month, year, hour, minute;
    private int dayStart, monthStart, yearStart, hourStart, minuteStart;
    private int dayEnd, monthEnd, yearEnd, hourEnd, minuteEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar_entry);
        initUI();
    }

    private void initUI() {
        startText = (TextView) findViewById(R.id.addCalendarEntryStart);
        startValue = (TextView) findViewById(R.id.addEntryStartTime);
        endText = (TextView) findViewById(R.id.addCalendarEntryEnd);
        endValue = (TextView) findViewById(R.id.addEntryEndTime);
        reminder = (TextView) findViewById(R.id.addCalendarEntryReminder);
        repetition = (TextView) findViewById(R.id.addCalendarEntryRepetition);
        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialogStart = new DatePickerDialog(CalendarAddEntryActivity.this,
                        CalendarAddEntryActivity.this, year, month, day);
                datePickerDialogStart.show();
            }
        });
        endText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarAddEntryActivity.this,
                        CalendarAddEntryActivity.this, year, month, day);
                datePickerDialog.show();
            }
        });
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CalendarAddEntryActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_calendar_reminder, null);
                RadioButton mNoReminder = (RadioButton) mView.findViewById(R.id.dialog_calendar_no_reminder);
                RadioButton mAtStart = (RadioButton) mView.findViewById(R.id.dialog_calendar_at_start);
                RadioButton m10Mins = (RadioButton) mView.findViewById(R.id.dialog_calendar_10);
                RadioButton m30Mins = (RadioButton) mView.findViewById(R.id.dialog_calendar_30);
                RadioButton mCustom = (RadioButton) mView.findViewById(R.id.dialog_calendar_custom);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
        repetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CalendarAddEntryActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_calendar_reminder, null);
                RadioButton mNoRepetition = (RadioButton) mView.findViewById(R.id.dialog_calendar_no_repetition);
                RadioButton mDaily = (RadioButton) mView.findViewById(R.id.dialog_calendar_daily);
                RadioButton mWeekly = (RadioButton) mView.findViewById(R.id.dialog_calendar_weekly);
                RadioButton mMonthly = (RadioButton) mView.findViewById(R.id.dialog_calendar_monthly);
                RadioButton mYearly = (RadioButton) mView.findViewById(R.id.dialog_calendar_yearly);
                RadioButton mCustom = (RadioButton) mView.findViewById(R.id.dialog_calendar_custom);
                mCustom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearStart = i;
        monthStart = i1;
        dayStart = i2;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(CalendarAddEntryActivity.this,
                CalendarAddEntryActivity.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourStart = i;
        minuteStart = i1;

        startValue.setText(dayStart + "." + monthStart + "." + yearStart + ", " + hourStart
                + ":" + minuteStart);

        endValue.setText(dayStart + "." + monthStart + "." + yearStart + ", " + hourStart
                + ":" + minuteStart);
    }
}