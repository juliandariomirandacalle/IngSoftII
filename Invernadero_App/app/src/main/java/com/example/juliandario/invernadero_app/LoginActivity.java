package com.example.juliandario.invernadero_app;

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
    Button btnLogin, btnSignUp;
    //String usuarioAdmin = "admin", passAdmin = "passadmin";
    String usuarioAdmin = "a", passAdmin = "pa";
    String usuarioOperario = "op", passOperario = "passop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context context = this;

        edTxtVwUsuario = (EditText) findViewById(R.id.edTxtVwUsuario) ;
        edTxtVwPassword = (EditText) findViewById(R.id.edTxtVwPassword) ;
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

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
                    if(usuario.equals(usuarioAdmin) && pass.equals(passAdmin)) {
                        Intent intent = new Intent(context, Administrador_Activity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                    else if(usuario.equals(usuarioOperario) && pass.equals(passOperario)) {
                        Intent intent = new Intent(context, Operario_Activity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "¡Usuario o Password no válidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SignUpActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }
}
