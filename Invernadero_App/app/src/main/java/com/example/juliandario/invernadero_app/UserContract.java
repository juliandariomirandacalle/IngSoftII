package com.example.juliandario.invernadero_app;

import android.provider.BaseColumns;

/**
 * Created by JULIAN DARIO on 28/03/2017.
 */

public final class UserContract {
    private UserContract(){
    }

    public static class UserEntry implements BaseColumns {

        // Nombre de la tabla
        public static final String TABLE_NAME = "Users";

        // Nombre de las columnas
        public static final String FNAME = "FNAME"; // First Name
        public static final String LNAME = "LNAME"; // Last Name
        public static final String EMAIL = "EMAIL"; // Email
        public static final String CELL = "CELL"; // Contact Email
        public static final String CCELL = "CCELL"; // Contact Cellphone
        public static final String PASS = "PASS"; // Password
        public static final String GENDER = "GENDER"; // Gender
        public static final String BLOODT = "BLOODT"; // Blood Type
    }
}
