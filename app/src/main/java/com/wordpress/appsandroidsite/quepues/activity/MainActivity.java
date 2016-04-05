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
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

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



        //LLENAR BASE DE DATOS

        nuevoTest("Aula 10");
        nuevoTest("Escuela Negocio");
        //enlaces por categoria
        listaCategorias(3);
        //Total de test que hemos creado/numero de preguntas del test/opciones por pregunta
        crearTest(2,6,4);



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

    public void listaCategorias(int enlacesPorCategoria){

        DBHelper dbHelper=new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valuesC;
        ContentValues valuesU;



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
     String [][]matrizUrls;
        matrizUrls= new String[8][];





        int idCateg;
        for(int i=0;i<categorias.length;i++) {


            valuesC = new ContentValues();
            valuesC.put(Categoria.KEY_name, categorias[i]);
        //    valuesC.put(Categoria.KEY_result, resultados[i]);
            idCateg=(int)db.insert(Categoria.TABLE, null, valuesC);
            for(int j=0;j<enlacesPorCategoria;j++) {
                valuesU=new ContentValues();
                valuesU.put(Url.KEY_url,"Url " + (j+1) + " de la Categoria " + categorias[i]);
                valuesU.put(Url.KEY_ID_test, 1);
                valuesU.put(Url.KEY_ID_category,idCateg);
                db.insert(Url.TABLE, null, valuesU);

            }

        }
        db.close();

    }

    public void crearTest(int totalTest, int totalPreguntas,int totalOpciones){
        DBHelper dbHelper=new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valuesPreg ;
        ContentValues valuesOpc ;

        int idPregunta;
        int categId ;

        for(int j=0;j<totalTest;j++) {
            categId=1;
            for (int i = 0; i < totalPreguntas; i++) {
                valuesPreg = new ContentValues();
                valuesPreg.put(Pregunta.KEY_number, (i + 1));
                valuesPreg.put(Pregunta.KEY_text, "Pregunta " + (i + 1) + " del Test " + (j + 1));

                valuesPreg.put(Pregunta.KEY_ID_test, (j + 1));

                idPregunta=(int)db.insert(Pregunta.TABLE, null, valuesPreg);

                if(categId>8){
                    categId=1 ;
                }

                for(int k =0;k<totalOpciones;k++){


                    valuesOpc= new ContentValues();

                    valuesOpc.put(Opcion.KEY_text, "Opcion " + (k + 1)+ " de la Pregunta " + (i + 1) + " de la categoria " + categId);
                    valuesOpc.put(Opcion.KEY_ID_question,idPregunta);
                    valuesOpc.put(Opcion.KEY_ID_category,categId);
                    db.insert(Opcion.TABLE, null, valuesOpc);
                    categId=categId+1;


                }

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
