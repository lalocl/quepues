package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.OpcionDAO;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.adapter.OpcionesAdapter;
import com.wordpress.appsandroidsite.quepues.adapter.SelectorAdaptador;
import com.wordpress.appsandroidsite.quepues.adapter.UrlAdapter;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Puntuaciones;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by laura on 14/04/2016.
 */
public class InicioActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "InicioActivity";


  //  private RecyclerView rc;
    private ListView listView;
    private TextView textPregunta;
    private ImageButton button;

    private String cod_test;
    int preguntaAMostrar,respuestaElegida;
    ArrayList<Pregunta> preguntas;
    ArrayList<Opcion> opciones;
  // LinkedList <Opcion> datos;
   //OpcionesAdapter adapter;
    SelectorAdaptador adapter;




    protected  void onStart(){
        super.onStart();


        listView=(ListView) findViewById(R.id.list_botones);
        respuestaElegida=-1;

        /*
        rc= (RecyclerView)findViewById(R.id.list_botones);

        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setHasFixedSize(true);
        */


        calcularPregunta(preguntaAMostrar);

    }

    public  void calcularPregunta(int numeroPregunta){


        Log.i(TAG,"Calculando pregunta...");
        textPregunta=(TextView)findViewById(R.id.textViewPregunta);




        OpcionDAO opcionDAO = new OpcionDAO(this);
        for(int i=0; i<preguntas.size();i++) {
            if(preguntas.get(i).numero==numeroPregunta) {
                opciones = opcionDAO.getListByPreguntaId(preguntas.get(i).pregunta_ID);
                textPregunta.setText(preguntas.get(i).texto);
                Log.i(TAG,"Pregunta : " + textPregunta.getText());
            }
        }




        adapter= new SelectorAdaptador(InicioActivity.this,opciones);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                view.setSelected(true);
                respuestaElegida=position;

                Log.i(TAG,"onItemClick");
              //  Toast.makeText(InicioActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(respuestaElegida>=0){
                //Mirar de cambiar por el tipo de categoria elegida en las respuestas.
                     if(respuestaElegida <5) {

                    cod_test = "a10";
                    Log.i(TAG, "Respuesta elegida: " + respuestaElegida + ", cod: " + cod_test);


                     }else {

                         cod_test = "CL";
                         Log.i(TAG, "Respuesta elegida: " + respuestaElegida + ", cod: " + cod_test);
                        // Toast.makeText(InicioActivity.this, "Marcada: " + respuestaElegida + ", elegido " + cod_test, Toast.LENGTH_SHORT).show();
                     }
                    Toast.makeText(InicioActivity.this, "Marcada: " + respuestaElegida + ", elegido " + cod_test, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(InicioActivity.this,TestActivity.class);
                    i.putExtra("cod_test",cod_test);
                    startActivity(i);

                }else{
                    Log.i(TAG, "No se ha elegido ninguna respuesta");
                    Toast.makeText(InicioActivity.this, "No marcada " + respuestaElegida, Toast.LENGTH_SHORT).show();
                    Toast.makeText(InicioActivity.this, "Debe elegir una opción de test ", Toast.LENGTH_SHORT).show();
                }




            }
        });




    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.recyclerview);
        setContentView(R.layout.listview);

        PreguntaDAO preguntaDAO= new PreguntaDAO(this);

        button=(ImageButton)findViewById(R.id.imageButton);

        cod_test="Inicio";

        Log.i(TAG,"Obteniendo las preguntas ...");
        preguntas=preguntaDAO.getListByTestId(cod_test);
        Log.i(TAG,"Total preguntas " + preguntas.size());


        preguntaAMostrar=1;

        CategoriaDAO categoriaDAO= new CategoriaDAO(this);
        int totalCateg=categoriaDAO.getSize();
        Log.i(TAG,"Total categorias: " +totalCateg);

        Puntuaciones.setPuntuaciones(new ArrayList<Puntuaciones>());

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {





    }
}
