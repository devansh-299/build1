package com.piworks.build1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class mydatabase extends SQLiteOpenHelper {

    static final private String Db_name = "date_value_database.db";
    static final private String Db_table = "date_val";
    static final private int Db_Version = 1;

    public static final String COL_2 = "currentdate";
    public static final String COL_3 = "amount";

    Context ctx;

    public mydatabase(Context ct) {
        super(ct, Db_name, null, Db_Version);
        this.ctx = ct;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Db_table + " (id INTEGER PRIMARY KEY AUTOINCREMENT, currentdate TEXT NOT NULL DEFAULT " + "0" + ", amount TEXT NOT NULL DEFAULT " + "0" + ");");
        Log.i("MyDatabase", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Db_table);
        onCreate(db);
    }

    public boolean insertData(String currentdate, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, currentdate);
        contentValues.put(COL_3, amount);
        long result = db.insert(Db_table, null, contentValues);
        if (result == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }

    }

    public String getvalue() {
        String finalstring ="0";
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String ourdate = formatter.format(date);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + Db_table + " where "+ COL_2 + " = '" + ourdate + "'";
        Cursor cr = db.rawQuery(query, null);
        if (cr.getCount() == 0) {
            // show message
            Log.i("Database cr count zero", "Zero!!!!!!!!!!!1");
            return  finalstring;
        }
        else {
            try {
                while (cr.moveToNext()) {
                    String id = cr.getString(0);
                    String datestring = cr.getString(1);
                    String amountstring = cr.getString(2);
                    finalstring =  amountstring;
                    if (finalstring == null){
                        Log.i("Database related!!!","finalstring is null");
                        finalstring = "1";
                        }
                }
            } catch (Exception e) {

            } finally {
                if (cr != null && !cr.isClosed()) {
                    cr.close();
                }
                db.close();
            }
            return finalstring;

        }

    }
}
