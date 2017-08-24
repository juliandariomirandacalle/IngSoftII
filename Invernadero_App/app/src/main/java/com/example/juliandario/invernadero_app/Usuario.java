package com.example.juliandario.invernadero_app;

import android.content.ContentValues;

/**
 * Created by JULIAN DARIO on 20/08/2017.
 */

public class Usuario {
    private String username;
    private String pass;
    private String tipoUsuario;

    Usuario(){

    }

    Usuario(String username, String pass, String tipoUsuario){
        this. username = username;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(UserContract.UsuarioEntry.TIPOUSUARIO, this.tipoUsuario);
        values.put(UserContract.UsuarioEntry.USERNAME, this.username);
        values.put(UserContract.UsuarioEntry.PASS, this.pass);

        return values;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPass() {
        return this.pass;
    }

    public String getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
