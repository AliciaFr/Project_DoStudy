package com.example.alicia.dostudy.TimeTable;

/**
 * Created by Vera on 18.09.2017.
 */

public class CourseItem {

    private String courseName;

    public CourseItem(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName(){
        return courseName;
    }

    @Override
    public String toString() {
        return "Name: " + getCourseName();
    }
}
