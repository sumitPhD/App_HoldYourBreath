package com.example.sumit.holdyourbreath;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SUMIT on 4/4/2020.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "breath.db";
    public static final String TABLE_NAME = "breath_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "Breath1";
    public static final String COL3 = "Breath2";
    public static final String COL4 = "Breath3";
    public static final String COL5 = "Average";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Breath1 INTEGER,Breath2 INTEGER,Breath3 INTEGER,Average INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String breath1,String breath2,String breath3,String avg ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, breath1);
        contentValues.put(COL3, breath2);
        contentValues.put(COL4, breath3);
        contentValues.put(COL5, avg);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
                return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
