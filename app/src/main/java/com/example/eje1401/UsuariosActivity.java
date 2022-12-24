package com.example.eje1401;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
    }

    public void OtraVentana(View view){
        Intent x=new Intent(this,CrearTipoUsuarioActivity.class);
        startActivity(x);
    }
}