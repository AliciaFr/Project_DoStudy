package com.example.alicia.dostudy.Grades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alicia.dostudy.R;

import java.util.ArrayList;

/**
 * Created by ninayazmin on 22.09.2017.
 */

public class GradesAdapter extends ArrayAdapter<Grade>{

    private ArrayList<Grade> courseList;
    private Context context;

    public GradesAdapter(Context context, ArrayList<Grade> listItems) {
        super(context, R.layout.grades_list, listItems);
        this.context = context;
        this.courseList = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grades_list, null);

        }

        Grade course = courseList.get(position);

        if (course != null) {
            TextView courseName = (TextView) v.findViewById(R.id.course_name);
            TextView courseGrade = (TextView) v.findViewById(R.id.course_grade);


            courseName.setText(course.getName());
            courseGrade.setText(course.getGrade());
        }

        return v;
    }

}

