package com.wordpress.appsandroidsite.quepues.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.R;

import com.wordpress.appsandroidsite.quepues.service.ActualizacionService;
import com.wordpress.appsandroidsite.quepues.soap.VolcarDatosTask;


/**
 * main de prueba para comprobar que funciona la base de datos
 * Created by laura on 21/03/2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ProgressBar progressBar;




    protected  void onStart(){
        super.onStart();

        //Falta implementar el upload mientras se carga
         if(new UrlDAO(this).getSize()!=0){
             Log.i(TAG, "Comprobando si hay cursos nuevos...");

            Intent service = new Intent(MainActivity.this, ActualizacionService.class);
            startService(service);

            Intent i = new Intent(MainActivity.this, InstruccionesActivity.class);
            startActivity(i);
        }else{

            progressBar = (ProgressBar) findViewById(R.id.pbEntry);
            setProgressBarIndeterminateVisibility(true);

            Toast toast=Toast.makeText(this,"Espere mientras se carga la base de datos", Toast.LENGTH_LONG);
            toast.show();



        }



    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);








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
