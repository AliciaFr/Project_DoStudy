package com.example.alicia.dostudy.ToDoList;


import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Task implements Comparable<Task> {

    private String name;
    private GregorianCalendar cal;

    public Task(String name, int day, int month, int year) {
        this.name = name;
        cal = new GregorianCalendar(year, month, day);
    }

    // Name der Aufgabe wird zurückgegeben.
    public String getName() {
        return name;
    }

    // Datum wird einen String umgewandelt.
    public String getFormattedDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.GERMANY);
        String date = df.format(cal.getTime());
        return date;
    }

    // Datum wird zurückgegeben.
    public String getDate() {
        return cal.getTime().toString();
    }

    @Override
    public int compareTo(Task another) {
        return getDate().compareTo("" + another.getDate());
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Date: " + getFormattedDate();
    }
}
