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

            view.setBackgroundResource(getColourForCourse(item.getColour()));

            TextView courseName = (TextView) view.findViewById(R.id.course_name);
            TextView courseBegin = (TextView) view.findViewById(R.id.course_begin);

            courseName.setText(item.getCourseName());
            courseBegin.setText(item.getCourseBegin());
        }

        return view;
    }

    private int getColourForCourse(int colour){
        if (colour == 0) {
            return R.color.lightBlue;
        } else if (colour == 1) {
            return R.color.darkBlue;
        } else if (colour == 2) {
            return R.color.aquamarine;
        } else if (colour == 3) {
            return R.color.lightGreen;
        } else if (colour == 4) {
            return R.color.darkGreen;
        } else if (colour == 5) {
            return R.color.yellow;
        } else if (colour == 6) {
            return R.color.red;
        } else {
            return R.color.white;
        }
    }
}
