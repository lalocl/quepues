package com.wordpress.appsandroidsite.quepues.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wordpress.appsandroidsite.quepues.R;
import com.wordpress.appsandroidsite.quepues.modelo.Opcion;

import java.nio.channels.Selector;
import java.util.List;

/**
 * Created by Aula10 on 26/05/2016.
 */
public class SelectorAdaptador extends ArrayAdapter<Opcion>{
    private static final String TAG = "SelectorAdaptador";

    private List<Opcion> lista;
    private Context context;
    private TextView textViewOpcion;
    View item;



    public SelectorAdaptador(Context context, List <Opcion> lista){
        super(context,R.layout.listview,lista);

        Log.i(TAG,"selectorAdaptador...(lista:"+lista.size()+")");

        this.lista= lista;


    }

    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        item=inflater.inflate(R.layout.detalle_listview,null);

        textViewOpcion=(TextView)item.findViewById(R.id.textViewOpcion);


        textViewOpcion.setText(lista.get(position).texto);


        return item;
    }






/*

    public static class MyViewHolder  {


        private TextView textViewOpcion;
     //   View itemView;


/*
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG,"MyViewHolder contructor...");

            this.itemView=itemView;

        }
*/

 //   }
/*
    public View getView(int position, View view, ViewGroup parent){

        Log.i(TAG,"getView...");

        MyViewHolder holder;
        View item= view;

        if(item==null){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalle_listview,parent,false);
            holder = new MyViewHolder();
          holder.textViewOpcion=(TextView)item.findViewById(R.id.textViewOpcion);
            item.setTag(holder);

            Log.i(TAG,"Creamos vista");


        }else{
           holder=(MyViewHolder)item.getTag();
            Log.i(TAG,"Reutilizamos vista");
        }

        holder.textViewOpcion.setText(lista.get(position).texto);

        return view;
    }

*/
}
