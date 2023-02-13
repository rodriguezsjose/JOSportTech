package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Reclamo;
import com.grupouno.josporttech.util.BD;

import java.util.ArrayList;
import java.util.List;

public class DAOReclamo {

    BD b;
    SQLiteDatabase db;
    Context c;

    public DAOReclamo(Context c) {
        b = new BD(c);
        this.c = c;

    }
    public void AbrirBD(){
        db = b.getWritableDatabase();
    }


    public String registrarReclamo(Reclamo reclamo){
        String mensaje = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("peticion", reclamo.getPeticion());
            valores.put("codReserva", reclamo.getCodReserva());
            valores.put("fecha", reclamo.getFecha());
            valores.put("centroD", reclamo.getCentroD());
            valores.put("medioContacto", reclamo.getMedioContacto());
            valores.put("contacto", reclamo.getContacto());
            valores.put("correo", reclamo.getCorreo());
            valores.put("motivo", reclamo.getDescMotivo());
            valores.put("descMotivo", reclamo.getDescMotivo());
            valores.put("sustento", reclamo.getSustento());

            long result = db.insert("rec", null, valores);

            if (result == -1){
                mensaje = "No se logró registrar su reclamo";
            }
            else {
                mensaje = "Se registró reclamo exitosamente";
            }


        }catch (Exception e){
            Log.d("=>", e.getMessage());

        }

        return mensaje;
    }

    public List<Reclamo> obtenerReclamo(){
        List<Reclamo> listarec = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM rec", null);
            while (c.moveToNext()){
                listarec.add(new Reclamo(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10)));
            }

        }catch (Exception e){
         Log.d("=>", e.getMessage());
        }
        return listarec;
    }

}
