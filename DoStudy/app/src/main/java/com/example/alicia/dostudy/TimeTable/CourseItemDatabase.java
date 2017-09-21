package com.example.alicia.dostudy.TimeTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Vera on 18.09.2017.
 */

public class CourseItemDatabase {

    private static final String DATABASE_NAME = "courselist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "courseitem";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "course";
    public static final String KEY_BEGIN = "timeBegin";
    public static final String KEY_END = "timeEnd";
    public static final String KEY_ROOM = "room";
    public static final String KEY_LECTURER = "lecturer";
    public static final String KEY_TEST_DATE = "testDate";
    public static final String KEY_COLOUR = "colour";

    public static final int COLUMN_NAME_INDEX = 1;
    public static final int COLUMN_BEGIN_INDEX = 2;
    public static final int COLUMN_END_INDEX = 3;
    public static final int COLUMN_ROOM_INDEX = 4;
    public static final int COLUMN_LECTURER_INDEX = 5;
    public static final int COLUMN_DATE_INDEX = 6;
    public static final int COLUMN_COLOUR_INDEX = 7;


    private ToDoDBOpenHelper dbHelper;
    private SQLiteDatabase db;

    public CourseItemDatabase(Context context) {
        dbHelper = new ToDoDBOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    public long insertCourseItem(CourseItem item) {
        ContentValues itemValues = new ContentValues();
        itemValues.put(KEY_NAME, item.getCourseName());
        itemValues.put(KEY_BEGIN, item.getCourseBegin());
        itemValues.put(KEY_END, item.getCourseEnd());
        itemValues.put(KEY_ROOM, item.getRoom());
        itemValues.put(KEY_LECTURER, item.getLecturer());
        itemValues.put(KEY_TEST_DATE, item.getTestDate());
        itemValues.put(KEY_COLOUR, item.getColour());

        return db.insert(TABLE_NAME, null, itemValues);
    }

    public void removeCourseItem(CourseItem item) {
        String toDelete = KEY_NAME + "=?";
        String[] deleteArguments = new String[]{item.getCourseName()};
        db.delete(TABLE_NAME, toDelete, deleteArguments);
    }

    public ArrayList<CourseItem> getAllCourseItems() {
        ArrayList<CourseItem> items = new ArrayList<CourseItem>();
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID, KEY_NAME, KEY_BEGIN, KEY_END, KEY_ROOM, KEY_LECTURER, KEY_TEST_DATE, KEY_COLOUR }, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(COLUMN_NAME_INDEX);
                String timeBegin = cursor.getString(COLUMN_BEGIN_INDEX);
                String timeEnd = cursor.getString(COLUMN_END_INDEX);
                String room = cursor.getString(COLUMN_ROOM_INDEX);
                String lecturer = cursor.getString(COLUMN_LECTURER_INDEX);
                String testDate = cursor.getString(COLUMN_DATE_INDEX);
                String colour = cursor.getString(COLUMN_COLOUR_INDEX);

                items.add(new CourseItem(name, timeBegin, timeEnd, room, lecturer, testDate, colour));

            } while (cursor.moveToNext());
        }
        return items;
    }


    private class ToDoDBOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE = "create table "
                + TABLE_NAME + " (" + KEY_ID + " integer primary key autoincrement, "
                + KEY_NAME + " text not null, "
                + KEY_BEGIN + " text not null, "
                + KEY_END + " text not null, "
                + KEY_ROOM + " text not null, "
                + KEY_LECTURER + " text not null, "
                + KEY_TEST_DATE + " text not null, "
                + KEY_COLOUR + " integer not null);";

        public ToDoDBOpenHelper(Context c, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
            super(c, dbname, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
