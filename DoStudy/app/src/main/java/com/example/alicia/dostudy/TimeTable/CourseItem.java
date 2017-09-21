package com.example.alicia.dostudy.TimeTable;

/**
 * Created by Vera on 18.09.2017.
 */

public class CourseItem {

    private String courseName, courseBegin;

    public CourseItem(String courseName, String courseBegin){
        this.courseName = courseName;
        this.courseBegin = courseBegin;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getCourseBegin(){
        return courseBegin;
    }

    @Override
    public String toString() {
        return "Name: " + getCourseName() + ", Beginn: " + getCourseBegin();
    }
}
