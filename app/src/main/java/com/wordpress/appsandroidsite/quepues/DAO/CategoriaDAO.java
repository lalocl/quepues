package com.wordpress.appsandroidsite.quepues.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;


/**
 * Created by laura on 30/03/2016.
 */
public class CategoriaDAO {
    private static final String TAG = "CategoriaDAO";
    //Creamos conexión a la base de datos
    private DBHelper dbHelper;
    //Constructor
    public CategoriaDAO(Context context){
        dbHelper=new DBHelper(context);
    }

    //insertar
    public int insert(Categoria categoria){
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //Creamos un array de clave-valor a partir de las variables asociadas en la clase modelo
        ContentValues values = new ContentValues();
        values.put(Categoria.KEY_name,categoria.nombre);
        values.put(Categoria.KEY_result,categoria.resultado);


        //añadimos nueva pregunta, y capturamos el id que nos devuelve del registro que hemos creado
        long categoria_Id=db.insert(Categoria.TABLE, null, values);
        Log.i(TAG, "creado nuevo registro");
        db.close();



        return(int)categoria_Id;
    }

    //Búsqueda por id simple
    public Categoria getById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery =  "SELECT  " +
                Categoria.KEY_ID + ", " +
                Categoria.KEY_name +", " +
                Categoria.KEY_result + 
                " FROM " + Categoria.TABLE
                + " WHERE " +
                Pregunta.KEY_ID + "=?";

        int iCount =0;
        Categoria categoria = new  Categoria();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                categoria.categoria_ID =cursor.getInt(cursor.getColumnIndex(Categoria.KEY_ID));
                categoria.nombre =cursor.getString(cursor.getColumnIndex(Categoria.KEY_name));
                categoria.resultado =cursor.getString(cursor.getColumnIndex(Categoria.KEY_result));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();



        return categoria;
    }




}
