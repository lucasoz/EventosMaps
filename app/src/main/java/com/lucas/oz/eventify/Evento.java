package com.lucas.oz.eventify;

public class Evento {
    private String titulo;
    private String enlace;
    private String enlace_imagen;
    private String categoria;

    public Evento(String titulo, String enlace, String enlace_imagen, String categoria) {
        this.titulo = titulo;
        this.enlace = enlace;
        this.enlace_imagen = enlace_imagen;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getEnlace_imagen() {
        return enlace_imagen;
    }

    public void setEnlace_imagen(String enlace_imagen) {
        this.enlace_imagen = enlace_imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}


