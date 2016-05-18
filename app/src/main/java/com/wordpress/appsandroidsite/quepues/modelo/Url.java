/**
 * @(#)Url.java   1.0 2016/03/18
 *
 * Licencia Creative Commons Atribution-NonCommercial-NoDerivates 4.0 International (CC BY-NC,ND 4.0)
 * Más información de la licencia: http://creativecommons.org/licenses/by-nc-nd/4.0/legalcode
 */

package com.wordpress.appsandroidsite.quepues.modelo;

/**
 * Clase modelo de la tabla Url
 *
 * La aplicación consiste en un test vocacional que tras realizarlo ofrece el resultado al usuario,
 * este resultado es por un lado, la rama de estudios que el usuario debería estudiar según sus preferencias
 * y por otro lado muestra enlaces donde poder visualizar los cursos que están disponible en esa rama.
 * Como existen dos opciones de
 * En el resultado, incluimos un texto para el resultado que le ha salido al usuario.
 *
 * @version 0.1 2016/03/18
 * @author laura
 */
public class Url {

    public Url(){

    }

    public Url(String subCategoria, String url, String codigo_categoria, String codigo_test) {
        this.subCategoria = subCategoria;
        this.url = url;
        this.codigo_categoria = codigo_categoria;
        this.codigo_test=codigo_test;

    }

    //Etiqueta del nombre de la tabla en la bbdd
    /**Tabla Url*/
    public static final String TABLE="Url";

    //Etiqueta del nombre de las columnas de la tabla Url
    /**Columna id de la tabla Url*/
    public static final String KEY_ID="id";
    /**Columna sub_categoria de la tabla Url*/
    public static final String KEY_subCategory="sub_categoria";
    /**Columna url de la tabla Url*/
    public static final String KEY_url="url";
 //   /**Columna id_categoria de la tabla Url*/
//    public static final String KEY_ID_category="id_categoria";
    /**Columna codigo_categoria de la tabla Url*/
    public static final String KEY_category_code="codigo_categoria";
 //   /**Columna id_test de la tabla Url*/
 //   public static final String KEY_ID_test="id_test";
    /**Columna test_code de la tabla Url*/
    public static final String KEY_test_code="codigo_test";
    /**Columna ultima_mod de la tabla Url*/
    public static final String KEY_last_change="ultima_mod";


    //Variables asociadas a las columnas de la tabla
    /** Variable id de la clase Url, asociada a la columna id de la tabla Url*/
    public int url_ID;
    /** Variable sub categoria de la clase Url, asociada a la columna sub_categoria de la tabla Url*/
    public String subCategoria;
    /** Variable url de la clase Url, asociada a la columna url de la tabla Url*/
    public String url;
 //   /** Variable id de la categoria de la clase Url, asociada a la columna id_categoria de la tabla Url*/
 //   public int categoria_ID;
    /** Variable codigo de la categoria de la clase Url, asociada a la columna codigo_categoria de la tabla Url*/
    public String codigo_categoria;
  //  /** Variable id del test de la clase Url, asociada a la columna id_test de la tabla Url*/
  //  public int test_ID;
    /** Variable codigo del test de la clase Url, asociada a la columna codigo_test de la tabla Url*/
    public String codigo_test;
    /** Variable de la última modificación efectuada de la clase Url, asociada a la columna ultima_mod de la tabla Url*/
    public String ultima_mod;

    public int getUrl_ID() {
        return url_ID;
    }

    public void setUrl_ID(int url_ID) {
        this.url_ID = url_ID;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCodigo_categoria() {
        return codigo_categoria;
    }

    public void setCodigo_categoria(String codigo_categoria) {
        this.codigo_categoria = codigo_categoria;
    }

    public String getCodigo_test() {
        return codigo_test;
    }

    public void setCodigo_test(String codigo_test) {
        this.codigo_test = codigo_test;
    }

    public String getUltima_mod() {
        return ultima_mod;
    }

    public void setUltima_mod(String ultima_mod) {
        this.ultima_mod = ultima_mod;
    }
}
