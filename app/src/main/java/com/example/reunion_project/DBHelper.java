package com.example.reunion_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Guestdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    DB.execSQL("create Table Guestlist(name TEXT  primary key, plate TEXT , report TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
    DB.execSQL("drop Table if exists Guestlist");
    }

    public  boolean addguestdata(String ename,String eplate, String ereport)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ename", ename);
        contentValues.put("eplate", eplate);
        contentValues.put("ereport", ereport);
        long result = DB.insert("Guestlist",null,contentValues);
        if (result == -1)
        {
            return false;
        }else {
            return true;
        }
    }

    public  boolean editguestdata(String ename,String eplate, String ereport)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eplate", eplate);
        contentValues.put("ereport", ereport);
        Cursor cursor = DB.rawQuery("Select * from Guestlist where ename = ?",new String[]{ename});
        if (cursor.getCount() > 0)
        {
            long result = DB.update("Guestlist",contentValues,"ename=?",new String[] {ename});
            if(result== -1){
                return false;
            }else {
                return true;
            }
        }else
        {
            return  false;
        }

    }

    public  boolean deleteguestdata(String ename)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Guestlist where ename = ?",new String[]{ename});
        if (cursor.getCount()>0)
        {
            long result=DB.delete("Guestlist","ename=?",new String[] {ename});
            if(result== -1){
                return false;
            }else {
                return true;
            }
        }else
        {
            return  false;
        }

    }
    public  Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Guestlist ",null);
       return  cursor;
    }

}
