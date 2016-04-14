package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.TestDAO;
import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.adapter.PreguntasParser;
import com.wordpress.appsandroidsite.quepues.adapter.UrlParser;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
import com.wordpress.appsandroidsite.quepues.modelo.Url;
import com.wordpress.appsandroidsite.quepues.service.volcarDatosService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * main de prueba para comprobar que funciona la base de datos
 * Created by laura on 21/03/2016.
 */
public class MainActivity extends Activity  {

    private static final String TAG = "MainActivity";




    protected  void onStart(){
        super.onStart();

        //Falta implementar el upload mientras se carga
        if(new CategoriaDAO(this).getSize()!=0){
            Intent i = new Intent(MainActivity.this, InicioActivity.class);
            startActivity(i);
        }else{

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
