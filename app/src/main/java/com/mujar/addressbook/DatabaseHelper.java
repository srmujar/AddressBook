package com.mujar.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "PHONE";
    public static final String COL4 = "COMPANY";
    public static final String COL5 = "EMAIL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT, PHONE TEXT, COMPANY TEXT, EMAIL TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String phone, String company, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, phone);
        contentValues.put(COL4, company);
        contentValues.put(COL5, email);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    /*public ArrayList<Contacts> getAllData(){
        ArrayList<Contacts> contactsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        while(res.moveToNext()){
            String id = res.getString(0);
            String name = res.getString(1);

            Contacts newContacts = new Contacts(id, name);
            contactsList.add(newContacts);
        }
        return contactsList;
    }*/

    public Cursor getItemID(String name, String phone, String company, String email) {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + name + "'" + " AND " + COL3 + " = '" + phone + "'" +
                    " AND " + COL4 + " = '" + company + "'" + " AND " + COL5 + " = '" + email + "'";
            Cursor data = db.rawQuery(query, null);
            return data;
        }

    public void updateData(String newName, String newPhone, String newCompany, String newEmail, int id, String oldName, String oldPhone, String oldCompany, String oldEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newName + "', " +  COL3 + " = '" + newPhone +
                "', " + COL4 + " = '" + newCompany + "', " + COL5 + " = '" + newEmail + "' WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" + oldName + "'" +
                " AND " + COL3 + " = '" + oldPhone + "'" + " AND " + COL4 + " = '" + oldCompany + "'" + " AND " + COL5 + " = '" + oldEmail + "'";
        db.execSQL(query);
    }

    public void deleteData(int id, String name, String phone, String company, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" + name + "'" +
                " AND " + COL3 + " = '" + phone + "'" + " AND " + COL4 + " = '" + company + "'" + " AND " + COL5 + " = '" + email + "'";
        db.execSQL(query);
    }
}