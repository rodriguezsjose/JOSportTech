package com.grupouno.josporttech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.grupouno.josporttech.entidad.Deporte;
import com.grupouno.josporttech.entidad.Sede;
import com.grupouno.josporttech.modelo.DAODeporte;
import com.grupouno.josporttech.modelo.DAOSede;

import java.util.ArrayList;
import java.util.List;


public class SedeListarActivity extends AppCompatActivity {

    RecyclerView _rvSedes;

    DAOSede _daoSede = new DAOSede(this);
    List<Sede> _listaSedes = new ArrayList<>();
    SedeListaAdaptador _adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sede_listar);
        asignarReferencias();
        _daoSede.abrirBD();
        mostrarSedes();
    }

    private void mostrarSedes(){
        _listaSedes = _daoSede.listarSedes();
        _adaptador = new SedeListaAdaptador(this, _listaSedes);
        _rvSedes.setAdapter(_adaptador);
        _rvSedes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){
        _rvSedes = findViewById(R.id.rvSedes);
    }

}