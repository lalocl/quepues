package com.wordpress.appsandroidsite.quepues.soap;


import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;



/**
 * Created by Aula10 on 12/05/2016.
 */
public class Conectar extends Application {
    private static final String TAG ="Conectar";

    private VolleyS volley;
    protected RequestQueue queue;
  //  StringRequest stringRequest;
    JsonObjectRequest jsonRequest;
   Context context;


    public Conectar(Context context){

        this.context=context;

    }

//    private static Conectar conexion;



    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Creando la Conexión");
   /*     volley= VolleyS.getInstance(this.getApplicationContext());
        queue=volley.getRequestQueue();*/

   //    conexion=this;

    }

    public void on(){
        Log.i(TAG, "Activando Conexión");
        volley= VolleyS.getInstance(context.getApplicationContext());
        queue=volley.getRequestQueue();
    }


  /*  public static synchronized Conectar getInstance(){

        return conexion;
    }*/
/*
    public RequestQueue getRequestQueue(){
        if(queue==null){
            queue= Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }
*/
    public void addToRequestQueue(Request req){

        Log.i(TAG, "Añadir a cola");

        if(req!=null){
            req.setTag(this);
            if(queue==null){
                queue=volley.getRequestQueue();
            }
            req.setRetryPolicy(new DefaultRetryPolicy(60000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
          //  onPreStartConnection();
        }

       queue.add(req);
    }






/*
    Conectar(Context context){
        this.context= context;

    }
*/



}
