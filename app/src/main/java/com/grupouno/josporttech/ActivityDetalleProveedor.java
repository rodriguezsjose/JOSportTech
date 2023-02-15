package com.grupouno.josporttech;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDetalleProveedor extends AppCompatActivity {
    EditText txtNom;
            //txtDir, txtHor, txtProv, txtDist, txtRef, txtDep, txtServ, txtGal;

    Proveedor p;
    DAOProv prov = new DAOProv(this);
    Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_proveedor);
        asignaReferencias();
        agregarDatos();
    }
    private void agregarDatos() {
    txtNom.setText(getIntent().getStringExtra("p_nom"));

    }

    private void asignaReferencias() {
       txtNom = findViewById(R.id.txtNombreCD);



    }
}