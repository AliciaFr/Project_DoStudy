package com.example.alicia.dostudy.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.alicia.dostudy.R;
import java.util.ArrayList;
import java.util.Collections;

/*
* this activity is called when the Notes button from the Home Menu is clicked
* It shows a listview with all saved Notes
* */

public class NotesActivity extends AppCompatActivity {

    private ImageView addNote;
    private ListView listView;
    private ArrayList<Notes> arrayList = new ArrayList<>();
    private NotesAdapter adapter;
    private NotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initDB();
        initUI();
        updateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        arrayList.clear();
        arrayList.addAll(database.getAllNotes());
        System.out.print(listView + " ListView");
        listView.setAdapter(adapter);
        sortData();
    }

    private void sortData() {
        Collections.sort(arrayList);
        adapter.notifyDataSetChanged();
    }

    private void initUI() {
        initListView();
        initListAdapter();
        addNote = (ImageView) findViewById(R.id.notes_add_note);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotesActivity.this, AddNoteActivity.class);
                startActivity(i);
            }
        });
    }

    private void initListAdapter() {
        adapter = new NotesAdapter(this, arrayList);
    }

    /* sends the information of the note via intent to the DetailsActivity */
    private void initListView() {
        listView = (ListView) findViewById(R.id.notes_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(NotesActivity.this, NotesDetails.class);
                intent.putExtra(getResources().getString(R.string.notes_intent), arrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initDB() {
        database = new NotesDatabase(this);
    }
}
