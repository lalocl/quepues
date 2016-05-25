package com.wordpress.appsandroidsite.quepues.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Aula10 on 23/05/2016.
 */
public class OpcionesAdapter extends RecyclerView.Adapter<OpcionesAdapter.OpcionViewHolder> {
    private static final String TAG = "OpcionesAdapter";

    private List<Opcion> opciones;
    private Context context;
    private int resource;
    private boolean modoSeleccion;
    private SparseBooleanArray seleccionados;




    public  class OpcionViewHolder extends RecyclerView.ViewHolder{


        public TextView texto;
        private View vista;


        public OpcionViewHolder(View vista){
            super(vista);
            this.vista=vista;

            Log.i(TAG,"opcionViewHolder...");
        }

        public void bindView(Opcion opcion){
            Log.i(TAG,"bindView...");
            texto=(TextView)vista.findViewById(R.id.textViewOpcion);
            texto.setText(opcion.texto);
            if(seleccionados.get(getAdapterPosition())){
                vista.setSelected(true);
            }else{
                vista.setSelected(false);
            }

            vista.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    if(!modoSeleccion){
                        modoSeleccion=true;
                        v.setSelected(true);
                        seleccionados.put(getAdapterPosition(),true);
                    }
                    return true;
                }
            });

            vista.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(modoSeleccion){
                        if(!v.isSelected()){
                            v.setSelected(true);
                            seleccionados.put(getAdapterPosition(),true);
                        }else{
                            v.setSelected(false);
                            seleccionados.put(getAdapterPosition(),false);
                            if(!haySeleccionados()){
                                modoSeleccion=false;
                            }
                        }
                    }
                }
            });


        }
    }

    public boolean haySeleccionados(){
        Log.i(TAG,"haySeleccionados...");
        for(int i=0;i<=opciones.size();i++){
            if(seleccionados.get(i)){
                return true;
            }

        }
        return false;
    }

    public LinkedList<Opcion>obtenerSeleccionados(){
        Log.i(TAG,"obtenerSeleccionados...");
        LinkedList<Opcion> marcados = new LinkedList<>();
        for(int i =0; i<opciones.size();i++){
            if(seleccionados. get(i)){
                marcados.add(opciones.get(i));
            }
        }
        return marcados;
    }

    public OpcionesAdapter(Context context,LinkedList <Opcion>opciones ){
        Log.i(TAG,"Creando el adaptador ...");
        this.opciones=opciones;
        this.context=context;
        seleccionados= new SparseBooleanArray();
    }


    @Override
    public OpcionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.i(TAG,"OnCreateViewHolder...");

      //  Context context=viewGroup.getConte();
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detalle_listview,viewGroup,false);

        OpcionViewHolder viewHolder=new OpcionViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OpcionViewHolder holder, int position) {

        Log.i(TAG,"onBindViewHolder...");
        Opcion opcion=opciones.get(position);
      //  String texto=opciones.get(position).texto;
        holder.bindView(opcion);
      //  Log.i(TAG,"Opcion " + position + " :" + texto);



    }

    @Override
    public int getItemCount() {
        return opciones.size();
    }


}
