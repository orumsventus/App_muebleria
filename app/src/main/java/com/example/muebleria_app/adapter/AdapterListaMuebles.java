package com.example.muebleria_app.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muebleria_app.Entidades.Mueble;
import com.example.muebleria_app.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import android.content.res.Resources;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.util.List;

public class AdapterListaMuebles extends FirestoreRecyclerAdapter<Mueble, AdapterListaMuebles.MyViewHolder>{

    public interface OnMuebleSelectedListener{
        void onMuebleSlected(DocumentSnapshot mueble);
    }

    private OnMuebleSelectedListener listener;

//    public AdapterListaMuebles(Query query, OnMuebleSelectedListener listener) {
//        super(query);
//        this.listener = listener;
//    }


    public AdapterListaMuebles(@NonNull FirestoreRecyclerOptions<Mueble> options){//, OnMuebleSelectedListener listener) {
        super(options);
        //this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.item_muebles,parent,false));
    }

//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.bind(getSnapshot(position), listener);
//    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Mueble model) {
        holder.nombre.setText(model.getNombre());
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre;
        TextView descripcion;
        TextView precio;

        public MyViewHolder(View view){
            super(view);
            nombre = view.findViewById(R.id.item_mueble_nombre);
            descripcion = view.findViewById(R.id.item_mueble_precio);
            precio = view.findViewById(R.id.item_mueble_precio);
        }

        public void bind(Mueble mueble){
            nombre.setText(mueble.getNombre());
            descripcion.setText(mueble.getDescripcion());
            precio.setText(mueble.getPrecio()+"");
        }

        public void bind(final DocumentSnapshot snapshot, final OnMuebleSelectedListener listener){
            Mueble mueble = snapshot.toObject(Mueble.class);
            Resources resources = itemView.getResources();

            nombre.setText(mueble.getNombre());
            descripcion.setText(mueble.getDescripcion());
            precio.setText(mueble.getPrecio()+"");

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"nada de nada", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
