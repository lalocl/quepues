package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.R;

import com.wordpress.appsandroidsite.quepues.adapter.UrlAdapter;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 04/04/2016.
 */
public class ResultadoActivity extends Activity {
    private static final String TAG = "ResultadoActivity";

    int id_test;
    int id_categoria1,id_categoria2;

    TextView resultado_categoria;
    TextView resultado_texto;
    ListView list_url;
    ArrayList<Url> lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        int id_test;
        int id_categoria1,id_categoria2;
        //Por defecto pondremos el id_test 1

        id_test=getIntent().getIntExtra("id_test",1);
        id_categoria1=getIntent().getIntExtra("id_categoria1",1);
        id_categoria2=getIntent().getIntExtra("id_categoria2",2);




        resultado_categoria = (TextView) findViewById(R.id.resultado_categoria);
        resultado_texto = (TextView) findViewById(R.id.resultado_texto);
        list_url=(ListView)findViewById(R.id.list_url);



        UrlDAO urlDAO= new UrlDAO(this);
        lista= urlDAO.getList(id_test,id_categoria1);


        UrlDAO urlDAOb= new UrlDAO(this);
        ArrayList<Url> lista2= urlDAOb.getList(id_test,id_categoria2);

        lista.addAll(lista2);
        Toast toast=Toast.makeText(this,"Tamaño lista " + lista.size(), Toast.LENGTH_LONG);
        toast.show();

        UrlAdapter adapter = new UrlAdapter(this,lista);
        list_url.setAdapter(adapter);
        list_url.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    /*
                     * Cuando pulsamos creamos una nueva actividad y recuperamos los datos a trav�s
                     * de un select con el id del usuario que hemos pulsado.
                     */
                Intent webIntent= new Intent(Intent.ACTION_VIEW, Uri.parse(lista.get(position).url));
                startActivity(webIntent);

              /*  Toast toast = Toast.makeText(ResultadoActivity.this, lista.get(position).url, Toast.LENGTH_SHORT);
                toast.show();
                */
            }
        });







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
