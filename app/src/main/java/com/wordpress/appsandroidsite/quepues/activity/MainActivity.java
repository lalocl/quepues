package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
public class MainActivity extends Activity  {

    private static final String TAG = "MainActivity";
    private int id_test;
    RadioGroup rg;
    RadioButton test1;
    RadioButton test2;
    Button buttonEntrarTest;
    Test t;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*
        //LLENAR BASE DE DATOS


        nuevoTest("Aula 10");
        nuevoTest("Escuela Negocio");
        crearTest(2);
        */

        buttonEntrarTest= (Button) findViewById(R.id.buttonEntrarTest);

        test1=(RadioButton)findViewById(R.id.radioButton1);
        test2=(RadioButton)findViewById(R.id.radioButton2);
        test1.setText(etiquetaTest(1));
        test2.setText(etiquetaTest(2));

        rg =(RadioGroup)findViewById(R.id.grbGrupo1);
        rg.clearCheck();



        buttonEntrarTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int testSeleccionado = rg.getCheckedRadioButtonId();


                if (testSeleccionado == R.id.radioButton1) {
                    id_test = 1;

                } else if (testSeleccionado == R.id.radioButton2) {
                    id_test = 2;
                } else {

                    Toast toast = Toast.makeText(MainActivity.this, "Debe elegir una opción de test", Toast.LENGTH_SHORT);
                    toast.show();
                }

                if(id_test>0) {
                    Intent i = new Intent(MainActivity.this, TesterActivity.class);
                    i.putExtra("id_test", id_test);
                    startActivity(i);
                }
            }
        });



    /*    id_test=2;


        //Le pasamos el id del test que queremos ejecutar
        Intent i=new Intent(this,TesterActivity.class);
        i.putExtra("id_test",id_test);
        startActivity(i);*/


    }
    public String etiquetaTest(int idTest){
        TestDAO testDAO = new TestDAO(this);

       t = testDAO.getTipeById(idTest);

        return t.tipo;
    }

    public void nuevoTest(String nombreTipo){
        TestDAO testDAO= new TestDAO(MainActivity.this);
        Test nuevoTest = new Test();
        nuevoTest.tipo=nombreTipo;
        int idTest= testDAO.insert(nuevoTest);
        Toast toast=Toast.makeText(this,"Agregado test con id " + idTest, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void crearTest(int totalTest){
        DBHelper dbHelper=new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(int j=0;j<totalTest;j++) {
            for (int i = 0; i < 20; i++) {
                values = new ContentValues();
                values.put(Pregunta.KEY_number, (i + 1));
                values.put(Pregunta.KEY_text, "Pregunta " + (i + 1) + "Del Test " +(j+1));
                values.put(Pregunta.KEY_ID_test, (j+1));

                db.insert(Pregunta.TABLE, null, values);

            }
        }
        db.close();
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
