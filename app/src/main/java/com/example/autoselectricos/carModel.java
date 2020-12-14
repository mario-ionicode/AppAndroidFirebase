package com.example.autoselectricos;

public class carModel {


    String id, modelo, marca, NoSerie, placa, precio, año, color, tipo;



    public carModel(String id, String modelo, String marca, String NoSerie, String placa, String precio, String año, String color, String tipo) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.NoSerie = NoSerie;
        this.placa = placa;
        this.precio = precio;
        this.año = año;
        this.color = color;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNoSerie() {
        return NoSerie;
    }

    public void setNoSerie(String noSerie) {
        NoSerie = noSerie;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String anio) {
        this.año = año;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
