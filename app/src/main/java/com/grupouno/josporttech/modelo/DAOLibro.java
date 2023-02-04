package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Libro;
import com.grupouno.josporttech.util.BD;

import java.util.ArrayList;
import java.util.List;

public class DAOLibro {
    BD base;
    SQLiteDatabase db;
    Context context;

    public DAOLibro(Context context){
        base = new BD(context);
        this.context = context;
    }

    public void abridBD(){
        db = base.getWritableDatabase();
    }

    public String registrarLibro(Libro libro){
        String mensaje = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("titulo", libro.getTitulo());
            valores.put("autor",libro.getAutor());
            valores.put("anio",libro.getAnio());
            long result = db.insert("libros",null,valores);
            if(result == -1){
                mensaje = "Error al insertar";
            }else{
                mensaje = "Se registró correctamente";
            }
        }
        catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return mensaje;
    }

    public List<Libro> obtenerLibros(){
        List<Libro> listaLibros = new ArrayList<>();
        try{
            Cursor c = db.rawQuery("SELECT * FROM libro", null);
            while(c.moveToNext()){
                listaLibros.add(new Libro(c.getInt(0),c.getString(1),c.getString(2), c.getInt(3)));
            }
        }catch (Exception e){
            Log.d("=>", e.getMessage());
        }
        return listaLibros;
    }

}
