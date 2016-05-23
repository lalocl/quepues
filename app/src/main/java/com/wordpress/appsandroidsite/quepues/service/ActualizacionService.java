package com.wordpress.appsandroidsite.quepues.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.DAO.UrlDAO;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.activity.ResultadoActivity;
import com.wordpress.appsandroidsite.quepues.modelo.Url;
import com.wordpress.appsandroidsite.quepues.soap.Peticion;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * Created by Aula10 on 12/05/2016.
 */
public class ActualizacionService extends Service{
    private static final String TAG ="ActualizacionService";
    private static final int CUSTOM_NOTIFICATION=1001;
    private Thread thread;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        thread = new Thread(new Runnable() {


            ArrayList<Url> lista;
            Peticion p;
            UrlDAO urlDAO=new UrlDAO(ActualizacionService.this);


            @Override
            public void run() {
                Log.i(TAG, "Actualizando...");

               String ultMod=urlDAO.ultActualizacion();
                Log.i(TAG, "Ultima fecha de actualización: " + ultMod);


                p= new Peticion(ActualizacionService.this);

                try {

                    p.verListaUrls(ultMod);



                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }



        });

        thread.start();




        return START_STICKY;

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
