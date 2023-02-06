package com.grupouno.josporttech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Expo1Activity extends AppCompatActivity {

    Button _btnReclamo, _btnReserva, _btnDeportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo1);
        asignarReferencias();
    }

    private void asignarReferencias(){
        _btnReclamo = findViewById(R.id.btnReclamo);
        _btnReserva = findViewById(R.id.btnReserva);
        _btnDeportes = findViewById(R.id.btnDeporte);
        ///
        _btnReclamo.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityListarReclamo.class);
            startActivity(intent);
        });
        _btnReserva.setOnClickListener(view -> {
            Intent intent = new Intent(this, TabReservaActivity.class);
            startActivity(intent);
        });
        _btnDeportes.setOnClickListener(view -> {
            Intent intent = new Intent(this, DeporteListarActivity.class);
            startActivity(intent);
        });

    }


}