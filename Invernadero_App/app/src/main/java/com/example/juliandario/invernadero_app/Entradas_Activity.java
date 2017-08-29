package com.example.juliandario.invernadero_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Entradas_Activity extends AppCompatActivity {

    Spinner spinner_insumo_admin, spinner_proveedores_admin;
    EditText edtxt_fecha_admin, edtxt_cantidad_admin;
    Button btnRegresarEntradas, btnEnviarEntradas;
    Context context = Entradas_Activity.this;
    UserDBHelper usersDB;
    String[] autocompleteInsumos, autocompleteProveedores;
    String nombreInsumo;
    TimerTask timerTask;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas_);

        usersDB = new UserDBHelper(context);
        autocompleteInsumos = usersDB.insumosList();

        spinner_insumo_admin = (Spinner) findViewById(R.id.spinner_insumo_admin);
        spinner_insumo_admin.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item, autocompleteInsumos));
        spinner_proveedores_admin = (Spinner) findViewById(R.id.spinner_proveedores_admin);
        edtxt_fecha_admin = (EditText) findViewById(R.id.edtxt_fecha_admin);
        edtxt_cantidad_admin = (EditText) findViewById(R.id.edtxt_cantidad_admin);
        btnEnviarEntradas = (Button) findViewById(R.id.btnEnviarEntradas);
        btnRegresarEntradas = (Button) findViewById(R.id.btnRegresarEntradas);

        edtxt_fecha_admin.setEnabled(false);
        iniciarTimerFecha();

        spinner_insumo_admin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                nombreInsumo = String.valueOf(parent.getItemAtPosition(pos));
                autocompleteProveedores = usersDB.getProveedoresPorInsumo(nombreInsumo);
                spinner_proveedores_admin.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_dropdown_item, autocompleteProveedores));

                /*try {Toast.makeText(context, usersDB.getProveedoresPorInsumo(nombreInsumo), Toast.LENGTH_SHORT).show();}
                catch(Exception e){ Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show(); }*/
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnEnviarEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = Integer.valueOf(edtxt_cantidad_admin.getText().toString().trim());
                String insumo = String.valueOf(spinner_insumo_admin.getSelectedItem());
                String proveedor = String.valueOf(spinner_proveedores_admin.getSelectedItem());
                String fecha = String.valueOf(System.currentTimeMillis());

                try {

                    usersDB.insertarEntrada(cantidad, fecha, usersDB.consultarInsumo(new Insumo(insumo)),
                            usersDB.consultarProveedor(new Proveedor(proveedor)));
                    usersDB.updateCantidadInsumo(insumo, (usersDB.getCantidadInsumo(insumo) + cantidad));
                    Toast.makeText(context, "Insumo Actualizado con éxito!", Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegresarEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Intent intent = new Intent(context, Administrador_Activity.class);
                startActivity(intent);
                Entradas_Activity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        Intent intent = new Intent(context, Administrador_Activity.class);
        startActivity(intent);
        Entradas_Activity.this.finish();
    }

    private void iniciarTimerFecha() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        edtxt_fecha_admin.setText(new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(new Date()));
                    }
                });
            }
        };

        // Task
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
