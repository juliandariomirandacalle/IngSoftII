package com.example.juliandario.ejerciciobocadillos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    EditText edTxtVwInvBcd, edTxtVwBcdEnt, edTxtVwInvMon100, edTxtVwInvMon200, edTxtVwInvMon500;
    EditText edTxtVlr, edTxtVwMon100, edTxtVwMon200, edTxtVwMon500;
    Button btnAdmon, btnEnviar;

    int vlrBcd = 700, cantBcd = 1, sld;
    int invBcd = 20, invMon100 = 20, invMon200 = 20, invMon500 = 20, bcdEnt = 0;
    int vlr, Mon100 = 0, Mon200 = 0, Mon500 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;

        edTxtVwInvBcd = (EditText) findViewById(R.id.edTxtVwInvBcd);
        edTxtVwBcdEnt = (EditText) findViewById(R.id.edTxtVwBcdEnt);
        edTxtVwInvMon100 = (EditText) findViewById(R.id.edTxtVwInvMon100);
        edTxtVwInvMon200 = (EditText) findViewById(R.id.edTxtVwInvMon200);
        edTxtVwInvMon500 = (EditText) findViewById(R.id.edTxtVwInvMon500);
        edTxtVlr = (EditText) findViewById(R.id.edTxtVlr);
        edTxtVwMon100 = (EditText) findViewById(R.id.edTxtVwMon100);
        edTxtVwMon200 = (EditText) findViewById(R.id.edTxtVwMon200);
        edTxtVwMon500 = (EditText) findViewById(R.id.edTxtVwMon500);
        btnAdmon = (Button) findViewById(R.id.btnAdmon);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                invBcd = Integer.valueOf(bundle.getString("invBcd"));
                bcdEnt = Integer.valueOf(bundle.getString("bcdEnt"));
                invMon100 = Integer.valueOf(bundle.getString("invMon100"));
                invMon200 = Integer.valueOf(bundle.getString("invMon200"));
                invMon500 = Integer.valueOf(bundle.getString("invMon500"));
                vlrBcd = Integer.valueOf(bundle.getString("vlrBcd"));
            }
        } catch(Exception e)
        {                  }

        edTxtVwInvBcd.setText(String.valueOf(invBcd));
        edTxtVwBcdEnt.setText(String.valueOf(bcdEnt));
        edTxtVwInvMon100.setText(String.valueOf(invMon100));
        edTxtVwInvMon200.setText(String.valueOf(invMon200));
        edTxtVwInvMon500.setText(String.valueOf(invMon500));
        edTxtVwMon100.setText(String.valueOf(Mon100));
        edTxtVwMon200.setText(String.valueOf(Mon200));
        edTxtVwMon500.setText(String.valueOf(Mon500));

        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                boolean error = false;

                try {
                    vlr = Integer.valueOf(edTxtVlr.getText().toString());
                }
                catch(NumberFormatException e){
                    error = true;
                }

                if(!error) {

                    if(vlr%100 == 0 && vlr >= vlrBcd) {
                        sld = vlr - (vlrBcd - cantBcd);
                        Mon500 = sld / 500;
                        if(Mon500 <= invMon500 && invBcd > 0) {
                            Mon200 = (sld % 500) / 200;
                            Mon100 = ((sld % 500) % 200) / 100;
                            invBcd -= cantBcd;
                            invMon100 -= Mon100;
                            invMon200 -= Mon200;
                            invMon500 -= Mon500;
                            bcdEnt += cantBcd;

                            edTxtVlr.setText("");
                            edTxtVwInvBcd.setText(String.valueOf(invBcd));
                            edTxtVwBcdEnt.setText(String.valueOf(bcdEnt));
                            edTxtVwInvMon100.setText(String.valueOf(invMon100));
                            edTxtVwInvMon200.setText(String.valueOf(invMon200));
                            edTxtVwInvMon500.setText(String.valueOf(invMon500));
                            edTxtVwMon100.setText(String.valueOf(Mon100));
                            edTxtVwMon200.setText(String.valueOf(Mon200));
                            edTxtVwMon500.setText(String.valueOf(Mon500));
                            Toast.makeText(getApplicationContext(), "Compra efectuada", Toast.LENGTH_SHORT).show();
                        }
                        else if(Mon500 > invMon500)
                            Toast.makeText(getApplicationContext(), "No hay Dinero de Cambio Suficiente", Toast.LENGTH_SHORT).show();
                        else if(invBcd <= 0)
                            Toast.makeText(getApplicationContext(), "Inventario de Bocadillos Agotado", Toast.LENGTH_SHORT).show();
                    }
                    else
                        edTxtVlr.setError("Valor no Válido");
                }
                else {
                    edTxtVlr.setError("Dato Inválido");
                    Toast.makeText(getApplicationContext(), "Ingresa el Valor de Entrada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdmon.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {

                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("invBcd", String.valueOf(invBcd));
                intent.putExtra("bcdEnt", String.valueOf(bcdEnt));
                intent.putExtra("invMon100", String.valueOf(invMon100));
                intent.putExtra("invMon200", String.valueOf(invMon200));
                intent.putExtra("invMon500", String.valueOf(invMon500));
                intent.putExtra("vlrBcd", String.valueOf(vlrBcd));
                startActivity(intent);
                UserActivity.this.finish();
            }
        });
    }
}

