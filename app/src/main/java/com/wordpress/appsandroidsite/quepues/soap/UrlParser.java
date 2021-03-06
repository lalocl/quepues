package com.wordpress.appsandroidsite.quepues.soap;

import android.content.Context;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by laura on 05/04/2016.
 */
public class UrlParser {
    private static final String TAG = "UrlParser";

    private Url[] urls;
    private InputStream urlFile;

    public UrlParser(Context c){
        this.urlFile= c.getResources().openRawResource(R.raw.urls);
    }

//Pendiente: Hacer transacción, o se insertan todos o que no se inserte ninguno.
    public boolean parse(){
        boolean parsed=false;
        urls=null;




        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(urlFile);
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("url");

            urls = new Url[items.getLength()];
            /** Recorremos cada uno de los nodos */
            for (int i = 0; i < items.getLength(); i++) {
                /** Obtenemos el nodo de la posición i */
                Node item = items.item(i);
                /** Obtenemos los atributos necesarios para construir cada objeto Country */

                String codigo_categoria = item.getAttributes().getNamedItem("categoryCode").getNodeValue();
                String url = item.getAttributes().getNamedItem("url").getNodeValue();
                String subcategoria = item.getAttributes().getNamedItem("subCategory").getNodeValue();
                String codigo_test = item.getAttributes().getNamedItem("testCode").getNodeValue();
                String ultima_mod = item.getAttributes().getNamedItem("lastChange").getNodeValue();



                /** Con los datos obtenidos, creamos el objeto Country en la posición i del array */
                urls[i] = new Url(subcategoria,url,codigo_categoria,codigo_test);
                urls[i].ultima_mod=ultima_mod;
            }
        parsed=true;


        } catch (ParserConfigurationException pce) {
            Log.e(TAG, "ParserConfigurationException: " + pce.getLocalizedMessage());
        } catch (Exception e) {
            Log.e(TAG, "Unknown Exception: "+e.getLocalizedMessage());
        }
        return parsed;
    }

    public Url[]getUrls(){
        return this.urls;
    }
}
