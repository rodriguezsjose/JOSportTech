package com.grupouno.josporttech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.grupouno.josporttech.entidad.Usuario;
import com.grupouno.josporttech.modelo.DAOUsuario;
public class UsuarioRegistrarActivity extends AppCompatActivity {

    TextView _lblTitulo;
    Integer id;
    EditText _txtNombres, _txtApellidos, _txtFecNacimiento, _txtPerfil, _txtCorreo, _txtPassword;
    EditText _txtGenero;
    Button _btnRegistrar;
    Usuario usuario;
    DAOUsuario daoUsuario = new DAOUsuario(this);
    boolean registra = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrar);
        asignarReferencias();
    }

    private void asignarReferencias(){
        _lblTitulo = findViewById(R.id.lblTitulo);
        _txtNombres = findViewById(R.id.txtNombres);
        _txtApellidos = findViewById(R.id.txtApellidos);
        _txtFecNacimiento = findViewById(R.id.txtFecNacimiento);
        _txtGenero = findViewById(R.id.txtGenero);
        _txtPerfil = findViewById(R.id.txtPerfil);
        _txtCorreo = findViewById(R.id.txtCorreo);
        _txtPassword = findViewById(R.id.txtPassword);
        _btnRegistrar = findViewById(R.id.btnRegistrar);
        _btnRegistrar.setOnClickListener(view -> {
            if(capturarDatos() == true) {
                daoUsuario.abrirBD();
                String mensaje = "";
                if (registra) {
                    mensaje = daoUsuario.registrarUsuario(usuario);
                } else {
                    mensaje = daoUsuario.actualizarUsuario(usuario);
                }
                mostrarMensaje(mensaje);
            }
        });
    }

    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar",(dialogInterface, i) -> {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

    private boolean capturarDatos(){
        boolean valida = true;
        String nombres = _txtNombres.getText().toString();
        String apellidos = _txtApellidos.getText().toString();
        String fecnacimiento = _txtFecNacimiento.getText().toString();
        String genero = _txtGenero.getText().toString();
        String perfil = _txtPerfil.getText().toString();
        String correo = _txtCorreo.getText().toString();
        String password = _txtPassword.getText().toString();
        if(nombres.equals("")){
            _txtNombres.setError("Nombre(s) es obligatorio");
            valida = false;
        }
        /*
        if(apellidos.equals("")){
            _txtApellidos.setError("Apellidos es obligatorio");
            valida = false;
        }
        */
        if(fecnacimiento.equals("")){
            _txtFecNacimiento.setError("Fec. de Nacimiento es obligatorio");
            valida = false;
        }
        /*
        if(genero.equals("")){
            _txtGenero.setError("Genero es obligatorio");
            valida = false;
        }
        */
        if(correo.equals("")){
            _txtCorreo.setError("Correo es Obligatorio");
            valida = false;
        }
        if(password.equals("")){
            _txtPassword.setError("Password es obligatorio");
            valida = false;
        }
        if(valida == true){
            if (registra){
                usuario = new Usuario(nombres,apellidos,fecnacimiento,Integer.parseInt(genero),perfil,correo,password);
            }else{
                usuario = new Usuario(id,nombres,apellidos,fecnacimiento,Integer.parseInt(genero),perfil,correo,password);
            }
        }
        return valida;
    }


}