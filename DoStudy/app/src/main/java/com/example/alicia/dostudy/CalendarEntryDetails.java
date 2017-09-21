package com.example.alicia.dostudy;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CalendarEntryDetails extends AppCompatActivity {

    private String title, description, date, time;
    private InternDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_entry_details);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        informationInBundle(extras);
        initUI();
        initDB();
    }

    private void initDB() {
        database = new InternDatabase(this);
    }

    private void initUI() {
        ImageView close = (ImageView) findViewById(R.id.calendar_entry_details_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CalendarEntryDetails.this, CalendarActivity.class);
                startActivity(i);
            }
        });
        ImageView delete = (ImageView) findViewById(R.id.calendar_entry_details_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEntry();
            }
        });
        TextView tvTitle = (TextView) findViewById(R.id.calendar_entry_details_title);
        TextView tvDescription = (TextView) findViewById(R.id.calendar_entry_details_description);
        TextView tvDate = (TextView) findViewById(R.id.calendar_entry_details_date);
        TextView tvTime = (TextView) findViewById(R.id.calendar_entry_details_time);

        tvTitle.setText(title);
        tvDescription.setText(description);
        tvDate.setText(date);
        tvTime.setText(time);
    }

    private void deleteEntry() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        database.deleteEntry(title, description, time);
                        Intent intent = new Intent(CalendarEntryDetails.this, CalendarActivity.class);
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

    private void informationInBundle(Bundle extras) {
        CalendarEntry entry = (CalendarEntry) extras.get(getResources().getString(R.string.calendar_entry_intent));
        title = entry.getTitle();
        description = entry.getDescription();
        date = DateFormatter.dateToString(entry.getDate());
        time = entry.getTime();
    }
}
