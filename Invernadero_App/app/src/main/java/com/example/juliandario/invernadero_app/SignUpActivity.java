package com.example.juliandario.invernadero_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    AutoCompleteTextView txtvwFirstName;
    EditText txtvwPassword, txtvwConfirmPassword;
    Button btnRegistrarSignUp, btnRegresarSignUp;
    final Context context = this;
    UserDBHelper usersDB;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usersDB = new UserDBHelper(context);

        txtvwFirstName = (AutoCompleteTextView) findViewById(R.id.txtvwFirstName);
        txtvwPassword = (EditText) findViewById(R.id.txtvwPassword);
        txtvwConfirmPassword = (EditText) findViewById(R.id.txtvwConfirmPassword);
        btnRegistrarSignUp = (Button) findViewById(R.id.btnRegistrarSignUp);
        btnRegresarSignUp = (Button) findViewById(R.id.btnRegresarSignUp);

        btnRegistrarSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        btnRegresarSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                SignUpActivity.this.finish();
            }
        });
    }

    public void validateData(){
        String name = txtvwFirstName.getText().toString();
        String pass = txtvwPassword.getText().toString();
        String cPass = txtvwConfirmPassword.getText().toString();
        boolean empty = false;

        if(name.length() == 0 || pass.length() == 0 || cPass.length() == 0){
            if(name.length() == 0)
                txtvwFirstName.setError("Campo Vacío");
            if(pass.length() == 0)
                txtvwFirstName.setError("Campo Vacío");
            if(cPass.length() == 0)
                txtvwFirstName.setError("Campo Vacío");
        }
        else{
            if(!pass.equals(cPass))
                Toast.makeText(context, "Contraseñas no coniciden", Toast.LENGTH_SHORT).show();
            else {
                if(usersDB.insertarUsuario(new Usuario(name, pass, "OP"))) {
                    Toast.makeText(context, "Usuario insertado con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    SignUpActivity.this.finish();
                }
            }
        }
    }
}
