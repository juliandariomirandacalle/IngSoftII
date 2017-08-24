package com.example.juliandario.invernadero_app;

import android.content.ContentValues;

/**
 * Created by JULIAN DARIO on 20/08/2017.
 */

public class Proveedor {
    private String nombre;

    Proveedor(String nombre){
        this.nombre = nombre;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(UserContract.InsumoEntry.NOMBRE, this.nombre);

        return values;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
