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
    private Thread notificacion;
    private ThreadNuevosCursos n;
    private ArrayList<Url> listaUrls;
    int nuevosCursosInsertados;

    Url[]array;

    UrlDAO urlDAO;






    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");



        notificacion = new Thread(new Runnable() {


            @Override
            public void run() {
                Log.i(TAG, "dentro del hilo");
                n = new ThreadNuevosCursos();
                n.start();

                urlDAO= new UrlDAO(ActualizacionService.this);


               synchronized (n){


                   try {
                       Log.i(TAG, "Esperando que se calculen los cursos...");
                       n.wait();
                   } catch (InterruptedException e) {
                       Log.e(TAG, "Erro al entrar en el hilo para calcular los cursos...");
                       e.printStackTrace();
                   }


               }
                listaUrls=n.lista;

                if(listaUrls.size()>0){
                    array= new Url[listaUrls.size()];
                    array=listaUrls.toArray(array);
                    nuevosCursosInsertados=urlDAO.insert(array);
                    Log.i(TAG,"Nuevos Cursos Insertados: "+ nuevosCursosInsertados);





                    Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ActualizacionService.this)
                            .setSmallIcon(R.drawable.books)
                            .setLargeIcon(((BitmapDrawable)getResources().getDrawable(R.drawable.aula10)).getBitmap())
                            .setContentTitle("¿Qué puedo estudiar online?")
                            .setContentText("Nueva notificación")
                            .setSound(ringtoneUri)
                            .setTicker("Nuevos cursos disponibles")
                            .setAutoCancel(true);

                    Intent noti = new Intent(ActualizacionService.this, ResultadoActivity.class);

                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,noti,PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent);

                    NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(CUSTOM_NOTIFICATION,mBuilder.build());


                }else{
                    Log.i(TAG,"No hay Nuevos Cursos");
                }





            }
        });

        notificacion.start();




        return START_STICKY;

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class ThreadNuevosCursos extends Thread{

        ArrayList<Url> lista;
        Peticion p;
        @Override
        public void run() {

            p= new Peticion(ActualizacionService.this);

            try {
                p.verListaUrls("2016-05-19 10:19:14");


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }

    }



}
