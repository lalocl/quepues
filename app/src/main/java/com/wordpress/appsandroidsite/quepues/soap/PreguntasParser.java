package com.wordpress.appsandroidsite.quepues.soap;

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
            Element raiz = dom.getDocumentElement();
            NodeList items = raiz.getElementsByTagName("pregunta");

            preguntas = new Pregunta[items.getLength()];
            for(int i=0; i<items.getLength();i++){

                Log.i(TAG, "Creando preguntas." + items.getLength() +" Pregunta: "+ (i+1));
                Node item = items.item(i);
                String texto = item.getAttributes().getNamedItem("text").getNodeValue();
                int numero= Integer.parseInt(item.getAttributes().getNamedItem("number").getNodeValue());
                String test = item.getAttributes().getNamedItem("testCode").getNodeValue();

                int id_test;
                if(test.equalsIgnoreCase("a10")){
                    id_test=1;
                }else{
                    id_test=2;
                }
                preguntas[i]= new Pregunta(texto,numero,id_test);
               NodeList itemsOpc=item.getChildNodes();

                for(int j=0;j<itemsOpc.getLength();j++){
                    Node nodo=itemsOpc.item(j);
                    if (nodo.getNodeType()==Node.ELEMENT_NODE) {

                        if (nodo.getNodeName().equalsIgnoreCase("opcion")&& nodo.hasAttributes()) {

                            String textoOp = nodo.getAttributes().getNamedItem("text").getNodeValue();
                            String codigoCategoria = nodo.getAttributes().getNamedItem("categoryCode").getNodeValue();
                            Log.i(TAG,"Opción " + j +" :"+ textoOp);

                            opciones.add(new Opcion(textoOp, codigoCategoria, (i + 1)));

                            Log.i(TAG, "Añadida opcion al array");


                        }
                    }
                }


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
