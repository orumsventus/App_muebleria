package com.example.muebleria_app.Entidades;

import android.view.View;

public class Mueble {
    private String nombre;
    private String descripcion;
    private String precio;
    private String imagen_url;
    View mas_menu;

    public Mueble() {
    }

    public Mueble(String nombre, String descripcion, String precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Mueble(String nombre, String descripcion, String precio, String imagen_url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen_url = imagen_url;
    }

    public Mueble(String nombre, String descripcion, String precio, String imagen_url, View mas_menu) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen_url = imagen_url;
        this.mas_menu = mas_menu;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public View getMas_menu() {
        return mas_menu;
    }

    public void setMas_menu(View mas_menu) {
        this.mas_menu = mas_menu;
    }
}
