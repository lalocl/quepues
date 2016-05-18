package com.wordpress.appsandroidsite.quepues.soap;

/**
 * Created by Aula10 on 17/05/2016.
 */
public class Constants {
   private static final String ip="192.168.1.59";
    private static final  String puerto="8080";
    private static final String bbdd="tests";

    private static final  String HTTP="http://"+ip+":"+puerto+"/"+bbdd+"/api/";
    private static final  String HTTP_ext_url= HTTP +"Url";

    public static String getHTTP_ext_url() {
        return HTTP_ext_url;
    }
}