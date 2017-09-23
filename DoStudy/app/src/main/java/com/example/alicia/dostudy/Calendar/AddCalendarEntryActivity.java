package com.example.alicia.dostudy.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alicia.dostudy.R;

public class AddCalendarEntryActivity extends Activity {

    private EditText editTitle;
    private TextView editDate, editTime, dateValue, timeValue, selectedCategory;
    private TextView tvReminder;
    private Button addEntry, addLocation;
    private Switch switchTime, switchLocation;

    private CalendarDatabase database;

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
        database = new CalendarDatabase(this);
    }

    private void initUI() {
        addEntry = (Button) findViewById(R.id.addCalendarEntry);
        editTitle = (EditText) findViewById(R.id.event_title);
        editDate = (TextView) findViewById(R.id.addCalendarEntryDate);
        dateValue = (TextView) findViewById(R.id.add_note_add_date);
        timeValue = (TextView) findViewById(R.id.addEntryEndTime);
        editTime = (TextView) findViewById(R.id.addCalendarEntryEnd);
        initReminder();
        initTimeSwitch();
        initSpinner();
        initLocationServices();
    }

    private void initSpinner() {
        Spinner categorySpinner = (Spinner) findViewById(R.id.add_calendar_entry_category);
        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(this, R.array.entry_category_array,
                R.layout.support_simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                selectedCategory = (TextView) view;
                /*((TextView) parentView.getChildAt(0)).setTextColor(Color.GREEN);
                ((TextView) parentView.getChildAt(1)).setTextColor(Color.BLACK);
                ((TextView) parentView.getChildAt(2)).setTextColor(Color.YELLOW);
                ((TextView) parentView.getChildAt(3)).setTextColor(Color.GRAY);
                ((TextView) parentView.getChildAt(4)).setTextColor(getResources().getColor(R.color.holoBlue));*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initReminder() {
        Spinner reminderSpinner = (Spinner) findViewById(R.id.add_calendar_entry_reminder);
        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(this, R.array.entry_reminder_array,
                R.layout.support_simple_spinner_dropdown_item);
        reminderSpinner.setAdapter(categoryAdapter);
        reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                tvReminder = (TextView) view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                String category = selectedCategory.getText().toString();
                String date = dateValue.getText().toString();
                String time = timeValue.getText().toString();
                String reminder = tvReminder.getText().toString();
                long reminderInMilliseconds = 0;
                switch (reminder) {
                    case "Pünktlich":
                        reminderInMilliseconds = 0;
                        break;
                    case "15 Min. vorher":
                        reminderInMilliseconds = 15 * 60000;
                        break;
                    case "30 Min. vorher":
                        reminderInMilliseconds = 30 * 60000;
                        break;
                    case "2 Stunden vorher":
                        reminderInMilliseconds = 2 * 60 * 60000;
                        break;
                    case "5 Stunden vorher":
                        reminderInMilliseconds = 5 * 60 * 60000;
                        break;
                    case "12 Stunden vorher":
                        reminderInMilliseconds = 12 * 60 * 60000;
                        break;
                    case "24 Stunden vorher":
                        reminderInMilliseconds = 24 * 60 * 60000;
                        break;
                }

                if (title.equals("")) {
                    Toast toast = Toast.makeText(AddCalendarEntryActivity.this, getResources().getString(R.string.toast_not_all_fields), Toast.LENGTH_SHORT);
                    toast.show();
                } else if (category.equals("")) {
                    Toast toast = Toast.makeText(AddCalendarEntryActivity.this, getResources().getString(R.string.toast_missing_category), Toast.LENGTH_SHORT);
                    toast.show();
                } else if (date.equals("")) {
                    Toast toast = Toast.makeText(AddCalendarEntryActivity.this, getResources().getString(R.string.toast_missing_date), Toast.LENGTH_SHORT);
                    toast.show();
                } else if (reminder.equals("")) {
                    Toast toast = Toast.makeText(AddCalendarEntryActivity.this, getResources().getString(R.string.toast_missing_reminder), Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    editTitle.setText("");
                    selectedCategory.setText("");
                    timeValue.setText("");
                    dateValue.setText("");
                    tvReminder.setText("");
                    addEntry(title, category, date, time, reminderInMilliseconds);
                    Toast toastAdded = Toast.makeText(AddCalendarEntryActivity.this, getResources().getString(R.string.toast_calendar_entry_added), Toast.LENGTH_SHORT);
                    toastAdded.show();
                }
            }
        });
    }

    private void addEntry(String title, String description, String date, String time, long reminder) {
        database.insertCalendarEntry(title, description, date, time, reminder);
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
            DateFormat dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
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