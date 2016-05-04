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
        textView.setText("Instrucciones");

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
