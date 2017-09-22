package com.example.alicia.dostudy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alicia.dostudy.Notes.NotesDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class AddNoteActivity extends AppCompatActivity {

    private EditText editTitle, editLecture, editNote;
    private TextView addDate, selectedDate, addPicture;
    private Button addButton;

    private NotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initDB();
        initUI();
        initDatePicker();
        initTakePicture();
        initAddButton();
    }

    private void initDB() {
        database = new NotesDatabase(this);
    }

    private void initUI() {
        editTitle = (EditText) findViewById(R.id.add_note_title);
        editLecture = (EditText) findViewById(R.id.add_note_lecture);
        editNote = (EditText) findViewById(R.id.add_note);
        addDate = (TextView) findViewById(R.id.add_note_add_date);
        selectedDate = (TextView) findViewById(R.id.add_note_date);
        addPicture = (TextView) findViewById(R.id.add_note_add_picture);
        addButton = (Button) findViewById(R.id.add_note_button);
    }

    private void initDatePicker() {
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void initTakePicture() {

    }

    private void initAddButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                String lecture = editLecture.getText().toString();
                String date = selectedDate.getText().toString();
                String note = editNote.getText().toString();

                if (title.equals("")) {
                    Toast toast = Toast.makeText(AddNoteActivity.this, getResources().getString(R.string.toast_not_all_fields), Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    editTitle.setText("");
                    editLecture.setText("");
                    selectedDate.setText("");
                    editNote.setText("");
                    long longfiller = 0;
                    addNote(title, lecture, date, note, longfiller);
                    Toast toastAdded = Toast.makeText(AddNoteActivity.this, getResources().getString(R.string.toast_note_added), Toast.LENGTH_SHORT);
                    toastAdded.show();
                }
            }
        });
    }

    private void addNote(String title, String lecture, String date, String note, long image) {
        database.insertNotes(title, lecture, date, note, image);
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
            TextView dateValue = (TextView) getActivity().findViewById(R.id.add_note_date);
            GregorianCalendar date = new GregorianCalendar(year, month, day);
            java.text.DateFormat dateformat = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, Locale.GERMANY);
            String dateToString = dateformat.format(date.getTime());
            dateValue.setText(dateToString);
        }
    }


}
