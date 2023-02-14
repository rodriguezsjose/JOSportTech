package com.grupouno.josporttech;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.grupouno.josporttech.entidad.Reclamo;
import com.grupouno.josporttech.entidad.Reserva;
import com.grupouno.josporttech.modelo.DAOReclamo;
import com.grupouno.josporttech.modelo.DAOReserva;

import java.util.ArrayList;
import java.util.List;


public class Reclamo_Activity extends AppCompatActivity {
    EditText txtPeticion, txtcodReserva, txtfechaReserva, txtCD, txtMedio, txtContacto, txtCorreo, txtMotivo, txtDescripcion, txtArchivoSustento;
    Button btnRegitrar;
    Reclamo reclamo;
    DAOReclamo daoReclamo = new DAOReclamo(this);
    Spinner spRec;
    DAOReserva dRes = new DAOReserva(this);
    List<Reserva> listaRes;
    ImageView imgV;
    ImageButton imbut;


    private BaseAdapter badap = new BaseAdapter() {
        @Override
        public int getCount() {
            return listaRes.size();
        }

        @Override
        public Object getItem(int position) {
            return listaRes.get(position);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ReservaHolder holder;
            View reservaView=convertView;
            if (reservaView==null){
                reservaView=getLayoutInflater().inflate(R.layout.fila_spinner_reserva, parent, false);
                holder=new ReservaHolder();
                holder.txtCod = reservaView.findViewById(R.id.txtCod);
                holder.txtDescCombo = reservaView.findViewById(R.id.txtDescCombo);
                reservaView.setTag(holder);
            }
            else {
                holder = (ReservaHolder) reservaView.getTag();
            }


            Reserva reserva = listaRes.get(position);

            holder.txtCod.setText(String.valueOf(reserva.getId()));
            holder.txtDescCombo.setText(String.valueOf(reserva.getId()));
            holder.txtCod.setVisibility(View.INVISIBLE);

            return reservaView;

        }
        class ReservaHolder {
            private TextView txtCod, txtDescCombo;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo);

        asignarReferencias();
        imbut = findViewById(R.id.btnimg);
        imgV = findViewById(R.id.fotoImg);




        imbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCam();
            }
        });


    }

    private  void abrirCam(){
        Intent inte = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // if (inte.resolveActivity(getPackageManager())!=null){

        startActivityForResult(inte, 1);
        //}

    }
    protected void onActivityResult(int    reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgbmap = (Bitmap) extras.get("data");
            imgV.setImageBitmap(imgbmap);
        }

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
        spRec = findViewById(R.id.idSpinnerReserva);

        cargarSpinnerReserva();

        spRec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idRes= ((Reserva) parent.getSelectedItem()).getId();
                Toast.makeText(Reclamo_Activity.this, "Seleccionado "+idRes, Toast.LENGTH_SHORT).show();

                String centro = ((Reserva) parent.getSelectedItem()).getDescCentro();
                txtCD.setText(centro);

                String codRese = ((Reserva) parent.getSelectedItem()).getDescDeporte();
                txtcodReserva.setText(codRese);

                String fecha = ((Reserva) parent.getSelectedItem()).getFecha();
                txtfechaReserva.setText(fecha);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
    public void cargarSpinnerReserva() {
        dRes.abrirBD();
        listaRes= new ArrayList<>();
        listaRes = dRes.obtenerListaReserva("ACTIVO");
        spRec.setAdapter(badap);
    }
    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("MENSAJE INF");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", (dialogInterface, i) -> {
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