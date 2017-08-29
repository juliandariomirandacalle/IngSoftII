package com.example.juliandario.invernadero_app;

/**
 * Created by JULIAN DARIO on 26/08/2017.
 */

public class Salida {

    private String insumo;
    private String lote;
    private int cantidad;
    private String fecha;

    Salida(String insumo, String lote, int cantidad, String fecha){
        this.insumo = insumo;
        this.lote = lote;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
