package com.wordpress.appsandroidsite.quepues.modelo;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by laura on 14/04/2016.
 */
public class Puntuaciones {
    private static final String TAG = "Puntuaciones";

    public static ArrayList<Puntuaciones> puntuaciones;

    String CategoryCode;
    int valor;

    //Constructores
    public Puntuaciones() {
    puntuaciones.add(this);

    }

    public Puntuaciones(String categoryCode) {
        CategoryCode = categoryCode;
       puntuaciones.add(this);

    }

    public Puntuaciones(String categoryCode, int valor) {
        CategoryCode = categoryCode;
        this.valor = valor;
      puntuaciones.add(this);
        Log.i(TAG, "Creado nuevo Puntuaciones");
    }

    //Métodos
    public void incremento(){
        valor=valor +1;
    }






    //Getters y Setters

    public static ArrayList<Puntuaciones> getPuntuaciones() {
        return puntuaciones;
    }

    public String getCategoryCode() {
        return CategoryCode;
    }

    public static void setPuntuaciones(ArrayList<Puntuaciones> puntuaciones) {
        Puntuaciones.puntuaciones = puntuaciones;
    }

    public void setCategoryCode(String categoryCode) {
        CategoryCode = categoryCode;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {

        return valor;
    }
}
