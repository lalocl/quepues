package com.wordpress.appsandroidsite.quepues.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Url;

import java.util.ArrayList;

/**
 * Created by laura on 04/04/2016.
 */
public class UrlAdapter extends ArrayAdapter<Url> {

    private ArrayList<Url> listaUrls;
    TextView nombreSubcategoria;
    ImageView imageView;
    View item;
    public UrlAdapter(Context context, ArrayList<Url> listaUrls) {
        super(context, R.layout.enlace_url,listaUrls);
        this.listaUrls=listaUrls;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        item=inflater.inflate(R.layout.enlace_url,null);
        imageView=(ImageView)item.findViewById(R.id.imageView);

        //falta establecer logo dependiendo del test

       nombreSubcategoria=(TextView)item.findViewById(R.id.nombre_subcategoria);


        nombreSubcategoria.setText(listaUrls.get(position).subCategoria);


        return item;
    }
}
