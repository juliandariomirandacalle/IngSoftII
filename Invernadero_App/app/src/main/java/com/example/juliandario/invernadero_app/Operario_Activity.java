package com.example.juliandario.invernadero_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Operario_Activity extends AppCompatActivity {

    Spinner spinner_insumo_op, spinner_proveedores_op;
    EditText edtxt_fecha_op, edtxt_cantidad_op;
    Button btnEnviarOp, btnRegresarOp;
    Context context = Operario_Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operario_);

        spinner_insumo_op = (Spinner) findViewById(R.id.spinner_insumo_op);
        spinner_proveedores_op = (Spinner) findViewById(R.id.spinner_proveedores_op);
        edtxt_fecha_op = (EditText) findViewById(R.id.edtxt_fecha_op);
        edtxt_cantidad_op = (EditText) findViewById(R.id.edtxt_cantidad_op);
        btnEnviarOp = (Button) findViewById(R.id.btnEnviarOp);
        btnRegresarOp = (Button) findViewById(R.id.btnRegresarOp);

        btnRegresarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Operario_Activity.this.finish();
            }
        });
    }
}
