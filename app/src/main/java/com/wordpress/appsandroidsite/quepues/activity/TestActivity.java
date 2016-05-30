package com.wordpress.appsandroidsite.quepues.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.OpcionDAO;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.adapter.SelectorAdaptador;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Puntuaciones;

import java.util.ArrayList;

/**
 * Created by laura on 30/03/2016.
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TesterActivity";

  //  int id_test;
    private String cod_test;
    int preguntaAMostrar;
    ArrayList<Pregunta> listByTestId;
    ArrayList<Opcion> listaOpciones;



    TextView textPregunta;
    ImageButton button;
    ListView listView;

    CheckBox checkBox;

    Integer[]checkBoxs={R.id.checkBox1,R.id.checkBox2,R.id.checkBox3,R.id.checkBox4};


    ArrayList <Boolean> seleccionadas;
    SelectorAdaptador adapter;
    int totalCateg;





    @Override
    public void onClick(View v) {




    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

    /*    Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);*/
        seleccionadas= new ArrayList<>();


        listView=(ListView)findViewById(R.id.list_botones);

        button=(ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(this);


        cod_test=getIntent().getStringExtra("cod_test");



       // Le indicamos el número de la primera pregunta a mostrar
        preguntaAMostrar=1;


        //Nos devuelve un array de preguntas
        PreguntaDAO preguntaDAO = new PreguntaDAO(this);
     //   listByTestId= preguntaDAO.getListByTestId(id_test);
        listByTestId= preguntaDAO.getListByTestId(cod_test);



        CategoriaDAO categoriaDAO= new CategoriaDAO(TestActivity.this);
        totalCateg=categoriaDAO.getSize();
        Log.i(TAG,"Total categorias: " +totalCateg);



        Puntuaciones.setPuntuaciones(new ArrayList<Puntuaciones>());
       // Log.i(TAG,"Total puntuaciones: " +Puntuaciones.getPuntuaciones().size());







        Log.i(TAG,"Pregunta a mostrar: " + preguntaAMostrar);
        calcularPregunta(preguntaAMostrar);

    }

    public  void calcularPregunta(int numeroPregunta){


        Log.i(TAG,"Calculando pregunta...");
        textPregunta=(TextView)findViewById(R.id.textViewPregunta);




        OpcionDAO opcionDAO = new OpcionDAO(TestActivity.this);
        Log.i(TAG,"Total preguntas: " +listByTestId.size());
        for(int i=0; i<listByTestId.size();i++) {
            if(listByTestId.get(i).numero==numeroPregunta) {
                listaOpciones = opcionDAO.getListByPreguntaId(listByTestId.get(i).pregunta_ID);
                textPregunta.setText(listByTestId.get(i).texto);
            }
        }

        seleccionadas.clear();
        for(int i=0;i<listaOpciones.size();i++){
            seleccionadas.add(i,false);
        }




        Log.i(TAG,"Total opciones: " +listaOpciones.size());
        adapter= new SelectorAdaptador(TestActivity.this,listaOpciones);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                if(view.isSelected()){
                    view.setSelected(false);
                }else {
                    view.setSelected(true);

                }

                seleccionadas.set(position,view.isSelected());


                Log.i(TAG,"onItemClick");
                //  Toast.makeText(InicioActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








                int aux=0;

                //Vamos a comprobar todos los check box
                for(int i=0;i<seleccionadas.size();i++) {


                    //si está seleccionado
                    if (seleccionadas.get(i)) {
                        Log.i(TAG, "Seleccionado " + seleccionadas.get(i));

                        int valorPuntuacion = 0;

                        if(Puntuaciones.getPuntuaciones().size()!=0){
                            Log.i(TAG, "Chekeando ... : puntuaciones si tiene valores : " + Puntuaciones.getPuntuaciones().size());
                            boolean encontrado = false;

                            for (int j = 0; j < Puntuaciones.getPuntuaciones().size(); j++) {
                                Log.i(TAG, "Chekeando ... : buscando valor actual de categoria");


                                if (listaOpciones.get(i).codigo_categoria.equalsIgnoreCase(Puntuaciones.getPuntuaciones().get(j).getCategoryCode())) {
                                    Log.i(TAG, "Encontrado");
                                    Log.i(TAG, "Sumando " + listaOpciones.get(i).codigo_categoria);
                                    Puntuaciones.getPuntuaciones().get(j).incremento();
                                    encontrado = true;
                                    valorPuntuacion = Puntuaciones.getPuntuaciones().get(j).getValor();
                                    j = Puntuaciones.getPuntuaciones().size();

                                }
                            }


                            if (!encontrado) {
                                Log.i(TAG, "No encontrado");
                                Log.i(TAG, "Nuevo");
                                Puntuaciones p = new Puntuaciones(listaOpciones.get(i).codigo_categoria, 1);

                                valorPuntuacion = p.getValor();


                            }
                        } else{
                            Log.i(TAG, "Chekeando ... : puntuaciones no tiene valores" + Puntuaciones.getPuntuaciones().size());
                            Log.i(TAG, "Nuevo");
                            Puntuaciones p = new Puntuaciones(listaOpciones.get(i).codigo_categoria, 1);

                            valorPuntuacion = p.getValor();

                        }
                         Log.i(TAG, "pulsado opcion " + (i + 1) + " que es categoria " + listaOpciones.get(i).codigo_categoria + " con valor " + valorPuntuacion);


                        listView.setSelected(false);


                    }
                }



                preguntaAMostrar = preguntaAMostrar + 1;
                Log.i(TAG,"Pregunta a mostrar: " + preguntaAMostrar);

                if (listByTestId.size() > (preguntaAMostrar-1)) {
                    calcularPregunta(preguntaAMostrar);
                } else {


                    //   Categoria.setPuntuaciones(valorCategorias);

                    Intent i = new Intent(TestActivity.this, ResultadoActivity.class);
                    i.putExtra("cod_test", cod_test);
                    i.putExtra("totalPreguntas", listByTestId.size());
                    Log.i(TAG, "Antes de mudarnos de actividad el total de puntuaciones es:  " + Puntuaciones.getPuntuaciones().size());
                    startActivity(i);






                }








            }
        });



    }


}
