/**
 * @(#)Categoria.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */
package com.wordpress.appsandroidsite.quepues.modelo;

/**
 * Clase modelo de la tabla Categoria
 * @version 1.0 2016/03/18
 * @author laura
 */
public class Categoria {

    //Etiqueta del nombre de la tabla en la bbdd
    public static final String TABLE="Categoria";

    //Etiqueta del nombre de las columnas de la tabla Categoria
    public static final String KEY_ID="id";
    public static final String KEY_name="nombre";
    public static final String KEY_url="url";
    public static final String KEY_result="resultado";


    //Variables asociadas a las columnas de la tabla
    public int categoria_ID;
    public String nombre;
    public String url;
    public String resultado;

}
