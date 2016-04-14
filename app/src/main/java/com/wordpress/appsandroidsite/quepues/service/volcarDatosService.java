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
import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.activity.InicioActivity;
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
    private static final String TAG = "VolcarDatosService";
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
                  //  insertarUrls();
                    //Parámetro: opciones por pregunta
                    crearTest();

                Intent i = new Intent(getBaseContext(), InicioActivity.class);
                startActivity(i);

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


//Método que hay que pasar al DAO;

        DBHelper dbHelper = new DBHelper(volcarDatosService.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        UrlParser parser = new UrlParser(this);
        ContentValues valuesU;

        Log.i(TAG, "Vamos a crear urls");
        if (parser.parse()) {
            Url[] urls = parser.getUrls();
            for (int j = 0; j < urls.length; j++) {
                Log.i(TAG, "Url a crear " + j);
                valuesU = new ContentValues();
                valuesU.put(Url.KEY_url,urls[j].url );
                valuesU.put(Url.KEY_subCategory,urls[j].subCategoria );
                valuesU.put(Url.KEY_ID_test,urls[j].test_ID );
                valuesU.put(Url.KEY_ID_category,urls[j].categoria_ID );
                valuesU.put(Url.KEY_category_code,urls[j].codigo_categoria );
                db.insert(Url.TABLE, null, valuesU);
                Log.i(TAG, "Url creada");

            }

        }

        db.close();
    }



    public void listaCategorias(){

        Log.i(TAG, "Vamos a categorias");
        CategoriaDAO categoriaDAO = new CategoriaDAO(volcarDatosService.this);
        CategoriasParser categoriasParser= new CategoriasParser(volcarDatosService.this);
        if( categoriasParser.parse()){
            int totalInsertados= categoriaDAO.insert(categoriasParser.getCategorias());
        }


    }

    public void insertarTests(){
        Log.i(TAG, "Vamos a crear test");
        TestDAO testDAO = new TestDAO(volcarDatosService.this);
        TestParser testParser= new TestParser(volcarDatosService.this);
        if(testParser.parse()) {
           int totalInsertados= testDAO.insert(testParser.getTests());
        }

    }



    public void insertarUrls(){
        Log.i(TAG, "Vamos a crear urls");
        UrlDAO urlDAO = new UrlDAO(volcarDatosService.this);
        UrlParser urlParser= new UrlParser(volcarDatosService.this);
        if(urlParser.parse()) {
            int totalInsertados= urlDAO.insert(urlParser.getUrls());
        }

    }

   //hay que pasar a DAO
    public void crearTest(){
        Log.i(TAG, "Vamos a crear preguntas");
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





            }
        }

        db.close();
    }




}
