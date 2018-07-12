package com.example.rakshitayadav.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (" +
                                                "id integer primary key not null," +
                                                "name text not null," +
                                                "email text not null," +
                                                "password text not null" +
                                                ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }


    public void insertContact(Contacts c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM contacts";
        Cursor cursor = db.rawQuery(query,null);
        //to get count of the contacts number
        int count = cursor.getCount();

        values.put(COLUMN_ID,count);

        values.put("COLUMN_UNAME",c.getUsername());
        values.put("COLUMN_EMAIL",c.getEmail());
        values.put("COLUMN_PASS",c.getPassword());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "SELECT uname,password FROM contacts";
        Cursor cursor = db.rawQuery(query,null);
        String user_id,pass = "Not found!";
        if(cursor.moveToFirst())
        {
            do
            {
               user_id = cursor.getString(0);

               if(user_id.equals(uname))
               {
                   pass = cursor.getString(1); //if the if-condition is true then we need to store this password
                   break; //now we don't need to check other details :)
               }

            }while(cursor.moveToNext());
        }

        db.close();
        return pass;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
