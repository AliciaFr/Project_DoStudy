package com.example.alicia.dostudy.ToDoList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alicia.dostudy.R;

import java.util.ArrayList;

public class ToDoListAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> taskList;
    private Context context;

    public ToDoListAdapter(Context context, ArrayList<Task> listItems) {
        super(context, R.layout.todolist_listlayout, listItems);
        this.context = context;
        this.taskList = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.todolist_listlayout, null);

        }

        Task task = taskList.get(position);

        if (task != null) {
            TextView taskName = (TextView) v.findViewById(R.id.task_name);
            TextView taskDate = (TextView) v.findViewById(R.id.task_date);

            taskName.setText(task.getName());
            taskDate.setText(task.getFormattedDate());
        }

        return v;
    }

}
