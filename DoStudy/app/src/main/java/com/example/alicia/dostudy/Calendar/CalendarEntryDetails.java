package com.example.alicia.dostudy.Calendar;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alicia.dostudy.DateFormatter;
import com.example.alicia.dostudy.R;

/* Shows all details of a CalendarEntry when the belonging listview element is clicked */

public class CalendarEntryDetails extends AppCompatActivity {

    private String title, description, date, time;
    private CalendarDatabase database;


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
        database = new CalendarDatabase(this);
    }

    /* sets up UI components and sets the views to the value of the entry */
    private void initUI() {
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

    /* when the user wants to delete an entry an alert dialog will show up
     * when positive the entry is deleted from the database */
    private void deleteEntry() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        database.deleteEntry(title, description, date, time);
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


    /* gets the data of the selected note from the ListView of the NoteActivity */
    private void informationInBundle(Bundle extras) {
        CalendarEntry entry = (CalendarEntry) extras.get(getResources().getString(R.string.calendar_entry_intent));
        title = entry.getTitle();
        description = entry.getDescription();
        date = DateFormatter.dateToString(entry.getDate());
        time = entry.getTime();
    }

    /* sets up a toolbar from where the user can share events in messages */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendar_entry_details_menu, menu);
        MenuItem share = menu.findItem(R.id.calendar_entry_details_share);
        ShareActionProvider mShareActionProvider = (android.support.v7.widget.ShareActionProvider)
                MenuItemCompat.getActionProvider(share);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String text = String.format(getResources().getString(R.string.menu_share_text), date, title);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        mShareActionProvider.setShareIntent(shareIntent);
        return true;
    }

    /* sets up back arrow */
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
}
