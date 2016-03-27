package com.example.sumon.mycontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Sumon on 3/12/2016.
 */
public class ContactManager {
    private DatabaseHelper helper;
    private Contact contact;
    private SQLiteDatabase database;

    public ContactManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }


    public boolean addContact(Contact contact) {
        this.open();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_NAME, contact.getName());
        contentValues.put(DatabaseHelper.COL_PHONE, contact.getPhoneNumber());

        long inserted = database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        database.close();
        this.close();

        if (inserted > 0) {
            return true;

        } else return false;

    }

    public Contact getContactById(int id) {
        this.open();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME, DatabaseHelper.COL_PHONE}, DatabaseHelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));

        contact = new Contact(mId, name, phoneNumber);
        this.close();
        return contact;
    }

    public ArrayList<Contact> getAllContact() {
        this.open();
        ArrayList<Contact> contactList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
                contact=new Contact(mId,name,phoneNumber);
                contactList.add(contact);
                cursor.moveToNext();

            }

        }
        this.close();
        return contactList;
    }


    public boolean deleteContactById(int id)
    {
        this.open();
        int deleted=database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (deleted>0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateContact(int id,Contact contact)
    {
        this.open();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME,contact.getName());
        contentValues.put(DatabaseHelper.COL_PHONE, contact.getPhoneNumber());
        int updated=database.update(DatabaseHelper.TABLE_NAME,contentValues,DatabaseHelper.COL_ID+" = "+id,null);
        this.close();
        if (updated>0)
        {
            return true;
        }
        else {
            return false;
        }


    }
}
