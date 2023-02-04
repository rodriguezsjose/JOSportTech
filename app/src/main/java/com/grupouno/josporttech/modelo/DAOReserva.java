package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Reserva;
import com.grupouno.josporttech.util.BD;

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
                mensaje = "Se registró correctamente";
            }

        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return mensaje;
    }

}
