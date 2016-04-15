package com.wordpress.appsandroidsite.quepues.soap;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.TestDAO;
import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.activity.InicioActivity;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 15/04/2016.
 */
public class VolcarDatosTask extends AsyncTask<Void, String, String> {

    private static final String TAG = "VolcarDatosTask";
    private Context context;

    public VolcarDatosTask(Context context){
        this.context=context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "Carga de datos Iniciada");

    }
    @Override
    protected String doInBackground(Void... params) {


        insertarTests();


        listaCategorias();
        crearUrls();
        //  insertarUrls();

        crearTest();
        return "Terminada";
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG, "Tarea cancelada");
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i(TAG, "Tarea Finalizada");
        Intent i = new Intent(context, InicioActivity.class);
        context.startActivity(i);


    }

    public void crearUrls() {


//Método que hay que pasar al DAO;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        UrlParser parser = new UrlParser(context);
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
        CategoriaDAO categoriaDAO = new CategoriaDAO(context);
        CategoriasParser categoriasParser= new CategoriasParser(context);
        if( categoriasParser.parse()){
            int totalInsertados= categoriaDAO.insert(categoriasParser.getCategorias());
        }


    }

    public void insertarTests(){
        Log.i(TAG, "Vamos a crear test");
        TestDAO testDAO = new TestDAO(context);
        TestParser testParser= new TestParser(context);
        if(testParser.parse()) {
            int totalInsertados= testDAO.insert(testParser.getTests());
        }

    }



    public void insertarUrls(){
        Log.i(TAG, "Vamos a crear urls");
        UrlDAO urlDAO = new UrlDAO(context);
        UrlParser urlParser= new UrlParser(context);
        if(urlParser.parse()) {
            int totalInsertados= urlDAO.insert(urlParser.getUrls());
        }

    }

    //hay que pasar a DAO
    public void crearTest(){
        Log.i(TAG, "Vamos a crear preguntas");
        DBHelper dbHelper=new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valuesPreg ;
        ContentValues valuesOpc ;
        PreguntasParser parser = new PreguntasParser(context);

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
                        valuesOpc.put(Opcion.KEY_category_code,  opciones.get(k).codigo_categoria);
                        db.insert(Opcion.TABLE, null, valuesOpc);

                    }

                }





            }
        }

        db.close();
    }


}
