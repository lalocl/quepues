package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.TestDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Test;


/**
 * main de prueba para comprobar que funciona la base de datos
 * Created by laura on 21/03/2016.
 */
public class MainActivityPruebas extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    Button mostrar;

    /*
    //Tipo

    TextView test_id;
    TextView tipo;
    Test test;
*/

    /*
    //Preguntas
    TextView pregunta_id;
    TextView texto_pregunta;
    TextView numero_pregunta;
    TextView pregunta_test_id;
    Pregunta pregunta;
    */

    //Categoria
    TextView categoria_Id;
    TextView nombre_categoria;
    TextView resultado_categoria;
    Categoria categoria;




    @Override
    public void onClick(View v) {
        Log.i(TAG, "Pulsado botón");

        categoria_Id=(TextView)findViewById(R.id.categoria_Id);
        nombre_categoria=(TextView)findViewById(R.id.nombre_categoria);
        resultado_categoria=(TextView)findViewById(R.id.resultado_categoria);


        //INSERTAR CATEGORIA
    /*    CategoriaDAO categoriaDAO= new CategoriaDAO(MainActivityPruebas.this);
        Categoria nuevaCategoria= new Categoria();
        nuevaCategoria.nombre="Gestión";
        nuevaCategoria.resultado="Gestión y Administración:  Desarrollar la actividad laboral dentro de la administración de las empresas.";
        int idCategoria=categoriaDAO.insert(nuevaCategoria);
        Toast toastT=Toast.makeText(this, "Agregado con: " + idCategoria, Toast.LENGTH_SHORT);
        toastT.show();*/

        //BUSCAR CATEGORIA
        CategoriaDAO categoriaDAO2= new CategoriaDAO(MainActivityPruebas.this);
        categoria= categoriaDAO2.getById(1);
        categoria_Id.setText(String.valueOf(categoria.categoria_ID));
        nombre_categoria.setText(categoria.nombre);
        resultado_categoria.setText(categoria.resultado);









    /*
        test_id = (TextView) findViewById(R.id.test_Id);
        tipo=(TextView) findViewById(R.id.tipo);

        //INSERTAR TEST
      TestDAO testDAO= new TestDAO(MainActivityPruebas.this);
        Test nuevoTest = new Test();
        nuevoTest.tipo="Aula 10";
        int idTest= testDAO.insert(nuevoTest);
        Toast toastT=Toast.makeText(this,"Agregado test con id " + idTest, Toast.LENGTH_SHORT);
        toastT.show();
        nuevoTest("Aula 10");
        nuevoTest("Escuela Negocio");

        //BUSCAR TEST
        TestDAO testDAO2= new TestDAO(MainActivity.this);
        test=testDAO2.getTipeById(2);
        tipo.setText(test.tipo);
        test_id.setText(String.valueOf(test.test_ID));

        */


    /*
        texto_pregunta = (TextView) findViewById(R.id.texto_pregunta);
        pregunta_test_id=(TextView) findViewById(R.id.test_Id);
        numero_pregunta=(TextView) findViewById(R.id.numero_pregunta);


        //INSERTAR PREGUNTAS

        PreguntaDAO pd = new PreguntaDAO(MainActivityPruebas.this);
        Pregunta nuevaPregunta= new Pregunta();
        nuevaPregunta.test_ID=2;
        nuevaPregunta.texto="Pregunta 2 Test 1";
        nuevaPregunta.numero=1;
        int idPregunta= pd.insert(nuevaPregunta);

        if(idPregunta>0) {
            Toast toast = Toast.makeText(this, "Agregado test con id " + idPregunta, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Toast toast = Toast.makeText(this, "No se ha podido agregar la pregunta, comprueba que existe el test al que desea agregarlo", Toast.LENGTH_SHORT);
            toast.show();
        }

        //PRUEBAS DE BUSCAR PREGUNTAS
        //a) Pregunta por Id
        PreguntaDAO preguntaDAO = new PreguntaDAO(MainActivity.this);
        pregunta=preguntaDAO.getById(21);
        texto_pregunta.setText(pregunta.texto);
        pregunta_test_id.setText(String.valueOf(pregunta.test_ID));
        numero_pregunta.setText(String.valueOf(pregunta.numero));

        //b) Lista de preguntas por idTest

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
    public void nuevoTest(String nombreTipo){
        TestDAO testDAO= new TestDAO(MainActivityPruebas.this);
        Test nuevoTest = new Test();
        nuevoTest.tipo=nombreTipo;
        int idTest= testDAO.insert(nuevoTest);
        Toast toast=Toast.makeText(this,"Agregado test con id " + idTest, Toast.LENGTH_SHORT);
        toast.show();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mostrar = (Button) findViewById(R.id.mostrar);

        mostrar.setOnClickListener(this);
        DBHelper dbHelper=new DBHelper(MainActivityPruebas.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
/*
    Descomentar para hacer pruebas con los registros


    //Insertar las 8 categorias

    //Insertar los 2 tipos de test
        nuevoTest("Aula 10");
        nuevoTest("Escuela Negocio");

    //Insertar n preguntas para cada test
        int n=20;
        ContentValues values = new ContentValues();
        for(int i=0;i<n;i++) {
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