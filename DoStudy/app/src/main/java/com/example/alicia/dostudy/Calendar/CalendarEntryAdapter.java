package com.example.alicia.dostudy.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alicia.dostudy.DateFormatter;
import com.example.alicia.dostudy.R;

import java.util.ArrayList;


public class CalendarEntryAdapter extends ArrayAdapter<CalendarEntry> {

    private Context context;
    private ArrayList<CalendarEntry> arrayList;

    public CalendarEntryAdapter(Context context, ArrayList<CalendarEntry> arrayList) {
        super(context, R.layout.calendar_entry, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.calendar_entry, null);
        } else {
            view = convertView;
        }
        if (arrayList.get(position) != null) {
            TextView title = (TextView) view.findViewById(R.id.entry_title);
            TextView date = (TextView) view.findViewById(R.id.entry_date);
            TextView time = (TextView) view.findViewById(R.id.entry_time);
            TextView description = (TextView) view.findViewById(R.id.entry_description);

            title.setText(arrayList.get(position).getTitle());
            date.setText(DateFormatter.dateToString(arrayList.get(position).getDate()));
            time.setText(arrayList.get(position).getTime());
            description.setText(arrayList.get(position).getDescription());
        }
        return view;
    }
}
