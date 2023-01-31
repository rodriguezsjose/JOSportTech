package com.grupouno.josporttech;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityListarReclamo extends AppCompatActivity {
    FloatingActionButton btnReg;
    RecyclerView rvRec;
    DAOReclamo daorec = new DAOReclamo(this);
    List<Reclamo> listarec = new ArrayList<>();
    AdaptadorRec adap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_reclamo);
        asignarReferencias();
        daorec.AbrirBD();
        mostrarReclamo();
    }

    private void mostrarReclamo() {
        listarec = daorec.obtenerReclamo();
        adap = new AdaptadorRec(this, listarec);
        rvRec.setAdapter(adap);
        rvRec.setLayoutManager(new LinearLayoutManager(this));

    }

    private void asignarReferencias() {
        btnReg = findViewById(R.id.btnRegRec);
        btnReg.setOnClickListener(v -> {
            Intent in = new Intent(this, Reclamo_Activity.class);
            startActivity(in);
        });

        rvRec = findViewById(R.id.nvRec);
    }
}