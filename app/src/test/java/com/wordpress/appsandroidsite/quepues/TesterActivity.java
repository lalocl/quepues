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
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;

import java.util.ArrayList;

/**
 * Created by laura on 30/03/2016.
 */
public class TesterActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "TestActivity";

    int id_test;
    int id_categoria1,id_categoria2;
    int preguntaAMostrar;
    ArrayList<Pregunta> listByTestId;
    ArrayList<Opcion> listaOpciones;


  //  TextView idTest;
  //  TextView idpregunta;
    TextView textPregunta;
    Button button;

 //   TextView idOpcion;
    CheckBox checkBox;
    Integer[]ids={R.id.id_opcion1,R.id.id_opcion2,R.id.id_opcion3,R.id.id_opcion4};
    Integer[]checkBoxs={R.id.checkBox1,R.id.checkBox2,R.id.checkBox3,R.id.checkBox4};

    Integer[] valorCategorias;
   // Categoria categoriaFinal;



    @Override
    public void onClick(View v) {




        int aux=0;
        for(int i=0;i<checkBoxs.length;i++){
            checkBox=(CheckBox)findViewById(checkBoxs[i]);
            int c;
           if( checkBox.isChecked() ){
              c=listaOpciones.get(i).categoria_ID -1;
               aux=valorCategorias[c]+1;
               valorCategorias[c]=aux;


               Toast toast=Toast.makeText(this,"pulsado opcion " + (i+1) +" que es categoria " + (c +1) + " con valor " + valorCategorias[c], Toast.LENGTH_LONG);
               toast.show();
               checkBox.setChecked(false);


           }
        }




        preguntaAMostrar=preguntaAMostrar+1;

        if(listByTestId.size()>preguntaAMostrar){
            calcularPregunta(preguntaAMostrar);
        }else{


         //   Categoria.setPuntuaciones(valorCategorias);
            Intent i = new Intent(TesterActivity.this, ResultadoActivity.class);
            i.putExtra("id_test", id_test);
            i.putExtra("totalPreguntas",listByTestId.size());
            startActivity(i);

            /*
           //Calculamos las dos opciones más seleccionadas
            id_categoria1=0;
            id_categoria2=0;
            aux=0;
            for(int j=0;j<valorCategorias.length;j++){
                Log.i(TAG, "Dentro del bucle de suma");

                if(valorCategorias[j]>aux){
                    id_categoria1=j;
                    aux=valorCategorias[j];
                }else if(valorCategorias[j]>=aux && valorCategorias[j]!=0){
                    id_categoria2=j;
                    aux=valorCategorias[j];
                }


            }

            if(aux ==0) {
                Toast toast=Toast.makeText(this,"No hay datos suficientes para calcular resultado", Toast.LENGTH_LONG);
                toast.show();
            }else {
                Toast toast = Toast.makeText(this, id_categoria1 + " - " + id_categoria2, Toast.LENGTH_LONG);
                toast.show();

                //Activity para mostrar lista de enlaces correspondientes a las dos categorias más seleccionadas
                Intent i = new Intent(TesterActivity.this, ResultadoActivity.class);
                //    i.putExtra("id_test", 2);
                i.putExtra("id_test", id_test);
                i.putExtra("id_categoria1", id_categoria1+1);
                i.putExtra("id_categoria2", id_categoria2+1);


                startActivity(i);
            }
            */
        }



    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pruebas);


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
        CategoriaDAO categoriaDAO= new CategoriaDAO(TesterActivity.this);
        int totalCateg=categoriaDAO.getSize();
        Log.i(TAG,"Total categorias: " +totalCateg);
        if(valorCategorias==null) {
            valorCategorias = new Integer[totalCateg];
        }
        for(int i=0;i<valorCategorias.length;i++) {
            valorCategorias[i]=0;
        }

        calcularPregunta(preguntaAMostrar);

    }

    public  void calcularPregunta(int numeroPregunta){
     //   idpregunta=(TextView)findViewById(R.id.id_pregunta);
     //   idpregunta.setText(String.valueOf(listByTestId.get(numeroPregunta).pregunta_ID));

        textPregunta=(TextView)findViewById(R.id.text_pregunta);
        textPregunta.setText(listByTestId.get(numeroPregunta).texto);

    //    idTest=(TextView)findViewById(R.id.id_test);
    //    idTest.setText(String.valueOf(listByTestId.get(numeroPregunta).test_ID));

        OpcionDAO opcionDAO = new OpcionDAO(TesterActivity.this);
        listaOpciones =opcionDAO.getListByPreguntaId(listByTestId.get(numeroPregunta).pregunta_ID);


        for(int i=0;i<listaOpciones.size();i++){
    //        idOpcion=(TextView)findViewById(ids[i]);
    //        idOpcion.setText(String.valueOf(listaOpciones.get(i).opcion_ID));
            checkBox=(CheckBox)findViewById(checkBoxs[i]);
            checkBox.setText(listaOpciones.get(i).texto);
        }


    }


}
