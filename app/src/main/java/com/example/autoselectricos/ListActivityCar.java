package com.example.autoselectricos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListActivityCar extends AppCompatActivity {

        java.util.List<carModel> mcarModelList = new ArrayList<>();
        RecyclerView recyclerview;

        //Crear instancia de FirebaseFirestore
        FirebaseFirestore db;

        CustomAdapter customAdapter;
        RecyclerView.LayoutManager layoutManager;
        ProgressDialog progressDialog;
        Context context;
        FloatingActionButton fabAgregar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("Ver registros");

            recyclerview = findViewById(R.id.recyclerview);
            recyclerview.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(layoutManager);
            fabAgregar = findViewById(R.id.fabAgregar);

            // Obtener instancia de Firebase
            db = FirebaseFirestore.getInstance();

            verDatos();

            fabAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ListActivityCar.this, MainActivity.class));
                    finish();
                }
            });
        }

        public void eliminarRegistro(int index) {
            db.collection("Documents").document(mcarModelList.get(index).getId())
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ListActivityCar.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                            verDatos();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ListActivityCar.this, "No se ha completado la operación" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void verDatos() {
            db.collection("Documents")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            mcarModelList.clear();
                            for (DocumentSnapshot doc : task.getResult()) {
                                carModel carModel = new carModel(
                                        doc.getString("id"),
                                        doc.getString("modelo"),
                                        doc.getString("marca"),
                                        doc.getString("Numero de Serie"),
                                        doc.getString("placa"),
                                        doc.getString("precio"),
                                        doc.getString("año"),
                                        doc.getString("color"),
                                        doc.getString("tipo")

                                );

                                mcarModelList.add(carModel);
                            }
                            customAdapter = new CustomAdapter(ListActivityCar.this, mcarModelList);
                            recyclerview.setAdapter(customAdapter);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ListActivityCar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


}
