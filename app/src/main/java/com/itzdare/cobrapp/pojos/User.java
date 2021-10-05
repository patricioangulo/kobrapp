package com.itzdare.cobrapp.pojos;

public class User {
    private String id;
    private String nombre;
    private String mail;
    private String foto;

    public User() {
    }

    public User(String id, String nombre, String mail, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
