package com.example.juliandario.invernadero_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InsumoLayout extends AppCompatActivity {

    LinearLayout linLayInsyProvee, linLayInsyProvee1, linLayProvee1, linLayIns1, layoutParcial;
    EditText txtvw_Ins1;
    Button btnAddInsyProvee1, btnRmvInsyProvee1, btnAgregarInsyProvee, btnRegresarInsyProvee;
    Spinner spinnerProveedor1;
    int contador = 1, cantidadViews = 1;
    final String linLayInsyProveeS = "linLayInsyProvee";
    final String linLayInsS = "linLayIns";
    final String linLayProveeS = "linLayProvee";
    final String txtvw_InsS = "txtvw_Ins";
    final String btnAddInsyProveeS = "btnAddInsyProvee";
    final String btnRmvInsyProveeS = "btnRmvInsyProvee";
    final String spinnerProveedorS = "spinnerProveedor";
    ArrayList<LinearLayout> linearLayoutsArray = new ArrayList<LinearLayout>();
    Context context = InsumoLayout.this;

    Typeface typefaceEditText, typefaceButton, typefaceSpinner;
    ViewGroup.LayoutParams layparamsLinLayInsyProvee, layparamsLinLayProvee, layparamsLinLayIns,
            layparamsEditText, layparamsButton, layparamsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumo_layout);

        linLayInsyProvee = (LinearLayout) findViewById(R.id.linLayInsyProvee);
        linLayInsyProvee1 = (LinearLayout) findViewById(R.id.linLayInsyProvee1);
        linLayProvee1 = (LinearLayout) findViewById(R.id.linLayProvee1);
        linLayIns1 = (LinearLayout) findViewById(R.id.linLayIns1);

        btnAddInsyProvee1 = (Button) findViewById(R.id.btnAddInsyProvee1);
        btnRmvInsyProvee1 = (Button) findViewById(R.id.btnRmvInsyProvee1);
        txtvw_Ins1 = (EditText) findViewById(R.id.txtvw_Ins1);
        spinnerProveedor1 = (Spinner) findViewById(R.id.spinnerProveedor1);

        linearLayoutsArray.add(linLayInsyProvee1);

        btnAgregarInsyProvee = (Button) findViewById(R.id.btnAgregarInsyProvee);
        btnRegresarInsyProvee = (Button) findViewById(R.id.btnRegresarInsyProvee);

        typefaceEditText = txtvw_Ins1.getTypeface();
        typefaceButton = btnAddInsyProvee1.getTypeface();

        layparamsLinLayInsyProvee = linLayInsyProvee1.getLayoutParams();
        layparamsLinLayProvee = linLayProvee1.getLayoutParams();
        layparamsLinLayIns = linLayIns1.getLayoutParams();
        layparamsEditText = txtvw_Ins1.getLayoutParams();
        layparamsButton = btnAddInsyProvee1.getLayoutParams();
        layparamsSpinner = spinnerProveedor1.getLayoutParams();

        btnAgregarInsyProvee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getInsumosProveedor() == null){ }
                else {
                    //TODO: Almacenar insumos en DB
                    Intent intent = new Intent(context, Administrador_Activity.class);
                    startActivity(intent);
                    InsumoLayout.this.finish();
                }
            }
        });

        btnRegresarInsyProvee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Administrador_Activity.class);
                startActivity(intent);
                InsumoLayout.this.finish();
            }
        });

        btnAddInsyProvee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInsumo(v);
            }
        });

        btnRmvInsyProvee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rmvInsumo(v);
            }
        });
    }

    public void addInsumo(View v){
        try {
            boolean emptyEdttxt = false;
            for (int i = 0; i < ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 1))).getChildCount(); i++) {
                View v1L = ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 1))).getChildAt(i);
                if(v1L.getTag().toString().contains(linLayInsS)) {
                    for (int ii = 0; ii < ((LinearLayout)v1L).getChildCount(); ii++) {
                        View v1Ed = ((LinearLayout)v1L).getChildAt(ii);
                        if (v1Ed instanceof EditText) {
                            if (((EditText) v1Ed).getText().toString().trim().length() == 0) {
                                emptyEdttxt = true;
                                ((EditText) v1Ed).setError("Campo Vacío");
                                break;
                            }
                        }
                    }
                }
            }

           if(!emptyEdttxt) {
                v.setVisibility(View.INVISIBLE);
                //((LinearLayout)(v.getParent())).removeView(v);

                contador++;
                cantidadViews++;

                LinearLayout linLayInsyProveeP = new LinearLayout(InsumoLayout.this);
                linLayInsyProveeP.setTag(linLayInsyProveeS + contador);
                linLayInsyProveeP.setLayoutParams(layparamsLinLayInsyProvee);
                linLayInsyProveeP.setOrientation(LinearLayout.VERTICAL);

                LinearLayout linLayIns = new LinearLayout(InsumoLayout.this);
                linLayIns.setTag(linLayInsS + contador);
                linLayIns.setLayoutParams(layparamsLinLayIns);
                linLayIns.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout linLayProvee = new LinearLayout(InsumoLayout.this);
                linLayProvee.setTag(linLayProveeS + contador);
                linLayProvee.setLayoutParams(layparamsLinLayProvee);
                linLayProvee.setBackgroundResource(R.drawable.border_file);
                linLayProvee.setOrientation(LinearLayout.VERTICAL);

                EditText editText = new EditText(InsumoLayout.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setTag(txtvw_InsS + contador);
                editText.setHint(R.string.nombre_insumo);
                editText.setTypeface(typefaceEditText);
                editText.setLayoutParams(layparamsEditText);
                linLayIns.addView(editText);

                Button button1 = new Button(InsumoLayout.this);
                button1.setTag(btnAddInsyProveeS + contador);
                button1.setText("+");
                button1.setGravity(Gravity.CENTER);
                button1.setTypeface(typefaceButton);
                button1.setLayoutParams(layparamsButton);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addInsumo(v);
                    }
                });

                linLayIns.addView(button1);

                Button button2 = new Button(InsumoLayout.this);
                button2.setTag(btnRmvInsyProveeS + contador);
                button2.setText("-");
                button2.setGravity(Gravity.CENTER);
                button2.setTypeface(typefaceButton);
                button2.setLayoutParams(layparamsButton);

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rmvInsumo(v);
                    }
                });

                linLayIns.addView(button2);

                Spinner spinner = new Spinner(InsumoLayout.this);
                spinner.setTag(spinnerProveedorS + contador);
                spinner.setLayoutParams(layparamsSpinner);
                linLayProvee.addView(spinner);

                linLayInsyProveeP.addView(linLayIns);
                linLayInsyProveeP.addView(linLayProvee);

                linLayInsyProvee.addView(linLayInsyProveeP);
                linearLayoutsArray.add(linLayInsyProveeP);
            }
        }
        catch(Exception e){}
    }

    public void rmvInsumo(View v){
        if(cantidadViews == 1){}
        else{
            String numberView = v.getTag().toString().substring(btnRmvInsyProveeS.length(),
                    v.getTag().toString().length());

            if(numberView.equals(String.valueOf(contador))){
                layoutParcial = ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 2)));
                for (int i = 0; i < layoutParcial.getChildCount(); i++) {
                    View v1L = layoutParcial.getChildAt(i);
                    if(v1L.getTag().toString().contains(linLayInsS)) {
                        for (int ii = 0; ii < ((LinearLayout)v1L).getChildCount(); ii++) {
                            View v1Ed = ((LinearLayout) v1L).getChildAt(ii);
                            if (v1Ed instanceof Button) {
                                if (v1Ed.getTag().toString().contains("Add")) {
                                    v1Ed.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                    }
                }
                contador = Integer.valueOf(layoutParcial.getTag().toString().substring(linLayInsyProveeS.length(),
                        layoutParcial.getTag().toString().length()));
            }

            linearLayoutsArray.remove(((LinearLayout)(v.getParent().getParent())));
            linLayInsyProvee.removeView(((LinearLayout)(v.getParent().getParent())));
            cantidadViews--;
        }
    }

    public String[] getInsumosProveedor(){
        return null;
    }
}

/*Toast.makeText(context, ((LinearLayout)(v.getParent().getParent())).getTag().toString()
        + " numb: " + numberView, Toast.LENGTH_LONG).show();*/