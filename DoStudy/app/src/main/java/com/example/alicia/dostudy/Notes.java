package com.example.alicia.dostudy;

import java.io.Serializable;

public class Notes implements Comparable<Notes>, Serializable {


    private String title;
    private String lecture;
    private int date;
    private String note;
    private long image;

    public Notes() {
    }

    public Notes(String title, String lecture, int date, String note, long image) {
        this.title = title;
        this.lecture = lecture;
        this.date = date;
        this.note = note;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }

    @Override
    public int compareTo(Notes another) {
        int comparisonResult = ( "" + date).compareTo("" + another.getDate());
        if(comparisonResult == 0){
            comparisonResult = DateFormatter.dateToString(date).compareTo(DateFormatter.dateToString(another.getDate()));
        }
        return comparisonResult;
    }
}
