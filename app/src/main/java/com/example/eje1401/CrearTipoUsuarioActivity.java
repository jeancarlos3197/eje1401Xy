package com.example.eje1401;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrearTipoUsuarioActivity extends AppCompatActivity {
    public EditText txtCod, txtNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tipo_usuario);
        txtCod = findViewById(R.id.txtCodigo);
        txtNom = findViewById(R.id.txtNombre);
    }

    public Connection conexionBD() {
        Connection cnn = null;
        try {
            StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(pol);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.43:1433/BDCarritoG1;" +
                    "instance=MSSQLSERVER;" +
                    "user=sa;" +
                    "password=Aa123;");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cnn;
    }

    public void Insertar(View view) {
        try {
            Statement st = conexionBD().createStatement();
            st.executeUpdate("INSERT INTO tipo_usuarios VALUES ('" + txtCod.getText().toString() + "','" + txtNom.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Ingresado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}