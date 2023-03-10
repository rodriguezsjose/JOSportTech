package com.grupouno.josporttech;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.grupouno.josporttech.entidad.Reclamo;
import com.grupouno.josporttech.modelo.DAOReclamo;

public class ActivityDetalleReclamo extends AppCompatActivity {
    EditText txtPeticion, txtcodReserva, txtfechaReserva, txtCD, txtMedio, txtContacto, txtCorreo, txtMotivo, txtDescripcion, txtArchivoSustento;
    Boolean registro = true;
    Reclamo reclamo;
    DAOReclamo daoReclamo = new DAOReclamo(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo);
        asignarReferencias();
        agregarDatos();

    }

    private void agregarDatos() {

        txtPeticion.setText(getIntent().getStringExtra("p_pet"));
        txtcodReserva.setText(getIntent().getStringExtra("p_res"));
        txtfechaReserva.setText(getIntent().getStringExtra("p_fec"));
        txtCD.setText(getIntent().getStringExtra("p_ced"));
        //txtMedio.setText(getIntent().getStringExtra("p_med"));
        //txtContacto.setText(getIntent().getStringExtra("p_con"));
        txtCorreo.setText(getIntent().getStringExtra("p_cor"));
        txtMotivo.setText(getIntent().getStringExtra("p_mot"));
        txtDescripcion.setText(getIntent().getStringExtra("p_des"));
        //txtArchivoSustento.setText(getIntent().getStringExtra("p_sus"));

        }


    private void asignarReferencias() {
            txtPeticion = findViewById(R.id.txtNombreCD);
            txtcodReserva = findViewById(R.id.txtDirCD);
            txtfechaReserva = findViewById(R.id.txtHorarioCD);
            txtCD = findViewById(R.id.txtProvCD);
            //txtMedio = findViewById(R.id.txtTel);
            //txtContacto = findViewById(R.id.txtCont);
            txtCorreo = findViewById(R.id.txtDisCD);
            txtMotivo = findViewById(R.id.txtDeportesCD);
            txtDescripcion = findViewById(R.id.txtServiciosCD);
         //   txtArchivoSustento = findViewById(R.id.txtAdj);




        }



    }
