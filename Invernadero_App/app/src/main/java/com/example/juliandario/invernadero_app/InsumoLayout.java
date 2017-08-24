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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class InsumoLayout extends AppCompatActivity {

    LinearLayout linLayInsyProvee, linLayInsyProvee1, linLayProvee1, linLayIns1, layoutParcial;
    AutoCompleteTextView txtvw_Ins1;
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
    String[] autocompleteInsumos, autocompleteProveedores;
    UserDBHelper usersDB;
    String nombreProveedor;

    Typeface typefaceEditText, typefaceButton, typefaceSpinner;
    ViewGroup.LayoutParams layparamsLinLayInsyProvee, layparamsLinLayProvee, layparamsLinLayIns,
            layparamsEditText, layparamsButton, layparamsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumo_layout);

        usersDB = new UserDBHelper(context);
        autocompleteInsumos = usersDB.insumosList();
        autocompleteProveedores = usersDB.proveedoresList();

        linLayInsyProvee = (LinearLayout) findViewById(R.id.linLayInsyProvee);
        linLayInsyProvee1 = (LinearLayout) findViewById(R.id.linLayInsyProvee1);
        linLayProvee1 = (LinearLayout) findViewById(R.id.linLayProvee1);
        linLayIns1 = (LinearLayout) findViewById(R.id.linLayIns1);

        btnAddInsyProvee1 = (Button) findViewById(R.id.btnAddInsyProvee1);
        btnRmvInsyProvee1 = (Button) findViewById(R.id.btnRmvInsyProvee1);
        txtvw_Ins1 = (AutoCompleteTextView) findViewById(R.id.txtvw_Ins1);
        /*txtvw_Ins1.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, autocompleteInsumos));*/
        spinnerProveedor1 = (Spinner) findViewById(R.id.spinnerProveedor1);
        spinnerProveedor1.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item, autocompleteProveedores));

        spinnerProveedor1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                nombreProveedor = String.valueOf(parent.getItemAtPosition(pos));
                autocompleteInsumos = usersDB.getInsumosPorProveedor(nombreProveedor);
                txtvw_Ins1.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_dropdown_item_1line, autocompleteInsumos));
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                ArrayList<InsumoProveedor> array;
                String cadena = "";
                boolean valid = true;

                if(verificarCampos()){ }
                else {
                    try {
                        array = getInsumosProveedor();
                        for (InsumoProveedor insumoProveedor : array) {
                            /*cadena += insumoProveedor.getInusmo().getNombre() + "/" +
                                    insumoProveedor.getProveedor().getNombre() + " - ";*/
                            valid &= usersDB.insertarInsumo(insumoProveedor.getInusmo(), insumoProveedor.getProveedor());
                        }
                        if(valid)
                            Toast.makeText(context, "Insumos insertados con éxito!", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, cadena, Toast.LENGTH_LONG).show();
                    } catch (Exception e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    /*Intent intent = new Intent(context, Administrador_Activity.class);
                    startActivity(intent);
                    InsumoLayout.this.finish();*/
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

    public boolean verificarCampos(){
        boolean emptyEdttxt = false;

        for (int i = 0; i < linearLayoutsArray.size(); i++) {
            LinearLayout v1L = (LinearLayout)(linearLayoutsArray.get(i));
            for(int ii = 0; ii < v1L.getChildCount(); ii++){
                LinearLayout v2L = (LinearLayout)v1L.getChildAt(ii);
                if(v2L.getTag().toString().contains(linLayInsS)) {
                    for (int iii = 0; iii < v2L.getChildCount(); iii++) {
                        View v1Ed = ((LinearLayout) v2L).getChildAt(iii);
                        if (v1Ed instanceof AutoCompleteTextView) {
                            if (((AutoCompleteTextView) v1Ed).getText().toString().trim().length() == 0) {
                                emptyEdttxt = true;
                                ((AutoCompleteTextView) v1Ed).setError("Campo Vacío");
                                break;
                            }
                        }
                    }
                }
            }
        }
        return emptyEdttxt;
    }

    public void addInsumo(View v){
        try {
            boolean emptyEdttxt = false;
            for (int i = 0; i < ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 1))).getChildCount(); i++) {
                View v1L = ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 1))).getChildAt(i);
                if(v1L.getTag().toString().contains(linLayInsS)) {
                    for (int ii = 0; ii < ((LinearLayout)v1L).getChildCount(); ii++) {
                        View v1Ed = ((LinearLayout)v1L).getChildAt(ii);
                        if (v1Ed instanceof AutoCompleteTextView) {
                            if (((AutoCompleteTextView) v1Ed).getText().toString().trim().length() == 0) {
                                emptyEdttxt = true;
                                ((AutoCompleteTextView) v1Ed).setError("Campo Vacío");
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

                final AutoCompleteTextView editText = new AutoCompleteTextView(InsumoLayout.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setTag(txtvw_InsS + contador);
                editText.setHint(R.string.nombre_insumo);
                editText.setTypeface(typefaceEditText);
                editText.setLayoutParams(layparamsEditText);
                /*editText.setAdapter(new ArrayAdapter<String>(context,
                       android.R.layout.simple_dropdown_item_1line, autocompleteInsumos));*/
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
                spinner.setGravity(View.TEXT_ALIGNMENT_CENTER);
                spinner.setAdapter(new ArrayAdapter<String>(context,
                       android.R.layout.simple_spinner_dropdown_item, autocompleteProveedores));
               spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                       nombreProveedor = String.valueOf(parent.getItemAtPosition(pos));
                       autocompleteInsumos = usersDB.getInsumosPorProveedor(nombreProveedor);
                       editText.setAdapter(new ArrayAdapter<String>(context,
                               android.R.layout.simple_dropdown_item_1line, autocompleteInsumos));
                   }
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });
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

    public ArrayList<InsumoProveedor> getInsumosProveedor(){
        Insumo insumo; Proveedor proveedor;
        InsumoProveedor insumoProveedor = new InsumoProveedor();
        ArrayList<InsumoProveedor> array = new ArrayList<InsumoProveedor>();

        for (int i = 0; i < linearLayoutsArray.size(); i++) {
            LinearLayout v1L = (LinearLayout)(linearLayoutsArray.get(i));
            for(int ii = 0; ii < v1L.getChildCount(); ii++){
                LinearLayout v2L = (LinearLayout)v1L.getChildAt(ii);
                    for (int iii = 0; iii < v2L.getChildCount(); iii++) {
                        View v1Ed = ((LinearLayout) v2L).getChildAt(iii);
                        if (v1Ed instanceof AutoCompleteTextView) {
                            insumo = new Insumo(((AutoCompleteTextView) v1Ed).getText().toString().trim(), 0);
                            insumoProveedor.setInusmo(insumo);
                        }
                        else if(v1Ed instanceof Spinner){
                            proveedor = new Proveedor((String)((Spinner)v1Ed).getSelectedItem());
                            insumoProveedor.setProveedor(proveedor);
                        }
                    }
            }
            array.add(insumoProveedor);
            insumoProveedor = new InsumoProveedor();
        }

        return array;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, Administrador_Activity.class);
        startActivity(intent);
        InsumoLayout.this.finish();
    }
}

/*for (int i = 0; i < ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 1))).getChildCount(); i++) {
            View v1L = ((LinearLayout)(linearLayoutsArray.get(cantidadViews - 1))).getChildAt(i);
            if(v1L.getTag().toString().contains(linLayInsS)) {
                for (int ii = 0; ii < ((LinearLayout)v1L).getChildCount(); ii++) {
                    View v1Ed = ((LinearLayout)v1L).getChildAt(ii);
                    if (v1Ed instanceof AutoCompleteTextView) {
                        if (((AutoCompleteTextView) v1Ed).getText().toString().trim().length() == 0) {
                            emptyEdttxt = true;
                            ((AutoCompleteTextView) v1Ed).setError("Campo Vacío");
                            break;
                        }
                    }
                }
            }
        }*/