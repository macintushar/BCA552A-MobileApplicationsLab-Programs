package com.tusharselvakumar.kiacarapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table name and column names
    private static final String TABLE_LOGIN = "login";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_VIN = "VIN";
    private static final String COLUMN_ENGINE_NUMBER = "EngineNumber";
    private static final String COLUMN_PASSWORD = "Password";

    // Create the table creation SQL statement
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_LOGIN + " (" +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_VIN + " TEXT, " +
                    COLUMN_ENGINE_NUMBER + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }

    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns to retrieve
        String[] columns = {COLUMN_EMAIL};

        // Define the selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        // Query the table
        Cursor cursor = db.query(TABLE_LOGIN, columns, selection, selectionArgs, null, null, null);

        // Check if a matching record exists
        boolean hasLogin = cursor.moveToFirst();

        cursor.close();
        db.close();

        return hasLogin;
    }
}