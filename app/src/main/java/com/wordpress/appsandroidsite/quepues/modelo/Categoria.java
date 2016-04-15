/**
 * @(#)Categoria.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */
package com.wordpress.appsandroidsite.quepues.modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase modelo de la tabla Categoria
 *
 * La aplicación consiste en un test vocacional que tras realizarlo ofrece el resultado al usuario,
 * este resultado es por un lado, la rama de estudios que el usuario debería estudiar según sus preferencias
 * y por otro lado muestra enlaces(tabla Url) donde poder visualizar los cursos que están disponible en esa rama,
 * la cual llamamos categoria. En el resultado, incluimos un texto para el usuario.
 *
 *
 * @version 0.1 2016/03/18
 * @author laura
 */
public class Categoria {

    //Etiqueta del nombre de la tabla en la bbdd
    /**Tabla Categoria*/
    public static final String TABLE="Categoria";



    public Categoria() {
    }

    public Categoria(String nombre,String codigo) {
        this.nombre = nombre;
        this.codigo=codigo;
    }
    //Etiquetas del nombre de las columnas de la tabla Categoria
    /**Columna id de la tabla Categoria*/
    public static final String KEY_ID="id";
    /**Columna nombre de la tabla Categoria*/
    public static final String KEY_name="nombre";
    /**Columna código de la tabla Categoria*/
    public static final String KEY_code="codigo";


    /**Columna url de la tabla Categoria
    public static final String KEY_url="url";
    Columna resultado de la tabla Categoria
    public static final String KEY_result="resultado";*/


    //Variables asociadas a las columnas de la tabla
    /** Variable id de la clase Categoria, asociada a la columna id de la tabla Categoria*/
    public int categoria_ID;
    /** Variable nombre de la clase Categoria, asociada a la columna nombre de la tabla Categoria*/
    public String nombre;
    /** Variable codigo de la clase Categoria, asociada a la columna codigode la tabla Categoria*/
    public String codigo;


    /** Variable url de la clase Categoria, asociada a la columna url de la tabla Categoria
    public String url;
    Variable resultado de la clase Categoria, asociada a la columna resultado de la tabla Categoria
    public String resultado;*/
/*
    public static Integer [] puntuaciones;
    public static Integer [] getPuntuaciones() {
        return puntuaciones;
    }
    public static void setPuntuaciones(Integer [] puntuaciones) {
        Categoria.puntuaciones = puntuaciones;
    }
    */

            /*
    public static HashMap puntuaciones= new HashMap();

    public static HashMap getPuntuaciones(){
        return puntuaciones;
    }
    public static void setPuntuaciones(HashMap puntuaciones) {
        Categoria.puntuaciones = puntuaciones;
    }

*/
}

