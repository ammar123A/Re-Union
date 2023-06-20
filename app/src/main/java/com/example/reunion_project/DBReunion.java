package com.example.reunion_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBReunion extends SQLiteOpenHelper {
    public static final String DBNAME = "Reunion.db";

    public DBReunion( Context context) {
        super(context, "Reunion.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Login (username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Login");
    }

    public Boolean insertuserdata(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = DB.insert("Login", null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Login where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public Boolean checkall(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Login where username = ?  and password = ?", new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean updatepassword(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = DB.update("Login", contentValues, "username =?", new String[]{username});

        if (result == -1)
            return false;
        else
            return true;
    }
}
