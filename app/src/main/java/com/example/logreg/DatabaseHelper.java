package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "felhasznalo.db"; //Adatbázis file neve
    public static final String TABLE_NAME = "felhasznalo"; //Tabla neve

    public static final String COL_1 = "ID"; //Oszlop 1
    public static final String COL_2 = "EMAIL"; //Oszlop 2
    public static final String COL_3 = "FELHNEV"; //Oszlop 3
    public static final String COL_4 = "JELSZO"; //Oszlop 4
    public static final String COL_5 = "TELJESNEV"; //Oszlop 4

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT NOT NULL UNIQUE, FELHNEV TEXT NOT NULL UNIQUE, JELSZO NOT NULL, TELJESNEV NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //Insert

    public boolean Insert(String email, String felhnev, String jelszo, String teljesnev)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, felhnev);
        contentValues.put(COL_4, jelszo);
        contentValues.put(COL_5, teljesnev);

        long eredmeny = database.insert(TABLE_NAME,null, contentValues);

        if (eredmeny == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor Select(String username, String password)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor eredmeny = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " = ? and " + COL_4 + " = ?", new String[]{String.valueOf(username), String.valueOf(password)});
        return eredmeny;
    }
}
