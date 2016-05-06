package com.wordpress.appsandroidsite.quepues.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wordpress.appsandroidsite.quepues.R;

/**
 * Created by laura on 04/05/2016.
 */
public class InstruccionesActivity extends AppCompatActivity {

    private static final String TAG = "InstruccionesActivity";

    TextView textView;
    Button button;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instrucciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        textView= (TextView)findViewById(R.id.textView);
        String texto="INTRUCCIONES TEST \n " +
                     "=================" +
                "“A continuación se te presentarán unas preguntas para ayudarte a determinar, según tus"

                + " preferencias, los cursos que más se ajustan a tu perfil.\n"
               + "Debes tener en cuenta:\n\t * Se pueden contestar varias opciones en cada " +
                "" +
                "pregunta \n\t" +
                " * No es necesario contestar una pregunta si no te sientes " +
               "identificad@ con ninguna respuesta.”";
        textView.setText(texto);

        button=(Button)findViewById(R.id.buttonEntrarTest);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(InstruccionesActivity.this, InicioActivity.class);
               startActivity(i);

           }
       });





    }
    protected  void onStart() {
        super.onStart();
    }

}
