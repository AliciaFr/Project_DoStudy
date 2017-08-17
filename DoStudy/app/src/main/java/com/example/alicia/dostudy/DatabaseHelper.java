package com.example.alicia.dostudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alicia on 15.08.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsDb";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERNAME = "username";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " (id integer primary key not null, " + COLUMN_NAME + " text not null, "
            + COLUMN_EMAIL + " email not null, " + COLUMN_USERNAME + " not null, " + COLUMN_PASSWORD + " text not null";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASSWORD, c.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPassword(String email) {
        db = this.getReadableDatabase();
        String query = "select email, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d(cursor.toString(), "cursor");
        String emailAddress, password;
        password = "not found";
        Log.d(password, "searchPassword: ");
        if (cursor.moveToFirst()) {
            do {
                emailAddress = cursor.getString(0);
                Log.d(emailAddress, " Email");
                if (emailAddress.equals(email)) {
                    password = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return password;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
