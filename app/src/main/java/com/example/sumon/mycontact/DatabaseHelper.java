package com.example.sumon.mycontact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sumon on 3/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "contact_manager.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "contact_table";
    static final String COL_ID = "id";
    static final String COL_NAME = "name";
    static final String COL_PHONE = "phone";

    String CREATE_TABLE_CONTACT = "CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY," + COL_NAME + " TEXT," + COL_PHONE + " TEXT )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
