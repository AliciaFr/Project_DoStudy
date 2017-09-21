package com.example.alicia.dostudy.TimeTable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alicia.dostudy.R;

import java.util.ArrayList;

/**
 * Created by Vera on 18.09.2017.
 */

public class CourseItemAdapter extends ArrayAdapter<CourseItem> {

    private ArrayList<CourseItem> itemArrayList;
    private Context context;

    public CourseItemAdapter(Context context, ArrayList<CourseItem> items) {
        super(context, R.layout.course_item, items);
        this.context = context;
        this.itemArrayList = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.course_item, null);

        }

        CourseItem item = itemArrayList.get(position);

        if (item != null) {
            TextView courseName = (TextView) view.findViewById(R.id.course_name);
            TextView courseBegin = (TextView) view.findViewById(R.id.course_begin);

            courseName.setText(item.getCourseName());
            courseBegin.setText(item.getCourseBegin());
        }

        return view;
    }
}
