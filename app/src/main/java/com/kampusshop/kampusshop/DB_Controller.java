package com.kampusshop.kampusshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "KampusShop.db";
    public static final String TABLE_NAME = "Ilanlar";
    public static final String COL_1 = "IlanId";
    public static final String COL_2 = "Baslik";
    public static final String COL_3 = "Fiyat";
    public static final String COL_4 = "Aciklama";
    public static final String COL_5 = "Marka";

    public DB_Controller(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase veritabani) {
        veritabani.execSQL("create table " + TABLE_NAME + " (IlanId INTEGER PRIMARY KEY AUTOINCREMENT, Baslik TEXT, Fiyat TEXT, Aciklama TEXT, Marka TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase veritabani, int oldVersion, int newVersion) {
        veritabani.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(veritabani);
    }

    public boolean IlanEkle(String Baslik, String Fiyat, String Aciklama, String Marka) {
        SQLiteDatabase veritabani = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Baslik);
        contentValues.put(COL_3, Fiyat);
        contentValues.put(COL_4, Aciklama);
        contentValues.put(COL_5, Marka);
        long result = veritabani.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor IlanListele() {
        SQLiteDatabase veritabani = this.getReadableDatabase();
        Cursor res = veritabani.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
