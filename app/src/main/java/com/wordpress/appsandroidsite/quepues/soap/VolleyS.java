package com.wordpress.appsandroidsite.quepues.soap;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Clase Singleton que trabaja con la libreria Volley
 *
 *  Se encarga de crear una sola cola de trabajo con todas las peticiones que hacemos al servidor.
 *  Desde la app movil las peticiones que haremos al servidor es saber si hay alguna actualización del contenido
 *  (en nuestro caso de las urls que nos devuelve una vez finalizado el test).
 *
 *
 * @version 0.1 2016/03/18
 * @author laura
 */


public class VolleyS {

    private static VolleyS mVolleyS = null;

    /** Cola donde se añaden las peticiones*/
    private RequestQueue mRequestQueue;

    private VolleyS(Context context) {

        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context) {
        if (mVolleyS == null) {
            mVolleyS = new VolleyS(context);
        }
        return mVolleyS;
    }

    public RequestQueue getRequestQueue() {

        return mRequestQueue;
    }
}