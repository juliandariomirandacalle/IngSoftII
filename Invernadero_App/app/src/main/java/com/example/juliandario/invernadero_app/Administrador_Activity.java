package com.example.juliandario.invernadero_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Administrador_Activity extends AppCompatActivity {

    Spinner spinner_insumo_admin, spinner_proveedores_admin;
    EditText edtxt_fecha_admin, edtxt_cantidad_admin;
    Button btnEnviarAdmin, btnRegresarAdmin;
    Context context = Administrador_Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_);

        spinner_insumo_admin = (Spinner) findViewById(R.id.spinner_insumo_admin);
        spinner_proveedores_admin = (Spinner) findViewById(R.id.spinner_proveedores_admin);
        edtxt_fecha_admin = (EditText) findViewById(R.id.edtxt_fecha_admin);
        edtxt_cantidad_admin = (EditText) findViewById(R.id.edtxt_cantidad_admin);
        btnEnviarAdmin = (Button) findViewById(R.id.btnEnviarAdmin);
        btnRegresarAdmin = (Button) findViewById(R.id.btnRegresarAdmin);

        btnRegresarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Administrador_Activity.this.finish();
            }
        });
    }
}
