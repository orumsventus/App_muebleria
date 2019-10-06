package com.example.muebleria_app.adapter;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muebleria_app.Entidades.Mueble;
import com.example.muebleria_app.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class MuebleAdapter extends FirestoreRecyclerAdapter<Mueble, MuebleAdapter.MuebleHolder>
        implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

//    public interface OnMuebleSelectedListener{
//        void onMuebleSlected(DocumentSnapshot mueble);
//    }
//    private OnMuebleSelectedListener listener;


    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),"1", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
//        Toast.makeText(,"2", Toast.LENGTH_SHORT).show();

        return false;
    }

    public MuebleAdapter(@NonNull FirestoreRecyclerOptions<Mueble> options){
        super(options);
    }

    @NonNull
    @Override
    public MuebleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MuebleHolder(inflater.inflate(R.layout.item_mueble,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull MuebleHolder holder, int position, @NonNull Mueble model) {
        holder.bind(model);
    }


    class MuebleHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView descipcion;
        TextView precio;
        View mas_menu;

        public MuebleHolder(View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.item_mueble_nombre);
            descipcion = itemView.findViewById(R.id.item_mueble_descripcion);
            precio = itemView.findViewById(R.id.item_mueble_precio);
            mas_menu = itemView.findViewById(R.id.item_mueble_boton_mas);
        }

        public void bind(final Mueble mueble){
            nombre.setText("Nombre: " + mueble.getNombre());
            descipcion.setText("Descripción: " + mueble.getDescripcion());
            precio.setText("Precio: $" + mueble.getPrecio());

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),mueble.getNombre(), Toast.LENGTH_SHORT).show();
                }
            });
            mas_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrar_menu_pop(itemView);
                }
            });
        }

        public void mostrar_menu_pop(final View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.pop_menu_mueble, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(view.getContext(),"borrando", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            popupMenu.show();
        }
    }
}
