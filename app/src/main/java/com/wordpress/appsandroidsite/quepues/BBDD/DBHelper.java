/**
 * @(#)DBHelper.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
import com.wordpress.appsandroidsite.quepues.modelo.Url;
import com.wordpress.appsandroidsite.quepues.soap.VolcarDatosTask;

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

    private static final String TAG = "DBHelper";

    //Cada vez que modifiquemos la base de datos hay que incrementar en uno el valor de esta variable

    private static final int DATABASE_VERSION = 1;
    //Nombre de la base de datos
    private static final String DATABASE_NAME="test.db";
    Context context;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context=context;
    }

    @Override
    /**
     * Crea la base de datos
     */
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE_TEST = " CREATE TABLE " + Test.TABLE + "("
                + Test.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Test.KEY_code + " TEXT , "
                + Test.KEY_tipe + " TEXT)";
        String CREATE_TABLE_CATEGORY= " CREATE TABLE " + Categoria.TABLE + "("
                + Categoria.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Categoria.KEY_code + " TEXT , "
                + Categoria.KEY_name + " TEXT"
                +")";
              //  +"," Categoria.KEY_result + " TEXT)";

        String CREATE_TABLE_QUESTION = "CREATE TABLE " + Pregunta.TABLE  + "("
                + Pregunta.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Pregunta.KEY_text + " TEXT, "
                + Pregunta.KEY_test_code + " TEXT, "
           //     + Pregunta.KEY_ID_test  + " INTEGER, "
                + Pregunta.KEY_number  + " INTEGER, "
           //     + " FOREIGN KEY ("+Pregunta.KEY_ID_test+") REFERENCES "+Test.TABLE+"("+Test.KEY_ID+")"
                + " FOREIGN KEY ("+Pregunta.KEY_test_code+") REFERENCES "+Test.TABLE+"("+Test.KEY_code+")"
                +");";
        String CREATE_TABLE_OPTION = "CREATE TABLE " + Opcion.TABLE  + "("
                + Opcion.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Opcion.KEY_text + " TEXT, "
                + Opcion.KEY_ID_question + " INTEGER, "
             //   + Opcion.KEY_ID_category  + " INTEGER, "
                + Opcion.KEY_category_code  + " TEXT, "
                + " FOREIGN KEY ("+Opcion.KEY_category_code+") REFERENCES "+Categoria.TABLE+"("+Categoria.KEY_code+")"
                + " FOREIGN KEY ("+Opcion.KEY_ID_question+") REFERENCES "+Pregunta.TABLE+"("+Pregunta.KEY_ID+")"
             //   + " FOREIGN KEY ("+Opcion.KEY_ID_category+") REFERENCES "+Categoria.TABLE+"("+Categoria.KEY_ID+")"
                +");";
        String CREATE_TABLE_URL = "CREATE TABLE " + Url.TABLE  + "("
                + Url.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Url.KEY_url + " TEXT, "
                + Url.KEY_subCategory + " TEXT, "
            //    + Url.KEY_ID_test + " INTEGER, "
                + Url.KEY_test_code + " TEXT, "
                + Url.KEY_category_code + " TEXT, "
                + Url.KEY_last_change + " TEXT, "
            //    + Url.KEY_ID_category  + " INTEGER, "
                + " FOREIGN KEY ("+Url.KEY_category_code+") REFERENCES "+Categoria.TABLE+"("+Test.KEY_code+"),"
                + " FOREIGN KEY ("+Url.KEY_test_code+") REFERENCES "+Test.TABLE+"("+Test.KEY_code+")"
             //   + " FOREIGN KEY ("+Url.KEY_ID_test+") REFERENCES "+Test.TABLE+"("+Test.KEY_ID+")"
             //   + " FOREIGN KEY ("+Url.KEY_ID_category+") REFERENCES "+Categoria.TABLE+"("+Categoria.KEY_ID+")"
                +");";



        db.execSQL("PRAGMA foreign_keys =ON;");
        db.execSQL(CREATE_TABLE_TEST);
        db.execSQL(CREATE_TABLE_QUESTION);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_OPTION);
        db.execSQL(CREATE_TABLE_URL);



        Log.i(TAG, "Voy a arrancar el servicio");

        VolcarDatosTask task = new VolcarDatosTask(context);
        task.execute();

      /*  Intent service = new Intent(context, volcarDatosService.class);
        context.startService(service);*/



    }

    @Override
    /**
     * Actualización de base de datos en nuevas versiones:
     * Borra la base de datos actual y la vuelve a crear.
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Pregunta.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Test.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Categoria.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Opcion.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Url.TABLE);

        onCreate(db);

    }
}
