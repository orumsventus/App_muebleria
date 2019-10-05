package com.example.muebleria_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.muebleria_app.adapter.AdapterListaMuebles;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    // VARIABLES PARA EL LOGUEO
    private FirebaseAuth auth;
    private static final int RC_SING_IN = 123;
    private FirebaseFirestore firestore;
    private Query myQuery;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    /////// ONCREATE ///////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        if (isUserLogged()){
            Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show();
        }else {
            signIn();
        }
    }


    /////// INICIAR SESION /////////////////////////////////////////////////////////////////////////
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

    // PREGUNTAR SI EL USUARIO ESTA LOGUEADO
    public boolean isUserLogged(){
        if (auth.getCurrentUser() != null){
            return true;
        }else{
            return false;
        }
    }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_informacion:
                Toast.makeText(this,"Aplicación hecha para prueba de EKS Solutions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_cerrar_sesion:
                AuthUI.getInstance().signOut(this);
                signIn();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
