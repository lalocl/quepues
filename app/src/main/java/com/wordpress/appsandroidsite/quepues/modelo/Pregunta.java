/**
 * @(#)Pregunta.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.modelo;

/**
 * Clase modelo de la tabla Pregunta
 * @version 1.0 2016/03/18
 * @author laura
 */


public class Pregunta {
    //Etiqueta del nombre de la tabla en la bbdd
    public static final String TABLE="Pregunta";

    //Etiqueta del nombre de las columnas de la tabla Pregunta
    public static final String KEY_ID="id";
    public static final String KEY_text="texto";

    //Variables asociadas a las columnas de la tabla
    public int pregunta_ID;
    public String texto;


}
