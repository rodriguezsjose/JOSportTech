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

    String query_user =
            "CREATE TABLE usr "+
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " nombres TEXT NOT NULL, "+
                    " apellidos TEXT NOT NULL, "+
                    " fec_nacimiento TEXT NOT NULL, "+
                    " id_genero INTEGER NOT NULL, "+
                    " perfil TEXT NOT NULL, "+
                    " correo TEXT NOT NULL, "+
                    " password TEXT NOT NULL);";
    sqLiteDatabase.execSQL(query_user);

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

        String queryCreateCentro =
                "CREATE TABLE centro "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " nombre TEXT NOT NULL, " +
                        " descripcion TEXT, " +
                        " idProveedor INTEGER NOT NULL, " +
                        " horaIni TEXT NOT NULL, " +
                        " horaFin TEXT NOT NULL ); ";
        sqLiteDatabase.execSQL(queryCreateCentro);

        String queryCreateSede =
                "CREATE TABLE sede "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " nombre TEXT NOT NULL, "+
                        " descripcion TEXT, "+
                        " ubigeo TEXT NOT NULL, "+
                        " latitud TEXT, "+
                        " longitud TEXT, "+
                        " idCentro INTEGER NOT NULL, "+
                        " telefono TEXT ); ";
        sqLiteDatabase.execSQL(queryCreateSede);

        String queryCreateDeporte =
                "CREATE TABLE deporte "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " nombre TEXT NOT NULL, "+
                        " descripcion TEXT); ";
        sqLiteDatabase.execSQL(queryCreateDeporte);

        String queryCreateHorario =
                "CREATE TABLE horario "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " idSede INTEGER NOT NULL, "+
                        " idDeporte INTEGER NOT NULL, "+
                        " fechaIni TEXT NOT NULL, "+
                        " horaIni TEXT NOT NULL, "+
                        " fechaFin TEXT NOT NULL,"+
                        " horaFin TEXT NOT NULL,"+
                        " cantidad INTEGER NOT NULL, "+
                        " aforo INTEGER NOT NULL, "+
                        " disponibilidad INTEGER NOT NULL); ";
        sqLiteDatabase.execSQL(queryCreateHorario);

        String queryCreateMotivoAnulacion =
                "CREATE TABLE motivoAnulacion "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " nombre TEXT NOT NULL, "+
                        " descripcion TEXT); ";
        sqLiteDatabase.execSQL(queryCreateMotivoAnulacion);

        String queryCreateReserva =
                "CREATE TABLE reserva "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " idSede INTEGER NOT NULL, "+
                        " idDeporte INTEGER NOT NULL, "+
                        " idHorario INTEGER NOT NULL, "+
                        " fecha TEXT NOT NULL, "+
                        " hora TEXT NOT NULL, "+
                        " estado TEXT NOT NULL, "+
                        " flagPagado INTEGER NOT NULL, "+
                        " cantReprogramaciones INTEGER NOT NULL, "+
                        " idMotivoAnulacion INTEGER); ";
        sqLiteDatabase.execSQL(queryCreateReserva);

        String queryCreatePago =
                "CREATE TABLE pago "+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " idReserva INTEGER, "+
                        " fecha TEXT NOT NULL); ";
        sqLiteDatabase.execSQL(queryCreatePago);



    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
