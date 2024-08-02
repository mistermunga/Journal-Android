package com.example.personne;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Diary.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Entries(Entry text, Date text primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Entries");
    }

    public Boolean insertEntry(String entry, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Entry", entry);
        contentValues.put("Date", date);

        long result = db.insert("Entries", null, contentValues);

        return result != -1;
    }

    public Boolean updateEntry(String entry, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Entry", entry);

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from Entries where Date = ?", new String[]{date});

        if(cursor.getCount() > 0){
            long result = db.update("Entries", contentValues, "Date = ?", new String[]{date});
            return result != -1;
        } else {
            return false;
        }
    }

    public Boolean deleteEntry(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Entries", "Date = ?", new String[]{date});
        return result != -1;
    }

    public Cursor getEntry(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from Entries where Date = ?", new String[]{date});
        return cursor;
    }

    public boolean checkEntry(String date){
        //check if entry exists
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from Entries where Date = ?", new String[]{date});
        return cursor.getCount() > 0;
    }

    public Cursor getAllEntries(){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from Entries", null);
        return cursor;
    }
}
