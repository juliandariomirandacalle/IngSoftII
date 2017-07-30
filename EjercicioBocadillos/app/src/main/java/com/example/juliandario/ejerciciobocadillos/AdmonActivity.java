package com.example.juliandario.ejerciciobocadillos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdmonActivity extends AppCompatActivity {

    EditText edTxtVwInvBcdAdmon, edTxtVwBcdEntAdmon, edTxtVwInvMon100Admon, edTxtVwInvMon200Admon;
    EditText edTxtVwInvMon500Admon, edTxtVwVlrBcdAdmon;
    Button btnEnviarAdmon, btnRegresarAdmon;

    int vlrBcd, invBcd, invMon100, invMon200, invMon500, bcdEnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admon);
        final Context context = this;

        edTxtVwInvBcdAdmon = (EditText) findViewById(R.id.edTxtVwInvBcdAdmon);
        edTxtVwVlrBcdAdmon = (EditText) findViewById(R.id.edTxtVwVlrBcdAdmon);
        edTxtVwBcdEntAdmon = (EditText) findViewById(R.id.edTxtVwBcdEntAdmon);
        edTxtVwInvMon100Admon = (EditText) findViewById(R.id.edTxtVwInvMon100Admon);
        edTxtVwInvMon200Admon = (EditText) findViewById(R.id.edTxtVwInvMon200Admon);
        edTxtVwInvMon500Admon = (EditText) findViewById(R.id.edTxtVwInvMon500Admon);
        btnEnviarAdmon = (Button) findViewById(R.id.btnEnviarAdmon);
        btnRegresarAdmon = (Button) findViewById(R.id.btnRegresarAdmon);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        invBcd = Integer.valueOf(bundle.getString("invBcd"));
        bcdEnt = Integer.valueOf(bundle.getString("bcdEnt"));
        invMon100 = Integer.valueOf(bundle.getString("invMon100"));
        invMon200 = Integer.valueOf(bundle.getString("invMon200"));
        invMon500 = Integer.valueOf(bundle.getString("invMon500"));
        vlrBcd = Integer.valueOf(bundle.getString("vlrBcd"));

        edTxtVwBcdEntAdmon.setText(String.valueOf(bcdEnt));

        btnRegresarAdmon.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {

                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("invBcd", String.valueOf(invBcd));
                intent.putExtra("bcdEnt", String.valueOf(bcdEnt));
                intent.putExtra("invMon100", String.valueOf(invMon100));
                intent.putExtra("invMon200", String.valueOf(invMon200));
                intent.putExtra("invMon500", String.valueOf(invMon500));
                intent.putExtra("vlrBcd", String.valueOf(vlrBcd));
                startActivity(intent);
                AdmonActivity.this.finish();
            }
        });

        btnEnviarAdmon.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {

                String invBcdString = edTxtVwInvBcdAdmon.getText().toString();
                String vlrBcdString = edTxtVwVlrBcdAdmon.getText().toString();
                String invMon100String = edTxtVwInvMon100Admon.getText().toString();
                String invMon200String = edTxtVwInvMon200Admon.getText().toString();
                String invMon500String = edTxtVwInvMon500Admon.getText().toString();
                boolean bandera = false;

                if(invBcdString.equals("")){
                    bandera = true;
                    edTxtVwInvBcdAdmon.setError("Campo Vacío");
                }
                else{
                    try {
                        invBcd = Integer.valueOf(invBcdString);
                    }
                    catch(NumberFormatException e){
                        bandera = true;
                        edTxtVwInvBcdAdmon.setError("Valor no Válido");
                    }
                }

                if(vlrBcdString.equals("")){
                    bandera = true;
                    edTxtVwVlrBcdAdmon.setError("Campo Vacío");
                }
                else{
                    try {
                        vlrBcd = Integer.valueOf(vlrBcdString);
                        if(vlrBcd%100 != 0){
                            bandera = true;
                            edTxtVwVlrBcdAdmon.setError("Valor no Válido");
                        }
                    }
                    catch(NumberFormatException e){
                        bandera = true;
                        edTxtVwVlrBcdAdmon.setError("Valor no Válido");
                    }
                }

                if(invMon100String.equals("")){
                    bandera = true;
                    edTxtVwInvMon100Admon.setError("Campo Vacío");
                }
                else{
                    try {
                        invMon100 = Integer.valueOf(invMon100String);
                    }
                    catch(NumberFormatException e){
                        bandera = true;
                        edTxtVwInvMon100Admon.setError("Valor no Válido");
                    }
                }

                if(invMon200String.equals("")){
                    bandera = true;
                    edTxtVwInvMon200Admon.setError("Campo Vacío");
                }
                else{
                    try {
                        invMon200 = Integer.valueOf(invMon200String);
                    }
                    catch(NumberFormatException e){
                        bandera = true;
                        edTxtVwInvMon200Admon.setError("Valor no Válido");
                    }
                }

                if(invMon500String.equals("")){
                    bandera = true;
                    edTxtVwInvMon500Admon.setError("Campo Vacío");
                }
                else{
                    try {
                        invMon500 = Integer.valueOf(invMon500String);
                    }
                    catch(NumberFormatException e){
                        bandera = true;
                        edTxtVwInvMon500Admon.setError("Valor no Válido");
                    }
                }

                if(!bandera){
                    invBcd = Integer.valueOf(invBcdString);
                    vlrBcd = Integer.valueOf(vlrBcdString);
                    invMon100 = Integer.valueOf(invMon100String);
                    invMon200 = Integer.valueOf(invMon200String);
                    invMon500 = Integer.valueOf(invMon500String);

                    edTxtVwInvBcdAdmon.setText("");
                    edTxtVwVlrBcdAdmon.setText("");
                    edTxtVwInvMon100Admon.setText("");
                    edTxtVwInvMon200Admon.setText("");
                    edTxtVwInvMon500Admon.setText("");

                    Toast.makeText(getApplicationContext(), "Actualizado con Éxito", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Llena todos los Campos Requeridos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
