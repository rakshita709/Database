package com.example.rakshitayadav.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private static final String TABLE_CREATE = "create table "+TABLE_NAME+" (" +
                                                COLUMN_ID+" integer primary key not null," +
                                                COLUMN_UNAME+" text not null," +
                                                COLUMN_EMAIL+" text not null," +
                                                COLUMN_PASS+" text not null" +
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


    public long insertContact(Contacts c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_UNAME,c.getUsername());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASS,c.getPassword());

        long id=db.insert(TABLE_NAME,null,values);
        db.close();

        return id;

    }

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "SELECT "+COLUMN_UNAME+", "+COLUMN_PASS+" FROM contacts";
        Cursor cursor = db.rawQuery(query,null);
        String username,pass = "Not found!";

        while (cursor.moveToNext()){
            username= cursor.getString(cursor.getColumnIndex(COLUMN_UNAME));
            if(username.equals(uname)){
                pass = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                break;
            }
        }

        db.close();
        return pass;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


}
