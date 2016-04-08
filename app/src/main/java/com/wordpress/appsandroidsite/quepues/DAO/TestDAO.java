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

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TestDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Insert de 1 en 1
    public int insert(Test test){
        //Open connection to write data
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Test.KEY_tipe,test.tipo);
        values.put(Test.KEY_code,test.codigo);

        long test_id = db.insert(Test.TABLE,null,values);
        db.close();

        return (int) test_id;
    }

    public int insert(Test[] tests){
        db = dbHelper.getWritableDatabase();
        ContentValues values;
        int totalInsertados=-1;
        if(tests.length>0){
            for(int i=0;i<tests.length;i++){
                values= new ContentValues();
                values.put(Test.KEY_tipe, tests[i].tipo);
                values.put(Test.KEY_code,tests[i].codigo);
                long test_id =db.insert(Test.TABLE, null, values);

                if(test_id>0 && totalInsertados==-1){
                    totalInsertados=1;

                }else if(test_id>0){
                    totalInsertados=totalInsertados+1;
                }

            }

        }
        db.close();

        return totalInsertados;
    }

    //Búsqueda de 1 Test
    public Test getTipeById(int Id){

        db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Test.KEY_ID + "," +
                Test.KEY_code + "," +
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
                test.codigo=cursor.getString(cursor.getColumnIndex(Test.KEY_code));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return test;
    }
}
