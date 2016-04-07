package com.wordpress.appsandroidsite.quepues.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.DAO.TestDAO;
import com.wordpress.appsandroidsite.quepues.adapter.PreguntasParser;
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

                    nuevoTest("Aula 10");

                    nuevoTest("Escuela Negocio");
                    //enlaces por categoria
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


    public void nuevoTest(String nombreTipo){
      try {
          TestDAO testDAO = new TestDAO(volcarDatosService.this);
          Test nuevoTest = new Test();
          nuevoTest.tipo = nombreTipo;
          int idTest = testDAO.insert(nuevoTest);
          Log.i(TAG, "Creado test "+ nombreTipo);
      }catch(Exception e){
          Log.e(TAG, "No creado test " + nombreTipo);
      }
      /*  Toast toast=Toast.makeText(this,"Agregado test con id " + idTest, Toast.LENGTH_SHORT);
        toast.show();*/

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

    }


    //   public void listaCategorias(int enlacesPorCategoria){
    public void listaCategorias(){

        DBHelper dbHelper=new DBHelper(volcarDatosService.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valuesC;
        //   ContentValues valuesU;



        String[] categorias = {"Gestión","Marketing","Diseño","Hosteleria y/o Turismo","Informática","Tecnológica","Sociosanitaria","Imagen Personal"};
     /*   String[] resultados = {"La Gestión es la ciencia social que tiene por objeto el estudio de las organizaciones y la técnica encargada de la planificación, organización, dirección y control de los recursos (humanos, financieros, materiales, tecnológicos, del conocimiento, etc.) de una organización, con el fin de obtener eficiencia o máximo beneficio posible; este beneficio puede ser social, económico o estratégico, dependiendo de los fines perseguidos por dicha organización.","" +
                "Marketing es un concepto inglés, traducido al castellano como mercadeo o mercadotecnia. Se trata de la disciplina dedicada al análisis del comportamiento de los mercados y de los consumidores. El marketing analiza la gestión comercial de las empresas con el objetivo de captar, retener y fidelizar a los clientes a través de la satisfacción de sus necesidades","" +
                "Diseño es una actividad creativa que tiene por fin proyectar objetos que sean útiles y estéticos.","" +
                "Hostelería es el nombre genérico de las actividades económicas consistentes en la prestación de servicios ligados al alojamiento y la alimentación esporádicos, muy usualmente ligados al turismo.","" +
                "La Informática es una ciencia que estudia métodos, técnicas, procesos, con el fin de almacenar, procesar y transmitir información y datos en formato digital.","" +
                "Tecnología es el conjunto de conocimientos técnicos, científicamente ordenados, que permiten diseñar, crear bienes, servicios que facilitan la adaptación al medio ambiente y satisfacer tanto las necesidades esenciales como los deseos de la humanidad","" +
                "La atención sociosanitaria es un título de formación profesional que reúne los servicios que coordinan la asistencia curativa, social y educativa de colectivos en situación de dependencia como la tercera edad, los enfermos crónicos y las personas con alguna discapacidad física, psíquica o sensoria","" +
                "La imagen personal es una fuerte herramienta, ya sea de ventas, de negociaciones o de vida social.Es la manera en la que nos presentamos en el mercado, de manera integral. Con ello nos referimos a que la imagen (aunque el término de por sí apele a lo visual) es un complemento de la estética o la apariencia física y la percepción abstracta."};

*/






        int idCateg;
        for(int i=0;i<categorias.length;i++) {


            valuesC = new ContentValues();
            valuesC.put(Categoria.KEY_name, categorias[i]);
            //    valuesC.put(Categoria.KEY_result, resultados[i]);
            idCateg=(int)db.insert(Categoria.TABLE, null, valuesC);
          /*  for(int j=0;j<enlacesPorCategoria;j++) {
                valuesU=new ContentValues();
                valuesU.put(Url.KEY_url,"Url " + (j+1) + " de la Categoria " + categorias[i]);
                valuesU.put(Url.KEY_ID_test, 1);
                valuesU.put(Url.KEY_ID_category,idCateg);
                db.insert(Url.TABLE, null, valuesU);

            }*/

        }
        db.close();

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
