package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.R;
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

    TextView idTest;
    TextView idpregunta;
    TextView textPregunta;
    Button button;

    TextView idOpcion1;
    TextView idOpcion2;
    TextView idOpcion3;
    TextView idOpcion4;

    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;


    @Override
    public void onClick(View v) {
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

        /*
        Falta calcular con DAO las opciones
         */
        idOpcion1=(TextView)findViewById(R.id.id_opcion1);
        idOpcion1.setText("Id Opción 1");

        idOpcion2=(TextView)findViewById(R.id.id_opcion2);
        idOpcion2.setText("Id Opción 2");

        idOpcion3=(TextView)findViewById(R.id.id_opcion3);
        idOpcion3.setText("Id Opción 3");

        idOpcion4=(TextView)findViewById(R.id.id_opcion4);
        idOpcion4.setText("Id Opción 4");

        checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        checkBox1.setText("Texto Opcion 1");

        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox2.setText("Texto Opcion 2");

        checkBox3=(CheckBox)findViewById(R.id.checkBox3);
        checkBox3.setText("Texto Opcion 3");

        checkBox4=(CheckBox)findViewById(R.id.checkBox4);
        checkBox4.setText("Texto Opcion 4");

    }


}
