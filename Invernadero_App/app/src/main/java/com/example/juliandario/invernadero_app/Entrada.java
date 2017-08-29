package com.example.juliandario.invernadero_app;

/**
 * Created by JULIAN DARIO on 26/08/2017.
 */

public class Entrada {
    private String insumo;
    private String proveedor;
    private int cantidad;
    private String fecha;

    Entrada(String insumo, String proveedor, int cantidad, String fecha){
        this.insumo = insumo;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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
