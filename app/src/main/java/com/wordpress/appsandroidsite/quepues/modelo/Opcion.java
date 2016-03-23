/**
 * @(#)Opcion.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.modelo;

/**
 * Clase modelo de la tabla Opcion
 *
 * La aplicación consiste en un test vocacional en el que el usuario tiene que ir contestando preguntas
 * eligiendo de las opciones que se le muestran según sus preferencias, estas opciones están asociadas
 * por un lado a la pregunta que contestan, y por otro lado a la categoria que representan.
 *
 * @version 0.1 2016/03/18
 * @author laura
 */
public class Opcion {
    //Etiqueta del nombre de la tabla en la bbdd
    /**Tabla Opcion*/
    public static final String TABLE="Opcion";

    //Etiquetas de los nombres de las columnas de la tabla Opcion
    /**Columna id de la tabla Opcion*/
    public static final String KEY_ID="id";
    /**Columna texto de la tabla Opcion*/
    public static final String KEY_text="texto";
    /**Columna id_categoria de la tabla Opcion*/
    public static final String KEY_ID_category="id_categoria";
    /**Columna id_pregunta de la tabla Opcion*/
    public static final String KEY_ID_question="id_pregunta";



    //Variables asociadas a las columnas de la tabla para operar en java
    /** Variable id de la clase Opcion, asociada a la columna id de la tabla Opcion*/
    public int opcion_ID;
    /** Variable texto de la clase Opcion, asociada a la columna texto de la tabla Opcion*/
    public String texto;
    /** Variable id de la categoria de la clase Opcion, asociada a la columna id_categoria de la tabla Opcion*/
    public int categoria_ID;
    /** Variable id de la pregunta de la clase Opcion, asociada a la columna id_pregunta de la tabla Opcion*/
    public int pregunta_ID;
}
