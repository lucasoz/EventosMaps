package com.lucas.oz.eventify;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class Evento {
    private String titulo;
    private String enlace;
    private String enlace_imagen;
    private String categoria;
    private double longitud;
    private double latitud;

    public Evento(String titulo, String enlace, String enlace_imagen, String categoria, double latitud,double longitud) {
        this.titulo = titulo;
        this.enlace = enlace;
        this.enlace_imagen = enlace_imagen;
        this.categoria = categoria;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
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

    public BitmapDescriptor iconoDeCategoria(){
        BitmapDescriptor icono;
        switch (categoria){
            case "arte" :
                icono = BitmapDescriptorFactory.fromResource(R.drawable.arte);
                break;
            case "deporte" :
                icono = BitmapDescriptorFactory.fromResource(R.drawable.deporte);
                break;
            case "fiesta" :
                icono = BitmapDescriptorFactory.fromResource(R.drawable.fiesta);
                break;
            default:
                icono = BitmapDescriptorFactory.fromResource(R.drawable.no_encontrado);
                break;
        }

        return icono;
    }
}


