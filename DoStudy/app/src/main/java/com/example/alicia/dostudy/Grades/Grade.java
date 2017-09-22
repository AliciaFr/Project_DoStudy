package com.example.alicia.dostudy.Grades;

import com.example.alicia.dostudy.ToDoList.Task;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by ninayazmin on 22.09.2017.
 */

public class Grade implements Comparable<Grade> {

    private String name;

    public Grade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Grade another) {
        return getName().compareTo("" + another.getName());
    }

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}
