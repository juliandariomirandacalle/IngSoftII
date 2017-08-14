package com.example.juliandario.invernadero_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JULIAN DARIO on 26/03/2017.
 */

public class UserDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TELLURICO.db";
    public static final int DATABASE_VERSION = 1;

    // COnsulta para eliminar la tabla si existe
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + "("
                + UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserContract.UserEntry.FNAME + " TEXT NOT NULL,"
                + UserContract.UserEntry.LNAME + " TEXT NOT NULL,"
                + UserContract.UserEntry.EMAIL + " TEXT NOT NULL,"
                + UserContract.UserEntry.CELL + " TEXT NULL,"
                + UserContract.UserEntry.CCELL + " TEXT  NOT NULL,"
                + UserContract.UserEntry.PASS + " TEXT  NOT NULL,"
                + UserContract.UserEntry.GENDER + " TEXT  NOT NULL,"
                + UserContract.UserEntry.BLOODT + " TEXT  NOT NULL,"
                + "UNIQUE (" + UserContract.UserEntry.EMAIL + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }

    public boolean insertarUsuario(User user){
        SQLiteDatabase db = getWritableDatabase();
        clearDB();

        long isInserted = db.insert(UserContract.UserEntry.TABLE_NAME, null, user.toContentValues());

        if(isInserted == -1)
            return false;
        else
            return true;
    }

    public boolean consultarUsuario(String email, String pass){
        SQLiteDatabase db = getWritableDatabase();

        int users = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME
                + " WHERE EMAIL LIKE ? AND PASS LIKE ?", new String [] {email.trim(), pass.trim()}).getCount();

        if(users >= 1)
            return true;
        else
            return false;
    }

    public String getUserEmail(){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME, null);

        String salida = "";

        while (cursor.moveToNext())
            salida = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.EMAIL));

        return salida;
    }

    public boolean chechUser(){
        SQLiteDatabase db = getWritableDatabase();
        int count = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME, null).getCount();

        if(count >= 1)
            return true;
        else
            return false;
    }

    public void clearDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void deleteDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void newDB(){
        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);
    }

    public boolean dbExists(){
        try{
            SQLiteDatabase db = getWritableDatabase();
            int val = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME, null).getCount();
            return true;
        } catch(Exception e){
            return false;
        }
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
}
