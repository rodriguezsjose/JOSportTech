package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.MotivoAnulacion;
import com.grupouno.josporttech.util.BD;

import java.util.ArrayList;
import java.util.List;

public class DAOMotivoAnulacion {

    BD base;
    SQLiteDatabase db;
    Context context;

    public DAOMotivoAnulacion(Context context){
        base = new BD(context);
        this.context = context;
    }


    public void abridBD(){
        db = base.getWritableDatabase();
    }

    public String registrarMotivoAnulacion(MotivoAnulacion motivoAnulacion){
        String mensaje ="";
        try{
            ContentValues valores = new ContentValues();
            valores.put("id",motivoAnulacion.getId());
            valores.put("nombre",motivoAnulacion.getNombre());
            valores.put("descripcion",motivoAnulacion.getDescripcion());
            long result = db.insert("motivoAnulacion", null, valores);
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
    public List<MotivoAnulacion> obtenerMotivoAnulacion(){
        List<MotivoAnulacion> listaMotivoAnulacion = new ArrayList<>();
        try{
            Cursor c = db.rawQuery("SELECT * FROM motivoAnulacion",null);
            while(c.moveToNext()){
                listaMotivoAnulacion.add(new MotivoAnulacion(c.getInt(0),c.getString(1),c.getString(2)));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaMotivoAnulacion;
    }
    public void borrarDatos(){
        try{
            db.delete("motivoAnulacion",null, null);
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
    }

}
