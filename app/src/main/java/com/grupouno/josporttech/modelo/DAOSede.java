package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Sede;
import com.grupouno.josporttech.util.BD;

import java.util.ArrayList;
import java.util.List;

public class DAOSede {
    BD base;
    SQLiteDatabase db;
    Context context;

    public DAOSede(Context context){
        base = new BD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = base.getWritableDatabase();
    }

    public String registrarSede(Sede sede){
        String mensaje ="";
        try{
            ContentValues valores = new ContentValues();
            valores.put("id",sede.getId());
            valores.put("nombre",sede.getNombre());
            valores.put("descripcion",sede.getDescripcion());
            valores.put("ubigeo", sede.getUbigeo());
            valores.put("latitud", sede.getLatitud());
            valores.put("longitud", sede.getLongitud());
            valores.put("idCentro", sede.getIdCentro());
            valores.put("telefono", sede.getTelefono());
            long result = db.insert("sede", null, valores);
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
    public List<Sede> obtenerSedes(int idCentro){
        List<Sede> listaSedes = new ArrayList<>();
        try{
            String[] selectionArgs = {String.valueOf(idCentro)};
            Cursor c = db.rawQuery("SELECT * FROM sede WHERE idCentro = ?",selectionArgs);
            while(c.moveToNext()){
                listaSedes.add(new Sede(c.getInt(0), c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getString(7)));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaSedes;
    }
    public void borrarDatos(){
        try{
            db.delete("sede",null, null);
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
    }

    public List<Sede> listarSedes(){
        List<Sede> listaSedes = new ArrayList<>();
        try{
            Cursor c = db.rawQuery("SELECT * FROM sede",null);
            while(c.moveToNext()){
                listaSedes.add(new Sede(c.getInt(0), c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getString(7)));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaSedes;
    }

}
