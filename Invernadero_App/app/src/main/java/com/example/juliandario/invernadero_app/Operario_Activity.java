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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Operario_Activity extends AppCompatActivity {

    Spinner spinner_insumo_op, spinner_lotes_op;
    EditText edtxt_fecha_op, edtxt_cantidad_op;
    TextView txtVwCantidad;
    Button btnEnviarOp, btnRegresarOp;
    Context context = Operario_Activity.this;
    UserDBHelper usersDB;
    TimerTask timerTask;
    Timer timer;
    String[] insumosList, lotesList;
    String nombreInsumo;
    int cantidadInsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_);

        usersDB = new UserDBHelper(context);

        insumosList = usersDB.insumosList();
        lotesList = usersDB.lotesList();

        spinner_insumo_op = (Spinner) findViewById(R.id.spinner_insumo_op);
        spinner_insumo_op.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item, insumosList));
        spinner_lotes_op = (Spinner) findViewById(R.id.spinner_lotes_op);
        spinner_lotes_op.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item, lotesList));
        edtxt_fecha_op = (EditText) findViewById(R.id.edtxt_fecha_op);
        edtxt_cantidad_op = (EditText) findViewById(R.id.edtxt_cantidad_op);
        btnEnviarOp = (Button) findViewById(R.id.btnEnviarOp);
        btnRegresarOp = (Button) findViewById(R.id.btnRegresarOp);
        txtVwCantidad = (TextView) findViewById(R.id.txtVwCantidad);

        edtxt_fecha_op.setEnabled(false);
        iniciarTimerFecha();

        spinner_insumo_op.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                nombreInsumo = String.valueOf(parent.getItemAtPosition(pos));
                cantidadInsumo = usersDB.getCantidadInsumo(nombreInsumo);
                txtVwCantidad.setText("Cantidad disponible: " + cantidadInsumo + " Kg");
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEnviarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = Integer.valueOf(edtxt_cantidad_op.getText().toString().trim());
                String insumo = String.valueOf(spinner_insumo_op.getSelectedItem());
                String lote = String.valueOf(spinner_lotes_op.getSelectedItem());
                String fecha = String.valueOf(System.currentTimeMillis());

                try {
                    if(cantidad <= cantidadInsumo) {
                        usersDB.insertarSalida(cantidad, fecha, usersDB.consultarInsumo(new Insumo(insumo)),
                                usersDB.consultarLote(lote));
                        usersDB.updateCantidadInsumo(insumo, (usersDB.getCantidadInsumo(insumo) - cantidad));
                        Toast.makeText(context, "Insumo Actualizado con éxito!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        edtxt_cantidad_op.setError("La cantidad supera el inventario de " + insumo);
                }
                catch(Exception e){ Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show(); }
            }
        });

        btnRegresarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Operario_Activity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        Operario_Activity.this.finish();
    }

    private void iniciarTimerFecha() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        edtxt_fecha_op.setText(new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(new Date()));
                    }
                });
            }
        };

        // Task
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
