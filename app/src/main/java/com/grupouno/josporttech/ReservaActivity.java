package com.grupouno.josporttech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ReservaActivity extends AppCompatActivity {

    Spinner sprCentro;
    ArrayAdapter<CharSequence> adapterCentro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        asignarReferencias();
    }

    private void asignarReferencias(){
        sprCentro = findViewById(R.id.sprCentro);
        adapterCentro = ArrayAdapter.createFromResource(this,R.array.centros, android.R.layout.simple_spinner_item);
        adapterCentro.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sprCentro.setAdapter(adapterCentro);
    }

}