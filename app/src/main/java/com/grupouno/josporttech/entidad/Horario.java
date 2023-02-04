package com.grupouno.josporttech.entidad;

public class Horario {
    private int id, idSede, idDeporte, cantidad, aforo, disponibilidad;
    private String fechaIni, fechaFin, nombreDeporte, horaIni, horaFin;

    public Horario(int id, int idSede, int idDeporte, String fechaIni, String horaIni, String fechaFin, String horaFin, int cantidad, int aforo, int disponibilidad) {
        this.id = id;
        this.idSede = idSede;
        this.idDeporte = idDeporte;
        this.cantidad = cantidad;
        this.aforo = aforo;
        this.disponibilidad = disponibilidad;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    public Horario(int idSede, int idDeporte, String fechaIni, String horaIni, String fechaFin, String horaFin, int cantidad, int aforo, int disponibilidad) {
        this.idSede = idSede;
        this.idDeporte = idDeporte;
        this.cantidad = cantidad;
        this.aforo = aforo;
        this.disponibilidad = disponibilidad;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }
    public Horario(int idDeporte, String nombreDeporte){
        this.idDeporte = idDeporte;
        this.nombreDeporte = nombreDeporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public int getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(int idDeporte) {
        this.idDeporte = idDeporte;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreDeporte() {return nombreDeporte; }

    public void setNombreDeporte(String nombreDeporte) {
        this.nombreDeporte = nombreDeporte;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
