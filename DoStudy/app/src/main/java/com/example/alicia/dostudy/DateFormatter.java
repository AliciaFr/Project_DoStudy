package com.example.alicia.dostudy;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Alicia on 18.09.2017.
 */

public class DateFormatter {

    public static String dateToString(int dateValue){
        String date = "" + dateValue;
        String day = "" + date.charAt(6) + date.charAt(7);
        String month = "" + date.charAt(4)+ date.charAt(5);
        String year = "" + date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);
        return "" + day + "." + month + "."+ year;
    }

    public static Date stringToDate(String date){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {

            return new Date();
        }
    }

    public static int dateToInteger(String date){
        Date dueDate = stringToDate(date);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dueDate);
        String month;
        String day;
        if (cal.get(Calendar.MONTH) + 1 < 10) {
            month = "" + 0 + (cal.get(Calendar.MONTH) + 1);
        } else {
            month = "" + (cal.get(Calendar.MONTH) + 1);
        }
        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "" + 0 + cal.get(Calendar.DAY_OF_MONTH);
        } else {
            day = "" + cal.get(Calendar.DAY_OF_MONTH);
        }
        return Integer.parseInt("" + cal.get(Calendar.YEAR) + month + day);

    }
}
