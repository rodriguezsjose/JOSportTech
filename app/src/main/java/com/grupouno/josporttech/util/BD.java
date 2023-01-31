package com.grupouno.josporttech.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {


public BD(Context c){
    super(c,"joseolaya1.db", null, 1);
}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String query =
            "CREATE TABLE tablaprueba "+
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " prueba TEXT NOT NULL);";
    sqLiteDatabase.execSQL(query);

    String query1 =
            "CREATE TABLE rec "+
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " peticion TEXT NOT NULL, "+
                    " codReserva TEXT NOT NULL, "+
                    " fecha TEXT NOT NULL, "+
                    " centroD TEXT NOT NULL, "+
                    " medioContacto TEXT NOT NULL, "+
                    " contacto TEXT NOT NULL, "+
                    " correo TEXT NOT NULL, "+
                    " motivo TEXT NOT NULL, "+
                    " descMotivo TEXT NOT NULL, "+
                    " sustento TEXT NOT NULL);";
            sqLiteDatabase.execSQL(query1);

        String query2 =
                "CREATE TABLE cd "+
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " nom TEXT NOT NULL, "+
                        " dir TEXT NOT NULL, "+
                        " hor TEXT NOT NULL, "+
                        " prov TEXT NOT NULL, "+
                        " dist TEXT NOT NULL, "+
                        " ref TEXT NOT NULL, "+
                        " dep TEXT NOT NULL, "+
                        " serv TEXT NOT NULL, "+
                        " gal TEXT NOT NULL);";

        sqLiteDatabase.execSQL(query2);


        String query3 =
                "CREATE TABLE recla "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " peticion TEXT NOT NULL, "+
                        " codReserva TEXT NOT NULL, "+
                        " fecha TEXT NOT NULL, "+
                        " centroD TEXT NOT NULL, "+
                        " medioContacto TEXT NOT NULL, "+
                        " contacto TEXT NOT NULL, "+
                        " correo TEXT NOT NULL, "+
                        " motivo TEXT NOT NULL, "+
                        " descMotivo TEXT NOT NULL, "+
                        " sustento TEXT NOT NULL);";
        sqLiteDatabase.execSQL(query3);





    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
