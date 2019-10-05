package com.example.muebleria_app.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muebleria_app.Entidades.Mueble;
import com.example.muebleria_app.R;
import com.example.muebleria_app.adapter.AdapterListaMuebles;
import com.example.muebleria_app.adapter.MuebleAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements AdapterListaMuebles.OnMuebleSelectedListener{
    // VARIABLES PARA EL LOGUEO
    private FirebaseAuth auth;
    private static final int RC_SING_IN = 123;
    private FirebaseFirestore firestore;
    private Query myQuery;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView texto;
    MuebleAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initFirestore();
        initRecyclerView(view);
        super.onViewCreated(view, savedInstanceState);
    }

    public void initFirestore(){
        firestore = FirebaseFirestore.getInstance();
        myQuery = firestore.collection("muebles")
                .orderBy("nombre", Query.Direction.DESCENDING)
                .limit(50);
    }

    /////// INICIALIZAR EL RECYCLERVIEW ////////////////////////////////////////////////////////////
    public void initRecyclerView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        FirestoreRecyclerOptions<Mueble> options = new FirestoreRecyclerOptions.Builder<Mueble>()
                .setQuery(myQuery, Mueble.class)
                .build();

//        AdapterListaMuebles adapterListaMuebles = new AdapterListaMuebles(options);
        adapter = new MuebleAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onMuebleSlected(DocumentSnapshot mueble) {
        Toast.makeText(getContext(),"clicik", Toast.LENGTH_SHORT).show();
    }
}
