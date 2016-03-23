package com.wordpress.appsandroidsite.quepues.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;

/**
 * Created by laura on 23/03/2016.
 */
public class PreguntaDAO {
    private static final String TAG = "PreguntaDAO";

    //Creamos conexión a la base de datos
    private DBHelper dbHelper;

    //Constructor
    public PreguntaDAO(Context context){
        dbHelper=new DBHelper(context);
    }

    public int insert(Pregunta pregunta){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Creamos un array de clave-valor a partir de las variables asociadas en la clase modelo
        ContentValues values = new ContentValues();
        values.put(Pregunta.KEY_text,pregunta.texto);
        values.put(Pregunta.KEY_ID_test,pregunta.test_ID);

        //añadimos nueva pregunta, y capturamos el id que nos devuelve del registro que hemos creado
        long pregunta_Id=db.insert(Pregunta.TABLE,null,values);
        Log.i(TAG, "creado nuevo registro");
        db.close();



        return(int)pregunta_Id;
    }

    public Pregunta getById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery =  "SELECT  " +
                Pregunta.KEY_ID + "," +
                Pregunta.KEY_text +"," +
                Pregunta.KEY_ID_test +
                " FROM " + Pregunta.TABLE
                + " WHERE " +
                Pregunta.KEY_ID + "=?";

        int iCount =0;
        Pregunta pregunta = new Pregunta();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id) } );

        if (cursor.moveToFirst()) {
            do {
                pregunta.pregunta_ID =cursor.getInt(cursor.getColumnIndex(Pregunta.KEY_ID));
                pregunta.texto =cursor.getString(cursor.getColumnIndex(Pregunta.KEY_text));
                pregunta.test_ID =cursor.getInt(cursor.getColumnIndex(Pregunta.KEY_ID_test));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();



        return pregunta;
    }



}
