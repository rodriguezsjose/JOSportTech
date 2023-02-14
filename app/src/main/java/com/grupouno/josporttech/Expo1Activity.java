package com.grupouno.josporttech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Expo1Activity extends AppCompatActivity {

    Button _btnReclamo, _btnReserva, _btnDeportes, _btnProv;
    ImageButton _btnWsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo1);
        asignarReferencias();
    }

   // @SuppressLint("WrongViewCast")
    private void asignarReferencias(){
        _btnReclamo = findViewById(R.id.btnReclamo);
        _btnReserva = findViewById(R.id.btnReserva);
        _btnDeportes = findViewById(R.id.btnDeporte);
        _btnProv =  findViewById(R.id.btnProv);
        _btnWsp = findViewById(R.id.btnWsp);
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

        _btnProv.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityListarProv.class);
            startActivity(intent);
        });

        _btnWsp.setOnClickListener(v -> {
            Intent intent = new Intent(this, ContactanosActivity.class);
            startActivity(intent);
        });



    }


}