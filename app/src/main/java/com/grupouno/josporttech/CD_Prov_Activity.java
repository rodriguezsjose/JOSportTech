package com.grupouno.josporttech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CD_Prov_Activity extends AppCompatActivity {
    EditText txtNom, txtDir, txtHor, txtProv, txtDist, txtRef, txtDep, txtServ, txtGal;
    Button btnRegistrar;
    Proveedor p;
    DAOProv prov = new DAOProv(this);
    Integer id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd_prov);
        asignarReferencias();
    }

    private void asignarReferencias() {
        txtNom = findViewById(R.id.txtNombre);
        txtDir = findViewById(R.id.txtDir);
        txtHor = findViewById(R.id.txtHor);
        txtProv = findViewById(R.id.txtProv);
        txtDist = findViewById(R.id.txtDis);
        txtRef = findViewById(R.id.txtRef);
        txtDep = findViewById(R.id.txtDep);
        txtServ = findViewById(R.id.txtServ);
        txtGal = findViewById(R.id.txtGal);
        btnRegistrar = findViewById(R.id.btnRgst);
        btnRegistrar.setOnClickListener(v -> {

            if (capturaDatos()== true){

                prov.abriBD();
                String mensaje = prov.registrarCD(p);
                mostrarMensaje(mensaje);
            }

        });


    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("MENSAJE INF");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", (dialogInterface, i) -> {
            Intent intent = new Intent(this, ActivityListarProv.class);
            startActivity(intent);


        });
        ventana.create().show();

    }

    private Boolean capturaDatos() {
        boolean valida = true;
        String nom = txtNom.getText().toString();
        String dir = txtDir.getText().toString();
        String hor = txtHor.getText().toString();
        String prov = txtProv.getText().toString();
        String dist = txtDist.getText().toString();
        String ref = txtRef.getText().toString();
        String dep = txtDep.getText().toString();
        String serv = txtServ.getText().toString();
        String gal = txtGal.getText().toString();

        if (nom.equals("")){
            txtNom.setError("Dato Obligatorio");
            valida=false;
        }
        if (dir.equals("")){
            txtDir.setError("Dato Obligatorio");
            valida=false;
        }
        if (hor.equals("")){
            txtHor.setError("Dato Obligatorio");
            valida=false;
        }
        if (prov.equals("")){
            txtProv.setError("Dato Obligatorio");
            valida=false;
        }
        if (dist.equals("")){
            txtDist.setError("Dato Obligatorio");
            valida=false;
        }
        if (ref.equals("")){
            txtRef.setError("Dato Obligatorio");
            valida=false;
        }
        if (dep.equals("")){
            txtDep.setError("Dato Obligatorio");
            valida=false;
        }
        if (serv.equals("")){
            txtServ.setError("Dato Obligatorio");
            valida=false;
        }
        if (gal.equals("")){
            txtGal.setError("Dato Obligatorio");
            valida=false;
        }
        if (valida==true){
            p = new Proveedor(nom,dir, hor, prov, dist, ref, dep, serv, gal);
        }


        return  valida;
    }
}