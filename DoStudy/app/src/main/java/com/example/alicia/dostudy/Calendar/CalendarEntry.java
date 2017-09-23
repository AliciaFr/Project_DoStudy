package com.example.alicia.dostudy.Calendar;
import java.io.Serializable;

/**
 * Created by Alicia on 18.09.2017.
 */

public class CalendarEntry implements Comparable<CalendarEntry>, Serializable {

    private String title;
    private String description;
    private int date;
    private String time;

    public CalendarEntry() {}

    public CalendarEntry(String title, String description, int date, String time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int compareTo(CalendarEntry another) {
        int comparisonResult = ("" + date).compareTo("" + another.getDate());
        if (comparisonResult == 0) {
            comparisonResult = time.compareTo(another.getTime());
        }
        return comparisonResult;
    }
}
