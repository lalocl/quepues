package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.DAO.OpcionDAO;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;

import java.util.ArrayList;

/**
 * Created by laura on 30/03/2016.
 */
public class TesterActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "TesterActivity";

    int id_test;
    int preguntaAMostrar;
    ArrayList<Pregunta> listByTestId;
    ArrayList<Opcion> listaOpciones;


    TextView idTest;
    TextView idpregunta;
    TextView textPregunta;
    Button button;

    TextView idOpcion;
    CheckBox checkBox;
    Integer[]ids={R.id.id_opcion1,R.id.id_opcion2,R.id.id_opcion3,R.id.id_opcion4};
    Integer[]checkBoxs={R.id.checkBox1,R.id.checkBox2,R.id.checkBox3,R.id.checkBox4};
    int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0,c8=0;
    Integer[] valorCategorias={c1,c2,c3,c4,c5,c6,c7,c8};
    Categoria categoriaFinal;
  //


    @Override
    public void onClick(View v) {




        int aux;
        for(int i=0;i<checkBoxs.length;i++){
            checkBox=(CheckBox)findViewById(checkBoxs[i]);
            int c;
           if( checkBox.isChecked() ){
            /*   c=listaOpciones.get(i).categoria_ID -1;
               aux= valorCategorias[c]+1;
              */
               Toast toast=Toast.makeText(this,"pulsado opcion " + (i+1), Toast.LENGTH_SHORT);
               toast.show();
               checkBox.setChecked(false);
           }
        }






        preguntaAMostrar=preguntaAMostrar+1;

        if(listByTestId.size()>preguntaAMostrar){
            calcularPregunta(preguntaAMostrar);
        }else{
            Toast toast=Toast.makeText(this,"No hay más preguntas", Toast.LENGTH_SHORT);
            toast.show();
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

        calcularPregunta(preguntaAMostrar);

    }

    public  void calcularPregunta(int numeroPregunta){
        idpregunta=(TextView)findViewById(R.id.id_pregunta);
        idpregunta.setText(String.valueOf(listByTestId.get(numeroPregunta).pregunta_ID));

        textPregunta=(TextView)findViewById(R.id.text_pregunta);
        textPregunta.setText(listByTestId.get(numeroPregunta).texto);

        idTest=(TextView)findViewById(R.id.id_test);
        idTest.setText(String.valueOf(listByTestId.get(numeroPregunta).test_ID));

        OpcionDAO opcionDAO = new OpcionDAO(TesterActivity.this);
        listaOpciones =opcionDAO.getListByPreguntaId(listByTestId.get(numeroPregunta).pregunta_ID);


        for(int i=0;i<listaOpciones.size();i++){
            idOpcion=(TextView)findViewById(ids[i]);
            idOpcion.setText(String.valueOf(listaOpciones.get(i).opcion_ID));
            checkBox=(CheckBox)findViewById(checkBoxs[i]);
            checkBox.setText(listaOpciones.get(i).texto);
        }


    }


}
