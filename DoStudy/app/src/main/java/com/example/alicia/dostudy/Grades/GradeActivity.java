package com.example.alicia.dostudy.Grades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alicia.dostudy.R;
import com.example.alicia.dostudy.ToDoList.Task;
import com.example.alicia.dostudy.ToDoList.ToDoListAdapter;
import com.example.alicia.dostudy.ToDoList.ToDoListDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ninayazmin on 22.09.2017.
 */

public class GradeActivity extends AppCompatActivity {

    private ArrayList<Grade> grades;
    private GradesAdapter grades_adapter;
    private GradesDatabase grates_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        initGradeList();
        initDatabase();
        initUI();
        refreshArrayList();

    }


    private void initGradeList() {
        grades = new ArrayList<Grade>();
        initListAdapter();
    }

    private void initListAdapter() {
        ListView gradeList = (ListView) findViewById(R.id.grades_listview);
        grades_adapter = new GradesAdapter(this, grades);
        gradeList.setAdapter(grades_adapter);

    }

    private void initDatabase() {
        grates_db = new GradesDatabase(this);
        grates_db.open();
    }

    private void initUI() {
        initButton();
        initListView();
    }

    private void initListView() {
        ListView gradeList = (ListView) findViewById(R.id.grades_listview);
        // Wenn ein Item in der Liste lange gedrückt wird, wird der Kurs und dessen Note an dieser Stelle gelöscht.
        gradeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id){
                removeGradeAtPosition(position);
                return true;
            }
        });

    }

    private void initButton() {
        Button addCourseButton = (Button) findViewById(R.id.grades_edit_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourseAndGradeToList();
            }
        });
    }

    // Der Kurs und seine Note werden der Liste hinzugefügt.
    private void addCourseAndGradeToList() {
        EditText editCourse = (EditText) findViewById(R.id.add_course);
        EditText editGrade = (EditText) findViewById(R.id.add_grade);
        String course = editCourse.getText().toString();
        String grade = editGrade.getText().toString();

        if (!course.equals("") && !grade.equals("")) {
            editCourse.setText("");
            editGrade.setText("");
            addNewCourse(course, grade);
            // Wenn beide Felder erfolgreich ausgefüllt wurden, bestätigt ein Toast die Speicherung.
            Toast.makeText(this, "Kurs hinzugefügt!", Toast.LENGTH_SHORT).show();
        } else {
            // Falls ein Feld oder beide Felder nicht ausgefüllt sind, erscheint ein Toast, der daraufhin weist die Felder zu füllen.
            Toast.makeText(this, "Bitte Kurs und Note eingeben", Toast.LENGTH_LONG).show();
        }
    }

    // Der Kurs & dessen Note werden in der Datenbank gespeichert.
    private void addNewCourse(String course, String grade) {
        Grade newGrade = new Grade(course, grade);
        grates_db.insertCourse(newGrade);
        refreshArrayList();
    }


    // Der Kurs an der entsprechenden Stelle wird gelöscht.
    private void removeGradeAtPosition(int position) {
        if (grades.get(position) != null){
            grates_db.removeCourse(grades.get(position));
            refreshArrayList();
        }
    }

    private void refreshArrayList() {
        ArrayList courseList = grates_db.getAllCourses();
        grades.clear();
        grades.addAll(courseList);
        grades_adapter.notifyDataSetChanged();
    }

}
