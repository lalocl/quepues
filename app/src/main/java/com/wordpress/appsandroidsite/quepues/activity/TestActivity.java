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

    Integer[] valorCategorias;




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



            Intent i = new Intent(TestActivity.this, ResultadoActivity.class);
            i.putExtra("id_test", id_test);
            i.putExtra("totalPreguntas",listByTestId.size());
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
        if(valorCategorias==null) {
            valorCategorias = new Integer[totalCateg];
        }
        for(int i=0;i<valorCategorias.length;i++) {
            valorCategorias[i]=0;
        }

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
