package com.grupouno.josporttech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Expo1Activity extends AppCompatActivity {

    Button _btnPriscilla, _btnJose, _btnCSC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo1);
        asignarReferencias();
    }

    private void asignarReferencias(){
        _btnPriscilla = findViewById(R.id.btnPriscilla);
        _btnJose = findViewById(R.id.btnJose);
        _btnCSC = findViewById(R.id.btnCSC);
        ///
        _btnPriscilla.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityListarReclamo.class);
            startActivity(intent);
        });
        _btnJose.setOnClickListener(view -> {
            Intent intent = new Intent(this, TabReservaActivity.class);
            startActivity(intent);
        });
        _btnCSC.setOnClickListener(view -> {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        });

    }


}