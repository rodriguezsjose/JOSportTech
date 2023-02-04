package com.grupouno.josporttech.entidad;

public class Usuario {

    private int id;
    private String nombres;
    private String apellidos;
    private String fec_nacimiento;
    private int id_genero;
    private String perfil;
    private String correo;
    private String password;

    public Usuario() {
    }

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
    public Usuario(int id, String correo, String password) {
        this.id = id;
        this.correo = correo;
        this.password = password;
    }

    public Usuario(String nombres, String apellidos, String fec_nacimiento, Integer id_genero, String perfil,String correo, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fec_nacimiento = fec_nacimiento;
        this.id_genero = id_genero;
        this.perfil = perfil;
        this.correo = correo;
        this.password = password;
    }

    public Usuario(int id, String nombres, String apellidos, String fec_nacimiento, Integer id_genero, String perfil) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fec_nacimiento = fec_nacimiento;
        this.id_genero = id_genero;
        this.perfil = perfil;
    }

    public Usuario(int id, String nombres, String apellidos, String fec_nacimiento, Integer id_genero, String perfil,String correo, String password) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fec_nacimiento = fec_nacimiento;
        this.id_genero = id_genero;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFec_nacimiento() {
        return fec_nacimiento;
    }

    public void setFec_nacimiento(String fec_nacimiento) {
        this.fec_nacimiento = fec_nacimiento;
    }

    public Integer getId_genero() {
        return id_genero;
    }
    public void setId_genero(Integer id_genero) {
        this.id_genero = id_genero;
    }

    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }


}
