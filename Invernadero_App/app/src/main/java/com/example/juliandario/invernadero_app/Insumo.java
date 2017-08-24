package com.example.juliandario.invernadero_app;

import android.content.ContentValues;

/**
 * Created by JULIAN DARIO on 20/08/2017.
 */

public class Insumo {
    private String nombre;
    private int cantidad;

    Insumo(String nombre, int cantidad){
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(UserContract.InsumoEntry.NOMBRE, this.nombre);
        values.put(UserContract.InsumoEntry.CANTIDAD, this.cantidad);

        return values;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
