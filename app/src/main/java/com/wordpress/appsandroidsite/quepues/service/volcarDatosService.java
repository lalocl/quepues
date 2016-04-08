package com.wordpress.appsandroidsite.quepues.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.TestDAO;
import com.wordpress.appsandroidsite.quepues.adapter.CategoriasParser;
import com.wordpress.appsandroidsite.quepues.adapter.PreguntasParser;
import com.wordpress.appsandroidsite.quepues.adapter.TestParser;
import com.wordpress.appsandroidsite.quepues.adapter.UrlParser;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 07/04/2016.
 */
public class volcarDatosService extends Service {
    private static final String TAG = "ChisteService";
    private static final int CUSTOM_NOTIFICATION = 1000;
    private Thread thread;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                    //LLENAR BASE DE DATOS

                 //   nuevoTest("Aula 10");

                //    nuevoTest("Escuela Negocio");
                    //enlaces por categoria

                    insertarTests();


                    listaCategorias();
                    crearUrls();
                    //Parámetro: opciones por pregunta
                    crearTest();

                }
             });

        thread.start();


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }





    public void crearUrls() {

/*
Método que hay que pasar al DAO;
 */
        DBHelper dbHelper = new DBHelper(volcarDatosService.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        UrlParser parser = new UrlParser(this);
        ContentValues valuesU;

        if (parser.parse()) {
            Url[] urls = parser.getUrls();
            for (int j = 0; j < urls.length; j++) {
                valuesU = new ContentValues();
                valuesU.put(Url.KEY_url,urls[j].url );
                valuesU.put(Url.KEY_subCategory,urls[j].subCategoria );
                valuesU.put(Url.KEY_ID_test,urls[j].test_ID );
                valuesU.put(Url.KEY_ID_category,urls[j].categoria_ID );
                db.insert(Url.TABLE, null, valuesU);

            }

        }

        db.close();
    }



    public void listaCategorias(){

        CategoriaDAO categoriaDAO = new CategoriaDAO(volcarDatosService.this);
        CategoriasParser categoriasParser= new CategoriasParser(volcarDatosService.this);
        if( categoriasParser.parse()){
            int totalInsertados= categoriaDAO.insert(categoriasParser.getCategorias());
        }


    }

    public void insertarTests(){
        TestDAO testDAO = new TestDAO(volcarDatosService.this);
        TestParser testParser= new TestParser(volcarDatosService.this);
        if(testParser.parse()) {
           int totalInsertados= testDAO.insert(testParser.getTests());
        }

    }

    public void crearTest(){
        DBHelper dbHelper=new DBHelper(volcarDatosService.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valuesPreg ;
        ContentValues valuesOpc ;
        PreguntasParser parser = new PreguntasParser(this);

        int idPregunta;
        // int categId =1;

        if (parser.parse()) {
            Pregunta[] preguntas = parser.getPreguntas();
            ArrayList<Opcion> opciones=parser.getOpciones();


            for (int i = 0; i < preguntas.length; i++) {
                valuesPreg = new ContentValues();
                valuesPreg.put(Pregunta.KEY_number, preguntas[i].numero);
                valuesPreg.put(Pregunta.KEY_text, preguntas[i].texto);

                valuesPreg.put(Pregunta.KEY_ID_test,preguntas[i].test_ID);

                idPregunta = (int) db.insert(Pregunta.TABLE, null, valuesPreg);

                for (int k = 0; k < opciones.size(); k++) {
                    if(opciones.get(k).pregunta_ID==(i+1)){
                        valuesOpc = new ContentValues();
                        valuesOpc.put(Opcion.KEY_text, opciones.get(k).texto);
                        valuesOpc.put(Opcion.KEY_ID_question, idPregunta);
                        valuesOpc.put(Opcion.KEY_ID_category,  opciones.get(k).categoria_ID);
                        db.insert(Opcion.TABLE, null, valuesOpc);

                    }

                }
/*
                    if (categId > 8) {
                        categId = 1;
                    }

                    for (int k = 0; k < totalOpciones; k++) {


                        valuesOpc = new ContentValues();

                        valuesOpc.put(Opcion.KEY_text, "Opcion " + (k + 1) + " de la Pregunta " + (i + 1) + " de la categoria " + categId);
                        valuesOpc.put(Opcion.KEY_ID_question, idPregunta);
                        valuesOpc.put(Opcion.KEY_ID_category, categId);
                        db.insert(Opcion.TABLE, null, valuesOpc);
                        categId = categId + 1;


                    }
                    */

            }
        }

        db.close();
    }




}
