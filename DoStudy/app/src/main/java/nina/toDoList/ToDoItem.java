package nina.toDoList;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by ninayazmin on 07.09.2017.
 */

public class ToDoItem implements Comparable<ToDoItem> {

    private String name;
    private GregorianCalendar cal;
    private String taskContent;

    public ToDoItem(String name, int day, int month, int year) {
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
    public int compareTo(ToDoItem another) {
        return getDueDate().compareTo(another.getDueDate());
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Date: " + getFormattedDate();
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskContent() {
        return taskContent;
    }
}


