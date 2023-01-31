package com.grupouno.josporttech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.plecca.proy6.reclamo.DAOReclamo;
import com.plecca.proy6.reclamo.Reclamo;

public class Reclamo_Activity extends AppCompatActivity {
    EditText txtPeticion, txtcodReserva, txtfechaReserva, txtCD, txtMedio, txtContacto, txtCorreo, txtMotivo, txtDescripcion, txtArchivoSustento;
    Button btnRegitrar;
    Reclamo reclamo;
    DAOReclamo daoReclamo = new DAOReclamo(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo);
        asignarReferencias();


    }


    private void asignarReferencias() {
        txtPeticion = findViewById(R.id.txtPet);
        txtcodReserva = findViewById(R.id.txtCodRes);
        txtfechaReserva = findViewById(R.id.txtFec);
        txtCD = findViewById(R.id.txtCDe);
        txtMedio = findViewById(R.id.txtTel);
        txtContacto = findViewById(R.id.txtCont);
        txtCorreo = findViewById(R.id.txtCor);
        txtMotivo = findViewById(R.id.txtMot);
        txtDescripcion = findViewById(R.id.txtDesc);
        txtArchivoSustento = findViewById(R.id.txtAdj);

        btnRegitrar = findViewById(R.id.btnRgst);
        btnRegitrar.setOnClickListener(v ->{
            if (capturaDatos()== true){
                // Toast.makeText(this, "odaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                daoReclamo.AbrirBD();
                String mensaje = daoReclamo.registrarReclamo(reclamo);
                mostrarMensaje(mensaje);
            }

        } );


    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("MENSAJE INF");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", (dialog, which) -> {
            Intent in = new Intent(this, ActivityListarReclamo.class);
            startActivity(in);
                }
        );
        ventana.create().show();
    }

    private boolean capturaDatos() {
        boolean valida = true;
        String peticion = txtPeticion.getText().toString();
        String codReserva = txtcodReserva.getText().toString();
        String fecha = txtfechaReserva.getText().toString();
        String centroD = txtCD.getText().toString();
        String medioContacto = txtMedio.getText().toString();
        String contacto = txtContacto.getText().toString();
        String correo = txtCorreo.getText().toString();
        String motivo = txtMotivo.getText().toString();
        String descMotivo = txtDescripcion.getText().toString();
        String sustento = txtArchivoSustento.getText().toString();


        if (peticion.equals("")){
            txtPeticion.setError("Es un dato obligatorio");
            valida = false;
        }
        if (codReserva.equals("")){
            txtcodReserva.setError("Es un dato obligatorio");
            valida = false;
        }
        if (fecha.equals("")){
            txtfechaReserva.setError("Es un dato obligatorio");
            valida = false;
        }
        if (centroD.equals("")){
            txtCD.setError("Es un dato obligatorio");
            valida = false;
        }
        if (medioContacto.equals("")){
            txtMedio.setError("Es un dato obligatorio");
            valida = false;
        }
        if (contacto.equals("")){
            txtContacto.setError("Es un dato obligatorio");
            valida = false;
        }
        if (correo.equals("")){
            txtCorreo.setError("Es un dato obligatorio");
            valida = false;
        }
        if (motivo.equals("")){
            txtMotivo.setError("Es un dato obligatorio");
            valida = false;
        }
        if (descMotivo.equals("")){
            txtDescripcion.setError("Es un dato obligatorio");
            valida = false;
        }
        if (sustento.equals("")){
            txtArchivoSustento.setError("Es un dato obligatorio");
            valida = false;
        }



        if (valida==true){
            reclamo = new Reclamo(peticion,codReserva,fecha, centroD,   medioContacto,   contacto,  correo,  motivo,  descMotivo, sustento);
        }

        return  valida;


    }


}