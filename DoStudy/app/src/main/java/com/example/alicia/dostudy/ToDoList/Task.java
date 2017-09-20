package com.example.alicia.dostudy.ToDoList;


import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Task implements Comparable<Task> {

    private String name;
    private GregorianCalendar cal;

    public Task(String name, int day, int month, int year) {
        this.name = name;
        cal = new GregorianCalendar(year, month, day);
    }


    public String getName() {
        return name;
    }

    public String getFormattedDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.GERMANY);
        return df.format(cal.getTime());
    }

    public Date getDueDate() {
        return cal.getTime();
    }

    @Override
    public int compareTo(Task another) {
        return getDueDate().compareTo(another.getDueDate());
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Date: " + getFormattedDate();
    }
}
