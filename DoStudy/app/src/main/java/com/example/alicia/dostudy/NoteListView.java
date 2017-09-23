package com.example.alicia.dostudy;

import java.io.Serializable;

/**
 * Created by Alicia on 23.09.2017.
 */

public class NoteListView implements Comparable<NoteListView>, Serializable {


    private String title;
    private String lecture;
    private int date;
    private String note;
    private String filePathImage;

    public NoteListView() {
    }

    public NoteListView(String title, String lecture, int date) {
        this.title = title;
        this.lecture = lecture;
        this.date = date;
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

    public String getFilePathImage() {
        return filePathImage;
    }

    public void setFilePathImage(String filePathImage) {
        this.filePathImage = filePathImage;
    }

    @Override
    public int compareTo(NoteListView another) {
        int comparisonResult = ( "" + date).compareTo("" + another.getDate());
        if(comparisonResult == 0){
            comparisonResult = DateFormatter.dateToString(date).compareTo(DateFormatter.dateToString(another.getDate()));
        }
        return comparisonResult;
    }
}
