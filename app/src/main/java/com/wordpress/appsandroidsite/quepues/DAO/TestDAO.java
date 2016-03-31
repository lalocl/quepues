package com.wordpress.appsandroidsite.quepues.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.modelo.Test;

/**
 * Created by laura on 30/03/2016.
 */
public class TestDAO {

    private DBHelper dbHelper;

    public TestDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Test test){
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Test.KEY_tipe,test.tipo);

        long test_id = db.insert(Test.TABLE,null,values);
        db.close();

        return (int) test_id;
    }

    public Test getTipeById(int Id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Test.KEY_ID + "," +
                Test.KEY_tipe +
                " FROM " + Test.TABLE
                + " WHERE " +
                Test.KEY_ID + "=?";

        int iCount =0;
        Test test = new Test();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                test.test_ID=cursor.getInt(cursor.getColumnIndex(Test.KEY_ID));
                test.tipo=cursor.getString(cursor.getColumnIndex(Test.KEY_tipe));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return test;
    }
}
