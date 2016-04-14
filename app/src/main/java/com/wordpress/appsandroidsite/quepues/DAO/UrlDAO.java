package com.wordpress.appsandroidsite.quepues.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.adapter.UrlParser;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 30/03/2016.
 */
public class UrlDAO {
    private static final String TAG = "UrlDAO";
    //Creamos conexión a la base de datos
    private DBHelper dbHelper;


    public UrlDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //insertar de 1 en 1
    public int insert(Url url) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
      //  db.execSQL("PRAGMA foreign_keys =ON;");

        //Creamos un array de clave-valor a partir de las variables asociadas en la clase modelo
        ContentValues values = new ContentValues();
        values.put(Url.KEY_url, url.url);
        values.put(Url.KEY_subCategory, url.subCategoria);
        values.put(Url.KEY_ID_category, url.categoria_ID);
        values.put(Url.KEY_category_code, url.codigo_categoria);
        values.put(Url.KEY_ID_test, url.test_ID);

        //añadimos nueva pregunta, y capturamos el id que nos devuelve del registro que hemos creado
        long url_Id = db.insert(Url.TABLE, null, values);
        Log.i(TAG, "creado nuevo registro");
        db.close();


        return (int) url_Id;
    }

    //insertar de archivo
    public int insert(Url [] urls) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values;
        int totalInsertados=-1;
        Log.i(TAG, "Vamos a crear urls");
        if (urls.length>0) {

            for (int j = 0; j < urls.length; j++) {
                Log.i(TAG, "Url a crear " + j);
                values = new ContentValues();
                values.put(Url.KEY_url, urls[j].url);
                values.put(Url.KEY_subCategory, urls[j].subCategoria);
                values.put(Url.KEY_ID_test, urls[j].test_ID);
                values.put(Url.KEY_ID_category, urls[j].categoria_ID);
                values.put(Url.KEY_category_code, urls[j].codigo_categoria);
               long url_id= db.insert(Url.TABLE, null, values);
                Log.i(TAG, "Url creada");

                if(url_id>0 && totalInsertados==-1){
                    totalInsertados=1;

                }else if(url_id>0){
                    totalInsertados=totalInsertados+1;
                }

            }

        }

        db.close();
        return totalInsertados;
    }




    //Lista por id de categoria y de categoria
    public ArrayList<Url> getList(int id_test, int id_categoria) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Url> list = new ArrayList<>();


        String selectQuery = "SELECT  " +

                Url.KEY_ID_test + "," +
                Url.KEY_url + "," +
                Url.KEY_subCategory + "," +
                Url.KEY_ID + "," +
                Url.KEY_ID_category +
                Url.KEY_category_code +
                " FROM " + Url.TABLE
                + " WHERE " + Url.KEY_ID_test + " =?" +
                " AND " + Url.KEY_ID_category + " =?";

        Url url;

        String[] parametros = new String[]{String.valueOf(id_test), String.valueOf(id_categoria)};
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToFirst()) {
            Log.i(TAG, "creando lista de urls");
            do {

                url = new Url();

                url.test_ID = cursor.getInt(cursor.getColumnIndex(Url.KEY_ID_test));
                url.subCategoria = cursor.getString(cursor.getColumnIndex(Url.KEY_subCategory));
                url.url = cursor.getString(cursor.getColumnIndex(Url.KEY_url));
                url.url_ID = cursor.getInt(cursor.getColumnIndex(Url.KEY_ID));
                url.categoria_ID = cursor.getInt(cursor.getColumnIndex(Url.KEY_ID_category));
                url.codigo_categoria = cursor.getString(cursor.getColumnIndex(Url.KEY_category_code));


                list.add(url);
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();


        return list;
    }

    //Lista por categoria_code, añadir categoria_test cuando esté implementada
    public ArrayList<Url> getList(String code) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Url> list = new ArrayList<>();


        String selectQuery = "SELECT  " +

                Url.KEY_ID_test + "," +
                Url.KEY_url + "," +
                Url.KEY_subCategory + "," +
                Url.KEY_ID + "," +
                Url.KEY_ID_category +"," +
                Url.KEY_category_code +
                " FROM " + Url.TABLE
                + " WHERE " + Url.KEY_category_code + " =?";

        Url url;

        String[] parametros = new String[]{code};
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToFirst()) {
            Log.i(TAG, "creando lista de urls");
            do {

                url = new Url();

                url.test_ID = cursor.getInt(cursor.getColumnIndex(Url.KEY_ID_test));
                url.subCategoria = cursor.getString(cursor.getColumnIndex(Url.KEY_subCategory));
                url.url = cursor.getString(cursor.getColumnIndex(Url.KEY_url));
                url.url_ID = cursor.getInt(cursor.getColumnIndex(Url.KEY_ID));
                url.categoria_ID = cursor.getInt(cursor.getColumnIndex(Url.KEY_ID_category));
                url.codigo_categoria = cursor.getString(cursor.getColumnIndex(Url.KEY_category_code));


                list.add(url);
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();


        return list;
    }




}
