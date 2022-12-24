package com.example.eje1401;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    public EditText txtUsu, txtPass;
    public Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsu=findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPassword);
    }

    public Connection conexionBD(){
        Connection cnn=null;
        try {
            StrictMode.ThreadPolicy pol=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(pol);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            cnn= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.43:1433/BDCarritoG1;"+
                    "instance=MSSQLSERVER;" +
                    "user=sa;" +
                    "password=Aa123;");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return cnn;
    }

    public void Consulta(View view){
        try {
            Statement st=conexionBD().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM usuarios WHERE " +
                    "logeo='"+txtUsu.getText().toString()+"' AND " +
                    "clave='"+ txtPass.getText().toString()+"'");
            if(rs.next()){
                System.out.println("rs ");
                String tipoU=rs.getString(2);
                System.out.println(tipoU);
                Toast.makeText(getApplicationContext(),"Conexion establecida Version 2.0 "+rs.getString(2),Toast.LENGTH_SHORT).show();
                if(tipoU.compareTo("TIU00001")==0){//cliente
                    Intent x=new Intent(this,MenuClienteActivity.class);
                    startActivity(x);
                }
                if(tipoU.compareTo("TIU00002")==0){//admin
                    Intent x=new Intent(this,MenuActivity.class);
                    startActivity(x);
                }
                if(tipoU.compareTo("TIU00003")==0){//invitado
                    Intent x=new Intent(this,MenuActivity.class);
                    startActivity(x);
                }
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


}