package com.grupouno.josporttech;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityListarProv extends AppCompatActivity {
    FloatingActionButton btnReg;
    RecyclerView rvProve;
    DAOProv daoProv = new DAOProv(this);
    List<Proveedor> listaprov = new ArrayList<>();
    AdaptadorProv adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_prov);
        asignarReferencias();
        daoProv.abriBD();
       //mostrarProv();

    }

    private void mostrarProv() {
        listaprov = daoProv.obtenerProv();
        adap = new AdaptadorProv(this, listaprov);
     rvProve.setAdapter(adap);
       rvProve.setLayoutManager(new LinearLayoutManager(this));

    }

    private void asignarReferencias() {
        btnReg = findViewById(R.id.btnRegCD);
        btnReg.setOnClickListener(v -> {
            Intent in = new Intent(this, CD_Prov_Activity.class);
            startActivity(in);


        });
        rvProve = findViewById(R.id.nvProv);
    }
}