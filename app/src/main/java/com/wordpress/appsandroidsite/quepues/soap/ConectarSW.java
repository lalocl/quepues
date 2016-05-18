package com.wordpress.appsandroidsite.quepues.soap;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Aula10 on 12/05/2016.
 */
public class ConectarSW {
    private static final String TAG ="ConectarSW";

    String ip="192.168.1.59";
    String puerto="8080";
    String bbdd="tests";

    final String HTTP="http://"+ip+":"+puerto+"/"+bbdd+"/api/";
 //   final String HTTP="http://localhost:8080/tests/api/";
    private HttpURLConnection urlConnection;
    private String metodo;

    public HttpURLConnection on (String ext,String metodo){
        URL url;

        try{
            url=new URL(HTTP+ext);

            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod(metodo);
            urlConnection.setUseCaches(false);
            urlConnection.setReadTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestProperty("Host","android.schoolportal.gr");
            urlConnection.connect();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        return urlConnection;
    }

    public void off(){
        urlConnection.disconnect();
    }


    public String getHTTP() {
        return HTTP;
    }

    public HttpURLConnection getUrlConnection() {
        return urlConnection;
    }

    public void setUrlConnection(HttpURLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
