/**
 * @(#)Pregunta.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.modelo;

/**
 * Clase modelo de la tabla Pregunta.
 *
 * La aplicación consiste en unos tests de preguntas, esta clase se encarga de gestionar el apartado preguntas
 * de la base de datos, en ella guardamos tanto las variables que utilizaremos para operar con las preguntas
 * como las que hacen falta para hacer la base de datos integrada.
 *
 * Dependiendo del perfil del cliente según sus estudios se le pasará un test u otro.
 *
 *
 * @version 0.1 2016/03/18
 * @author laura
 */


public class Pregunta {
    //Etiqueta del nombre de la tabla en la bbdd
    /**Tabla Pregunta*/
    public static final String TABLE="Pregunta";

    public Pregunta() {

    }

    public Pregunta(String texto, int numero, String codigo_test) {
        this.texto = texto;
        this.numero = numero;
        this.codigo_test=codigo_test;

    }
    //Etiquetas de los nombres de las columnas de la tabla Pregunta
    /** Columna id de la tabla Pregunta*/
    public static final String KEY_ID="id";
    /** Columna texto de la tabla Pregunta*/
    public static final String KEY_text="texto";
  //  /** Columna id_test de la tabla Pregunta*/
  //  public static final String KEY_ID_test="id_test";
    /**Columna test_code de la tabla Pregunta*/
    public static final String KEY_test_code="codigo_test";
    /** Columna numero de la tabla Pregunta*/
    public static final String KEY_number="numero";

    //Variables asociadas a las columnas de la tabla
    /** Variable id de la clase Pregunta, asociada a la columna id de la tabla Pregunta*/
    public int pregunta_ID;
    /**Variable texto de la clase Pregunta, asociada a la columna texto de la tabla Pregunta*/
    public String texto;
  //  /** Variable id del test de la clase Pregunta, asociada a la columna id de la tabla Pregunta*/
  //  public int test_ID;
    /** Variable codigo del test de la clase Url, asociada a la columna codigo_test de la tabla Url*/
    public String codigo_test;
    /** Variable número de la pregunta correspondiente a su test de la clase Pregunta, asociada a la columna numero de la tabla Pregunta*/
    public int numero;



}
