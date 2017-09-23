package com.example.alicia.dostudy.Notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alicia.dostudy.DateFormatter;

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


    public void insertNotes(String title, String lecture, String date, String note, String filePathImage){
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTES_TITLE, title);
        values.put(KEY_NOTES_LECTURE, lecture);
        values.put(KEY_NOTES_DATE, DateFormatter.dateToInteger(date));
        values.put(KEY_NOTES_NOTE, note);
        values.put(KEY_NOTES_IMAGE, filePathImage);
        db.insert(DATABASE_TABLE_NOTES, null, values);
        close();
    }

    public String getFilePathImage() {
        Cursor cursor = db.query(DATABASE_TABLE_NOTES, new String[]{
                KEY_NOTES_TITLE, KEY_NOTES_LECTURE, KEY_NOTES_DATE, KEY_NOTES_NOTE, KEY_NOTES_IMAGE}, null, null, null, null, null);
        cursor.moveToPosition(4);
        String imageFilePath = cursor.getString(4);
        cursor.close();
        close();
        return imageFilePath;
    }

    public int getDate() {
        Cursor cursor = db.query(DATABASE_TABLE_NOTES, new String[]{
                KEY_NOTES_TITLE, KEY_NOTES_LECTURE, KEY_NOTES_DATE, KEY_NOTES_NOTE, KEY_NOTES_IMAGE}, null, null, null, null, null);
        cursor.moveToPosition(2);
        int date = cursor.getInt(2);
        cursor.close();
        close();
        return date;
    }

    public String title() {
        Cursor cursor = db.query(DATABASE_TABLE_NOTES, new String[]{
                KEY_NOTES_TITLE, KEY_NOTES_LECTURE, KEY_NOTES_DATE, KEY_NOTES_NOTE, KEY_NOTES_IMAGE}, null, null, null, null, null);
        cursor.moveToPosition(0);
        String title = cursor.getString(0);
        cursor.close();
        close();
        return title;
    }

    public String lecture() {
        Cursor cursor = db.query(DATABASE_TABLE_NOTES, new String[]{
                KEY_NOTES_TITLE, KEY_NOTES_LECTURE, KEY_NOTES_DATE, KEY_NOTES_NOTE, KEY_NOTES_IMAGE}, null, null, null, null, null);
        cursor.moveToPosition(1);
        String lecture = cursor.getString(1);
        cursor.close();
        close();
        return lecture;
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
                String imageFilePath = cursor.getString(4);
                notes.add(new Notes(title, lecture, date, note, imageFilePath));
            } while(cursor.moveToNext());
        }
        cursor.close();
        close();

        return notes;
    }

    public void deleteNotes(String title, String lecture){
        String whereClause = KEY_NOTES_TITLE + " =? AND " + KEY_NOTES_LECTURE + " =?";
        open();
        db.delete(DATABASE_TABLE_NOTES, whereClause, new String[]{
                title, lecture
        });
        close();
    }

    private class DBOpenHelper extends SQLiteOpenHelper {
        private final String CREATE_NOTES_DATABASE = "create table " + DATABASE_TABLE_NOTES + " ("
                + KEY_NOTES_TITLE + " string, " + KEY_NOTES_LECTURE + " string, " + KEY_NOTES_DATE + " integer, " + KEY_NOTES_NOTE + " string, "
                + KEY_NOTES_IMAGE + " string);";

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
