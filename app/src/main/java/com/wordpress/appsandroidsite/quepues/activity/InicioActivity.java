package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Test;

/**
 * Created by laura on 14/04/2016.
 */
public class InicioActivity extends Activity {

    private static final String TAG = "InicioActivity";
    private int id_test;
    RadioGroup rg;
    RadioButton radioButton0;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    Button buttonEntrarTest;
    Test t;


    protected  void onStart(){
        super.onStart();
        buttonEntrarTest= (Button) findViewById(R.id.buttonEntrarTest);

        radioButton0 =(RadioButton)findViewById(R.id.radioButton0);
        radioButton1 =(RadioButton)findViewById(R.id.radioButton1);
        radioButton2 =(RadioButton)findViewById(R.id.radioButton2);
        radioButton3 =(RadioButton)findViewById(R.id.radioButton3);
        radioButton4 =(RadioButton)findViewById(R.id.radioButton4);
        radioButton5 =(RadioButton)findViewById(R.id.radioButton5);
        radioButton6 =(RadioButton)findViewById(R.id.radioButton6);



        rg =(RadioGroup)findViewById(R.id.grbGrupo1);
        rg.clearCheck();



        buttonEntrarTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int testSeleccionado = rg.getCheckedRadioButtonId();

                if (testSeleccionado > 0) {

                switch(testSeleccionado){
                    case R.id.radioButton0:
                    case R.id.radioButton1:
                    case R.id.radioButton2:
                    case R.id.radioButton3:
                    case R.id.radioButton4:
                        id_test=1;
                        break;
                    case R.id.radioButton5:
                    case R.id.radioButton6:
                        id_test=2;
                        break;


                }



                    Intent i = new Intent(InicioActivity.this, TestActivity.class);
                    i.putExtra("id_test", id_test);
                    startActivity(i);
                }else{
                    Toast toast = Toast.makeText(InicioActivity.this, "Debe elegir una opción de test", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });



    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        //   basePruebaUrl();
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
