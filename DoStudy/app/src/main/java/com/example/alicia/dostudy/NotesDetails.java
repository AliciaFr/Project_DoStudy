package com.example.alicia.dostudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class NotesDetails extends AppCompatActivity {

    private NotesDatabase database;

    private String title, lecture, date, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        informationInBundle(extras);
        initUI();
        initDB();
    }

    private void initDB() {
        database = new NotesDatabase(this);
    }

    private void initUI() {
        ImageView close = (ImageView) findViewById(R.id.notes_details_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotesDetails.this, NotesActivity.class);
                startActivity(i);
            }
        });
        TextView tvTitle = (TextView) findViewById(R.id.notes_details_title);
        TextView tvLecture = (TextView) findViewById(R.id.notes_details_lecture);
        TextView tvDate = (TextView) findViewById(R.id.notes_details_date);
        TextView tvNote = (TextView) findViewById(R.id.notes_details_note);

        tvTitle.setText(title);
        tvLecture.setText(lecture);
        tvDate.setText(date);
        tvNote.setText(note);
    }

    private void informationInBundle(Bundle extras) {
        Notes notes = (Notes) extras.get(getResources().getString(R.string.notes_intent));
        title = notes.getTitle();
        lecture = notes.getLecture();
        date = DateFormatter.dateToString(notes.getDate());
        note = notes.getNote();
    }
}
