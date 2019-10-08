package com.example.muebleria_app.fragments;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.muebleria_app.Entidades.Mueble;
import com.example.muebleria_app.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MuebleDetalleFragment extends Fragment {
    TextView nombre;
    TextView descripcion;
    TextView precio;
    Button boton_guardar;
    ImageView imagen;
    ImageButton boton_imagen;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLARY = 2;

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
        imagen = (ImageView) getView().findViewById(R.id.mueble_detalle_imagen);
        boton_imagen = (ImageButton) getView().findViewById(R.id.mueble_detalle_boton_imagen);

        boton_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"funciona",Toast.LENGTH_SHORT).show();
                Intent buscar_imagen = new Intent(Intent.ACTION_GET_CONTENT);
                buscar_imagen.setType("image/*");
                startActivityForResult(buscar_imagen,PICK_FROM_GALLARY);

            }
        });

        try {
            if ( getArguments().getString("id")!= null ){
                /////// EDITAR /////////////////////////////////////////////////////////////////////
                getActivity().setTitle("Editar información");
                final String id = getArguments().getString("id");
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference reference = db.collection("muebles").document(id);
                reference.get().addOnCompleteListener(new OnCompleteListener < DocumentSnapshot > () {
                    @Override
                    public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                        if (task.isSuccessful()) {

                            DocumentSnapshot doc = task.getResult();

                            nombre.setText(doc.get("nombre").toString());
                            descripcion.setText(doc.get("descripcion").toString());
                            precio.setText(doc.get("precio").toString());
                            String url_inner = doc.get("imagen").toString();
                            Glide.with(getView().getContext())
                                    .load(url_inner)
                                    .into(imagen);

                            boton_guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(final View v) {
                                    Map<String, Object> mueble_editado = new HashMap<>();
                                    mueble_editado.put("nombre", nombre.getText().toString());
                                    mueble_editado.put("descripcion", descripcion.getText().toString());
                                    mueble_editado.put("precio", descripcion.getText().toString());

                                    db.collection("muebles").document(id)
                                            .set(mueble_editado)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Navigation.findNavController(v).navigateUp();
                                                    Toast.makeText(getContext(),"Mueble editado exitosamente", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Navigation.findNavController(v).navigateUp();
                                                    Toast.makeText(getContext(),"No se pudo editar", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });


            }
        }catch (NullPointerException e){
            /////// AGREGAR ////////////////////////////////////////////////////////////////////////
            getActivity().setTitle("Agregar un mueble");

            boton_guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Map<String, Object> mueble_nuevo = new HashMap<>();
                    mueble_nuevo.put("nombre", nombre.getText().toString());
                    mueble_nuevo.put("descripcion", descripcion.getText().toString());
                    mueble_nuevo.put("precio", precio.getText().toString());

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("muebles")
                            .add(mueble_nuevo)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Navigation.findNavController(v).navigateUp();
                                    Toast.makeText(getContext(),"Mueble agregado exitosamente", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(),"Hubo un problema y no se pudo agregar", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(v).navigateUp();}
                            });
                }
            });


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PICK_FROM_GALLARY:
                if (requestCode == Activity.RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imagen.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
}
