package com.grupouno.josporttech;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.grupouno.josporttech.entidad.Centro;
import com.grupouno.josporttech.entidad.Deporte;
import com.grupouno.josporttech.entidad.Horario;
import com.grupouno.josporttech.entidad.MotivoAnulacion;
import com.grupouno.josporttech.entidad.Reserva;
import com.grupouno.josporttech.entidad.Sede;
import com.grupouno.josporttech.modelo.DAOCentro;
import com.grupouno.josporttech.modelo.DAODeporte;
import com.grupouno.josporttech.modelo.DAOHorario;
import com.grupouno.josporttech.modelo.DAOMotivoAnulacion;
import com.grupouno.josporttech.modelo.DAOReserva;
import com.grupouno.josporttech.modelo.DAOSede;

import java.util.ArrayList;
import java.util.List;

public class ReservaActivity extends AppCompatActivity {

    Button btnAccion;
    TextView txtEtiquetaMotAnul;

    // CENTRO
    Spinner sprCentro, sprSede, sprDeporte, sprFecha, sprHora, sprMotAnul;
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
    String nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, telefonoSede;
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

    int idCentroFiltro, idSedeFiltro, idDeporteFiltro, idHorarioFiltro, idMotivoAnulacionFiltro = 0;
    String fechaFiltro, horaFiltro = "";

    //RESERVA
    DAOReserva daoReserva = new DAOReserva(this);
    Reserva reserva;

    //DETALLE
    int idReservaDetalle, idCentroDetalle, idSedeDetalle, idDeporteDetalle, idMotivoAnulacionDetalle;
    String fechaIniDetalle, horaIniDetalle, estadoDetalle;

    //ACCION
    int Accion;

    //MOTIVO ANULACION
    DAOMotivoAnulacion daoMotivoAnulacion = new DAOMotivoAnulacion(this);
    MotivoAnulacion motivoAnulacion;
    List<MotivoAnulacion> listaMotivoAnulacion;

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
    private BaseAdapter deporteHorarioAdapter = new BaseAdapter() {
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
    private BaseAdapter fechaHorarioAdapter = new BaseAdapter() {
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
    private BaseAdapter horaHorarioAdapter = new BaseAdapter() {
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

            Horario horario = listaHoraHorario.get(i);
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
    private BaseAdapter motivoAnulacionAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listaMotivoAnulacion.size();
        }

        @Override
        public Object getItem(int i) {
            return listaMotivoAnulacion.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MotivoAnulacionHolder holder;
            View motivoAnulacionView = view;

            if (motivoAnulacionView == null){
                motivoAnulacionView = getLayoutInflater().inflate(R.layout.fila_motivo_anulacion_spinner, viewGroup,false);

                holder = new MotivoAnulacionHolder();
                holder.txtMotAnulId = motivoAnulacionView.findViewById(R.id.txtMotAnulId);
                holder.txtMotAnulNombre = motivoAnulacionView.findViewById(R.id.txtMotAnulNombre);
                motivoAnulacionView.setTag(holder);
            }else{
                holder = (MotivoAnulacionHolder) motivoAnulacionView.getTag();
            }

            MotivoAnulacion motivoAnulacion = listaMotivoAnulacion.get(i);
            holder.txtMotAnulId.setText(String.valueOf(motivoAnulacion.getId()));
            holder.txtMotAnulId.setVisibility(View.INVISIBLE);
            holder.txtMotAnulNombre.setText(motivoAnulacion.getNombre());

            return motivoAnulacionView;
        }
        class MotivoAnulacionHolder{
            private TextView txtMotAnulId;
            private TextView txtMotAnulNombre;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        /****Ini: Cargar Data de Prueba****/
        cargarCentros();
        cargarSedes();
        cargarDeportes();
        cargarHorarios();
        CargarMotivoAnulacion();
        /****Fin: Cargar Data de Prueba****/
        asignarReferencias();
        CargaDetalleReserva();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(getIntent().hasExtra("p_id")){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.reserva_menu,menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mreserva_reprogramar:
                //Toast.makeText(this, "Reprogramar", Toast.LENGTH_SHORT).show();
                sprFecha.setEnabled(true);
                sprHora.setEnabled(true);
                btnAccion.setText("Reprogramar");
                btnAccion.setVisibility(View.VISIBLE);
                Accion = 3;
                return true;
            case R.id.mreserva_pagar:
                //Toast.makeText(this,"Pagar", Toast.LENGTH_SHORT).show();
                btnAccion.setText("Pagar");
                btnAccion.setVisibility(View.VISIBLE);
                Accion = 4;
                return true;
            case R.id.mreserva_anular:
                //Toast.makeText(this, "Anular",Toast.LENGTH_SHORT).show();
                btnAccion.setText("Anular");
                btnAccion.setVisibility(View.VISIBLE);
                txtEtiquetaMotAnul.setVisibility(View.VISIBLE);
                sprMotAnul.setVisibility(View.VISIBLE);
                sprMotAnul.setEnabled(true);
                Accion = 5;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void CargaDetalleReserva(){
        setTitle("Crear Reserva");
        Accion = 1; // REGISTRO
        if(getIntent().hasExtra("p_id")) {

            setTitle("Visualizar Reserva");
            idReservaDetalle = Integer.parseInt(getIntent().getStringExtra("p_id"));
            idCentroDetalle = Integer.parseInt(getIntent().getStringExtra("p_idCentro"));
            idSedeDetalle = Integer.parseInt(getIntent().getStringExtra("p_idSede"));
            idDeporteDetalle = Integer.parseInt(getIntent().getStringExtra("p_idDeporte"));
            fechaIniDetalle =  getIntent().getStringExtra("p_fecha");
            horaIniDetalle =  getIntent().getStringExtra("p_hora");
            estadoDetalle = getIntent().getStringExtra("p_estado");
            idMotivoAnulacionDetalle = Integer.parseInt(getIntent().getStringExtra("p_idMotivoAnulacion"));
            Accion = 2; // DETALLE
            btnAccion.setVisibility(View.INVISIBLE);
            if (estadoDetalle.equals("ANULADA")){
                txtEtiquetaMotAnul.setVisibility(View.VISIBLE);
                sprMotAnul.setVisibility(View.VISIBLE);
            }

//            /* Setear Centro */
//            int cont = 0;
//            int tam = listaCentros.size();
//            while (cont < tam){
//                if (((Centro)listaCentros.get(cont)).getId() == idCentroDetalle){
//                    sprCentro.setSelection(cont);
//                    cont = tam;
//                }
//                cont = cont + 1;
//            }
//            /* Setear Sede */
//            cont = 0;
//            tam = listaSedes.size();
//            while (cont < tam){
//                if (((Sede)listaSedes.get(cont)).getId() == idSedeDetalle){
//                    sprSede.setSelection(cont);
//                    cont = tam;
//                }
//                cont = cont + 1;
//            }
//            /* Setear Deporte */
//            cont = 0;
//            tam = listaDeportesHorario.size();
//            while (cont < tam){
//                if (((Horario)listaDeportesHorario.get(cont)).getIdDeporte() == idDeporteDetalle){
//                    sprDeporte.setSelection(cont);
//                    cont = tam;
//                }
//                cont = cont + 1;
//            }
        }
    }

    private void asignarReferencias(){
        sprCentro = findViewById(R.id.sprCentro);
        sprSede = findViewById(R.id.sprSede);
        sprDeporte = findViewById(R.id.sprDeporte);
        sprFecha = findViewById(R.id.sprFecha);
        sprHora = findViewById(R.id.sprHora);
        btnAccion = findViewById(R.id.btnAccion);
        txtEtiquetaMotAnul = findViewById(R.id.txtEtiquetaMotAnul);
        sprMotAnul = findViewById(R.id.sprMotAnul);

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
                if (Accion == 2){
                    /* Setear Centro */
                    int cont = 0;
                    int tam = listaCentros.size();
                    while (cont < tam){
                        if (((Centro)listaCentros.get(cont)).getId() == idCentroDetalle){
                            sprCentro.setSelection(cont);
                            cont = tam;
                        }
                        cont = cont + 1;
                    }
                    sprCentro.setEnabled(false);
                }
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
                if (Accion == 2){
                    /* Setear Sede */
                    int cont = 0;
                    int tam = listaSedes.size();
                    while (cont < tam){
                        if (((Sede)listaSedes.get(cont)).getId() == idSedeDetalle){
                            sprSede.setSelection(cont);
                            cont = tam;
                        }
                        cont = cont + 1;
                    }
                    sprSede.setEnabled(false);
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
                }
                if (Accion == 2){
                    /* Setear Deporte */
                    int cont = 0;
                    int tam = listaDeportesHorario.size();
                    while (cont < tam){
                        if (((Horario)listaDeportesHorario.get(cont)).getIdDeporte() == idDeporteDetalle){
                            sprDeporte.setSelection(cont);
                            cont = tam;
                        }
                        cont = cont + 1;
                    }
                    sprDeporte.setEnabled(false);
                }
                CargaSpinnerFecha(idSedeFiltro, idDeporteFiltro);
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
                if (Accion == 2){
                    /* Setear Deporte */
                    int cont = 0;
                    int tam = listaFechaHorario.size();
                    while (cont < tam){
                        String fechaIni_1 = ((Horario)listaFechaHorario.get(cont)).getFechaIni();
                        if ( fechaIni_1.equals(fechaIniDetalle)){
                            sprFecha.setSelection(cont);
                            cont = tam;
                        }
                        cont = cont + 1;
                    }
                    sprFecha.setEnabled(false);
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
                if (Accion == 2){
                    /* Setear Deporte */
                    int cont = 0;
                    int tam = listaHoraHorario.size();
                    while (cont < tam){
                        String horaIni_1 = ((Horario)listaHoraHorario.get(cont)).getHoraIni();
                        if ( horaIni_1.equals(horaIniDetalle)){
                            sprHora.setSelection(cont);
                            cont = tam;
                        }
                        cont = cont + 1;
                    }
                    sprHora.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        CargaSpinnerMotivoAnulacion();
        sprMotAnul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int idMotivoAnulacion = ((MotivoAnulacion) adapterView.getSelectedItem()).getId();
                idMotivoAnulacionFiltro = 0;
                if (idMotivoAnulacion > 0 ){
                    idMotivoAnulacionFiltro = idMotivoAnulacion;
                }
                if (Accion == 2){
                    /* Setear Centro */
                    int cont = 0;
                    int tam = listaMotivoAnulacion.size();
                    while (cont < tam){
                        if (((MotivoAnulacion)listaMotivoAnulacion.get(cont)).getId() == idMotivoAnulacionDetalle){
                            sprMotAnul.setSelection(cont);
                            cont = tam;
                        }
                        cont = cont + 1;
                    }
                    sprMotAnul.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAccion.setOnClickListener(view -> {
            String mensajeBoton;
            switch (Accion)
            {
                case 1: //REGISTRO
                    if (capturarDatos() == true){
                        daoReserva.abrirBD();
                        mensajeBoton = daoReserva.registrarReserva(reserva);
                        mostrarMensaje(mensajeBoton);
                        break;
                    }
                case 3: //REPROGRAMAR
                    daoReserva.abrirBD();
                    mensajeBoton = daoReserva.reprogramarReserva(idReservaDetalle,fechaFiltro, horaFiltro);
                    mostrarMensaje(mensajeBoton);
                    break;
                case 4: //PAGAR
                    daoReserva.abrirBD();
                    mensajeBoton = daoReserva.pagarReserva(idReservaDetalle);
                    mostrarMensaje(mensajeBoton);
                    break;
                case 5: //ANULAR
                    daoReserva.abrirBD();
                    mensajeBoton = daoReserva.AnularReserva(idReservaDetalle, idMotivoAnulacionFiltro);
                    mostrarMensaje(mensajeBoton);
                    break;
                default:
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
    private void CargaSpinnerMotivoAnulacion(){
        daoMotivoAnulacion.abridBD();
        listaMotivoAnulacion = new ArrayList<>();
        listaMotivoAnulacion = daoMotivoAnulacion.obtenerMotivoAnulacion();
        sprMotAnul.setAdapter(motivoAnulacionAdapter);
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

        nombreCentro = "Polideportivo Dansey";
        descripcionCentro = "Polideportivo Dansey";
        idProveedorCentro = 1;
        horaIniCentro = "08:00:00";
        horaFinCentro = "22:00:00";
        centro = new Centro(1,nombreCentro, descripcionCentro,idProveedorCentro,horaIniCentro,horaFinCentro);
        daoCentro.registrarCentro(centro);

        nombreCentro = "Videna";
        descripcionCentro = "Videna";
        idProveedorCentro = 1;
        horaIniCentro = "08:00:00";
        horaFinCentro = "22:00:00";
        centro = new Centro(2, nombreCentro, descripcionCentro,idProveedorCentro,horaIniCentro,horaFinCentro);
        daoCentro.registrarCentro(centro);

        nombreCentro = "Centro Recreativo";
        descripcionCentro = "Centro Recreativo";
        idProveedorCentro = 1;
        horaIniCentro = "08:00:00";
        horaFinCentro = "22:00:00";
        centro = new Centro(3, nombreCentro, descripcionCentro,idProveedorCentro,horaIniCentro,horaFinCentro);
        daoCentro.registrarCentro(centro);

    }
    public void cargarSedes(){
        daoSede.abrirBD();
        daoSede.borrarDatos();

        nombreSede = "Sede Lince";
        descripcionSede = "Sede Lince";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 1;
        telefonoSede = "01 606 0606";
        sede = new Sede(1,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede,telefonoSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede Miraflores";
        descripcionSede = "Sede Miraflores";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 1;
        telefonoSede = "01 606 0607";
        sede = new Sede(2, nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede,telefonoSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede Los Olivos";
        descripcionSede = "Sede Los Olivos";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 1;
        telefonoSede = "01 606 0608";
        sede = new Sede(3,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede,telefonoSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede Rimac";
        descripcionSede = "Sede Rimac";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 2;
        telefonoSede = "01 606 0609";
        sede = new Sede(4,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede,telefonoSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede Chorillos";
        descripcionSede = "Sede Chorillos";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 2;
        telefonoSede = "01 606 0610";
        sede = new Sede(5,nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede,telefonoSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede San Borja";
        descripcionSede = "Sede San Borja";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 3;
        telefonoSede = "01 606 0611";
        sede = new Sede(6, nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede,telefonoSede);
        daoSede.registrarSede(sede);

        nombreSede = "Sede La Victoria";
        descripcionSede = "Sede La Victoria";
        ubigeoSede = "000000";
        latitudSede = "000000";
        longitudSede = "000000";
        idCentroSede = 3;
        telefonoSede = "01 606 0612";
        sede = new Sede(7, nombreSede, descripcionSede, ubigeoSede, latitudSede, longitudSede, idCentroSede,telefonoSede);
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

        nombreDeporte = "Ping Pong";
        descripcionDeporte = "Ping Pong";
        deporte = new Deporte(7, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

        nombreDeporte = "Karate";
        descripcionDeporte = "Karate";
        deporte = new Deporte(8, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

        nombreDeporte = "Bowling";
        descripcionDeporte = "Bowling";
        deporte = new Deporte(9, nombreDeporte, descripcionDeporte);
        daoDeporte.registrarDeporte(deporte);

    }
    private void cargarHorarios(){
        daoHorario.abrirBD();
        daoHorario.borrarDatos();

//      SEDE 1
//      DEPORTE 1
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
        idDeporteHorario = 1;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(2, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 1;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "21:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "22:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(3, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 1;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "10:00:00";
        fechaFinHorario = "15/02/2023";
        horaFinHorario = "11:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(4, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 1
//      DEPORTE 2
        idSedeHorario = 1;
        idDeporteHorario = 2;
        fechaIniHorario = "19/02/2023";
        horaIniHorario = "13:00:00";
        fechaFinHorario = "19/02/2023";
        horaFinHorario = "19:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(5,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 2;
        fechaIniHorario = "19/02/2023";
        horaIniHorario = "14:00:00";
        fechaFinHorario = "19/02/2023";
        horaFinHorario = "15:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(6,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 2;
        fechaIniHorario = "20/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "20/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(7,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 1
//      DEPORTE 3
        idSedeHorario = 1;
        idDeporteHorario = 3;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "16:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "17:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(8,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 3;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "17:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "18:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(9,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 1;
        idDeporteHorario = 3;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "18:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "19:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(10,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 2
//      DEPORTE 4
        idSedeHorario = 2;
        idDeporteHorario = 4;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "19:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "20:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(11, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 2;
        idDeporteHorario = 4;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(12, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 2;
        idDeporteHorario = 4;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "21:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "22:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(13, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 2;
        idDeporteHorario = 4;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "10:00:00";
        fechaFinHorario = "15/02/2023";
        horaFinHorario = "11:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(14, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 2
//      DEPORTE 5
        idSedeHorario = 2;
        idDeporteHorario = 5;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "13:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "14:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(15,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 2;
        idDeporteHorario = 5;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "14:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "15:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(16,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 2;
        idDeporteHorario = 5;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "15/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(17,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 2
//      DEPORTE 6
        idSedeHorario = 2;
        idDeporteHorario = 6;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "16:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "17:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(18,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 2;
        idDeporteHorario = 6;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "17:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "18:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(19,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 2;
        idDeporteHorario = 6;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "18:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "19:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(20,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 3
//      DEPORTE 7
        idSedeHorario = 3;
        idDeporteHorario = 7;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "19:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "20:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(21, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 3;
        idDeporteHorario = 7;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(22, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 3;
        idDeporteHorario = 7;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "21:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "22:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(23, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 3;
        idDeporteHorario = 7;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "10:00:00";
        fechaFinHorario = "15/02/2023";
        horaFinHorario = "11:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(24, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 3
//      DEPORTE 8
        idSedeHorario = 3;
        idDeporteHorario = 8;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "13:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "14:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(25,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 3;
        idDeporteHorario = 8;
        fechaIniHorario = "14/02/2023";
        horaIniHorario = "14:00:00";
        fechaFinHorario = "14/02/2023";
        horaFinHorario = "15:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(26,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 3;
        idDeporteHorario = 8;
        fechaIniHorario = "15/02/2023";
        horaIniHorario = "20:00:00";
        fechaFinHorario = "15/02/2023";
        horaFinHorario = "21:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(27,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

//      SEDE 3
//      DEPORTE 9
        idSedeHorario = 3;
        idDeporteHorario = 9;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "16:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "17:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(28,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 3;
        idDeporteHorario = 9;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "17:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "18:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(29,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

        idSedeHorario = 3;
        idDeporteHorario = 9;
        fechaIniHorario = "17/02/2023";
        horaIniHorario = "18:00:00";
        fechaFinHorario = "17/02/2023";
        horaFinHorario = "19:00:00";
        cantidadHorario = 10;
        aforoHorario = 20;
        disponibilidadHorario = 10;
        horario = new Horario(30,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
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
        horario = new Horario(31,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
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
        horario = new Horario(32, idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
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
        horario = new Horario(33,idSedeHorario, idDeporteHorario, fechaIniHorario, horaIniHorario, fechaFinHorario, horaFinHorario, cantidadHorario, aforoHorario, disponibilidadHorario);
        daoHorario.RegistrarHorario(horario);

    }
    private void CargarMotivoAnulacion(){
       daoMotivoAnulacion.abridBD();
       daoMotivoAnulacion.borrarDatos();
       daoMotivoAnulacion.registrarMotivoAnulacion(new MotivoAnulacion(1,"Salud","Salud"));
       daoMotivoAnulacion.registrarMotivoAnulacion(new MotivoAnulacion(2,"Viaje","Viaje"));
       daoMotivoAnulacion.registrarMotivoAnulacion(new MotivoAnulacion(3,"Trabajo","Trabajo"));
       daoMotivoAnulacion.registrarMotivoAnulacion(new MotivoAnulacion(4,"Otros","Otros"));
    }
}