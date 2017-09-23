package com.example.alicia.dostudy.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alicia.dostudy.DateFormatter;

import java.util.ArrayList;

/**
 * Created by Alicia on 18.09.2017.
 */

public class CalendarDatabase {

    private static final String DATABASE_NAME = "calendarentry";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "entries";

    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";

    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public CalendarDatabase(Context context) {
        helper = new DBOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void open() throws SQLException {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            db = helper.getReadableDatabase();
        }
    }

    private void close() {
        db.close();
    }

    public void insertCalendarEntry(String title, String description, String date, String time){
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_DESCRIPTION, description);
        values.put(KEY_DATE, DateFormatter.dateToInteger(date));
        values.put(KEY_TIME, time);
        db.insert(DATABASE_TABLE, null, values);
        close();
    }

    public ArrayList<CalendarEntry> getAllEntries(){
        open();
        ArrayList<CalendarEntry> entries = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{
                        KEY_TITLE, KEY_DESCRIPTION, KEY_DATE, KEY_TIME}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(0);
                String description = cursor.getString(1);
                int date = cursor.getInt(2);
                String time = cursor.getString(3);
                entries.add(new CalendarEntry(title, description, date, time));
                } while(cursor.moveToNext());
        }
        cursor.close();
        close();

        return entries;
    }

    public void deleteEntry(String title, String description, String time){
        String whereClause = KEY_TITLE + " =? AND " + KEY_DESCRIPTION + " =? AND " + KEY_TIME + " =?";
        open();
        db.delete(DATABASE_TABLE, whereClause, new String[]{
                title, description, time
        });
        close();
    }



    private class DBOpenHelper extends SQLiteOpenHelper {

        private final String CREATE_DATABASE = "create table " + DATABASE_TABLE + " ("
                + KEY_TITLE + " string, " + KEY_DESCRIPTION + " string, " + KEY_DATE + " integer, " + KEY_TIME + " string);";

        public DBOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
