package com.grupouno.josporttech.entidad;

public class Reclamo {
    private int id;
    private String peticion, codReserva, fecha, centroD, medioContacto, contacto, correo, motivo, descMotivo, sustento;

    public Reclamo(String peticion, String codReserva, String fecha, String centroD, String medioContacto, String contacto, String correo, String motivo, String descMotivo, String sustento) {

        this.peticion = peticion;
        this.codReserva = codReserva;
        this.fecha = fecha;
        this.centroD = centroD;
        this.medioContacto = medioContacto;
        this.contacto = contacto;
        this.correo = correo;
        this.motivo = motivo;
        this.descMotivo = descMotivo;
        this.sustento = sustento;
    }

    public Reclamo(int id, String peticion, String codReserva, String fecha, String centroD, String medioContacto, String contacto, String correo, String motivo, String descMotivo, String sustento) {
        this.id = id;
        this.peticion = peticion;
        this.codReserva = codReserva;
        this.fecha = fecha;
        this.centroD = centroD;
        this.medioContacto = medioContacto;
        this.contacto = contacto;
        this.correo = correo;
        this.motivo = motivo;
        this.descMotivo = descMotivo;
        this.sustento = sustento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public String getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(String codReserva) {
        this.codReserva = codReserva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCentroD() {
        return centroD;
    }

    public void setCentroD(String centroD) {
        this.centroD = centroD;
    }

    public String getMedioContacto() {
        return medioContacto;
    }

    public void setMedioContacto(String medioContacto) {
        this.medioContacto = medioContacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescMotivo() {
        return descMotivo;
    }

    public void setDescMotivo(String descMotivo) {
        this.descMotivo = descMotivo;
    }

    public String getSustento() {
        return sustento;
    }

    public void setSustento(String sustento) {
        this.sustento = sustento;
    }
}
