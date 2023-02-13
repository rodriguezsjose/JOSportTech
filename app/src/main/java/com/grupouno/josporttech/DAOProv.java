package com.grupouno.josporttech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.grupouno.josporttech.util.BD;;

import java.util.ArrayList;
import java.util.List;


public class DAOProv {
    BD b;
    SQLiteDatabase db;
    Context c;

    public DAOProv(Context c){
        b = new BD(c);
        this.c = c;


    }

    public String registrarCD (Proveedor cd){
        String mensaje = "";
        try {
            ContentValues values = new ContentValues();
            values.put("nom", cd.getNom());
            values.put("dir", cd.getDir());
            values.put("hor", cd.getHor());
            values.put("prov", cd.getProv());
            values.put("dist", cd.getDir());
            values.put("ref", cd.getRef());
            values.put("dep", cd.getDep());
            values.put("serv", cd.getServ());
            values.put("gal", cd.getGal());
            long result = db.insert("cd", null, values);

            if (result ==-1){
                mensaje= "No se logró registrar su Centro Deportivo";
            }else {
                mensaje = "Se insertó su Centro Deportivo";
            }


        }catch (Exception e){
            Log.d("=>", e.getMessage());
        }
        return  mensaje;

    }

    public void abriBD() {

        db =b.getWritableDatabase();
    }

    public List<Proveedor> obtenerProv(){
        List<Proveedor> listaprov = new ArrayList<>();

        try{

            Cursor c = db.rawQuery("SELECT * FROM cd", null);
            while (c.moveToNext()){
                listaprov.add(new Proveedor(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9)));


            }
        }catch (Exception e){
            Log.d("=>", e.getMessage());
        }
        return listaprov;
    }

}
