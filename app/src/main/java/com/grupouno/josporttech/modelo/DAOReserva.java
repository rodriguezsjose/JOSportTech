package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Reserva;
import com.grupouno.josporttech.util.BD;

import java.util.ArrayList;
import java.util.List;

public class DAOReserva {
    BD base;
    SQLiteDatabase db;
    Context context;

    public DAOReserva(Context context){
        base = new BD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = base.getWritableDatabase();
    }

    public String registrarReserva(Reserva reserva){
        String mensaje ="";
        try{
            ContentValues valores = new ContentValues();
            //valores.put("id",reserva.getId());
            valores.put("idSede",reserva.getIdSede());
            valores.put("idDeporte",reserva.getIdDeporte());
            valores.put("idHorario", reserva.getIdHorario());
            valores.put("fecha", reserva.getFecha());
            valores.put("hora", reserva.getHora());
            valores.put("estado", reserva.getEstado());
            valores.put("flagPagado", reserva.getFlagPagado());
            valores.put("cantReprogramaciones", reserva.getCantReprogramaciones());
            valores.put("idMotivoAnulacion", reserva.getIdMotivoAnulacion());
            long result = db.insert("reserva", null, valores);
            if(result == -1){
                mensaje = "Error al insertar";
            }else{
                mensaje = "Reserva registrada correctamente";
            }

        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return mensaje;
    }
    public List<Reserva> obtenerListaReserva(String estado){
        List<Reserva> listaReservas = new ArrayList<>();
        try{
            String[] selectionArgs = {estado};
            Cursor c = db.rawQuery("SELECT a.id, b.idCentro, c.nombre as nombreCentro, a.idSede, " +
                    " b.nombre as nombreSede, a.idDeporte, d.nombre as nombreDeporte, a.fecha, " +
                    " a.hora, a.estado, a.flagPagado, a.cantReprogramaciones, a.idMotivoAnulacion " +
                    " FROM reserva a " +
                    " INNER JOIN sede b ON b.id = a.idSede " +
                    " INNER JOIN centro c ON c.id = b.idCentro " +
                    " INNER JOIN deporte d ON d.id = a.idDeporte " +
                    " WHERE a.estado = ?",selectionArgs);
            while(c.moveToNext()){
                Reserva reserva;
                reserva = new Reserva(c.getInt(0), c.getInt(1), c.getString(2), c.getInt(3), c.getString(4), c.getInt(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9),c.getInt(10),c.getInt(11),c.getInt(12));
                listaReservas.add(reserva);
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaReservas;
    }
    public Reserva obtenerReservaporId(int id){
        Reserva reserva = null;
        try{
            String[] selectionArgs = {String.valueOf(id)};
            Cursor c = db.rawQuery("SELECT a.id, b.idCentro, c.nombre as nombreCentro, a.idSede, " +
                    " b.nombre as nombreSede, a.idDeporte, d.nombre as nombreDeporte, a.fecha, " +
                    " a.hora, a.estado, a.flagPagado, a.cantReprogramaciones, a.idMotivoAnulacion " +
                    " FROM reserva a " +
                    " INNER JOIN sede b ON b.id = a.idSede " +
                    " INNER JOIN centro c ON c.id = b.idCentro " +
                    " INNER JOIN deporte d ON d.id = a.idDeporte " +
                    " WHERE a.id = ?",selectionArgs);
            while(c.moveToNext()){
                reserva = new Reserva(c.getInt(0), c.getInt(1), c.getString(2), c.getInt(3), c.getString(4), c.getInt(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9),c.getInt(10),c.getInt(11),c.getInt(12));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return reserva;
    }
    public String pagarReserva(int idReserva){
        String mensaje = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("flagPagado","1");
            long result = db.update("reserva",valores, "id="+idReserva, null);
            if(result == -1){
                mensaje = "Error al pagar";
            }else{
                mensaje = "Reserva pagada correctamente";
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return mensaje;
    }
    public String reprogramarReserva(int idReserva, String fecha, String hora){
        String mensaje = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("fecha",fecha);
            valores.put("hora",hora);
            long result = db.update("reserva",valores, "id="+idReserva, null);
            if(result == -1){
                mensaje = "Error al reprogramar";
            }else{
                mensaje = "Reserva reprograma correctamente";
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return mensaje;
    }
    public String AnularReserva(int idReserva, int idMotivoAnulacion){
        String mensaje = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("estado","ANULADA");
            valores.put("idMotivoAnulacion", idMotivoAnulacion);
            long result = db.update("reserva",valores, "id="+idReserva, null);
            if(result == -1){
                mensaje = "Error al anular";
            }else{
                mensaje = "Reserva anulada correctamente";
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return mensaje;
    }

}
