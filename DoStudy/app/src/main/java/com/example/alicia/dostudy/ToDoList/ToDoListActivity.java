package com.example.alicia.dostudy.ToDoList;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alicia.dostudy.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class ToDoListActivity extends AppCompatActivity {

    private ArrayList<Task> tasks;
    private ToDoListAdapter tasks_adapter;
    private ToDoListDatabase toDoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        initTaskList();
        initDatabase();
        initUI();
        refreshArrayList();
    }

    private void initDatabase() {
        toDoDB = new ToDoListDatabase(this);
        toDoDB.open();
    }

    private void refreshArrayList(){
        ArrayList tempList = toDoDB.getAllToDoItems();
        tasks.clear();
        tasks.addAll(tempList);
        tasks_adapter.notifyDataSetChanged();
    }

    private void initTaskList() {
        tasks = new ArrayList<Task>();
        initListAdapter();
    }

    private void initUI() {
        initTaskButton();
        initListView();
        initDateField();
    }

    private void initTaskButton() {
        Button addTaskButton = (Button) findViewById(R.id.todo_edit_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputToList();
            }
        });
    }

    // Initialisierung der EditTexts
    private void addInputToList() {
        EditText edit = (EditText) findViewById(R.id.todo_edit_input);
        EditText dateEdit = (EditText) findViewById(R.id.todo_edit_date);
        String task = edit.getText().toString();
        String date = dateEdit.getText().toString();

        if (!task.equals("") && !date.equals("")) {
            edit.setText("");
            dateEdit.setText("");
            addNewTask(task, date);
            // Wenn beide Felder erfolgreich ausgefüllt wurden, bestätigt ein Toast die Speicherung.
            Toast.makeText(this, "Gespeichert!", Toast.LENGTH_SHORT).show();
        } else {
            // Falls ein Feld oder beide Felder nicht ausgefüllt sind, erscheint ein Toast, der daraufhin weist, die Felder zu füllen.
            Toast.makeText(this, "Bitte Aufgabe und Datum eingeben", Toast.LENGTH_LONG).show();
        }
    }

    private void initListView() {
        ListView list = (ListView) findViewById(R.id.todo_list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                removeTaskAtPosition(position);
                return true;
            }
        });
    }

    private void initListAdapter() {
        ListView list = (ListView) findViewById(R.id.todo_list);
        tasks_adapter = new ToDoListAdapter(this, tasks);
        list.setAdapter(tasks_adapter);
    }

    // Speichern der Aufgabe in der Datenbank
    private void addNewTask(String task, String date) {
        Date dueDate = getDateFromString(date);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dueDate);

        Task newTask = new Task(task, cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

        toDoDB.insertToDoItem(newTask);
        refreshArrayList();
    }

    // Datumseingabe mit dem DatePicker verbinden.
    private void initDateField() {
        EditText dateEdit = (EditText) findViewById(R.id.todo_edit_date);
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showDatePickerDialog();
            }
        });

    }

    // Die Aufgabe an der jeweiligen Position wird aus der Datenbank gelöscht.
    private void removeTaskAtPosition(int position) {
        if (tasks.get(position) != null) {
            toDoDB.removeToDoItem(tasks.get(position));
            refreshArrayList();
        }
    }

    public void showDatePickerDialog() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");
    }

    private Date getDateFromString(String dateString) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.GERMANY);
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    // Optionen Menü der ActionBar wird erstellt.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_todolist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Aktionen in dem Dropdown-Menü der Actionsbar - Aufgaben sortieren und alle Aufgbaben löschen.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_sort:
                sortList();
                return true;
            case R.id.delete_all:
                deleteList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteList() {
        tasks.clear();
        toDoDB.removeAllItems();
        tasks_adapter.notifyDataSetChanged();
    }

    private void sortList() {
        Collections.sort(tasks);
        tasks_adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toDoDB.close();
    }
}
