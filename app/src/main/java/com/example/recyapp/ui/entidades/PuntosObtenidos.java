package com.example.recyapp.ui.entidades;

import java.util.Date;

public class PuntosObtenidos {
    private Integer idUsuario;
    private String nombreUsuario;
    private String residuo;
    private Integer puntos;
    private String fecha;

    public PuntosObtenidos() {

    }

    public PuntosObtenidos(Integer idUsuario, String nombreUsuario, String residuo, Integer puntos, String fecha) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.residuo = residuo;
        this.puntos = puntos;
        this.fecha = fecha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getResiduo() {
        return residuo;
    }

    public void setResiduo(String residuo) {
        this.residuo = residuo;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
