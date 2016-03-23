
/**
 * @(#)Test.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.modelo;

/**
 * Clase modelo de la tabla Test
 *
 * La aplicación consiste en un test vocacional, dependiendo del perfil del usuario, le direccionará a uno u otro.
 *
 *
 * @version 0.1 2016/03/18
 * @author laura
 */
public class Test {

    //Etiqueta del nombre de la tabla en la bbdd
    /**Tabla Test*/
    public static final String TABLE="Test";

    //Etiqueta del nombre de las columnas de la tabla Categoria
    /**Columna id de la tabla Test*/
    public static final String KEY_ID="id";
    /**Columna tipo de la tabla Test*/
    public static final String KEY_tipe="tipo";

    //Variables asociadas a las columnas de la tabla
    /** Variable id de la clase Test, asociada a la columna id de la tabla Test*/
    public int test_ID;
    /** Variable tipo de la clase Test, asociada a la columna tipo de la tabla Test*/
    public String tipo;

}
