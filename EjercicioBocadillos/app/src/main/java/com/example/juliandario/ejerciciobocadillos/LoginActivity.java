package com.example.juliandario.ejerciciobocadillos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edTxtVwUsuario, edTxtVwPassword;
    Button btnRegresarLogin, btnLogin;
    int vlrBcd, invBcd, invMon100, invMon200, invMon500, bcdEnt;
    String usuarioValido = "usuario", passValido = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context context = this;

        edTxtVwUsuario = (EditText) findViewById(R.id.edTxtVwUsuario) ;
        edTxtVwPassword = (EditText) findViewById(R.id.edTxtVwPassword) ;
        btnRegresarLogin = (Button) findViewById(R.id.btnRegresarLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        invBcd = Integer.valueOf(bundle.getString("invBcd"));
        bcdEnt = Integer.valueOf(bundle.getString("bcdEnt"));
        invMon100 = Integer.valueOf(bundle.getString("invMon100"));
        invMon200 = Integer.valueOf(bundle.getString("invMon200"));
        invMon500 = Integer.valueOf(bundle.getString("invMon500"));
        vlrBcd = Integer.valueOf(bundle.getString("vlrBcd"));

        btnRegresarLogin.setOnClickListener(new View.OnClickListener()
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
                LoginActivity.this.finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {

                String usuario = edTxtVwUsuario.getText().toString();
                String pass = edTxtVwPassword.getText().toString();
                boolean bandera = false;

                if(usuario.equals("")){
                    bandera = true;
                    edTxtVwUsuario.setError("Campo Vacío");
                }
                if(pass.equals("")){
                    bandera = true;
                    edTxtVwPassword.setError("Campo Vacío");
                }

                if(!bandera)
                {
                    if(usuario.equals(usuarioValido) && pass.equals(passValido)) {
                        Intent intent = new Intent(context, AdmonActivity.class);
                        intent.putExtra("invBcd", String.valueOf(invBcd));
                        intent.putExtra("bcdEnt", String.valueOf(bcdEnt));
                        intent.putExtra("invMon100", String.valueOf(invMon100));
                        intent.putExtra("invMon200", String.valueOf(invMon200));
                        intent.putExtra("invMon500", String.valueOf(invMon500));
                        intent.putExtra("vlrBcd", String.valueOf(vlrBcd));
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "¡Usuario o Password no válidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
