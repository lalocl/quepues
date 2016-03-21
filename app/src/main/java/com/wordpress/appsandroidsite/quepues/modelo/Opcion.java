/**
 * @(#)Opcion.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.modelo;

/**
 * Clase modelo de la tabla Opcion
 * @version 1.0 2016/03/18
 * @author laura
 */
public class Opcion {
    //Etiqueta del nombre de la tabla en la bbdd
    public static final String TABLE="Opcion";

    //Etiqueta del nombre de las columnas de la tabla Opcion
    public static final String KEY_ID="id";
    public static final String KEY_text="texto";
    public static final String KEY_ID_category="id_categoria";
    public static final String KEY_ID_question="id_pregunta";



    //Variables asociadas a las columnas de la tabla para operar en java
    public int opcion_ID;
    public String texto;
}
