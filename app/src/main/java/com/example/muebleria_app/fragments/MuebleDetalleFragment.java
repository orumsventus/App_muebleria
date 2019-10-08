package com.example.muebleria_app.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muebleria_app.Entidades.Mueble;
import com.example.muebleria_app.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MuebleDetalleFragment extends Fragment {
    TextView nombre;
    TextView descripcion;
    TextView precio;
    Button boton_guardar;
    private static final String TAG = "TAG.........................................TAG...........::__";

    public MuebleDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mueble_detalle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nombre = (TextView) getView().findViewById(R.id.mueble_detalle_nombre);
        descripcion = (TextView) getView().findViewById(R.id.mueble_detalle_descripcion);
        precio = (TextView) getView().findViewById(R.id.mueble_detalle_precio);
        boton_guardar = (Button) getView().findViewById(R.id.mueble_detalle_boton_guardar);

        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"hols",Toast.LENGTH_SHORT).show();

                Map<String, Object> city = new HashMap<>();
                city.put("nombre", nombre.getText().toString());
                city.put("descripcion", descripcion.getText().toString());
                city.put("precio", precio.getText().toString());

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("muebles")
                        .add(city)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });

//        String id = getArguments().getString("id");
//        if (id != null){
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//            Query query = db.collection("muebles/"+id);
//            DocumentReference document = db.collection("muebles").document(id);
//            document.get().toString();

//        }
    }

    public void guardar(View view) {
        Toast.makeText(getContext(),"hols",Toast.LENGTH_SHORT).show();

        Map<String, Object> city = new HashMap<>();
        city.put("nombre", nombre.getText());
        city.put("descripcion", descripcion.getText());
        city.put("precio", precio.getText());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("muebles")
                .add(city)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
