package com.grupouno.josporttech.entidad;

public class Reserva {
    private int id, idSede, idDeporte, idHorario, flagPagado, cantReprogramaciones, idMotivoAnulacion;
    private String estado, fecha, hora;

    public Reserva(int idSede, int idDeporte, int idHorario, String fecha, String hora, String estado, int flagPagado, int cantReprogramaciones, int idMotivoAnulacion) {
        this.idSede = idSede;
        this.idDeporte = idDeporte;
        this.idHorario = idHorario;
        this.estado = estado;
        this.flagPagado = flagPagado;
        this.cantReprogramaciones = cantReprogramaciones;
        this.idMotivoAnulacion = idMotivoAnulacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Reserva(int id, int idSede, int idDeporte, int idHorario, String fecha, String hora, String estado, int flagPagado, int cantReprogramaciones, int idMotivoAnulacion) {
        this.id = id;
        this.idSede = idSede;
        this.idDeporte = idDeporte;
        this.idHorario = idHorario;
        this.estado = estado;
        this.flagPagado = flagPagado;
        this.cantReprogramaciones = cantReprogramaciones;
        this.idMotivoAnulacion = idMotivoAnulacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Reserva(int idSede, int idDeporte, int idHorario, String fecha, String hora, String estado, int flagPagado, int cantReprogramaciones) {
        this.id = id;
        this.idSede = idSede;
        this.idDeporte = idDeporte;
        this.idHorario = idHorario;
        this.estado = estado;
        this.flagPagado = flagPagado;
        this.cantReprogramaciones = cantReprogramaciones;
        this.idMotivoAnulacion = idMotivoAnulacion;
        this.fecha = fecha;
        this.hora = hora;
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

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getFlagPagado() {
        return flagPagado;
    }

    public void setFlagPagado(int flagPagado) {
        this.flagPagado = flagPagado;
    }

    public int getCantReprogramaciones() {
        return cantReprogramaciones;
    }

    public void setCantReprogramaciones(int cantReprogramaciones) {
        this.cantReprogramaciones = cantReprogramaciones;
    }

    public int getIdMotivoAnulacion() {
        return idMotivoAnulacion;
    }

    public void setIdMotivoAnulacion(int idMotivoAnulacion) {
        this.idMotivoAnulacion = idMotivoAnulacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

