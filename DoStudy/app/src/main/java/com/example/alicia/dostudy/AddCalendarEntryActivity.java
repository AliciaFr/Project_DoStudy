package com.example.alicia.dostudy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddCalendarEntryActivity extends Activity {

    private EditText editTitle, editDescription;
    private TextView editDate, editTime, dateValue, timeValue;
    private TextView reminder;
    private Button addEntry, addLocation;
    private Switch switchTime, switchLocation;


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
        addEntry = (Button) findViewById(R.id.addCalendarEntry);
        editTitle = (EditText) findViewById(R.id.event_title);
        editDescription = (EditText) findViewById(R.id.event_description);
        editDate = (TextView) findViewById(R.id.addCalendarEntryStart);
        dateValue = (TextView) findViewById(R.id.addEntryStartTime);
        timeValue = (TextView) findViewById(R.id.addEntryEndTime);
        editTime = (TextView) findViewById(R.id.addCalendarEntryEnd);
        reminder = (TextView) findViewById(R.id.addCalendarEntryReminder);
        initReminder();
        initTimeSwitch();
        initLocationServices();
    }

    private void initReminder() {
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddCalendarEntryActivity.this);
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
    }

    private void initTimeSwitch() {
        switchTime = (Switch) findViewById(R.id.switchButton);
        switchTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    editTime.setVisibility(View.GONE);
                    timeValue.setVisibility(View.GONE);
                    timeValue.setText("00:00");
                } else {
                    editTime.setVisibility(View.VISIBLE);
                    timeValue.setVisibility(View.VISIBLE);
                    timeValue.setText("");
                }
            }
        });
    }

    private void initLocationServices() {
        addLocation = (Button) findViewById(R.id.calendar_entry_location_button);
        switchLocation = (Switch) findViewById(R.id.switch_button_location);
        /*addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCalendarEntryActivity.this, AddLocationActivity.class);
                startActivity(intent);
            }
        });*/
        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            }
        });

    }

    private void initAddButton() {
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();
                String date = dateValue.getText().toString();
                String time = timeValue.getText().toString();

                if (title.equals("") || description.equals("") || date.equals("") || time.equals("")) {
                    Toast toast = Toast.makeText(AddCalendarEntryActivity.this, getResources().getString(R.string.toast_not_all_fields), Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    editTitle.setText("");
                    editDescription.setText("");
                    editDate.setText("");
                    editTime.setText("");
                    addEntry(title, description, date, time);
                    Toast toastAdded = Toast.makeText(AddCalendarEntryActivity.this, getResources().getString(R.string.toast_calendar_entry_added), Toast.LENGTH_SHORT);
                    toastAdded.show();
                }
            }
        });
    }

    private void addEntry(String title, String description, String date, String time) {
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

        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            TextView dateValue = (TextView) getActivity().findViewById(R.id.add_note_add_date);
            GregorianCalendar date = new GregorianCalendar(year, month, day);
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

        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            TextView timeValue = (TextView) getActivity().findViewById(R.id.addEntryEndTime);
            String sHour;
            String sMinute;
            if (hour < 10) {
                sHour = getResources().getString(R.string.adding_0) + hour;
            } else {
                sHour = "" + hour;
            }
            if (minute < 10) {
                sMinute = getResources().getString(R.string.adding_0) + minute;
            } else {
                sMinute = "" + minute;
            }
            timeValue.setText(sHour + getResources().getString(R.string.adding_double_dot) + sMinute);
        }
    }
}