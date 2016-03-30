/**
 * @(#)DBHelper.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Test;

/**
 *
 * Clase DBHelper donde se crea la base de datos embebida que da soporte a la aplicación.
 * En ella guardamos los datos de las preguntas, las diferentes opciones y los resultados del test, así
 * como las rutas donde direccionaremos al usuario para poder visualizar los cursos.
 *
 *
 * @version 0.1 2016/03/18
 * @author laura
 */
public class DBHelper extends SQLiteOpenHelper {


    //Cada vez que modifiquemos la base de datos hay que incrementar en uno el valor de esta variable

    private static final int DATABASE_VERSION = 2;
    //Nombre de la base de datos
    private static final String DATABASE_NAME="test.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    /**
     * Crea la base de datos
     */
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_TEST = " CREATE TABLE " + Test.TABLE + "("
                + Test.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Test.KEY_tipe + " TEXT)";

        String CREATE_TABLE_QUESTION = "CREATE TABLE " + Pregunta.TABLE  + "("
                + Pregunta.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Pregunta.KEY_text + " TEXT, "
                + Pregunta.KEY_ID_test  + " INTEGER, "
                + Pregunta.KEY_number  + " INTEGER)";

        db.execSQL(CREATE_TABLE_TEST);
        db.execSQL(CREATE_TABLE_QUESTION);

        /*
        ContentValues values = new ContentValues();
        values.put(Pregunta.KEY_ID, 1);
        values.put(Pregunta.KEY_text,"Pregunta 1" );
        values.put(Pregunta.KEY_ID_test,1 );

        db.insert(Pregunta.TABLE,null,values);*/



    }

    @Override
    /**
     * Actualización de base de datos en nuevas versiones:
     * Borra la base de datos actual y la vuelve a crear.
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Pregunta.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Test.TABLE);
        onCreate(db);

    }
}
