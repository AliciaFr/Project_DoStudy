package com.example.alicia.dostudy.TimeTable;

/**
 * Created by Vera on 18.08.2017.
 */

public class CourseItem {

    private String courseName, courseBegin, courseEnd, room, lecturer, testDate;
    int colour;

    /*
    defines the content of a CourseItem
    each CourseItem has a name, a start time, an end, a room, a lecturer, a test date and a colour
     */

    public CourseItem(String courseName, String courseBegin, String courseEnd, String room, String lecturer, String tesDate, int colour){
        this.courseName = courseName;
        this.courseBegin = courseBegin;
        this.courseEnd = courseEnd;
        this.room = room;
        this.lecturer = lecturer;
        this.testDate = tesDate;
        this.colour = colour;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getCourseBegin(){
        return courseBegin;
    }

    public String getCourseEnd(){
        return courseEnd;
    }

    public String getRoom(){
        return room;
    }

    public String getLecturer(){
        return lecturer;
    }

    public String getTestDate(){
        return testDate;
    }

    public int getColour(){
        return colour;
    }

    @Override
    public String toString() {
        return "Name: " + getCourseName() + ", Beginn: " + getCourseBegin() +
                ", End: " + getCourseEnd() + ", Room: " + getRoom() +
                ", Lecturer: " + getLecturer() + ", TestDate: " + getTestDate() +
                ", Colour: " + getColour();
    }
}
