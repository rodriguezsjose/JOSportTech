package com.grupouno.josporttech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupouno.josporttech.entidad.Usuario;
import com.grupouno.josporttech.util.BD;

public class DAOUsuario {
    BD base;
    SQLiteDatabase db;
    Context context;

    public DAOUsuario(Context context){
        base = new BD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = base.getWritableDatabase();
    }

    public Usuario ValidarInicio(String p_strCorreo, String p_strPassword){
        Usuario _usuario = new Usuario();
        _usuario.setId(-404);
        _usuario.setNombres("Error correo o clave incorrectos");
        try{
            String[] args = new String []{p_strCorreo,p_strPassword};
            Cursor c = db.rawQuery("SELECT id, nombres, apellidos FROM usr WHERE correo=? AND password=?",args);
            while(c.moveToNext()) {
                _usuario.setId(c.getInt(0));
                _usuario.setNombres(c.getString(1));
                _usuario.setApellidos(c.getString(2));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return _usuario;
    }

    public String registrarUsuario(Usuario p_usuario){
        String mensaje = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("nombres", p_usuario.getNombres());
            valores.put("apellidos", p_usuario.getApellidos());
            valores.put("fec_nacimiento", p_usuario.getFec_nacimiento());
            valores.put("id_genero", p_usuario.getId_genero());
            valores.put("perfil", p_usuario.getPerfil());

            valores.put("correo", p_usuario.getCorreo());
            valores.put("password", p_usuario.getPassword());

            long result = db.insert("usr",null,valores);
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

    public String actualizarUsuario(Usuario p_usuario){
        String mensaje = "implementar";
        /*
        try {
            ContentValues valores = new ContentValues();
            valores.put("nombres", p_usuario.getNombres());
            valores.put("apellidos", p_usuario.getApellidos());
            valores.put("fec_nacimiento", p_usuario.getFec_nacimiento());
            valores.put("id_genero", p_usuario.getId_genero());
            valores.put("perfil", p_usuario.getPerfil());

            valores.put("correo", p_usuario.getCorreo());
            valores.put("password", p_usuario.getPassword());

            long result = db.insert("usr",null,valores);
            if(result == -1){
                mensaje = "Error al insertar";
            }else{
                mensaje = "Se registró correctamente";
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        */
        return mensaje;
    }

}
