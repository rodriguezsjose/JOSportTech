package com.grupouno.josporttech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.grupouno.josporttech.entidad.Deporte;
import com.grupouno.josporttech.modelo.DAODeporte;

import java.util.ArrayList;
import java.util.List;

public class DeporteListarActivity extends AppCompatActivity {

    RecyclerView _rvDeportes;

    DAODeporte _daoDeporte = new DAODeporte(this);
    List<Deporte> _listaDeportes = new ArrayList<>();
    DeporteListaAdaptador _adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deporte_listar);
        asignarReferencias();
        _daoDeporte.abrirBD();
        mostrarDeportes();
    }

    private void mostrarDeportes(){
        _listaDeportes = _daoDeporte.listarDeportes();
        _adaptador = new DeporteListaAdaptador(this, _listaDeportes);
        _rvDeportes.setAdapter(_adaptador);
        _rvDeportes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){
        _rvDeportes = findViewById(R.id.rvDeportes);
    }

}