package com.wordpress.appsandroidsite.quepues.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;

import java.util.ArrayList;

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

    /**
     * Inserta campos en la tabla pregunta
     * @param pregunta Pregunta que se inserta
     * @return int Devuelve el id de la pregunta insertada
     */
    //insertar
    public int insert(Pregunta pregunta){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Creamos un array de clave-valor a partir de las variables asociadas en la clase modelo
        ContentValues values = new ContentValues();
        values.put(Pregunta.KEY_text,pregunta.texto);
        values.put(Pregunta.KEY_ID_test,String.valueOf(pregunta.test_ID));
        values.put(Pregunta.KEY_number,String.valueOf(pregunta.numero));

        //añadimos nueva pregunta, y capturamos el id que nos devuelve del registro que hemos creado
        long pregunta_Id=db.insert(Pregunta.TABLE, null, values);
        Log.i(TAG, "creado nuevo registro");
        db.close();



        return(int)pregunta_Id;
    }

    /**
     * Crea una lista de preguntas de un tipo de test determinado
     * @param id_test Id del test sobre el que queremos hacer la lista
     * @return ArrayList<Pregunta> Devuelve la lista de preguntas
     */
    //Lista por id-test
    public ArrayList<Pregunta> getListByTestId(int id_test){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Pregunta>list = new ArrayList<>();


        String selectQuery =  "SELECT  " +

                Pregunta.KEY_ID_test +"," +
                Pregunta.KEY_number + "," +
                Pregunta.KEY_ID + "," +
                Pregunta.KEY_text +
                " FROM " + Pregunta.TABLE
                + " WHERE " + Pregunta.KEY_ID_test + " =?";

        Pregunta pregunta ;

        String[]parametros= new String[]{String.valueOf(id_test)};
        Cursor cursor = db.rawQuery(selectQuery, parametros);

        if (cursor.moveToFirst()) {
            Log.i(TAG, "rellenando la lista");
            do {

                pregunta = new Pregunta();

                pregunta.test_ID =cursor.getInt(cursor.getColumnIndex(Pregunta.KEY_ID_test));
                pregunta.numero =cursor.getInt(cursor.getColumnIndex(Pregunta.KEY_number));
                pregunta.pregunta_ID =cursor.getInt(cursor.getColumnIndex(Pregunta.KEY_ID));
                pregunta.texto =cursor.getString(cursor.getColumnIndex(Pregunta.KEY_text));



                list.add(pregunta);
            } while (cursor.moveToNext());
        }




        cursor.close();
        db.close();


        return list;
    }

    /**
     * Busca una pregunta concreta a partir de un id
     * @param id Id de la pregunta a buscar
     * @return Pregunta Devuelve la pregunta a la que corresponde el Id
     */


    //Búsqueda por id simple
    public Pregunta getById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery =  "SELECT  " +
                Pregunta.KEY_ID + "," +
                Pregunta.KEY_text +"," +
                Pregunta.KEY_number + "," +
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
                pregunta.numero =cursor.getInt(cursor.getColumnIndex(Pregunta.KEY_number));
                pregunta.test_ID =cursor.getInt(cursor.getColumnIndex(Pregunta.KEY_ID_test));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();



        return pregunta;
    }



}
