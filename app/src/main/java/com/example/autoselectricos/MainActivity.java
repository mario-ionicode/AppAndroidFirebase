package com.example.autoselectricos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText etModelo, etMarca, etNoSerie, etPlaca, etPrecio, etAño, etColor, etTipo;
    FloatingActionButton fabGuardar, fabListar;

    ProgressDialog progressDialog;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String updateId, updateModelo, updateMarca, updateNoSerie, updatePlaca,updatePrecio, updateAño, updateColor,updateTipo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etModelo = findViewById(R.id.etModelo);
        etMarca = findViewById(R.id.etMarca);
        etNoSerie = findViewById(R.id.etNoSerie);
        etPlaca = findViewById(R.id.etPlaca);
        etPrecio = findViewById(R.id.etPrecio);
        etAño = findViewById(R.id.etAño);
        etColor = findViewById(R.id.etColor);
        etTipo = findViewById(R.id.etTipo);

        fabGuardar = findViewById(R.id.fabGuardar);
        fabListar = findViewById(R.id.fabListar);

        progressDialog = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar registro");


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setTitle("Actualizar Datos");

            updateId = bundle.getString("updateId");
            updateModelo = bundle.getString("updateModelo");
            updateMarca = bundle.getString("updateMarca");
            updateNoSerie = bundle.getString("updateNoSerie");
            updatePlaca = bundle.getString("updatePlaca");
            updatePrecio = bundle.getString("updatePrecio");
            updateAño = bundle.getString("updateAño");
            updateColor = bundle.getString("updateColor");
            updateTipo = bundle.getString("updateTipo");

            etModelo.setText(updateModelo);
            etMarca.setText(updateMarca);
            etNoSerie.setText(updateNoSerie);
            etPlaca.setText(updatePlaca);
            etPrecio.setText(updatePrecio);
            etAño.setText(updateAño);
            etColor.setText(updateColor);
            etTipo.setText(updateTipo);

        } else {
            actionBar.setTitle("Agregar");
        }


        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = updateId;
                    String modelo = etModelo.getText().toString().trim();
                    String marca = etMarca.getText().toString().trim();
                    String NoSerie = etNoSerie.getText().toString().trim();
                    String placa= etPlaca.getText().toString().trim();
                    String precio = etPrecio.getText().toString().trim();
                    String año = etAño.getText().toString().trim();
                    String color =etColor.getText().toString().trim();
                    String tipo = etTipo.getText().toString().trim();

                    actualizarDatos(id, modelo, marca, NoSerie, placa, precio, año, color, tipo);

                } else {
                    String modelo = etModelo.getText().toString().trim();
                    String marca = etMarca.getText().toString().trim();
                    String NoSerie = etNoSerie.getText().toString().trim();
                    String placa= etPlaca.getText().toString().trim();
                    String precio = etPrecio.getText().toString().trim();
                    String año = etAño.getText().toString().trim();
                    String color =etColor.getText().toString().trim();
                    String tipo = etTipo.getText().toString().trim();

                    cargarDatos(modelo, marca, NoSerie, placa, precio, año, color, tipo);
                }
            }
        });


        fabListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivityCar.class));
                finish();
            }
        });

    }


    private void cargarDatos(String modelo, String marca, String NoSerie, String placa, String precio, String año, String color, String tipo){
        progressDialog.setTitle("Agregar datos");
        progressDialog.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("modelo", modelo);
        doc.put("marca", marca);
        doc.put("NoSerie", NoSerie);
        doc.put("placa", placa);
        doc.put("precio", precio);
        doc.put("año", año);
        doc.put("color", color);
        doc.put("tipo", tipo);


        db.collection("Documents").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Datos almacenados con éxito...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void actualizarDatos(String id, String modelo, String marca, String NoSerie, String placa, String precio, String año, String color, String tipo) {
        progressDialog.setTitle("Actualizando datos a Firebase");
        progressDialog.show();

        /*
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("nombre", nombre);
        doc.put("apaterno", apaterno);
        doc.put("amaterno", amaterno);
        doc.put("sexo", sexo);
        doc.put("direccion", direccion);
        doc.put("facebook", facebook);
        doc.put("instagram", instagram);

         */


        db.collection("Documents")
                .document(id).update(
        "modelo", modelo,
        "marca", marca,
        "NoSerie", NoSerie,
        "placa", placa,
        "precio", precio,
        "año", año,
        "color", color,
        "tipo", tipo
        )
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Actualización exitosa...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Ha ocurrido un error..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}