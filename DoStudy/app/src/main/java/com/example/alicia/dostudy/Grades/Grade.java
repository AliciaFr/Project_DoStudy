package com.example.alicia.dostudy.Grades;

import com.example.alicia.dostudy.ToDoList.Task;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by ninayazmin on 22.09.2017.
 */

public class Grade implements Comparable<Grade> {

    private String course_name;
    private String grade;

    public Grade(String course_name, String grade) {
        this.course_name = course_name;
        this.grade = grade;
    }

    public String getName() {
        return course_name;
    }

    public String getGrade(){
        return grade;
    }

    @Override
    public int compareTo(Grade another) {
        return getName().compareTo("" + another.getName());
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "Note:" + getGrade();
    }
}
