package com.example.alicia.dostudy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CalendarAddEntryActivity extends Activity {

    private EditText editTitle, editDescription;
    private TextView editDate, editTime, dateValue, timeValue;
    private TextView reminder, repetition;
    private Button add;

    private InternDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar_entry);
        initDB();
        initUI();
        initDatePicker();
        initTimePicker();
        initAddButton();
    }

    private void initDB() {
        database = new InternDatabase(this);
    }

    private void initUI() {
        add = (Button) findViewById(R.id.addCalendarEntry);
        editTitle = (EditText) findViewById(R.id.event_title);
        editDescription = (EditText) findViewById(R.id.event_description);
        editDate = (TextView) findViewById(R.id.addCalendarEntryStart);
        dateValue = (TextView) findViewById(R.id.addEntryStartTime);
        timeValue = (TextView) findViewById(R.id.addEntryEndTime);
        editTime = (TextView) findViewById(R.id.addCalendarEntryEnd);
        reminder = (TextView) findViewById(R.id.addCalendarEntryReminder);
        repetition = (TextView) findViewById(R.id.addCalendarEntryRepetition);
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

    private void initAddButton() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();
                String date = dateValue.getText().toString();
                String time = timeValue.getText().toString();

                if (title.equals("") || description.equals("") || date.equals("") || time.equals("")) {
                    Toast toast = Toast.makeText(CalendarAddEntryActivity.this, "Fülle bitte alle Felder aus", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    editTitle.setText("");
                    editDescription.setText("");
                    editDate.setText("");
                    editTime.setText("");
                    addEntry(title, description, date, time);

                }
            }
        });
    }

    private void addEntry(String title, String description, String date, String time) {
        CalendarEntry entry = new CalendarEntry(title, description, DateFormatter.dateToInteger(date), time);
        database.insertCalendarEntry(title, description, date, time);
    }

    private void initDatePicker() {
       editDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showDatePickerDialog();
           }
       });
    }

    private void initTimePicker() {
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), getResources().getString(R.string.date_picker));
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            TextView dateValue = (TextView) getActivity().findViewById(R.id.addEntryStartTime);
            GregorianCalendar date = new GregorianCalendar(i, i1, i2);
            java.text.DateFormat dateformat = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, Locale.GERMANY);
            String dateToString = dateformat.format(date.getTime());
            dateValue.setText(dateToString);
        }
    }

    private void showTimePickerDialog() {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getFragmentManager(), getResources().getString(R.string.time_picker));
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            TextView timeValue = (TextView) getActivity().findViewById(R.id.addEntryEndTime);
            String hour;
            String minute;
            if (i < 10) {
                hour = getResources().getString(R.string.adding_0) + i;
            } else {
                hour = "" + i;
            }
            if (i1 < 10) {
                minute = getResources().getString(R.string.adding_0) + i1;
            } else {
                minute = "" + i1;
            }
            timeValue.setText(hour + getResources().getString(R.string.adding_double_dot) + minute);
        }
    }
}