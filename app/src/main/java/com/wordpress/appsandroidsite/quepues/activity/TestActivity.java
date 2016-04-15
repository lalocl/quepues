package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.OpcionDAO;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Puntuaciones;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by laura on 30/03/2016.
 */
public class TestActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "TesterActivity";

    int id_test;
    int preguntaAMostrar;
    ArrayList<Pregunta> listByTestId;
    ArrayList<Opcion> listaOpciones;



    TextView textPregunta;
    Button button;

    CheckBox checkBox;

    Integer[]checkBoxs={R.id.checkBox1,R.id.checkBox2,R.id.checkBox3,R.id.checkBox4};

   // Integer[] valorCategorias;
  //  HashMap valorCategorias;





    @Override
    public void onClick(View v) {




        int aux=0;

        //Vamos a comprobar todos los check box
        for(int i=0;i<checkBoxs.length;i++) {
            checkBox = (CheckBox) findViewById(checkBoxs[i]);

            //si está checkeado
            if (checkBox.isChecked()) {
                Log.i(TAG, "Chekeado " + checkBox.getText().toString());

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
                    Toast toast = Toast.makeText(this, "pulsado opcion " + (i + 1) + " que es categoria " + listaOpciones.get(i).codigo_categoria + " con valor " + valorPuntuacion, Toast.LENGTH_LONG);
                toast.show();
                checkBox.setChecked(false);


                }
            }


            preguntaAMostrar = preguntaAMostrar + 1;

            if (listByTestId.size() > preguntaAMostrar) {
                calcularPregunta(preguntaAMostrar);
            } else {


                //   Categoria.setPuntuaciones(valorCategorias);

                Intent i = new Intent(TestActivity.this, ResultadoActivity.class);
                i.putExtra("id_test", id_test);
                i.putExtra("totalPreguntas", listByTestId.size());
                Log.i(TAG, "Antes de mudarnos de actividad el total de puntuaciones es:  " + Puntuaciones.getPuntuaciones().size());
                startActivity(i);






        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);


        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        //Por defecto pondremos el id_test 1
        id_test =1;
        id_test=getIntent().getIntExtra("id_test",1);

        //Asignamos el valor cero en la posición del array, que será la primera en mostrar
        preguntaAMostrar=0;



        //Nos devuelve un array de preguntas
        PreguntaDAO preguntaDAO = new PreguntaDAO(this);
        listByTestId= preguntaDAO.getListByTestId(id_test);


        //
        CategoriaDAO categoriaDAO= new CategoriaDAO(TestActivity.this);
        int totalCateg=categoriaDAO.getSize();
        Log.i(TAG,"Total categorias: " +totalCateg);



            Puntuaciones.setPuntuaciones(new ArrayList<Puntuaciones>());







        calcularPregunta(preguntaAMostrar);

    }

    public  void calcularPregunta(int numeroPregunta){


        textPregunta=(TextView)findViewById(R.id.text_pregunta);
        textPregunta.setText(listByTestId.get(numeroPregunta).texto);



        OpcionDAO opcionDAO = new OpcionDAO(TestActivity.this);
        listaOpciones =opcionDAO.getListByPreguntaId(listByTestId.get(numeroPregunta).pregunta_ID);


        for(int i=0;i<listaOpciones.size();i++){

            checkBox=(CheckBox)findViewById(checkBoxs[i]);
            checkBox.setText(listaOpciones.get(i).texto);
        }


    }


}
