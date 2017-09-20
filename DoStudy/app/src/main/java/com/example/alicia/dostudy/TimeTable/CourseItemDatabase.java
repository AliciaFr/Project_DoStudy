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

    public static final int COLUMN_NAME_INDEX = 1;

    private ToDoDBOpenHelper dbHelper;
    private SQLiteDatabase db;

    public CourseItemDatabase(Context context, String database_name) {
        dbHelper = new ToDoDBOpenHelper(context, database_name, null, DATABASE_VERSION);
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
        return db.insert(TABLE_NAME, null, itemValues);
    }

    public void removeCourseItem(CourseItem item) {
        String toDelete = KEY_NAME + "=?";
        String[] deleteArguments = new String[]{item.getCourseName()};
        db.delete(TABLE_NAME, toDelete, deleteArguments);
    }

    public ArrayList<CourseItem> getAllCourseItems() {

        ArrayList<CourseItem> items = new ArrayList<CourseItem>();
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID, KEY_NAME }, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(COLUMN_NAME_INDEX);
                items.add(new CourseItem(name));

            } while (cursor.moveToNext());
        }
        return items;
    }

    private class ToDoDBOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE = "create table "
                + TABLE_NAME + " (" + KEY_ID
                + " integer primary key autoincrement, " + KEY_NAME
                + " text not null);";

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
