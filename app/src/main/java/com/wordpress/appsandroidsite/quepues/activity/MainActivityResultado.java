package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.BBDD.DBHelper;
import com.wordpress.appsandroidsite.quepues.DAO.TestDAO;
import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 04/04/2016.
 */
public class MainActivityResultado  extends Activity {
    private static final String TAG = "MainActivity";

    TextView resultado_categoria;
    TextView resultado_texto;
    ListView list_url;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //base de datos rápida para hacer pruebas
        nuevoTest("Aula 10");
        nuevoTest("Escuela Negocio");
        //enlaces por categoria
        listaCategorias(5);


        resultado_categoria = (TextView) findViewById(R.id.resultado_categoria);
        resultado_texto = (TextView) findViewById(R.id.resultado_texto);
        list_url=(ListView)findViewById(R.id.list_url);


        UrlDAO urlDAO= new UrlDAO(this);
        ArrayList<Url> lista= urlDAO.getList(1,1);
        UrlAdapter adapter = new UrlAdapter(this,lista);
        list_url.setAdapter(adapter);









    }
    public void nuevoTest(String nombreTipo){
        TestDAO testDAO= new TestDAO(MainActivityResultado.this);
        Test nuevoTest = new Test();
        nuevoTest.tipo=nombreTipo;
        int idTest= testDAO.insert(nuevoTest);
        Toast toast=Toast.makeText(this,"Agregado test con id " + idTest, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void listaCategorias(int enlacesPorCategoria){

        DBHelper dbHelper=new DBHelper(MainActivityResultado.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valuesC;
        ContentValues valuesU;
        Categoria categ;


        String[] categorias = {"Gestión","Marketing","Diseño","Hosteleria y/o Turismo","Informática","Tecnológica","Sociosanitaria","Imagen Personal"};
        String[] resultados = {"La Gestión es la ciencia social que tiene por objeto el estudio de las organizaciones y la técnica encargada de la planificación, organización, dirección y control de los recursos (humanos, financieros, materiales, tecnológicos, del conocimiento, etc.) de una organización, con el fin de obtener eficiencia o máximo beneficio posible; este beneficio puede ser social, económico o estratégico, dependiendo de los fines perseguidos por dicha organización.","" +
                "Marketing es un concepto inglés, traducido al castellano como mercadeo o mercadotecnia. Se trata de la disciplina dedicada al análisis del comportamiento de los mercados y de los consumidores. El marketing analiza la gestión comercial de las empresas con el objetivo de captar, retener y fidelizar a los clientes a través de la satisfacción de sus necesidades","" +
                "Diseño es una actividad creativa que tiene por fin proyectar objetos que sean útiles y estéticos.","" +
                "Hostelería es el nombre genérico de las actividades económicas consistentes en la prestación de servicios ligados al alojamiento y la alimentación esporádicos, muy usualmente ligados al turismo.","" +
                "La Informática es una ciencia que estudia métodos, técnicas, procesos, con el fin de almacenar, procesar y transmitir información y datos en formato digital.","" +
                "Tecnología es el conjunto de conocimientos técnicos, científicamente ordenados, que permiten diseñar, crear bienes, servicios que facilitan la adaptación al medio ambiente y satisfacer tanto las necesidades esenciales como los deseos de la humanidad","" +
                "La atención sociosanitaria es un título de formación profesional que reúne los servicios que coordinan la asistencia curativa, social y educativa de colectivos en situación de dependencia como la tercera edad, los enfermos crónicos y las personas con alguna discapacidad física, psíquica o sensoria","" +
                "La imagen personal es una fuerte herramienta, ya sea de ventas, de negociaciones o de vida social.Es la manera en la que nos presentamos en el mercado, de manera integral. Con ello nos referimos a que la imagen (aunque el término de por sí apele a lo visual) es un complemento de la estética o la apariencia física y la percepción abstracta."};

        int idCateg;
        for(int i=0;i<categorias.length;i++) {


            valuesC = new ContentValues();
            valuesC.put(Categoria.KEY_name, categorias[i]);
            valuesC.put(Categoria.KEY_result, resultados[i]);
            idCateg=(int)db.insert(Categoria.TABLE, null, valuesC);
            for(int j=0;j<enlacesPorCategoria;j++) {
                valuesU=new ContentValues();
                valuesU.put(Url.KEY_url,"Url " + (j+1) + " de la Categoria " + categorias[i]);
                valuesU.put(Url.KEY_ID_test, 1);
                valuesU.put(Url.KEY_ID_category,idCateg);
                db.insert(Url.TABLE, null, valuesU);

            }

        }
        db.close();

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
