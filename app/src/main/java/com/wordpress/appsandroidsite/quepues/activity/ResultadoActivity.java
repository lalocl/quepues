package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.wordpress.appsandroidsite.quepues.DAO.CategoriaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.PreguntaDAO;
import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.R;

import com.wordpress.appsandroidsite.quepues.adapter.UrlAdapter;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;
import com.wordpress.appsandroidsite.quepues.modelo.Puntuaciones;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 04/04/2016.
 */
public class ResultadoActivity extends Activity {
    private static final String TAG = "ResultadoActivity";

    int id_test;



    TextView resultado_categoria;
    TextView resultado_texto;
    ListView list_url;
    ArrayList<Url> lista;
  // Integer[]valoresPuntuaciones;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

   //     valoresPuntuaciones=Categoria.getPuntuaciones();
        int id_test;
        int totalPreguntas;
   //     int totalCategorias=valoresPuntuaciones.length;


   //     Log.i(TAG, "Total Categorias " + totalCategorias);


        totalPreguntas=getIntent().getIntExtra("totalPreguntas",4);
        id_test=getIntent().getIntExtra("id_test", 1);







        lista= new ArrayList<>();
        ArrayList<Url> lista2;


        Log.i(TAG, "Tamaño puntuaciones en la nueva actividad " + Puntuaciones.getPuntuaciones().size());
        for(int i=0;i< Puntuaciones.getPuntuaciones().size();i++){



            /*
             *Dividimos por 4 porque es la mitad de la mitad de las preguntas. Es decir, la puntuacion
             *máxima por categoria es la mitad de las preguntas, y para aparecer en el lustado, el usuario
             *tiene que haberla pulsado al menos la mitad de veces que aparece en el test
             */
            int puntuacionMinima=totalPreguntas/4;
            if(Puntuaciones.getPuntuaciones().get(i).getValor()>=puntuacionMinima){
                Log.i(TAG, "Entro a crear nueva lista");

                lista2= new UrlDAO(this).getList(Puntuaciones.getPuntuaciones().get(i).getCategoryCode());
                Log.i(TAG, "Valor puntuaciones: "+ Puntuaciones.getPuntuaciones().get(i).getCategoryCode()+" : "+ Puntuaciones.getPuntuaciones().get(i).getValor());

                lista.addAll(lista2);

            }

        }



        if(lista.size()>0) {

            resultado_categoria = (TextView) findViewById(R.id.resultado_categoria);
            resultado_texto = (TextView) findViewById(R.id.resultado_texto);
            list_url = (ListView) findViewById(R.id.list_url);




            UrlAdapter adapter = new UrlAdapter(this, lista);
            list_url.setAdapter(adapter);
            list_url.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   /*
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(lista.get(position).url));
                    startActivity(webIntent);
                    */



                    Intent i = new Intent( ResultadoActivity.this,WebViewActivity.class);
                    i.putExtra("url", lista.get(position).url);
                    startActivity(i);
                }
            });


        }else{
            Toast toast2 = Toast.makeText(ResultadoActivity.this,"No se han reunido suficientes datos, visite nuestra página", Toast.LENGTH_SHORT);
            toast2.show();

        }




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
