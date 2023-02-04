package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Deporte;
import com.grupouno.josporttech.util.BD;

public class DAODeporte {
    BD base;
    SQLiteDatabase db;
    Context context;

    public DAODeporte(Context context){
        base = new BD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = base.getWritableDatabase();
    }

    public String registrarDeporte(Deporte deporte){
        String mensaje="";
        try{
            ContentValues valores = new ContentValues();
            valores.put("id",deporte.getId());
            valores.put("nombre",deporte.getNombre());
            valores.put("descripcion",deporte.getDescripcion());
            long result = db.insert("deporte", null, valores);
            if(result == -1){
                mensaje = "Error al insertar";
            }else{
                mensaje = "Se registró correctamente";
            }
        }catch (Exception e){
            Log.d("=>", e.getMessage());
        }
        return mensaje;
    }
    public void borrarDatos(){
        try{
            db.delete("deporte",null, null);
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
    }

}
