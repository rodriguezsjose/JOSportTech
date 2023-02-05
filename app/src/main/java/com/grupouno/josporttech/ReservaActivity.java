package com.grupouno.josporttech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.grupouno.josporttech.entidad.Centro;
import com.grupouno.josporttech.entidad.Deporte;
import com.grupouno.josporttech.entidad.Horario;
import com.grupouno.josporttech.entidad.Reserva;
import com.grupouno.josporttech.entidad.Sede;
import com.grupouno.josporttech.modelo.DAOCentro;
import com.grupouno.josporttech.modelo.DAODeporte;
import com.grupouno.josporttech.modelo.DAOHorario;
import com.grupouno.josporttech.modelo.DAOReserva;
import com.grupouno.josporttech.modelo.DAOSede;

import java.util.ArrayList;
import java.util.List;


public class ReservaActivity extends AppCompatActivity {

    Button btnAccion;

    // CENTRO
    Spinner sprCentro, sprSede, sprDeporte, sprFecha, sprHora;
    //ArrayAdapter<CharSequence> adapter;

    Centro centro;
    DAOCentro daoCentro = new DAOCentro(this);

    String nombreCentro, descripcionCentro, horaIniCentro, horaFinCentro;
    int idProveedorCentro;

    List<Centro> listaCentros;
    ArrayAdapter<Centro> arrayAdapterCentro;

    //SEDES
    Sede sede;
    DAOSede daoSede = new DAOSede(this);
    String nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede;
    int idCentroSede;

    List<Sede> listaSedes;
    ArrayAdapter<Sede> arrayAdapterSede;

    //DEPORTE
    Deporte deporte;
    DAODeporte daoDeporte = new DAODeporte(this);
    String nombreDeporte, descripcionDeporte;
    int idDeporte;

    //HORARIO
    Horario deporteHorario, horario, fechaHorario;
    DAOHorario daoHorario = new DAOHorario(this);
    List<Horario> listaDeportesHorario, listaFechaHorario, listaHoraHorario;
    int idSedeHorario, idDeporteHorario, cantidadHorario, aforoHorario, disponibilidadHorario;
    String fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario;

    int idCentroFiltro, idSedeFiltro, idDeporteFiltro, idHorarioFiltro = 0;
    String fechaFiltro, horaFiltro = "";

    //RESERVA
    DAOReserva daoReserva = new DAOReserva(this);
    Reserva reserva;

    private BaseAdapter centroAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listaCentros.size();
        }

        @Override
        public Object getItem(int i) {
            return listaCentros.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            CentroHolder holder;
            View centroView = view;

            if (centroView == null){
                centroView = getLayoutInflater().inflate(R.layout.fila_centro_spinner, viewGroup,false);

                holder = new CentroHolder();
                holder.txtCentroId = centroView.findViewById(R.id.txtCentroId);
                holder.txtCentroNombre = centroView.findViewById(R.id.txtCentroNombre);
                centroView.setTag(holder);
            }else{
                holder = (CentroHolder) centroView.getTag();
            }

            Centro centro = listaCentros.get(i);
            holder.txtCentroId.setText(String.valueOf(centro.getId()));
            holder.txtCentroId.setVisibility(View.INVISIBLE);
            holder.txtCentroNombre.setText(centro.getNombre());

            return centroView;
        }
        class CentroHolder{
            private TextView txtCentroId;
            private TextView txtCentroNombre;
        }
    };

    private BaseAdapter sedeAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listaSedes.size();
        }

        @Override
        public Object getItem(int i) {
            return listaSedes.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            SedeHolder holder;
            View sedeView = view;

            if (sedeView == null){
                sedeView = getLayoutInflater().inflate(R.layout.fila_sede_spinner, viewGroup,false);

                holder = new SedeHolder();
                holder.txtSedeId = sedeView.findViewById(R.id.txtSedeId);
                holder.txtSedeNombre = sedeView.findViewById(R.id.txtSedeNombre);
                sedeView.setTag(holder);
            }else{
                holder = (SedeHolder) sedeView.getTag();
            }

            Sede sede = listaSedes.get(i);
            holder.txtSedeId.setText(String.valueOf(sede.getId()));
            holder.txtSedeId.setVisibility(View.INVISIBLE);
            holder.txtSedeNombre.setText(sede.getNombre());

            return sedeView;
        }
        class SedeHolder{
            private TextView txtSedeId;
            private TextView txtSedeNombre;
        }


    };

    public BaseAdapter deporteHorarioAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listaDeportesHorario.size();
        }

        @Override
        public Object getItem(int i) {
            return listaDeportesHorario.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            DeporteHorarioHolder holder;
            View deporteHorarioView = view;

            if (deporteHorarioView == null){
                deporteHorarioView = getLayoutInflater().inflate(R.layout.fila_deporte_spinner, viewGroup,false);

                holder = new DeporteHorarioHolder();
                holder.txtDeporteId = deporteHorarioView.findViewById(R.id.txtDeporteId);
                holder.txtDeporteNombre = deporteHorarioView.findViewById(R.id.txtDeporteNombre);
                deporteHorarioView.setTag(holder);
            }else{
                holder = (DeporteHorarioHolder) deporteHorarioView.getTag();
            }

            Horario horario = listaDeportesHorario.get(i);
            if (horario != null){
                holder.txtDeporteId.setText(String.valueOf(horario.getIdDeporte()));
                holder.txtDeporteId.setVisibility(View.INVISIBLE);
                holder.txtDeporteNombre.setText(horario.getNombreDeporte());
            }
            return deporteHorarioView;
        }
        public void setError(String mensaje){

        }
        class DeporteHorarioHolder{
            private TextView txtDeporteId;
            private TextView txtDeporteNombre;
        }
    };

    public BaseAdapter fechaHorarioAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listaFechaHorario.size();
        }

        @Override
        public Object getItem(int i) {
            return listaFechaHorario.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            FechaHorarioHolder holder;
            View fechaHorarioView = view;

            if (fechaHorarioView == null){
                fechaHorarioView = getLayoutInflater().inflate(R.layout.fila_fecha_spinner, viewGroup,false);

                holder = new FechaHorarioHolder();
                holder.txtFechaReservaID = fechaHorarioView.findViewById(R.id.txtFechaReservaID);
                holder.txtFechaReservaNombre = fechaHorarioView.findViewById(R.id.txtFechaReservaNombre);
                fechaHorarioView.setTag(holder);
            }else{
                holder = (FechaHorarioHolder) fechaHorarioView.getTag();
            }

            Horario horario = listaFechaHorario.get(i);
            if (horario != null){
                holder.txtFechaReservaID.setText(String.valueOf(horario.getId()));
                holder.txtFechaReservaID.setVisibility(View.INVISIBLE);
                holder.txtFechaReservaNombre.setText(horario.getFechaIni());
            }
            return fechaHorarioView;
        }
        class FechaHorarioHolder{
            private TextView txtFechaReservaID;
            private TextView txtFechaReservaNombre;
        }
    };

    public BaseAdapter horaHorarioAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listaHoraHorario.size();
        }

        @Override
        public Object getItem(int i) {
            return listaHoraHorario.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            HoraHorarioHolder holder;
            View horaHorarioView = view;

            if (horaHorarioView == null){
                horaHorarioView = getLayoutInflater().inflate(R.layout.fila_hora_spinner, viewGroup,false);

                holder = new HoraHorarioHolder();
                holder.txtHoraReservaID = horaHorarioView.findViewById(R.id.txtHoraReservaId);
                holder.txtHoraReservaNombre = horaHorarioView.findViewById(R.id.txtHoraReservaNombre);
                horaHorarioView.setTag(holder);
            }else{
                holder = (HoraHorarioHolder) horaHorarioView.getTag();
            }

            Horario horario = listaFechaHorario.get(i);
            if (horario != null){
                holder.txtHoraReservaID.setText(String.valueOf(horario.getId()));
                holder.txtHoraReservaID.setVisibility(View.INVISIBLE);
                holder.txtHoraReservaNombre.setText(horario.getHoraIni());
            }
            return horaHorarioView;
        }
        class HoraHorarioHolder{
            private TextView txtHoraReservaID;
            private TextView txtHoraReservaNombre;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        cargarCentros();
        cargarSedes();
        cargarDeportes();
        cargarHorarios();
        asignarReferencias();
    }
    private void asignarReferencias(){
        sprCentro = findViewById(R.id.sprCentro);
        sprSede = findViewById(R.id.sprSede);
        sprDeporte = findViewById(R.id.sprDeporte);
        sprFecha = findViewById(R.id.sprFecha);
        sprHora = findViewById(R.id.sprHora);
        btnAccion = findViewById(R.id.btnAccion);

        CargaSpinnerCentro();
        sprCentro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ReservaActivity.this, listaCentros.get(i).getNombre(), Toast.LENGTH_SHORT).show();
                //int idCentro = ((Centro) adapterView.getSelectedItem()).getId();
                int idCentro = ((Centro) adapterView.getSelectedItem()).getId();
                idCentroFiltro = 0;
                if (idCentro > 0 ){
                    idCentroFiltro = idCentro;
                }
                //Toast.makeText(ReservaActivity.this,String.valueOf(idCentro),Toast.LENGTH_SHORT).show();
                CargaSpinnerSede(idCentroFiltro);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sprSede.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idSede = ((Sede) adapterView.getSelectedItem()).getId();
                idSedeFiltro = 0;
                if(idSede > 0){
                    idSedeFiltro = idSede;
                }
                CargaSpinnerDeporte(idSede);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sprDeporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idDeporte = ((Horario) adapterView.getSelectedItem()).getIdDeporte();
                idDeporteFiltro =0;
                if(idDeporte > 0){
                    idDeporteFiltro = idDeporte;
                    CargaSpinnerFecha(idSedeFiltro, idDeporteFiltro);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sprFecha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String fecha = ((Horario) adapterView.getSelectedItem()).getFechaIni();
                fechaFiltro = "";
                if(!fecha.equals("")){
                    fechaFiltro = fecha;
                }
                CargaSpinnerHora(idSedeFiltro, idDeporteFiltro, fecha);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sprHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idHorario = ((Horario) adapterView.getSelectedItem()).getId();
                idHorarioFiltro = 0;
                if (idHorario > 0){
                    idHorarioFiltro = idHorario;
                }
                String hora = ((Horario) adapterView.getSelectedItem()).getHoraIni();
                horaFiltro = "";
                if (!hora.equals("")){
                    horaFiltro = hora;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAccion.setOnClickListener(view -> {
            if (capturarDatos() == true){
                daoReserva.abrirBD();
                String mensaje;
                mensaje = daoReserva.registrarReserva(reserva);
                mostrarMensaje(mensaje);
            }
        });

    }
    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar",(dialogInterface,i) -> {
            Intent intent = new Intent(this, TabReservaActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

    private void CargaSpinnerCentro(){
        //adapter = ArrayAdapter.createFromResource(this,R.array.centros, android.R.layout.simple_spinner_item);
        //adapter = ArrayAdapter.createFromResource(this,R.array.centros, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //spr.setAdapter(adapter);

        //daoCentro.abridBD();
        //listaCentros = new ArrayList<>();
        //listaCentros = daoCentro.obtenerCentros();
        //arrayAdapterCentro = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCentros);
        //spr.setAdapter(arrayAdapterCentro);

        daoCentro.abridBD();
        listaCentros = new ArrayList<>();
        listaCentros = daoCentro.obtenerCentros();
        if (listaCentros.size() < 1){
            sprSede.setAdapter(null);
            sprDeporte.setAdapter(null);
            sprFecha.setAdapter(null);
            sprHora.setAdapter(null);
            idCentroFiltro = 0;
            idSedeFiltro = 0;
            idDeporteFiltro = 0;
            fechaFiltro = "";
            horaFiltro = "";
        }
        sprCentro.setAdapter(centroAdapter);

    }
    private void CargaSpinnerSede(int i){
        daoSede.abrirBD();
        listaSedes = new ArrayList<>();
        listaSedes = daoSede.obtenerSedes(i);
        if (listaSedes.size() < 1){
            sprDeporte.setAdapter(null);
            sprFecha.setAdapter(null);
            sprHora.setAdapter(null);
            idSedeFiltro = 0;
            idDeporteFiltro = 0;
            fechaFiltro = "";
            horaFiltro = "";
        }
        sprSede.setAdapter(sedeAdapter);

    }

    private void CargaSpinnerDeporte(int idSede){
        daoHorario.abrirBD();
        listaDeportesHorario = new ArrayList<>();
        listaDeportesHorario = daoHorario.ObtenerDeporteSede(idSede);
        if ( listaDeportesHorario.size() < 1 ){
            sprFecha.setAdapter(null);
            sprHora.setAdapter(null);
            idDeporteFiltro = 0;
            fechaFiltro = "";
            horaFiltro = "";
        }
        sprDeporte.setAdapter(deporteHorarioAdapter);
    }

    private void CargaSpinnerFecha(int idSede, int idDeporte){
        daoHorario.abrirBD();
        listaFechaHorario = new ArrayList<>();
        listaFechaHorario = daoHorario.ObtenerFechaHorario(idSede, idDeporte);
        if ( listaFechaHorario.size() < 1 ){
            sprHora.setAdapter(null);
            fechaFiltro = "";
            horaFiltro = "";
        }
        sprFecha.setAdapter(fechaHorarioAdapter);
    }

    private void CargaSpinnerHora(int idSede, int idDeporte, String fecha){
        daoHorario.abrirBD();
        listaHoraHorario = new ArrayList<>();
        listaHoraHorario = daoHorario.ObtenerHorarHorario(idSede, idDeporte, fecha);
        if (listaHoraHorario.size() < 1 ){
            horaFiltro = "";
        }
        sprHora.setAdapter(horaHorarioAdapter);
    }

    private boolean capturarDatos(){
        boolean valida = true;
        if(idCentroFiltro < 1){
            Toast.makeText(ReservaActivity.this,"Seleccione Centro",Toast.LENGTH_SHORT).show();
            valida = false;
            return valida;
        }
        if(idSedeFiltro < 1){
            Toast.makeText(ReservaActivity.this,"Seleccione Sede",Toast.LENGTH_SHORT).show();
            valida = false;
            return valida;
        }
        if(idDeporteFiltro < 1){
            Toast.makeText(ReservaActivity.this,"Seleccione Deporte",Toast.LENGTH_SHORT).show();
            valida = false;
            return valida;
            //((TextView)sprDeporte.getAdapter().get(R.id.txtHoraReservaNombre)).setError("Error");
            //((TextView)sprDeporte.getTag(R.id.txtHoraReservaNombre)).setError("dddd");
            //sprDeporte.getAdapter().setError("");
        }
        if(fechaFiltro.equals("")){
            Toast.makeText(ReservaActivity.this,"Seleccione Fecha",Toast.LENGTH_SHORT).show();
            valida = false;
            return valida;
        }
        if(horaFiltro.equals("")){
            Toast.makeText(ReservaActivity.this,"Seleccione Hora",Toast.LENGTH_SHORT).show();
            valida = false;
            return valida;
        }

        if(valida == true){
            reserva = new Reserva(idSedeFiltro, idDeporteFiltro, idHorarioFiltro, fechaFiltro, horaFiltro, "ACTIVO", 0,0);
        }
        return valida;
    }

    ///////////////////////////////////////////////////////////////////////////////////

    private void cargarCentros(){
        daoCentro.abridBD();
        daoCentro.borrarDatos();

        nombreCentro = "Centro1";
        descripcionCentro = "Centro1";
        idProveedorCentro = 1;
        horaIniCentro = "08:00:00";
        horaFinCentro = "22:00:00";
        centro = new Centro(1,nombreCentro, descripcionCentro,idProveedorCentro,horaIniCentro,horaFinCentro);
        daoCentro.registrarCentro(centro);

        nombreCentro = "Centro2";
        descripcionCentro = "Centro2";
        idProveedorCentro = 1;
        horaIniCentro = "08:00:00";
        horaFinCentro = "22:00:00";
        centro = new Centro(2, nombreCentro, descripcionCentro,idProveedorCentro,horaIniCentro,horaFinCentro);
        daoCentro.registrarCentro(centro);

        nombreCentro = "Centro3";
        descripcionCentro = "Centro3";
        idProveedorCentro = 1;
        horaIniCentro = "08:00:00";
        horaFinCentro = "22:00:00";
        centro = new Centro(3, nombreCentro, descripcionCentro,idProveedorCentro,horaIniCentro,horaFinCentro);
        daoCentro.registrarCentro(centro);

    }
    public void cargarSedes(){
        daoSede.abrirBD();
        daoSede.borrarDatos();

        nombreSede = "Sede 1_1";
        descripcionSede = "Sede 1_1";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 1;
        sede = new Sede(1,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede 1_2";
        descripcionSede = "Sede 1_2";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 1;
        sede = new Sede(2, nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede 1_3";
        descripcionSede = "Sede 1_3";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 1;
        sede = new Sede(3,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede 2_1";
        descripcionSede = "Sede 2_1";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 2;
        sede = new Sede(4,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede 2_2";
        descripcionSede = "Sede 2_2";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 2;
        sede = new Sede(5,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede 3_1";
        descripcionSede = "Sede 3_1";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 3;
        sede = new Sede(6, nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede 3_2";
        descripcionSede = "Sede 3_2";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 3;
        sede = new Sede(7, nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede);
        daoSede.registrarSede(sede);

    }
    public void cargarDeportes(){
        daoDeporte.abrirBD();
        daoDeporte.borrarDatos();

        nombreDeporte = "Futbol";
        descripcionDeporte = "Futbol";
        deporte = new Deporte(1, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

        nombreDeporte = "Basket";
        descripcionDeporte = "Basket";
        deporte = new Deporte(2, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

        nombreDeporte = "Voley";
        descripcionDeporte = "Voley";
        deporte = new Deporte(3, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

        nombreDeporte = "Natacion";
        descripcionDeporte = "Natacion";
        deporte = new Deporte(4, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

        nombreDeporte = "Boxeo";
        descripcionDeporte = "Boxeo";
        deporte = new Deporte(5, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

        nombreDeporte = "Esgrima";
        descripcionDeporte = "Esgrima";
        deporte = new Deporte(6, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

    }
    private void cargarHorarios(){
        daoHorario.abrirBD();
        daoHorario.borrarDatos();

        idSedeHorario = 1;
        idDeporteHorario = 1;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "19:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "20:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(1, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 2;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(2,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 3;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "21:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "22:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(3,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 4;
        idDeporteHorario = 1;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "19:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "20:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(4,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 4;
        idDeporteHorario = 2;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(5, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 4;
        idDeporteHorario = 3;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "21:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "22:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(6,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

    }
}