package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;


/**
 * main de prueba para comprobar que funciona la base de datos
 * Created by laura on 21/03/2016.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    Button mostrar;
    TextView pregunta_id;
    TextView texto_pregunta;
    Pregunta pregunta;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    public void onClick(View v) {
        Log.i(TAG, "Pulsado botón");
        texto_pregunta = (TextView) findViewById(R.id.texto_pregunta);

        Pregunta pregunta2= new Pregunta();
        pregunta2.pregunta_ID=2;
        pregunta2.texto="pregunta 2";
        pregunta2.pregunta_ID=2;
          /*
        ContentValues values = new ContentValues();
        values.put(Pregunta.KEY_ID, 1);
        values.put(Pregunta.KEY_text,"Pregunta 1" );
        values.put(Pregunta.KEY_ID_test,1 );

        db.insert(Pregunta.TABLE,null,values);*/




        PreguntaDAO preguntaDAO = new PreguntaDAO(MainActivity.this);
        preguntaDAO.insert(pregunta2);

        PreguntaDAO preguntaDAO2 = new PreguntaDAO(MainActivity.this);

        pregunta = preguntaDAO2.getById(2);





        texto_pregunta = (TextView) findViewById(R.id.texto_pregunta);

        texto_pregunta.setText(pregunta.texto);
        //  texto_pregunta.setText("ok");


    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mostrar = (Button) findViewById(R.id.mostrar);

        mostrar.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
