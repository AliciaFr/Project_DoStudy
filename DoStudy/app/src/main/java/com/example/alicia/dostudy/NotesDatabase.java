package com.example.alicia.dostudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Alicia on 21.09.2017.
 */

public class NotesDatabase {

    private static final String DATABASE_NAME = "notesdatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE_NOTES= "notes";


    // Constants for notes database
    private static final String KEY_NOTES_TITLE = "title";
    private static final String KEY_NOTES_LECTURE = "lecture";
    private static final String KEY_NOTES_DATE = "date";
    private static final String KEY_NOTES_NOTE = "note";
    private static final String KEY_NOTES_IMAGE = "image";

    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public NotesDatabase(Context context) {
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

    public void insertNotes(String title, String lecture, String date, String note, long image){
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTES_TITLE, title);
        values.put(KEY_NOTES_LECTURE, lecture);
        values.put(KEY_NOTES_DATE, DateFormatter.dateToInteger(date));
        values.put(KEY_NOTES_NOTE, note);
        db.insert(DATABASE_TABLE_NOTES, null, values);
        close();
    }

    public ArrayList<Notes> getAllNotes(){
        open();
        ArrayList<Notes> notes = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE_NOTES, new String[]{
                KEY_NOTES_TITLE, KEY_NOTES_LECTURE, KEY_NOTES_DATE, KEY_NOTES_NOTE, KEY_NOTES_IMAGE}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(0);
                String lecture = cursor.getString(1);
                int date = cursor.getInt(2);
                String note = cursor.getString(3);
                long image = cursor.getLong(4);
                notes.add(new Notes(title, lecture, date, note, image));
            } while(cursor.moveToNext());
        }
        cursor.close();
        close();

        return notes;
    }

    public void deleteNotes(String title, String description, String time){
        String whereClause = KEY_NOTES_TITLE + " =? AND " + KEY_NOTES_LECTURE + " =?";
        open();
        db.delete(DATABASE_TABLE_NOTES, whereClause, new String[]{
                title, description, time
        });
        close();
    }

    private class DBOpenHelper extends SQLiteOpenHelper {
        private final String CREATE_NOTES_DATABASE = "create table " + DATABASE_TABLE_NOTES + " ("
                + KEY_NOTES_TITLE + " string, " + KEY_NOTES_LECTURE + " string, " + KEY_NOTES_DATE + " integer, " + KEY_NOTES_NOTE + " string, "
                + KEY_NOTES_IMAGE + " long);";

        public DBOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_NOTES_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }


}
