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
    int preguntaAMostrar;
    ArrayList<Pregunta> preguntas;
    ArrayList<Opcion> opciones;
  // LinkedList <Opcion> datos;
   //OpcionesAdapter adapter;
    SelectorAdaptador adapter;




    protected  void onStart(){
        super.onStart();


        listView=(ListView) findViewById(R.id.list_botones);

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



    //  datos= new LinkedList<Opcion>(opciones);
     //   final OpcionesAdapter adapter = new OpcionesAdapter(InicioActivity.this,datos);
       // rc.setAdapter(adapter);
        adapter= new SelectorAdaptador(InicioActivity.this,opciones);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG,"onItemClick");
                Toast.makeText(InicioActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                // Capture ListView item click
                Log.i(TAG,"onItemLongClick");
                Toast.makeText(InicioActivity.this, "onItemLongClick", Toast.LENGTH_SHORT).show();
                listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                    @Override
                    public void onItemCheckedStateChanged(ActionMode mode,
                                                          int position, long id, boolean checked) {
                        Log.i(TAG,"onItemCheckedStateChanged");
                        Toast.makeText(InicioActivity.this, "onItemCheckedStateChanged", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                        Log.i(TAG,"onActionItemClicked");
                        Toast.makeText(InicioActivity.this, "onActionItemClicked", Toast.LENGTH_SHORT).show();
                            return true;

                    }


                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        Log.i(TAG,"onCreateActionMode");
                        Toast.makeText(InicioActivity.this, "onCreateActionMode", Toast.LENGTH_SHORT).show();

                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        Log.i(TAG,"onDestroyActionMode");
                        Toast.makeText(InicioActivity.this, "onDestroyActionMode", Toast.LENGTH_SHORT).show();
                        //  mAdapter.removeSelection();
                    }
                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        Log.i(TAG,"onPrepareActionMode");
                        Toast.makeText(InicioActivity.this, "onPrepareActionMode", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                return false;
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
