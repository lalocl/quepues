package com.wordpress.appsandroidsite.quepues.soap;

import android.content.Context;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Test;
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
public class TestParser {
    private static final String TAG = "TestParser";

    private Test[] tests;
    private InputStream testFile;

    public TestParser(Context c){
        this.testFile= c.getResources().openRawResource(R.raw.tests);
    }

//Hacer transacción, o se insertan todos o que no se inserte ninguno. 
    public boolean parse(){
        boolean parsed=false;
        tests=null;




        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(testFile);
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("test");

            tests = new Test[items.getLength()];
            /** Recorremos cada uno de los nodos */
            for (int i = 0; i < items.getLength(); i++) {
                /** Obtenemos el nodo de la posición i */
                Node item = items.item(i);
                /** Obtenemos los atributos necesarios para construir cada objeto Test */

                String tipo = item.getAttributes().getNamedItem("tipe").getNodeValue();
                String test = item.getAttributes().getNamedItem("testCode").getNodeValue();



                /** Con los datos obtenidos, creamos el objeto Country en la posición i del array */
                tests[i] = new Test(tipo,test);
            }
        parsed=true;


        } catch (ParserConfigurationException pce) {
            Log.e(TAG, "ParserConfigurationException: " + pce.getLocalizedMessage());
        } catch (Exception e) {
            Log.e(TAG, "Unknown Exception: "+e.getLocalizedMessage());
        }
        return parsed;
    }

    public Test[]getTests(){
        return this.tests;
    }
}
