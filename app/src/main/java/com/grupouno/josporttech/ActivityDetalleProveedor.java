package com.grupouno.josporttech;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDetalleProveedor extends AppCompatActivity {
    EditText txtNom, txtDir, txtHor, txtProv, txtDist, txtRef, txtDep, txtServ, txtGal;

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
    txtDir.setText(getIntent().getStringExtra("p_dir"));
        txtDir.setText(getIntent().getStringExtra("p_hor"));
        txtDir.setText(getIntent().getStringExtra("p_prov"));
        txtDir.setText(getIntent().getStringExtra("p_dist"));
        txtDir.setText(getIntent().getStringExtra("p_ref"));
        txtDir.setText(getIntent().getStringExtra("p_dep"));
        txtDir.setText(getIntent().getStringExtra("p_serv"));

    }

    private void asignaReferencias() {
       txtNom = findViewById(R.id.txtNombreCD);
       txtDir = findViewById(R.id.txtDirCD);
       txtHor = findViewById(R.id.txtHorarioCD);
       txtProv = findViewById(R.id.txtProvCD);
       txtDist = findViewById(R.id.txtDisCD);
       txtRef = findViewById(R.id.txtRef);
       txtDep = findViewById(R.id.txtDeportesCD) ;
       txtServ = findViewById(R.id.txtServiciosCD);



    }
}