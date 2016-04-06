package com.wordpress.appsandroidsite.quepues.adapter;

import android.content.Context;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;
import com.wordpress.appsandroidsite.quepues.modelo.Pregunta;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by laura on 06/04/2016.
 */
public class PreguntasParser {
    private static final String TAG="PreguntasParser";

    private Pregunta[] preguntas;
    private ArrayList<Opcion> opciones;
    private InputStream preguntasFile;

    public PreguntasParser(Context c){
        this.preguntasFile=c.getResources().openRawResource(R.raw.preguntas);
    }
    public boolean parse(){
        boolean parsed= false;
        preguntas= null;
        opciones= new ArrayList<>();

        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(preguntasFile);
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("pregunta");

            preguntas = new Pregunta[items.getLength()];
            for(int i=0; i<items.getLength();i++){
                Node item = items.item(i);
                String texto = item.getAttributes().getNamedItem("text").getNodeValue();
                int numero= Integer.parseInt(item.getAttributes().getNamedItem("number").getNodeValue());
                String test = item.getAttributes().getNamedItem("categoryTest").getNodeValue();

                int id_test;
                if(test.equalsIgnoreCase("a10")){
                    id_test=1;
                }else{
                    id_test=2;
                }
                preguntas[i]= new Pregunta(texto,numero,id_test);


            }
            parsed=true;

        } catch (ParserConfigurationException pce) {
            Log.e(TAG, "ParserConfigurationException: " + pce.getLocalizedMessage());
        } catch (Exception e) {
            Log.e(TAG, "Unknown Exception: "+e.getLocalizedMessage());
        }
        return parsed;
    }
    public Pregunta[] getPreguntas(){
        return this.preguntas;
    }
    public ArrayList<Opcion> getOpciones(){
        return this.opciones;
    }
}
