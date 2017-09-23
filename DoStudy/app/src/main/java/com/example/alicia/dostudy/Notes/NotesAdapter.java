package com.example.alicia.dostudy.Notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alicia.dostudy.DateFormatter;
import com.example.alicia.dostudy.R;

import java.util.ArrayList;


public class NotesAdapter extends ArrayAdapter<Notes> {

    private Context context;
    private ArrayList<Notes> arrayList;

    public NotesAdapter(Context context, ArrayList<Notes> arrayList) {
        super(context, R.layout.notes, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.notes, null);
        } else {
            view = convertView;
        }

        if (arrayList.get(position) != null) {
            TextView title = (TextView) view.findViewById(R.id.notes_title);
            TextView date = (TextView) view.findViewById(R.id.notes_date);
            TextView lecture = (TextView) view.findViewById(R.id.notes_lecture);

            title.setText(arrayList.get(position).getTitle());
            date.setText(DateFormatter.dateToString(arrayList.get(position).getDate()));
            lecture.setText(arrayList.get(position).getLecture());
        }
        return view;
    }
}
