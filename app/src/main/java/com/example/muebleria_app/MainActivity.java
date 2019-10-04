package com.example.muebleria_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // VARIABLES PARA EL LOGUEO
    private FirebaseAuth auth;
    private static final int RC_SING_IN = 123;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();


        if (isUserLogged()){
            Toast.makeText(this,"esta logueado",Toast.LENGTH_SHORT).show();
        }else {
            signIn();
        }


    }

    // INICIAR SESION
    public void signIn(){
        // PROBEDORES DE AUTENTICACION
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        // COMENZAMOS LA ACTIVIDAD PARA LOGUEAR
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SING_IN
        );
    }

    public boolean isUserLogged(){
        if (auth.getCurrentUser() != null){
            return true;
        }else{
            return false;
        }
    }


//        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

/*
    }


    */

    @Override
    protected void onStart() {
        super.onStart();
        if (!isUserLogged()){
            signIn();
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SING_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if(resultCode == RESULT_OK){
                // SE LOGUEO EXITOSAMENTE
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            }else {

            }
        }
    }

    public void accion_1(View view) {
        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_muebleDetalleFragment);
    }
}
