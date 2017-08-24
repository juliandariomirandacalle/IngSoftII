package com.example.juliandario.invernadero_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Administrador_Activity extends AppCompatActivity {

    Button btnRegresarAdmin, btnAgregarLoteAdmin, btnAgregarInsAdmin, btnAgregarProveAdmin, btnIngresarEntrada;
    Button btnInformeEntradas, btnInformeSalidas, btnInformeProvee, btnInformeLotes;
    Context context = Administrador_Activity.this;
    UserDBHelper usersDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_);
        usersDB = new UserDBHelper(context);

        btnRegresarAdmin = (Button) findViewById(R.id.btnRegresarAdmin);
        btnAgregarLoteAdmin = (Button) findViewById(R.id.btnAgregarLoteAdmin);
        btnAgregarInsAdmin = (Button) findViewById(R.id.btnAgregarInsAdmin);
        btnAgregarProveAdmin = (Button) findViewById(R.id.btnAgregarProveAdmin);
        btnIngresarEntrada = (Button) findViewById(R.id.btnIngresarEntrada);

        btnInformeEntradas = (Button) findViewById(R.id.btnInformeEntradas);
        btnInformeSalidas = (Button) findViewById(R.id.btnInformeSalidas);
        btnInformeProvee = (Button) findViewById(R.id.btnInformeProvee);
        btnInformeLotes = (Button) findViewById(R.id.btnInformeLotes);

        btnIngresarEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Entradas_Activity.class);
                startActivity(intent);
                Administrador_Activity.this.finish();
            }
        });

        btnRegresarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                Administrador_Activity.this.finish();
            }
        });

        btnAgregarLoteAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Administrador_Activity.this);
                alertDialog.setTitle("LOTES A ADICIONAR");
                alertDialog.setMessage("Inserte la cantidad de lotes");

                final EditText input = new EditText(Administrador_Activity.this);
                input.setGravity(Gravity.CENTER_HORIZONTAL);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)}); // Max Length 4 Characters

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                //alertDialog.setIcon(R.drawable.key);

                alertDialog.setPositiveButton("Adicionar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(input.getText().toString().trim().equals(""))
                                    input.setError("Campo Vacío");
                                else {
                                    int lote = Integer.valueOf(input.getText().toString());
                                    int inicio = usersDB.cantidadLotes();
                                    for(int i = inicio + 1; i <= (lote + inicio); i++) {
                                        try{usersDB.insertarLote(String.valueOf("Lote" + i), String.valueOf(System.currentTimeMillis()));}
                                        catch(Exception e){Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();}
                                    }
                                    Toast.makeText(context, "Lotes insertados con éxito!", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    /*String fecha = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").
                                            format(new Date(System.currentTimeMillis()));*/
                                }
                            }
                        });

                alertDialog.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
        });

        btnAgregarInsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InsumoLayout.class);
                startActivity(intent);
                Administrador_Activity.this.finish();
            }
        });

        btnAgregarProveAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProveedorLayout.class);
                startActivity(intent);
                Administrador_Activity.this.finish();
            }
        });

        btnInformeLotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, usersDB.toStringLotes(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        Administrador_Activity.this.finish();
    }

    public String millisToDate(Long timeInMillis){
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" +
                calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }
}

                /*final EditText input = new EditText(Administrador_Activity.this);
                input.setGravity(Gravity.CENTER_HORIZONTAL);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(30)}); // Max Length 20 Characters

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);*/

                /*alertDialog.setPositiveButton("Adicionar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //String proveedor = input.getText().toString();
                                // Add proveedor to DB
                            }
                        });

                alertDialog.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });*/

/*AlertDialog.Builder alertDialog = new AlertDialog.Builder(Administrador_Activity.this);
                alertDialog.setTitle("LOTES A ADICIONAR");
                alertDialog.setMessage("Inserte la cantidad de lotes");

                final EditText input = new EditText(Administrador_Activity.this);
                input.setGravity(Gravity.CENTER_HORIZONTAL);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)}); // Max Length 4 Characters

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                //alertDialog.setIcon(R.drawable.key);

                alertDialog.setPositiveButton("Adicionar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                int lote = Integer.valueOf(input.getText().toString());
                                // Add lote to DB
                                dialog.cancel();
                            }
                        });

                alertDialog.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Administrador_Activity.this);
                LayoutInflater inflater = Administrador_Activity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_proveedor_layout, null);
                dialogBuilder.setView(dialogView);

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                v.invalidate();*/


    /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(Administrador_Activity.this);
alertDialog.setTitle("INSUMO A ADICIONAR");
        alertDialog.setMessage("Escriba el nombre del insumo");

final EditText input = new EditText(Administrador_Activity.this);
        input.setGravity(Gravity.CENTER_HORIZONTAL);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(30)}); // Max Length 20 Characters

final Spinner spinner = new Spinner(Administrador_Activity.this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout linearSp = new LinearLayout(Administrador_Activity.this);
        linearSp.setBackgroundResource(R.drawable.border_file);
        linearSp.addView(spinner);

        spinner.setLayoutParams(lp);
        input.setLayoutParams(lp);

        LinearLayout linearGeneral = new LinearLayout(Administrador_Activity.this);
        linearGeneral.setOrientation(LinearLayout.VERTICAL);
        linearGeneral.addView(input);
        linearGeneral.addView(linearSp);

        alertDialog.setView(linearGeneral);
        //alertDialog.setIcon(R.drawable.key);

        alertDialog.setPositiveButton("Adicionar",
        new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog, int which) {
        String proveedor = input.getText().toString();
        // TODO: Add insumo to DB
        dialog.cancel();
        }
        });

        alertDialog.setNegativeButton("Cancelar",
        new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
        }
        });

        alertDialog.show();*/