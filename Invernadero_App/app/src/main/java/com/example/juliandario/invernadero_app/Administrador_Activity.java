package com.example.juliandario.invernadero_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        btnInformeEntradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "Entradas.xls";
                if(excelFileEntradas(context, filename, usersDB)) {
                    File file = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), filename);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file),"application/vnd.ms-excel");
                    startActivity(intent);
                }
            }
        });

        btnInformeSalidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "Salidas.xls";
                if(excelFileSalidas(context, filename, usersDB)) {
                    File file = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), filename);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file),"application/vnd.ms-excel");
                    startActivity(intent);
                }
            }
        });

        btnInformeProvee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private static boolean excelFileEntradas(Context context, String fileName, UserDBHelper usersDB) {

        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.AQUA.index);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        Font defaultFont = wb.createFont();
        defaultFont.setFontHeightInPoints((short)10);
        defaultFont.setFontName("Garamond");
        defaultFont.setColor(IndexedColors.BLACK.getIndex());
        defaultFont.setBold(true);
        defaultFont.setItalic(false);
        cs.setFont(defaultFont);

        CellStyle cs2 = wb.createCellStyle();
        cs2.setFillForegroundColor(HSSFColor.WHITE.index);
        cs2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs2.setAlignment(CellStyle.ALIGN_LEFT);
        cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        defaultFont = wb.createFont();
        defaultFont.setFontHeightInPoints((short)10);
        defaultFont.setFontName("Garamond");
        defaultFont.setColor(IndexedColors.BLACK.getIndex());
        defaultFont.setBold(false);
        defaultFont.setItalic(false);
        cs.setFont(defaultFont);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("Entradas");

        // Generate column headings
        Row row = sheet1.createRow(0);

        c = row.createCell(0);
        c.setCellValue("Insumo");
        c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("Proveedor");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Cantidad");
        c.setCellStyle(cs);

        c = row.createCell(3);
        c.setCellValue("Fecha");
        c.setCellStyle(cs);

        sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (15 * 500));
        sheet1.setColumnWidth(2, (15 * 500));
        sheet1.setColumnWidth(3, (15 * 500));

        ArrayList<Entrada> entradas = usersDB.toStringEntradas();

        for (int i = 0; i < entradas.size(); i++) {
            Entrada entrada = entradas.get(i);

            row = sheet1.createRow(i + 1);

            c = row.createCell(0);
            c.setCellValue(entrada.getInsumo());
            c.setCellStyle(cs2);

            c = row.createCell(1);
            c.setCellValue(entrada.getProveedor());
            c.setCellStyle(cs2);

            c = row.createCell(2);
            c.setCellValue(entrada.getCantidad());
            c.setCellStyle(cs2);

            c = row.createCell(3);
            c.setCellValue(entrada.getFecha());
            c.setCellStyle(cs2);

            sheet1.setColumnWidth(0, (15 * 500));
            sheet1.setColumnWidth(1, (15 * 500));
            sheet1.setColumnWidth(2, (15 * 500));
            sheet1.setColumnWidth(3, (15 * 500));
        }

        // Create a path where we will place our List of objects on external storage
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            success = true;
        }
        catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if (null != os)
                    os.close();
            }
            catch (Exception ex) { }
        }

        return success;
    }

    private static boolean excelFileSalidas(Context context, String fileName, UserDBHelper usersDB) {

        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.AQUA.index);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        Font defaultFont = wb.createFont();
        defaultFont.setFontHeightInPoints((short)10);
        defaultFont.setFontName("Garamond");
        defaultFont.setColor(IndexedColors.BLACK.getIndex());
        defaultFont.setBold(true);
        defaultFont.setItalic(false);
        cs.setFont(defaultFont);

        CellStyle cs2 = wb.createCellStyle();
        cs2.setFillForegroundColor(HSSFColor.WHITE.index);
        cs2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cs2.setAlignment(CellStyle.ALIGN_LEFT);
        cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        defaultFont = wb.createFont();
        defaultFont.setFontHeightInPoints((short)10);
        defaultFont.setFontName("Garamond");
        defaultFont.setColor(IndexedColors.BLACK.getIndex());
        defaultFont.setBold(false);
        defaultFont.setItalic(false);
        cs.setFont(defaultFont);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("Salidas");

        // Generate column headings
        Row row = sheet1.createRow(0);

        c = row.createCell(0);
        c.setCellValue("Insumo");
        c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("Lote");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Cantidad");
        c.setCellStyle(cs);

        c = row.createCell(3);
        c.setCellValue("Fecha");
        c.setCellStyle(cs);

        sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (15 * 500));
        sheet1.setColumnWidth(2, (15 * 500));
        sheet1.setColumnWidth(3, (15 * 500));

        ArrayList<Salida> salidas = usersDB.toStringSalidas();

        for (int i = 0; i < salidas.size(); i++) {
            Salida salida = salidas.get(i);

            row = sheet1.createRow(i + 1);

            c = row.createCell(0);
            c.setCellValue(salida.getInsumo());
            c.setCellStyle(cs2);

            c = row.createCell(1);
            c.setCellValue(salida.getLote());
            c.setCellStyle(cs2);

            c = row.createCell(2);
            c.setCellValue(salida.getCantidad());
            c.setCellStyle(cs2);

            c = row.createCell(3);
            c.setCellValue(salida.getFecha());
            c.setCellStyle(cs2);

            sheet1.setColumnWidth(0, (15 * 500));
            sheet1.setColumnWidth(1, (15 * 500));
            sheet1.setColumnWidth(2, (15 * 500));
            sheet1.setColumnWidth(3, (15 * 500));
        }

        // Create a path where we will place our List of objects on external storage
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            success = true;
        }
        catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if (null != os)
                    os.close();
            }
            catch (Exception ex) { }
        }

        return success;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}