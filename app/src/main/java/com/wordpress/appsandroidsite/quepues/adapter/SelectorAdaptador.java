package com.wordpress.appsandroidsite.quepues.adapter;

import android.content.Context;
import android.graphics.Color;
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
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Aula10 on 26/05/2016.
 */
public class SelectorAdaptador extends ArrayAdapter<Opcion>{
    private static final String TAG = "SelectorAdaptador";

    private List<Opcion> lista;
    private Context context;
    private TextView textViewOpcion;

   private HashMap<Integer, Boolean> mSeleccion= new HashMap<Integer, Boolean>();


    View item;



    public SelectorAdaptador(Context context, List <Opcion> lista){
        super(context,R.layout.listview,lista);

        Log.i(TAG,"selectorAdaptador...(lista:"+lista.size()+")");

        this.lista= lista;


    }

    public void setNuevaSeleccion(int position, boolean value){
        mSeleccion.put(position,value);
        notifyDataSetChanged();
    }
    public boolean isPositionChecked(int position){
        Boolean result= mSeleccion.get(position);
        return result==null?false:result;
    }
    public Set<Integer>getCurrentCheckedPosition(){
        return mSeleccion.keySet();
    }
    public void removeSelection(int position) {
        mSeleccion.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        mSeleccion = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }

    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        item=inflater.inflate(R.layout.detalle_listview,null);

        item.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));

        if(mSeleccion.get(position)!=null){

            item.setBackgroundResource(R.drawable.list_item_bg_normal);
        }

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
