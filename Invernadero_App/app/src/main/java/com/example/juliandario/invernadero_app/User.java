package com.example.juliandario.invernadero_app;

import android.content.ContentValues;

/**
 * Created by JULIAN DARIO on 10/03/2017.
 */

public class User {
    String firstName;
    String lastName;
    String email;
    String cellphone;
    String contactCellphone;
    String password;
    String bloodType;
    String gender;

    public User(String firstName, String lastName, String email, String cellphone, String gender, String bloodType, String contactCellphone, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cellphone = cellphone;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contactCellphone = contactCellphone;
        this.password = password;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    // Método para agrupar los datos de la clase en pares de columnas-valor
    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        values.put(UserContract.UserEntry.FNAME, firstName);
        values.put(UserContract.UserEntry.LNAME, lastName);
        values.put(UserContract.UserEntry.EMAIL, email);
        values.put(UserContract.UserEntry.CELL, cellphone);
        values.put(UserContract.UserEntry.CCELL, contactCellphone);
        values.put(UserContract.UserEntry.PASS, password);
        values.put(UserContract.UserEntry.GENDER, gender);
        values.put(UserContract.UserEntry.BLOODT, bloodType);

        return values;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
