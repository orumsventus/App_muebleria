package com.example.muebleria_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muebleria_app.Entidades.Mueble;
import com.example.muebleria_app.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MuebleAdapter extends FirestoreRecyclerAdapter<Mueble, MuebleAdapter.MuebleHolder> {

    public MuebleAdapter(@NonNull FirestoreRecyclerOptions<Mueble> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MuebleHolder holder, int position, @NonNull Mueble model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public MuebleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MuebleHolder(inflater.inflate(R.layout.item_muebles,parent,false));
    }


    class MuebleHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView descipcion;
        TextView precio;

        public MuebleHolder(View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.item_mueble_nombre);
            descipcion = itemView.findViewById(R.id.item_mueble_precio);
            precio = itemView.findViewById(R.id.item_mueble_precio);
        }

        public void bind(Mueble mueble){
            nombre.setText(mueble.getNombre());
            descipcion.setText(mueble.getDescripcion());
            precio.setText(mueble.getPrecio());
        }
    }
}
