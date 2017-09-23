package com.example.alicia.dostudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import com.example.alicia.dostudy.Notes.NotesActivity;

/* This activity creates a TabLayout where the user can choose between adding notes or grades
 */
public class NotesGradesActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_grades);
        initUI();
    }

    private void initUI() {
        ImageView notes = (ImageView) findViewById(R.id.notes_grades_button_notes);
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toNotes = new Intent(NotesGradesActivity.this, NotesActivity.class);
                startActivity(toNotes);
            }
        });

        ImageView grades = (ImageView) findViewById(R.id.notes_grades_button_grades);
        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGrades = new Intent(NotesGradesActivity.this, GradeActivity.class);
                startActivity(toGrades);
            }
        });

    }
}
