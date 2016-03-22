package com.wordpress.appsandroidsite.quepues.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wordpress.appsandroidsite.quepues.R;


/**
 * Created by laura on 21/03/2016.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    Button mostrar;
    TextView pregunta_id;
    TextView texto_pregunta;


    @Override
    public void onClick(View v) {

        texto_pregunta=(TextView)findViewById(R.id.texto_pregunta);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mostrar=(Button)findViewById(R.id.mostrar);
        mostrar.setOnClickListener(this);


    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
