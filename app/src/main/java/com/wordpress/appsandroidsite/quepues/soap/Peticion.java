package com.wordpress.appsandroidsite.quepues.soap;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.modelo.Url;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by Aula10 on 12/05/2016.
 */
public class Peticion extends Conectar{
    private static final String TAG = "Peticion";


    private JSONObject jsonParam;

    Url nuevaUrl= null;
    ArrayList<Url> lista=null;
    Url [] array=null;

    int totalInsertados;

    public Peticion(Context context){
        super(context);


    }

    public ArrayList<Url> getLista() {

        return lista;
    }


    public void onCreate() {
       super.onCreate();
        Log.i(TAG, "Creado Peticion");
    }


    public  void verListaUrls(String mod) throws UnsupportedEncodingException {
        Log.i(TAG, "Método verListaUrls");
        String fecha="";



        if(mod!=null) {
          //  la codificamos para que nos reconozca la url los espacios y caracteres especiales

           fecha =Constants.HTTP_ext_url+ "?ultima_mod=" + URLEncoder.encode(mod,"UTF-8");
        }else{
            fecha =Constants.HTTP_ext_url;
        }


        lista= new ArrayList<Url> ();

        Log.i(TAG, "Conectando con : " + fecha + "...");
        JsonArrayRequest rq = new JsonArrayRequest(fecha, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "Haciendo petición...");
                try{
                    if(response.length()>0){
                        Log.i(TAG, "Hay respuesta");
                        for(int i =0;i<response.length();i++){
                            jsonParam= response.getJSONObject(i);
                            nuevaUrl= new Url();
                            nuevaUrl.setUrl(jsonParam.getString("url"));
                            nuevaUrl.setSubCategoria(jsonParam.getString("subCategoria"));
                            nuevaUrl.setCodigo_categoria(jsonParam.getString("categoria"));
                            nuevaUrl.setCodigo_test(jsonParam.getString("test"));
                            nuevaUrl.setUltima_mod(jsonParam.getString("ultimaMod"));
                            nuevaUrl.setUrl_ID(jsonParam.getInt("id"));
                            lista.add(nuevaUrl);
                            Log.i(TAG, i + " Url agregada a la lista: " + lista.get(i).getUrl());
                        }


                        array=new Url[lista.size()];
                        array=lista.toArray(array);
                        UrlDAO urlDAO= new UrlDAO(context);
                       totalInsertados= urlDAO.insert(array);
                        if(totalInsertados>0) {
                            Log.i(TAG, "Los registros han sido correctamente. Total: " + totalInsertados);
                        }else{
                            Log.i(TAG, "No se han podido insertar los registros");
                        }



                    }else{
                        Log.i(TAG, "No hay nuevos cursos insertados");
                    }


                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG,"Error: "+ error.getMessage());
                Log.i(TAG, "Error al hacer petición");
            }

        });


        on();
        addToRequestQueue(rq);




    }






}