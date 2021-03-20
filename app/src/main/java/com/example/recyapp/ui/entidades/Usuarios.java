package com.example.recyapp.ui.entidades;

public class Usuarios {
    static Integer IdUsuario;
    static String nombres;
    static String usuario;
    static String contrasenia;
    static String fechaRegistro;

    public Usuarios() {

    }

    public Usuarios(Integer idUsuario, String nombres, String usuario, String contrasenia, String fechaRegistro) {
        this.IdUsuario = idUsuario;
        this.nombres = nombres;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
