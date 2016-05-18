package com.wordpress.appsandroidsite.quepues.soap;


import android.app.Application;
import android.content.Context;

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
public class Conectar extends Application{
    private static final String TAG ="Conectar";

    private RequestQueue queue;
  //  StringRequest stringRequest;
    JsonObjectRequest jsonRequest;
    Context context;



    private static Conectar conexion;

    public void onCreate(){
        super.onCreate();
        conexion=this;

    }
    public static synchronized Conectar getInstance(){
        return conexion;
    }

    public RequestQueue getRequestQueue(){
        if(queue==null){
            queue= Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }

    public <T>void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }






/*
    Conectar(Context context){
        this.context= context;

    }
*/



}
