package com.example.alicia.dostudy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.example.alicia.dostudy.Calendar.CalendarEntry;
import com.example.alicia.dostudy.Calendar.CalendarDatabase;
import com.example.alicia.dostudy.ToDoList.Task;
import com.example.alicia.dostudy.ToDoList.ToDoListDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;


public class NotificationService extends Service {

    private ArrayList<CalendarEntry> entries = new ArrayList<>();
    private ArrayList<Task> items = new ArrayList<>();
    private ToDoListDatabase toDoDB;
    private CalendarDatabase database;


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initDB();
        getEntries();
        getTasks();
        for (int i = 0; i < entries.size(); i++) {
            scheduleNotification(i);
        }
        for (int i = 0; i < items.size(); i++) {
            toDoNotification(i);
        }
        return START_STICKY;
    }

    private void scheduleNotification(int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY);
        String date = DateFormatter.dateToString(entries.get(i).getDate());
        String time = entries.get(i).getTime();
        String dateAndTime = date + " " + time;
        long reminder = entries.get(i).getReminder();
        long dateAndTimeInLong;
        try {
            Date entryDate = simpleDateFormat.parse(dateAndTime);
            dateAndTimeInLong = entryDate.getTime();
        } catch (ParseException e) {
            return;
        }
        long notificationTime = dateAndTimeInLong - reminder;

        Intent intent = new Intent(getApplicationContext(), CalendarAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, notificationTime, pendingIntent);
        }
    }

     private void toDoNotification(int i){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY);
        String toDoDate = items.get(i).getFormattedDate();
        long dateAndTimeInLong;
         String defaultTime = "13:00";
         String dateAndTime = toDoDate + " " +  defaultTime;
        try {
            Date taskDate = simpleDateFormat.parse(dateAndTime);
            dateAndTimeInLong = taskDate.getTime();
        } catch (ParseException e){
            return;
        }
        long notificationTime = dateAndTimeInLong;

        Intent intent = new Intent(getApplicationContext(), CalendarAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, notificationTime, pendingIntent);
        }
    }

    public void getEntries() {
        entries.clear();
        entries.addAll(database.getAllEntries());
        Collections.sort(entries);
    }

     public void getTasks() {
        items.clear();
        items.addAll(toDoDB.getAllToDoItems());
        Collections.sort(items);
    }


    private void initDB() {
        database = new CalendarDatabase(this);
        toDoDB = new ToDoListDatabase(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
