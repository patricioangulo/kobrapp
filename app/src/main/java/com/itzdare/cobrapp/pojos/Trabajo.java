package com.itzdare.cobrapp.pojos;

public class Trabajo {
    private String id_user;
    private String id_trabajo;
    private String titulo;
    private String descripcion;
    private String precio;
    private String valor_hora;
    private String valor_material;
    private String hora;

    public Trabajo() {
    }

    public Trabajo(String id_user, String id_trabajo, String titulo, String descripcion, String precio, String valor_hora, String valor_material, String hora) {
        this.id_user = id_user;
        this.id_trabajo = id_trabajo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.valor_hora = valor_hora;
        this.valor_material = valor_material;
        this.hora = hora;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_trabajo() {
        return id_trabajo;
    }

    public void setId_trabajo(String id_trabajo) {
        this.id_trabajo = id_trabajo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(String valor_hora) {
        this.valor_hora = valor_hora;
    }

    public String getValor_material() {
        return valor_material;
    }

    public void setValor_material(String valor_material) {
        this.valor_material = valor_material;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
