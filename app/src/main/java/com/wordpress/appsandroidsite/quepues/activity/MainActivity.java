package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.TestDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * main de prueba para comprobar que funciona la base de datos
 * Created by laura on 21/03/2016.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    Button mostrar;

    /*
    //Tipo
     */
    TextView texto_id;
    TextView tipo;

   /*
    //Preguntas
    TextView pregunta_id;
    TextView texto_pregunta;
    TextView numero_pregunta;
    TextView pregunta_test_id;
    Pregunta pregunta;*/





    @Override
    public void onClick(View v) {

        texto_id = (TextView) findViewById(R.id.test_Id);
        tipo=(TextView) findViewById(R.id.tipo);

        TestDAO testDAO= new TestDAO(MainActivity.this);
        Test nuevoTest = new Test();
        nuevoTest.tipo="Aula 10";
        int idTest= testDAO.insert(nuevoTest);
        Toast toast=Toast.makeText(this,"Agregado test con id " + idTest, Toast.LENGTH_SHORT);
        toast.show();




    /*    Log.i(TAG, "Pulsado botón");
        texto_pregunta = (TextView) findViewById(R.id.texto_pregunta);
        pregunta_test_id=(TextView) findViewById(R.id.test_Id);
        numero_pregunta=(TextView) findViewById(R.id.numero_pregunta);


        //INSERTAR PREGUNTAS

        PreguntaDAO pd = new PreguntaDAO(MainActivity.this);
        Pregunta nuevaPregunta= new Pregunta();
        nuevaPregunta.test_ID=1;
        nuevaPregunta.texto="Pregunta 1 Test1";
        nuevaPregunta.numero=1;
        pd.insert(nuevaPregunta);

        //PRUEBAS DE BUSCAR PREGUNTAS
        //a) Pregunta por Id
        PreguntaDAO preguntaDAO = new PreguntaDAO(MainActivity.this);
        pregunta=preguntaDAO.getById(21);
        texto_pregunta.setText(pregunta.texto);
        pregunta_test_id.setText(String.valueOf(pregunta.test_ID));
        numero_pregunta.setText(String.valueOf(pregunta.numero));*/

        //b) Lista de preguntas por idTest
/*
        int preguntaAMostrar=16;

        PreguntaDAO preguntaDAO2 = new PreguntaDAO(MainActivity.this);


        ArrayList<Pregunta> listByTestId= preguntaDAO2.getListByTestId(2);


        if(listByTestId.size()!=0) {
           String objLista= String.valueOf(listByTestId.size());
            for (int i = 0; i < listByTestId.size(); i++) {

                if (listByTestId.get(i).numero == preguntaAMostrar) {
                    texto_pregunta.setText(listByTestId.get(i).texto);
                    pregunta_test_id.setText(String.valueOf(listByTestId.get(i).test_ID));
                    numero_pregunta.setText(String.valueOf(listByTestId.get(i).numero));
                }



            }
            Toast toast=Toast.makeText(this,"Número de registros creados:" + objLista, Toast.LENGTH_SHORT);
            toast.show();
        }

*/


    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mostrar = (Button) findViewById(R.id.mostrar);

        mostrar.setOnClickListener(this);
        DBHelper dbHelper=new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
/*
    Descomentar para hacer pruebas con los registros
        ContentValues values = new ContentValues();
        for(int i=0;i<20;i++) {
            values = new ContentValues();
            values.put(Pregunta.KEY_number, (i+1));
            values.put(Pregunta.KEY_text, "Pregunta " + (i+1));
            values.put(Pregunta.KEY_ID_test, 2);

            db.insert(Pregunta.TABLE, null, values);

        }
        db.close();

*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
 Snackbar.make(v, "Texto a mostrar", Snackbar.LENGTH_LONG)
                        .setAction("Acción", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.i("Snackbar", "Ejemplo Snackbar");
                            }
                        }).show();
 */