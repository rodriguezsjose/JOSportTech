package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Horario;
import com.grupouno.josporttech.util.BD;

import java.util.ArrayList;
import java.util.List;

public class DAOHorario {
    BD base;
    SQLiteDatabase db;
    Context context;

    public DAOHorario(Context context){
        base = new BD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = base.getWritableDatabase();
    }

    public String RegistrarHorario(Horario horario){
        String mensaje="";
        try{
            ContentValues valores = new ContentValues();
            valores.put("id",horario.getId());
            valores.put("idSede",horario.getIdSede());
            valores.put("idDeporte",horario.getIdDeporte());
            valores.put("fechaIni",horario.getFechaIni());
            valores.put("horaIni",horario.getHoraIni());
            valores.put("fechaFin",horario.getFechaFin());
            valores.put("horaFin",horario.getHoraFin());
            valores.put("cantidad",horario.getCantidad());
            valores.put("aforo", horario.getAforo());
            valores.put("disponibilidad", horario.getDisponibilidad());
            long result = db.insert("horario", null, valores);
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

    public List<Horario> ObtenerDeporteSede(int idSede){
        List<Horario> listaHorario = new ArrayList<>();
        try{
            String[] selectionArgs = {String.valueOf(idSede)};
            Cursor c = db.rawQuery("SELECT DISTINCT h.idDeporte, d.nombre  FROM horario h INNER JOIN deporte d ON d.id = h.idDeporte WHERE h.idSede = ?",selectionArgs);
            while(c.moveToNext()){
                listaHorario.add(new Horario(c.getInt(0),c.getString(1)));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaHorario;
    }

    public List<Horario> ObtenerFechaHorario(int idSede, int idDeporte){
        List<Horario> listaHorario = new ArrayList<>();
        try{
            String[] selectionArgs = {String.valueOf(idSede), String.valueOf(idDeporte)};
//            Cursor c = db.rawQuery("SELECT id, idSede, idDeporte, fechaIni, horaIni, fechaFin, horaFin, cantidad, aforo, disponibilidad " +
//                    " FROM horario WHERE idSede = ? AND idDeporte = ?",selectionArgs);
            Cursor c = db.rawQuery("SELECT DISTINCT 0, 0, 0, fechaIni, null, null, null, 0, 0, 0 " +
                    " FROM horario WHERE idSede = ? AND idDeporte = ?",selectionArgs);
            while(c.moveToNext()) {
                Horario horario = new Horario(c.getInt(0), c.getInt(1), c.getInt(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6), c.getInt(7), c.getInt(8), c.getInt(9));
                listaHorario.add(horario);
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaHorario;
    }

    public List<Horario> ObtenerHorarHorario(int idSede, int idDeporte, String fecha){
        List<Horario> listaHorario = new ArrayList<>();
        try{
            String[] selectionArgs = {String.valueOf(idSede), String.valueOf(idDeporte), fecha};
//            Cursor c = db.rawQuery("SELECT id, idSede, idDeporte, fechaIni, horaIni, fechaFin, horaFin, cantidad, aforo, disponibilidad " +
//                    " FROM horario WHERE idSede = ? AND idDeporte = ? AND fechaIni = ?",selectionArgs);
            Cursor c = db.rawQuery("SELECT id, 0, 0, null, horaIni, null, null, 0, 0, 0 " +
                    " FROM horario WHERE idSede = ? AND idDeporte = ? AND fechaIni = ?",selectionArgs);
            while(c.moveToNext()) {
                Horario horario = new Horario(c.getInt(0), c.getInt(1), c.getInt(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6), c.getInt(7), c.getInt(8), c.getInt(9));
                listaHorario.add(horario);
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaHorario;
    }

    public void borrarDatos(){
        try{
            db.delete("horario",null, null);
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
    }

}
