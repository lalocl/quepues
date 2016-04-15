package com.wordpress.appsandroidsite.quepues.soap;

import android.content.Context;
import android.util.Log;

import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Categoria;
import com.wordpress.appsandroidsite.quepues.modelo.Test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by laura on 07/04/2016.
 */
public class CategoriasParser {
    private static final String TAG = "CategoriasParser";

    private Categoria[] categorias;
    private InputStream categoriasFile;


    public CategoriasParser(Context c){
        this.categoriasFile= c.getResources().openRawResource(R.raw.categorias);
    }

    //Hacer transacción, o se insertan todos o que no se inserte ninguno.
    public boolean parse(){
        boolean parsed=false;
        categorias=null;




        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(categoriasFile);
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("categoria");

            categorias = new Categoria[items.getLength()];

            for (int i = 0; i < items.getLength(); i++) {

                Node item = items.item(i);


                String nombre = item.getAttributes().getNamedItem("name").getNodeValue();
                String codigo = item.getAttributes().getNamedItem("categoryCode").getNodeValue();


                categorias[i] = new Categoria(nombre,codigo);
            }
            parsed=true;


        } catch (ParserConfigurationException pce) {
            Log.e(TAG, "ParserConfigurationException: " + pce.getLocalizedMessage());
        } catch (Exception e) {
            Log.e(TAG, "Unknown Exception: "+e.getLocalizedMessage());
        }
        return parsed;
    }

    public Categoria[]getCategorias(){
        return this.categorias;
    }
}