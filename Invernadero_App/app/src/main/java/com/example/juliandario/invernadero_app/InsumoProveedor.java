package com.example.juliandario.invernadero_app;

/**
 * Created by JULIAN DARIO on 23/08/2017.
 */

public class InsumoProveedor {

    private Insumo inusmo;
    private Proveedor proveedor;

    InsumoProveedor(){

    }

    public Insumo getInusmo() {
        return inusmo;
    }

    public void setInusmo(Insumo inusmo) {
        this.inusmo = inusmo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
