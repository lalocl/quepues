package com.wordpress.appsandroidsite.quepues.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
    private SQLiteDatabase db;
    //Constructor
    public CategoriaDAO(Context context){
        dbHelper=new DBHelper(context);
    }

    //insertar 1 en 1
    public int insert(Categoria categoria){
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //Creamos un array de clave-valor a partir de las variables asociadas en la clase modelo
        ContentValues values = new ContentValues();
        values.put(Categoria.KEY_name,categoria.nombre);
        values.put(Categoria.KEY_code,categoria.codigo);
   //     values.put(Categoria.KEY_result,categoria.resultado);


        //añadimos nueva pregunta, y capturamos el id que nos devuelve del registro que hemos creado
        long categoria_Id=db.insert(Categoria.TABLE, null, values);
        Log.i(TAG, "creado nuevo registro");
        db.close();



        return(int)categoria_Id;
    }

    //insertar de archivo
    public int insert(Categoria [] categorias){

        db = dbHelper.getWritableDatabase();
        ContentValues values;
        int totalInsertados=-1;
        if(categorias.length>0){
            for(int i=0;i<categorias.length;i++){
                values= new ContentValues();
                values.put(Categoria.KEY_name, categorias[i].nombre);
                values.put(Categoria.KEY_code,categorias[i].codigo);
                long test_id =db.insert(Categoria.TABLE, null, values);

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
    //Búsqueda por id simple
    public Categoria getById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery =  "SELECT  " +
                Categoria.KEY_ID + ", " +
                Categoria.KEY_name +", " +
                Categoria.KEY_code +
        //        Categoria.KEY_result +
                " FROM " + Categoria.TABLE
                + " WHERE " +
                Categoria.KEY_ID + "=?";

        int iCount =0;
        Categoria categoria = new  Categoria();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                categoria.categoria_ID =cursor.getInt(cursor.getColumnIndex(Categoria.KEY_ID));
                categoria.nombre =cursor.getString(cursor.getColumnIndex(Categoria.KEY_name));
                categoria.codigo =cursor.getString(cursor.getColumnIndex(Categoria.KEY_code));
       //         categoria.resultado =cursor.getString(cursor.getColumnIndex(Categoria.KEY_result));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();



        return categoria;
    }

    //Búsqueda por id simple
    public Categoria getByCode(String code){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery =  "SELECT  " +
                Categoria.KEY_ID + ", " +
                Categoria.KEY_name +", " +
                Categoria.KEY_code +
                //        Categoria.KEY_result +
                " FROM " + Categoria.TABLE
                + " WHERE " +
                Categoria.KEY_code + "=?";

        int iCount =0;
        Categoria categoria = new  Categoria();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{code});

        if (cursor.moveToFirst()) {
            do {
                categoria.categoria_ID =cursor.getInt(cursor.getColumnIndex(Categoria.KEY_ID));
                categoria.nombre =cursor.getString(cursor.getColumnIndex(Categoria.KEY_name));
                categoria.codigo =cursor.getString(cursor.getColumnIndex(Categoria.KEY_code));
                //         categoria.resultado =cursor.getString(cursor.getColumnIndex(Categoria.KEY_result));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();



        return categoria;
    }



    public int getSize(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int numCategorias=(int) DatabaseUtils.queryNumEntries(db,Categoria.TABLE);

       //String selectQuery =  "SELECT  COUNT(*) FROM" +Categoria.TABLE;




        return numCategorias;
    }

}
