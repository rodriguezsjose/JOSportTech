package com.grupouno.josporttech.entidad;

public class Sede {
    private int id, idCentro;
    private String nombre;
    private String descripcion;
    private String ubigeo;
    private String latitud;
    private String longitud;

    private String telefono;

    public Sede(int id, String nombre, String descripcion, String ubigeo, String latitud, String longitud, int idCentro, String telefono) {
        this.id = id;
        this.idCentro = idCentro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubigeo = ubigeo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
    }

    public Sede(String nombre, String descripcion, String ubigeo, String latitud, String longitud, int idCentro, String telefono) {
        this.idCentro = idCentro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubigeo = ubigeo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
