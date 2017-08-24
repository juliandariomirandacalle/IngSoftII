package com.example.juliandario.invernadero_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JULIAN DARIO on 26/03/2017.
 */

public class UserDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "YAJU.db";
    public static final int DATABASE_VERSION = 1;

    // COnsulta para eliminar la tabla si existe
    private static final String SQL_DELETE_ENTRIES_USUARIOS =
            "DROP TABLE IF EXISTS " + UserContract.UsuarioEntry.TABLE_NAME_USUARIOS;
    private static final String SQL_DELETE_ENTRIES_LOTES =
            "DROP TABLE IF EXISTS " + UserContract.LoteEntry.TABLE_NAME_LOTES;
    private static final String SQL_DELETE_ENTRIES_INSUMOS =
            "DROP TABLE IF EXISTS " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS;
    private static final String SQL_DELETE_ENTRIES_PROVEEDORES =
            "DROP TABLE IF EXISTS " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES;
    private static final String SQL_DELETE_ENTRIES_INSUMOSPROVEEDORES =
            "DROP TABLE IF EXISTS " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES;
    private static final String SQL_DELETE_ENTRIES_ENTRADAS =
            "DROP TABLE IF EXISTS " + UserContract.EntradaEntry.TABLE_NAME_ENTRADAS;
    private static final String SQL_DELETE_ENTRIES_SALIDAS =
            "DROP TABLE IF EXISTS " + UserContract.SalidaEntry.TABLE_NAME_SALIDAS;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UserContract.UsuarioEntry.TABLE_NAME_USUARIOS + "("
                + UserContract.UsuarioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.UsuarioEntry.TIPOUSUARIO + " TEXT NOT NULL,"
                + UserContract.UsuarioEntry.USERNAME + " TEXT NOT NULL,"
                + UserContract.UsuarioEntry.PASS + " TEXT NOT NULL,"
                + "UNIQUE (" + UserContract.UsuarioEntry.USERNAME + "))");

        db.execSQL("CREATE TABLE " + UserContract.LoteEntry.TABLE_NAME_LOTES + "("
                + UserContract.LoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.LoteEntry.NOMBRE + " TEXT NOT NULL,"
                + UserContract.LoteEntry.FECHA_INICIO + " TEXT NOT NULL,"
                + "UNIQUE (" + UserContract.LoteEntry.NOMBRE + "))");

        db.execSQL("CREATE TABLE " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS + "("
                + UserContract.InsumoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.InsumoEntry.NOMBRE + " TEXT NOT NULL,"
                + UserContract.InsumoEntry.CANTIDAD + " TEXT NOT NULL,"
                + "UNIQUE (" + UserContract.InsumoEntry.NOMBRE + "))");

        db.execSQL("CREATE TABLE " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES + "("
                + UserContract.ProveedorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.ProveedorEntry.NOMBRE + " TEXT NOT NULL,"
                + "UNIQUE (" + UserContract.ProveedorEntry.NOMBRE + "))");

        db.execSQL("CREATE TABLE " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES + "("
                + UserContract.InsumosProveedoresEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.InsumosProveedoresEntry.ID_INSUMO + " INTEGER NOT NULL,"
                + UserContract.InsumosProveedoresEntry.ID_PROVEEDOR + " INTEGER NOT NULL,"
                + "FOREIGN KEY(" + UserContract.InsumosProveedoresEntry.ID_INSUMO + ") REFERENCES "
                    + UserContract.InsumoEntry.TABLE_NAME_INSUMOS + "(" + UserContract.InsumoEntry._ID + ")"
                + ",FOREIGN KEY(" + UserContract.InsumosProveedoresEntry.ID_PROVEEDOR + ") REFERENCES "
                    + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES + "(" + UserContract.ProveedorEntry._ID + "))");

        db.execSQL("CREATE TABLE " + UserContract.EntradaEntry.TABLE_NAME_ENTRADAS + "("
                + UserContract.EntradaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.EntradaEntry.CANTIDAD + " TEXT NOT NULL,"
                + UserContract.EntradaEntry.FECHA + " TEXT NOT NULL,"
                + UserContract.InsumosProveedoresEntry.ID_INSUMO + " INTEGER NOT NULL,"
                + UserContract.InsumosProveedoresEntry.ID_PROVEEDOR + " INTEGER NOT NULL,"
                + "FOREIGN KEY(" + UserContract.InsumosProveedoresEntry.ID_INSUMO + ") REFERENCES "
                    + UserContract.InsumoEntry.TABLE_NAME_INSUMOS + "(" + UserContract.InsumoEntry._ID + ")"
                + ",FOREIGN KEY(" + UserContract.InsumosProveedoresEntry.ID_PROVEEDOR + ") REFERENCES "
                    + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES + "(" + UserContract.ProveedorEntry._ID + "))");

        db.execSQL("CREATE TABLE " + UserContract.SalidaEntry.TABLE_NAME_SALIDAS + "("
                + UserContract.SalidaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.SalidaEntry.CANTIDAD + " TEXT NOT NULL,"
                + UserContract.SalidaEntry.FECHA + " TEXT NOT NULL,"
                + UserContract.SalidaEntry.ID_INSUMO + " INTEGER NOT NULL,"
                + UserContract.SalidaEntry.ID_LOTE + " INTEGER NOT NULL,"
                + "FOREIGN KEY(" + UserContract.SalidaEntry.ID_INSUMO + ") REFERENCES "
                + UserContract.SalidaEntry.TABLE_NAME_SALIDAS + "(" + UserContract.InsumoEntry._ID + ")"
                + ",FOREIGN KEY(" + UserContract.SalidaEntry.ID_LOTE + ") REFERENCES "
                + UserContract.LoteEntry.TABLE_NAME_LOTES + "(" + UserContract.LoteEntry._ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }

    // TODO DB GENERAL

    public void clearDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES_USUARIOS);
        db.execSQL(SQL_DELETE_ENTRIES_LOTES);
        db.execSQL(SQL_DELETE_ENTRIES_INSUMOS);
        db.execSQL(SQL_DELETE_ENTRIES_PROVEEDORES);
        db.execSQL(SQL_DELETE_ENTRIES_INSUMOSPROVEEDORES);
        db.execSQL(SQL_DELETE_ENTRIES_ENTRADAS);
        db.execSQL(SQL_DELETE_ENTRIES_SALIDAS);
        onCreate(db);
    }

    public void deleteDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES_USUARIOS);
        db.execSQL(SQL_DELETE_ENTRIES_LOTES);
        db.execSQL(SQL_DELETE_ENTRIES_INSUMOS);
        db.execSQL(SQL_DELETE_ENTRIES_PROVEEDORES);
        db.execSQL(SQL_DELETE_ENTRIES_INSUMOSPROVEEDORES);
        db.execSQL(SQL_DELETE_ENTRIES_ENTRADAS);
        db.execSQL(SQL_DELETE_ENTRIES_SALIDAS);
    }

    public void newDB(){
        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);
    }

    public boolean dbExists(){
        try{
            SQLiteDatabase db = getWritableDatabase();
            int val = db.rawQuery("SELECT * FROM " + UserContract.UsuarioEntry.TABLE_NAME_USUARIOS, null).getCount();
            return true;
        } catch(Exception e){
            return false;
        }
    }

    // TODO: USUARIOS

    public boolean insertarUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();

        usuario.toContentValues();
        long isInserted = db.insert(UserContract.UsuarioEntry.TABLE_NAME_USUARIOS, null, usuario.toContentValues());

        if(isInserted == -1)
            return false;
        else
            return true;
    }

    public String consultarUsuario(String username, String pass) throws Exception{
        SQLiteDatabase db = getWritableDatabase();
        String salida = "";

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.UsuarioEntry.TABLE_NAME_USUARIOS
                + " WHERE USERNAME LIKE ? AND PASS LIKE ?", new String [] {username.trim(), pass.trim()});

        if(cursor.getCount() >= 1) {
            while (cursor.moveToNext())
                salida = cursor.getString(cursor.getColumnIndex(UserContract.UsuarioEntry.TIPOUSUARIO));
            return salida;
        }
        else
            return null;
    }

    public String getUserUsername(){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.UsuarioEntry.TABLE_NAME_USUARIOS, null);

        String salida = "";

        while (cursor.moveToNext())
            salida = cursor.getString(cursor.getColumnIndex(UserContract.UsuarioEntry.USERNAME));

        return salida;
    }

    public boolean checkUser(){
        SQLiteDatabase db = getWritableDatabase();
        int count = db.rawQuery("SELECT * FROM " + UserContract.UsuarioEntry.TABLE_NAME_USUARIOS, null).getCount();

        if(count >= 1)
            return true;
        else
            return false;
    }

    // TODO: INSUMOS

    public int consultarInsumo(Insumo insumo){
        SQLiteDatabase db = getWritableDatabase();
        int salida = -1;

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS
                + " WHERE NOMBRE LIKE ?", new String [] {insumo.getNombre().trim()});

        if(cursor.getCount() >= 1) {
            while (cursor.moveToNext())
                salida = Integer.valueOf(cursor.getString(cursor.getColumnIndex(UserContract.InsumoEntry._ID)));
            return salida;
        }
        else
            return -1;
    }

    public boolean insertarInsumo(Insumo insumo){
        SQLiteDatabase db = getWritableDatabase();

        insumo.toContentValues();
        long isInserted = db.insert(UserContract.InsumoEntry.TABLE_NAME_INSUMOS, null, insumo.toContentValues());

        if(isInserted == -1)
            return false;
        else
            return true;
    }

    public boolean insertarInsumo(Insumo insumo, Proveedor proveedor){
        SQLiteDatabase db = getWritableDatabase();
        int id_insumo = consultarInsumo(insumo);
        boolean valid = true;

        if(id_insumo == -1){
            db.insert(UserContract.InsumoEntry.TABLE_NAME_INSUMOS, null, insumo.toContentValues());
            valid = insertarInsumoProveedor(cantidadInsumos(), consultarProveedor(proveedor));
        }
        else{
            if(consultarInsumoProveedor(insumo, proveedor))
                valid = insertarInsumoProveedor(id_insumo, consultarProveedor(proveedor));
        }
        return valid;
    }

    public String[] insumosList(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS, null);

        String[] insumos = new String[cantidadInsumos()];
        int index = 0;

        while (cursor.moveToNext()) {
            insumos[index] = cursor.getString(cursor.getColumnIndex(UserContract.InsumoEntry.NOMBRE));
            index++;
        }

        return insumos;
    }

    public String[] getProveedoresPorInsumo(String nombreInsumo){
        SQLiteDatabase db = getWritableDatabase();
        int id_insumo = 0, id_proveedor;
        ArrayList<String> nombreProveedores = new ArrayList<String>();
        String[] proveedoresPorInsumo;
        Cursor cursor1, cursor2;
        int contador = 0;

        cursor1 = db.rawQuery("SELECT * FROM " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS
                + " WHERE NOMBRE LIKE ?", new String [] {nombreInsumo});

        while (cursor1.moveToNext())
            id_insumo = Integer.valueOf(cursor1.getString(cursor1.getColumnIndex(UserContract.InsumoEntry._ID)));

        cursor1 = db.rawQuery("SELECT * FROM " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES
                + " WHERE ID_INSUMO LIKE ?", new String [] {String.valueOf(id_insumo)});

        while (cursor1.moveToNext()) {
            id_proveedor = Integer.valueOf(cursor1.getString(cursor1.getColumnIndex(
                    UserContract.InsumosProveedoresEntry.ID_PROVEEDOR)));
            cursor2 = db.rawQuery("SELECT * FROM " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES
                    + " WHERE _ID LIKE ?", new String [] {String.valueOf(id_proveedor)});
            while (cursor2.moveToNext()) {
                nombreProveedores.add(cursor2.getString(cursor2.getColumnIndex(
                        UserContract.ProveedorEntry.NOMBRE)));
            }
        }

        proveedoresPorInsumo = new String[nombreProveedores.size()];
        for(int i = 0; i < proveedoresPorInsumo.length; i++)
            proveedoresPorInsumo[i] = nombreProveedores.get(i);

        return proveedoresPorInsumo;
    }

    public String[] getInsumosPorProveedor(String nombreProveedor){
        SQLiteDatabase db = getWritableDatabase();
        int id_proveedor = 0, id_insumo;
        ArrayList<String> nombreInsumos = new ArrayList<String>();
        String[] insumosPorProveedor;
        Cursor cursor1, cursor2;
        int contador = 0;

        cursor1 = db.rawQuery("SELECT * FROM " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES
                + " WHERE NOMBRE LIKE ?", new String [] {nombreProveedor});

        while (cursor1.moveToNext())
            id_proveedor = Integer.valueOf(cursor1.getString(cursor1.getColumnIndex(UserContract.ProveedorEntry._ID)));

        cursor1 = db.rawQuery("SELECT * FROM " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES
                + " WHERE ID_PROVEEDOR LIKE ?", new String [] {String.valueOf(id_proveedor)});

        while (cursor1.moveToNext()) {
            id_insumo = Integer.valueOf(cursor1.getString(cursor1.getColumnIndex(
                    UserContract.InsumosProveedoresEntry.ID_INSUMO)));
            cursor2 = db.rawQuery("SELECT * FROM " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS
                    + " WHERE _ID LIKE ?", new String [] {String.valueOf(id_insumo)});
            while (cursor2.moveToNext()) {
                nombreInsumos.add(cursor2.getString(cursor2.getColumnIndex(
                        UserContract.InsumoEntry.NOMBRE)));
            }
        }

        insumosPorProveedor = new String[nombreInsumos.size()];
        for(int i = 0; i < insumosPorProveedor.length; i++)
            insumosPorProveedor[i] = nombreInsumos.get(i);

        return insumosPorProveedor;
    }

    public int cantidadInsumos(){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS, null).getCount();
    }

    // TODO: PROVEEDORES

    public boolean insertarProveedor(Proveedor proveedor){
        SQLiteDatabase db = getWritableDatabase();

        if(consultarProveedor(proveedor) == -1) {
            proveedor.toContentValues();
            long isInserted = db.insert(UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES, null,
                    proveedor.toContentValues());

            if (isInserted == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public boolean consultarInsumoProveedor(Insumo insumo, Proveedor proveedor){
        SQLiteDatabase db = getWritableDatabase();

        String id_insumo = String.valueOf(consultarInsumo(insumo));
        String id_proveedor = String.valueOf(consultarProveedor(proveedor));

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES
                + " WHERE ID_INSUMO LIKE ? AND ID_PROVEEDOR LIKE ?", new String [] {id_insumo, id_proveedor});

        if(cursor.getCount() >= 1) {
            return false; // Cuando ya existe el insumo asociado con el proveedor
        }
        else
            return true; // Cuando no existe el insumo asociado con el proveedor
    }

    public int consultarProveedor(Proveedor proveedor){
        SQLiteDatabase db = getWritableDatabase();
        int salida = -1;

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES
                + " WHERE NOMBRE LIKE ?", new String [] {proveedor.getNombre().trim()});

        if(cursor.getCount() >= 1) {
            while (cursor.moveToNext())
                salida = Integer.valueOf(cursor.getString(cursor.getColumnIndex(UserContract.ProveedorEntry._ID)));
            return salida;
        }
        else
            return -1;
    }

    public String[] proveedoresList(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES, null);

        String[] proveedores = new String[cantidadProveedores()];
        int index = 0;

        while (cursor.moveToNext()) {
            proveedores[index] = cursor.getString(cursor.getColumnIndex(UserContract.ProveedorEntry.NOMBRE));
            index++;
        }

        return proveedores;
    }

    public int cantidadProveedores(){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES, null).getCount();
    }

    // TODO: INSUMOSPROVEEDORES

    public boolean insertarInsumoProveedor(int idInsumo, int idProveedor){
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.execSQL("INSERT INTO " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES
                    + "(" + UserContract.InsumosProveedoresEntry.ID_INSUMO + ","
                    + UserContract.InsumosProveedoresEntry.ID_PROVEEDOR + ") VALUES ("
                    + idInsumo + "," + idProveedor + ")");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public String toStringInsumosProveedores(){
        SQLiteDatabase db = getWritableDatabase();
        String salida = "";
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES, null);
        while (cursor.moveToNext()) {
            salida += "Insumo " + cursor.getString(cursor.getColumnIndex(UserContract.InsumosProveedoresEntry.ID_INSUMO))
                + " - Proveedor " + cursor.getString(cursor.getColumnIndex(UserContract.InsumosProveedoresEntry.ID_PROVEEDOR)) + " / ";
        }
        return salida;
    }

    // TODO: LOTE

    public void insertarLote(String nombreLote, String fecha){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO " + UserContract.LoteEntry.TABLE_NAME_LOTES
                + "(" + UserContract.LoteEntry.NOMBRE + ","
                + UserContract.LoteEntry.FECHA_INICIO + ") VALUES ('"
                + nombreLote + "','" + fecha + "')");
    }

    public String[] lotesList(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.LoteEntry.TABLE_NAME_LOTES, null);

        String[] lotes = new String[cantidadLotes()];
        int index = 0;

        while (cursor.moveToNext()) {
            lotes[index] = cursor.getString(cursor.getColumnIndex(UserContract.LoteEntry.NOMBRE));
            index++;
        }

        return lotes;
    }

    public int cantidadLotes(){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + UserContract.LoteEntry.TABLE_NAME_LOTES, null).getCount();
    }

    public String toStringLotes(){
        SQLiteDatabase db = getWritableDatabase();
        String salida = "";
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.LoteEntry.TABLE_NAME_LOTES, null);
        while (cursor.moveToNext()) {
            salida += "Nombre " + cursor.getString(cursor.getColumnIndex(UserContract.LoteEntry.NOMBRE))
                    + " - Fecha " + millisToDate(Long.valueOf(cursor.getString(cursor.getColumnIndex(
                    UserContract.LoteEntry.FECHA_INICIO)))) + " / ";
        }
        return salida;
    }

    public String millisToDate(Long timeInMillis){
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" +
                calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }

    /*public String consultarUsuario(){
        SQLiteDatabase db = getWritableDatabase();

        //int users = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME
        //        + " WHERE EMAIL LIKE ? AND PASS LIKE ?", new String [] {email.trim(), pass.trim()}).getCount();

        //int users = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME
        //        + " WHERE EMAIL LIKE ?", new String [] {email.trim()}).getCount();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME, null);

        String salida = "";

        while (cursor.moveToNext()){
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.FNAME)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.LNAME)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.EMAIL)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.CEMAIL)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.CCELL)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.PASS)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.GENDER)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.BLOODT));
        }

        return salida;
    }*/

    /*public String consultarUsuario(String email, String pass){
        SQLiteDatabase db = getWritableDatabase();

        //int users = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME
        //        + " WHERE EMAIL LIKE ? AND PASS LIKE ?", new String [] {email.trim(), pass.trim()}).getCount();

        //int users = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME
        //        + " WHERE EMAIL LIKE ?", new String [] {email.trim()}).getCount();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME, null);

        String salida = "";

        while (cursor.moveToNext()){
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.FNAME)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.LNAME)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.EMAIL)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.CEMAIL)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.CCELL)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.PASS)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.GENDER)) + ", ";
            salida += cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.BLOODT));
        }

        return salida;
    }*/

    /*public String getProveedoresPorInsumo(String nombreInsumo){
        SQLiteDatabase db = getWritableDatabase();
        int id_insumo = 0, id_proveedor;
        String salida = "";
        Cursor cursor1, cursor2;
        int contador = 0;

        cursor1 = db.rawQuery("SELECT * FROM " + UserContract.InsumoEntry.TABLE_NAME_INSUMOS
                + " WHERE NOMBRE LIKE ?", new String [] {nombreInsumo});

        while (cursor1.moveToNext())
            id_insumo = Integer.valueOf(cursor1.getString(cursor1.getColumnIndex(UserContract.InsumoEntry._ID)));

        cursor1 = db.rawQuery("SELECT * FROM " + UserContract.InsumosProveedoresEntry.TABLE_NAME_INSUMOSPROVEEDORES
                + " WHERE ID_INSUMO LIKE ?", new String [] {String.valueOf(id_insumo)});

        //proveedoresPorInsumo = new String[cursor1.getCount()];

        while (cursor1.moveToNext()) {
            id_proveedor = Integer.valueOf(cursor1.getString(cursor1.getColumnIndex(
                    UserContract.InsumosProveedoresEntry.ID_PROVEEDOR)));
            cursor2 = db.rawQuery("SELECT * FROM " + UserContract.ProveedorEntry.TABLE_NAME_PROVEEDORES
                    + " WHERE _ID LIKE ?", new String [] {String.valueOf(id_proveedor)});
            while (cursor2.moveToNext()) {
                salida += cursor2.getString(cursor2.getColumnIndex(
                        UserContract.ProveedorEntry.NOMBRE)) + ", ";
            }
            contador++;
        }
        return salida;
    }*/
}
