package com.wordpress.appsandroidsite.quepues.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;

import java.util.ArrayList;


/**
 * Created by laura on 30/03/2016.
 */
public class OpcionDAO {
    private static final String TAG = "OpcionDAO";
    //Creamos conexión a la base de datos
    private DBHelper dbHelper;
    //Constructor
    public OpcionDAO(Context context){
        dbHelper=new DBHelper(context);
    }

    //insertar
    public int insert(Opcion opcion){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys =ON;");

        //Creamos un array de clave-valor a partir de las variables asociadas en la clase modelo
        ContentValues values = new ContentValues();
        values.put(Opcion.KEY_text,opcion.texto);
        values.put(Opcion.KEY_ID_question,opcion.pregunta_ID);
        values.put(Opcion.KEY_ID_category,opcion.categoria_ID);

        //añadimos nueva pregunta, y capturamos el id que nos devuelve del registro que hemos creado
        long opcion_Id=db.insert(Opcion.TABLE, null, values);
        Log.i(TAG, "creado nuevo registro");
        db.close();



        return(int)opcion_Id;
    }

    //Lista de opciones por id-pregunta
    public ArrayList<Opcion> getListByPreguntaId(int id_pregunta){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Opcion>list = new ArrayList<>();


        String selectQuery =  "SELECT  " +

                Opcion.KEY_ID +"," +
                Opcion.KEY_text + "," +
                Opcion.KEY_ID_question + "," +
                Opcion.KEY_ID_category +
                " FROM " + Opcion.TABLE
                + " WHERE " + Opcion.KEY_ID_question + " =?";

        Opcion opcion ;

        String[]parametros= new String[]{String.valueOf(id_pregunta)};
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToFirst()) {
            Log.i(TAG, "creando lista de opciones");
            do {

                opcion = new Opcion();

                opcion.opcion_ID =cursor.getInt(cursor.getColumnIndex(Opcion.KEY_ID));
                opcion.texto =cursor.getString(cursor.getColumnIndex(Opcion.KEY_text));
                opcion.pregunta_ID =cursor.getInt(cursor.getColumnIndex(Opcion.KEY_ID_question));
                opcion.categoria_ID =cursor.getInt(cursor.getColumnIndex(Opcion.KEY_ID_category));


                list.add(opcion);
            } while (cursor.moveToNext());
        }




        cursor.close();
        db.close();


        return list;
    }
}
