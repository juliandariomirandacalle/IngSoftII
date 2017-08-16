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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ProveedorLayout extends AppCompatActivity {

    LinearLayout linerLayoutInsumos, linerLayoutInsumo1, layoutParcial ;
    EditText txtvw_Proveedor, txtvw_Insumo1;
    Button btnAddInsumo1, btnRmvInsumo1, btnAgregarProveedor, btnRegresarProveedor;
    int contador = 1, cantidadViews = 1;
    final String linerLayoutInsumo = "linerLayoutInsumo";
    final String txtvw_Insumo = "txtvw_Insumo";
    final String btnAddInsumo = "btnAddInsumo";
    final String btnRmvInsumo = "btnRmvInsumo";
    ArrayList<LinearLayout> linearLayoutsArray = new ArrayList<LinearLayout>();
    Context context = ProveedorLayout.this;

    Typeface typefaceEditText, typefaceButton;
    ViewGroup.LayoutParams layoutparamsLinearLayout, layoutparamsEditText, layoutparamsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_layout);

        linerLayoutInsumos = (LinearLayout) findViewById(R.id.linerLayoutInsumos);
        linerLayoutInsumo1 = (LinearLayout) findViewById(R.id.linerLayoutInsumo1);
        btnAddInsumo1 = (Button) findViewById(R.id.btnAddInsumo1);
        btnRmvInsumo1 = (Button) findViewById(R.id.btnRmvInsumo1);
        txtvw_Proveedor = (EditText) findViewById(R.id.txtvw_Proveedor);
        txtvw_Insumo1 = (EditText) findViewById(R.id.txtvw_Insumo1);
        linearLayoutsArray.add(linerLayoutInsumo1);

        btnAgregarProveedor = (Button) findViewById(R.id.btnAgregarProveedor);
        btnRegresarProveedor = (Button) findViewById(R.id.btnRegresarProveedor);

        typefaceEditText = txtvw_Insumo1.getTypeface();
        typefaceButton = btnAddInsumo1.getTypeface();
        layoutparamsLinearLayout = linerLayoutInsumo1.getLayoutParams();
        layoutparamsEditText = txtvw_Insumo1.getLayoutParams();
        layoutparamsButton = btnAddInsumo1.getLayoutParams();

        btnAgregarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proveedor = txtvw_Proveedor.getText().toString();
                String[] insumos = getInsumosProveedor();
                if(proveedor.length() == 0)
                    txtvw_Proveedor.setError("Campo Vacío");
                else if(insumos == null){ }
                else {
                    //TODO: Almacenar insumos en DB
                    Intent intent = new Intent(context, Administrador_Activity.class);
                    startActivity(intent);
                    ProveedorLayout.this.finish();
                }
            }
        });

        btnRegresarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Administrador_Activity.class);
                startActivity(intent);
                ProveedorLayout.this.finish();
            }
        });

        btnAddInsumo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInsumo(v);
            }
        });

        btnRmvInsumo1.setOnClickListener(new View.OnClickListener() {
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
                View v1 = ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 1))).getChildAt(i);
                if (v1 instanceof EditText) {
                    if(((EditText) v1).getText().toString().trim().length() == 0){
                        emptyEdttxt = true;
                        ((EditText) v1).setError("Campo Vacío");
                        break;
                    }
                }
            }

            if(!emptyEdttxt) {
                v.setVisibility(View.INVISIBLE);
                //((LinearLayout)(v.getParent())).removeView(v);

                contador++;
                cantidadViews++;

                LinearLayout linearLayout = new LinearLayout(ProveedorLayout.this);
                linearLayout.setTag(linerLayoutInsumo + contador);

                linearLayout.setLayoutParams(layoutparamsLinearLayout);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                EditText editText = new EditText(ProveedorLayout.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setTag(txtvw_Insumo + contador);
                editText.setHint(R.string.nombre_insumo);
                editText.setTypeface(typefaceEditText);
                editText.setLayoutParams(layoutparamsEditText);
                linearLayout.addView(editText);

                Button button1 = new Button(ProveedorLayout.this);
                button1.setTag(btnAddInsumo + contador);
                button1.setText("+");
                button1.setGravity(Gravity.CENTER);
                button1.setTypeface(typefaceButton);
                button1.setLayoutParams(layoutparamsButton);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addInsumo(v);
                    }
                });

                linearLayout.addView(button1);

                //lp2.weight = (float) 0.2;
                Button button2 = new Button(ProveedorLayout.this);
                button2.setTag(btnRmvInsumo + contador);
                button2.setText("-");
                button2.setGravity(Gravity.CENTER);
                button2.setTypeface(typefaceButton);
                button2.setLayoutParams(layoutparamsButton);

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rmvInsumo(v);
                    }
                });

                linearLayout.addView(button2);

                linerLayoutInsumos.addView(linearLayout);
                linearLayoutsArray.add(linearLayout);
            }
        }
        catch(Exception e){}
    }

    public void rmvInsumo(View v){
        if(cantidadViews == 1){}
        else{
            String numberView = v.getTag().toString().substring(btnRmvInsumo.length(),
                    v.getTag().toString().length());

            if(numberView.equals(String.valueOf(contador))){
                layoutParcial = ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 2)));
                for (int i = 0; i < layoutParcial.getChildCount(); i++) {
                    View v1 = layoutParcial.getChildAt(i);
                    if (v1 instanceof Button) {
                        if(v1.getTag().toString().contains("Add")) {
                            v1.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                }
                contador = Integer.valueOf(layoutParcial.getTag().toString().substring(linerLayoutInsumo.length(),
                        layoutParcial.getTag().toString().length()));
            }

            linearLayoutsArray.remove(((LinearLayout)(v.getParent())));
            linerLayoutInsumos.removeView(((LinearLayout)(v.getParent())));
            cantidadViews--;
        }
    }

    public String[] getInsumosProveedor(){
        String[] insumos = new String[linearLayoutsArray.size()];
        boolean campoVacio = false;

        for (LinearLayout layout : linearLayoutsArray) {
            for (int i = 0; i < layout.getChildCount(); i++) {
                View v1 = layout.getChildAt(i);
                if (v1 instanceof EditText) {
                    if (((EditText) v1).getText().toString().length() == 0) {
                        ((EditText) v1).setError("Campo Vacío");
                        campoVacio = true;
                        break;
                    }
                    else
                        insumos[i] = ((EditText) v1).getText().toString();
                }
            }
        }

        if(campoVacio)
            return null;
        else
            return insumos;
    }
}