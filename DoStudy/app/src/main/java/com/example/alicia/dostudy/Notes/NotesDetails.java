package com.example.alicia.dostudy.Notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alicia.dostudy.DateFormatter;
import com.example.alicia.dostudy.R;

import static com.example.alicia.dostudy.R.id.notes_details_delete;

/* Shows all details of a Note when the belonging listview element is clicked */

public class NotesDetails extends AppCompatActivity {

    private NotesDatabase database;

    private String title, lecture, date, note;
    String imageFilePath;

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

    /* sets up UI components and sets the views to the value of the Note */
    private void initUI() {
        Bitmap mBitmap = BitmapFactory.decodeFile(imageFilePath);

        ImageView delete = (ImageView) findViewById(notes_details_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
            }
        });
        
        TextView tvTitle = (TextView) findViewById(R.id.notes_details_title);
        TextView tvLecture = (TextView) findViewById(R.id.notes_details_lecture);
        TextView tvDate = (TextView) findViewById(R.id.notes_details_date);
        TextView tvNote = (TextView) findViewById(R.id.notes_details_note);
        ImageView image = (ImageView) findViewById(R.id.notes_details_image);

        tvTitle.setText(title);
        tvLecture.setText(lecture);
        tvDate.setText(date);
        tvNote.setText(note);
        image.setImageBitmap(mBitmap);
    }

    /* when the user wants to delete a note an alert dialog will show up
     * when positive the note is deleted from the database */
    private void deleteNote() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        database.deleteNotes(title, lecture);
                        Intent intent = new Intent(NotesDetails.this, NotesActivity.class);
                        startActivity(intent);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialogInterface.dismiss();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.alert_dialog_builder_title))
                .setMessage(getResources().getString(R.string.alert_dialog_builder_delete_title))
                .setPositiveButton(getResources().getString(R.string.alert_dialog_builder_delete_yes), dialogClickListener)
                .setNegativeButton(getResources().getString(R.string.alert_dialog_builder_delete_no), dialogClickListener).show();
    }

    /* gets the data of the selected note from the ListView of the NoteActivity */
    private void informationInBundle(Bundle extras) {
        Notes notes = (Notes) extras.get(getResources().getString(R.string.notes_intent));
        title = notes.getTitle();
        lecture = notes.getLecture();
        date = DateFormatter.dateToString(notes.getDate());
        note = notes.getNote();
        imageFilePath = notes.getFilePathImage();
    }
}
