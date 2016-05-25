package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
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
import com.wordpress.appsandroidsite.quepues.adapter.UrlAdapter;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Puntuaciones;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 14/04/2016.
 */
public class InicioActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "InicioActivity";


    private RecyclerView rc;
    private TextView textPregunta;
    private ImageButton button;

    private String cod_test;
    int preguntaAMostrar;
    ArrayList<Pregunta> preguntas;
    ArrayList<Opcion> opciones;



    protected  void onStart(){
        super.onStart();

        rc= (RecyclerView)findViewById(R.id.list_botones);
     //   rc.(AbsListView.CHOICE_MODE_MULTIPLE);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setHasFixedSize(true);


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
                Log.i(TAG,"Pregunta : " + textPregunta.toString());
            }
        }


        OpcionesAdapter adapter = new OpcionesAdapter(opciones);
        rc.setAdapter(adapter);




    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        PreguntaDAO preguntaDAO= new PreguntaDAO(this);


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
