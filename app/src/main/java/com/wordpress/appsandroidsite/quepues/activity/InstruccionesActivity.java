package com.wordpress.appsandroidsite.quepues.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.adapter.UrlAdapter;
import com.wordpress.appsandroidsite.quepues.modelo.Url;
import com.wordpress.appsandroidsite.quepues.soap.Conectar;
import com.wordpress.appsandroidsite.quepues.soap.Peticion;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by laura on 04/05/2016.
 */
public class InstruccionesActivity extends AppCompatActivity {

    private static final String TAG = "InstruccionesActivity";

    TextView textView;
    Button button;
    ListView lst;


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

     /*  //Pruebas de peticion get de urls

      button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Peticion p= new Peticion(InstruccionesActivity.this);

                //pasar por parámetro la ultima fecha, la codificamos para que nos reconozca la url los espacios y caracteres especiales
                try {
                    ArrayList<Url> lista=p.verListaUrls("2016-05-19 10:13:36");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        });*/




    }
    protected  void onStart() {
        super.onStart();
    }

}
