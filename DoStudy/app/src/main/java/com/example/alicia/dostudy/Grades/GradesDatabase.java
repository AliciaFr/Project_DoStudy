package com.example.alicia.dostudy.Grades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alicia.dostudy.ToDoList.Task;
import com.example.alicia.dostudy.ToDoList.ToDoListDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ninayazmin on 22.09.2017.
 */

public class GradesDatabase {

    private static final String DATABASE_NAME = "grades.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE = "courses";

    public static final String KEY_ID = "_id";
    public static final String KEY_TASK = "course";


    public static final int COLUMN_TASK_INDEX = 1;
    public static final int COLUMN_DATE_INDEX = 2;

    private GradesDatabase.ToDoDBOpenHelper dbHelper;

    private SQLiteDatabase database;

    public GradesDatabase(Context context) {
        dbHelper = new GradesDatabase.ToDoDBOpenHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public void open() throws SQLException {
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            database = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        database.close();
    }

    public long insertCourse(Task item) {
        ContentValues itemValues = new ContentValues();
        itemValues.put(KEY_TASK, item.getName());
        return database.insert(DATABASE_TABLE, null, itemValues);
    }

    public void removeCourse(Grade item) {

        String toDelete = KEY_TASK + "=?";
        String[] deleteArguments = new String[]{item.getName()};
        database.delete(DATABASE_TABLE, toDelete, deleteArguments);

    }

    public ArrayList<Grade> getAllCourses() {
        open();
        ArrayList<Grade> course = new ArrayList<Grade>();
        Cursor cursor = database.query(DATABASE_TABLE, new String[] { KEY_ID,
                KEY_TASK}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String task = cursor.getString(COLUMN_TASK_INDEX);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return course;
    }

    private class ToDoDBOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE = "create table "
                + DATABASE_TABLE + " (" + KEY_ID
                + " integer primary key autoincrement, " + KEY_TASK
                + " text not null, " + " text);";

        public ToDoDBOpenHelper(Context c, String dbname,
                                SQLiteDatabase.CursorFactory factory, int version) {
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

