package com.example.juliandario.invernadero_app;

import android.provider.BaseColumns;

/**
 * Created by JULIAN DARIO on 28/03/2017.
 */

public final class UserContract {
    private UserContract(){
    }

    public static class UsuarioEntry implements BaseColumns {

        // Nombre de la tabla y sus columnas
        public static final String TABLE_NAME_USUARIOS = "USUARIOS";
        public static final String TIPOUSUARIO = "TIPOUSUARIO";
        public static final String USERNAME = "USERNAME";
        public static final String PASS = "PASS";
    }

    public static class LoteEntry implements BaseColumns {
        // Nombre de la tabla y sus columnas
        public static final String TABLE_NAME_LOTES = "LOTES";
        public static final String NOMBRE = "NOMBRE";
        public static final String FECHA_INICIO = "FECHA_INICIO";
    }

    public static class InsumoEntry implements BaseColumns {
        // Nombre de la tabla y sus columnas
        public static final String TABLE_NAME_INSUMOS = "INSUMOS";
        public static final String NOMBRE = "NOMBRE";
        public static final String CANTIDAD = "CANTIDAD";
    }

    public static class ProveedorEntry implements BaseColumns {
        // Nombre de la tabla y sus columnas
        public static final String TABLE_NAME_PROVEEDORES = "PROVEEDORES";
        public static final String NOMBRE = "NOMBRE";
    }

    public static class InsumosProveedoresEntry implements BaseColumns {
        // Nombre de la tabla y sus columnas
        public static final String TABLE_NAME_INSUMOSPROVEEDORES = "INSUMOSPROVEEDORES";
        public static final String ID_INSUMO = "ID_INSUMO";
        public static final String ID_PROVEEDOR = "ID_PROVEEDOR";
    }

    public static class SalidaEntry implements BaseColumns {
        // Nombre de la tabla y sus columnas
        public static final String TABLE_NAME_SALIDAS = "SALIDAS";
        public static final String ID_INSUMO = "ID_INSUMO";
        public static final String ID_LOTE = "ID_LOTE";
        public static final String CANTIDAD = "CANTIDAD";
        public static final String FECHA = "FECHA";
    }

    public static class EntradaEntry implements BaseColumns {
        // Nombre de la tabla y sus columnas
        public static final String TABLE_NAME_ENTRADAS = "ENTRADAS";
        public static final String ID_INSUMO = "ID_INSUMO";
        public static final String ID_PROVEEDOR = "ID_PROVEEDOR";
        public static final String CANTIDAD = "CANTIDAD";
        public static final String FECHA = "FECHA";
    }
}
