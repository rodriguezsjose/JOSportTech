package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Centro;
import com.grupouno.josporttech.util.BD;

import java.util.ArrayList;
import java.util.List;

public class DAOCentro {
    BD base;
    SQLiteDatabase db;
    Context context;

    public DAOCentro(Context context){
        base = new BD(context);
        this.context = context;
    }

    public void abridBD(){
        db = base.getWritableDatabase();
    }

    public String registrarCentro(Centro centro){
        String mensaje ="";
        try{
            ContentValues valores = new ContentValues();
            valores.put("id",centro.getId());
            valores.put("nombre",centro.getNombre());
            valores.put("descripcion",centro.getDescripcion());
            valores.put("idProveedor", centro.getIdProveedor());
            valores.put("horaIni", centro.getHoraIni());
            valores.put("horaFin", centro.getHoraFin());
            long result = db.insert("centro", null, valores);
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
    public List<Centro> obtenerCentros(){
        List<Centro> listaCentros = new ArrayList<>();
        try{
            Cursor c = db.rawQuery("SELECT * FROM centro",null);
            while(c.moveToNext()){
                listaCentros.add(new Centro(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getString(4),c.getString(5)));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaCentros;
    }
    public void borrarDatos(){
        try{
            db.delete("centro",null, null);
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
    }

}
