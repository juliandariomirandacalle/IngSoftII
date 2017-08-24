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
    Usuario usuario;
    UserDBHelper usersDB;
    final Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = new Usuario();
        usersDB = new UserDBHelper(context);
        //inizializarDB(usersDB);

        edTxtVwUsuario = (EditText) findViewById(R.id.edTxtVwUsuario) ;
        edTxtVwPassword = (EditText) findViewById(R.id.edTxtVwPassword) ;
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                usuario.setUsername(edTxtVwUsuario.getText().toString());
                usuario.setPass(edTxtVwPassword.getText().toString());
                boolean bandera = false;

                if(usuario.getUsername().equals("")){
                    bandera = true;
                    edTxtVwUsuario.setError("Campo Vacío");
                }
                if(usuario.getPass().equals("")){
                    bandera = true;
                    edTxtVwPassword.setError("Campo Vacío");
                }

                if(!bandera)
                {
                    try {
                        String tipoUsuario = usersDB.consultarUsuario(usuario.getUsername(), usuario.getPass());

                        if(tipoUsuario == null)
                            Toast.makeText(getApplicationContext(), "¡Usuario o Password no válidos!", Toast.LENGTH_SHORT).show();
                        else {
                            if(tipoUsuario.equals("AD")){
                                Intent intent = new Intent(context, Administrador_Activity.class);
                                startActivity(intent);
                                LoginActivity.this.finish();
                            }
                            else if(tipoUsuario.equals("OP")){
                                Intent intent = new Intent(context, Operario_Activity.class);
                                startActivity(intent);
                                LoginActivity.this.finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "¡Usuario o Password no válidos!", Toast.LENGTH_SHORT).show();
                        }
                    } catch(Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
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

    public static void inizializarDB(UserDBHelper usersDB){
        String usuarioAdmin = "a", passAdmin = "pa";
        String usuarioOperario = "op", passOperario = "passop";
        usersDB.clearDB();
        //usersDB.deleteDB();
        usersDB.insertarUsuario(new Usuario(usuarioAdmin, passAdmin, "AD"));
        usersDB.insertarUsuario(new Usuario(usuarioOperario, passOperario, "OP"));
    }
}

        /*if(!bandera)
        {
            if(usuario.getUsername().equals(usuarioAdmin) && usuario.getPass().equals(passAdmin)) {
            Intent intent = new Intent(context, Administrador_Activity.class);
            startActivity(intent);
            LoginActivity.this.finish();
            }
            else if(usuario.getUsername().equals(usuarioOperario) && usuario.getPass().equals(passOperario)) {
            Intent intent = new Intent(context, Operario_Activity.class);
            startActivity(intent);
            LoginActivity.this.finish();
            }
            else
            Toast.makeText(getApplicationContext(), "¡Usuario o Password no válidos!", Toast.LENGTH_SHORT).show();
            }
        }*/