package com.grupouno.josporttech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.grupouno.josporttech.entidad.Deporte;
import com.grupouno.josporttech.entidad.Sede;
import com.grupouno.josporttech.entidad.Usuario;
import com.grupouno.josporttech.modelo.DAODeporte;
import com.grupouno.josporttech.modelo.DAOSede;
import com.grupouno.josporttech.modelo.DAOUsuario;

public class StartActivity extends AppCompatActivity {


    EditText _txtCorreo, _txtPassword;
    TextView _lblTitulo;
    Button _btnIniciarSesion, _btnRegistrarse, _btnInvitado;

    Usuario _usuario;
    DAOUsuario _daoUsuario = new DAOUsuario(this);
    DAODeporte _daoDeporte = new DAODeporte(this);
    DAOSede _daoSede = new DAOSede(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        asignarReferencias();
        registrarUsuarios();
    }

    private void registrarUsuarios(){
        _daoUsuario.abrirBD();
        String msgu = "";
        msgu = _daoUsuario.registrarUsuario(new Usuario("Jose","Olaya PE","1782-07-28",0,"user","jolaya@gob.pe", "1234"));
        msgu = _daoUsuario.registrarUsuario(new Usuario("Gladys","Tejada","1985-09-30",1,"user","gtejada@gob.pe", "6789"));
        _daoDeporte.abrirBD();
        String msgd = "";
        msgd = _daoDeporte.registrarDeporte(new Deporte(101,"Fulbito","campo 7vs7"));
        msgd = _daoDeporte.registrarDeporte(new Deporte(102,"Basquet ball","profesional"));
        msgd = _daoDeporte.registrarDeporte(new Deporte(103,"Natacion","9 carriles"));
        _daoSede.abrirBD();
        String msgs = "";
        msgs = _daoSede.registrarSede(new Sede(901,"Nacional","Nacional","101010","-12.068990", "-77.033152",-9));
        msgs = _daoSede.registrarSede(new Sede(102,"Matute","Matute","101010","-12.068367","-77.022110",-9));
        msgs = _daoSede.registrarSede(new Sede(103,"Monumental","Monumental","101010","-12.056168","-76.937743",-9));
    }

    private void asignarReferencias(){
        _lblTitulo = findViewById(R.id.lblTitulo);
        _txtCorreo = findViewById(R.id.txtCorreo);
        _txtPassword = findViewById(R.id.txtPassword);
        ///
        _btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        _btnRegistrarse = findViewById(R.id.btnRegistrarse);
        _btnInvitado = findViewById(R.id.btnInvitado);
        ///
        _btnIniciarSesion.setOnClickListener(view -> {
            if(capturarDatos() == true) {
                _daoUsuario.abrirBD();
                _usuario = _daoUsuario.ValidarInicio(_txtCorreo.getText().toString(), _txtPassword.getText().toString());
                if(_usuario.getId()==-404){
                    msgError(_usuario.getNombres());
                }else{
                    msgBienvenida("Bienvenido(a): "+_usuario.getNombres()+" "+_usuario.getApellidos());
                }
            }
        });
        _btnRegistrarse.setOnClickListener(view -> {
            msgRegistrar("Quiere registrarse como usuario(a) de la app?");
        });
        _btnInvitado.setOnClickListener(view -> {
            msgInvitado("Quiere ingresar como invitado?");
        });

    }

    private boolean capturarDatos(){
        boolean valida = true;
        String usuario = _txtCorreo.getText().toString();
        String clave = _txtPassword.getText().toString();
        if(usuario.equals("")){
            _txtCorreo.setError("El Correo es obligatorio");
            valida = false;
        }
        if(clave.equals("")){
            _txtPassword.setError("La Clave es obligatoria");
            valida = false;
        }
        if(valida == true){
            valida = true;
        }
        return valida;
    }

    private void msgError(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar",null);
        ventana.create().show();
    }

    private void msgInvitado(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setNegativeButton("No",null);
        ventana.setPositiveButton("Si",(dialogInterface, i) -> {
            Intent intent = new Intent(this, DeporteListarActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

    private void msgBienvenida(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar",(dialogInterface, i) -> {
            Intent intent = new Intent(this, Expo1Activity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

    private void msgRegistrar(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setNegativeButton("No",null);
        ventana.setPositiveButton("Si",(dialogInterface, i) -> {
            Intent intent = new Intent(this, UsuarioRegistrarActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

}