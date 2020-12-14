package com.example.autoselectricos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    ListActivityCar listActivityCar;
    List<carModel> mcarModelList;

    public CustomAdapter(ListActivityCar listActivity, List<carModel> carModelList) {
        this.listActivityCar = listActivity;
        this.mcarModelList = carModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String modelo = mcarModelList.get(position).getModelo();
                String año = mcarModelList.get(position).getAño();
                String color = mcarModelList.get(position).getColor();
                Toast.makeText(listActivityCar, modelo + " " + año + " " + color, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivityCar);
                String[] options = {"Actualizar datos", "Eliminar"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String id = mcarModelList.get(position).getId();
                            String modelo = mcarModelList.get(position).getModelo();
                            String marca = mcarModelList.get(position).getMarca();
                            String NoSerie = mcarModelList.get(position).getNoSerie();
                            String placa = mcarModelList.get(position).getPlaca();
                            String precio = mcarModelList.get(position).getPrecio();
                            String año = mcarModelList.get(position).getAño();
                            String color = mcarModelList.get(position).getColor();
                            String tipo = mcarModelList.get(position).getTipo();


                            Intent actualizarDatos = new Intent(listActivityCar, MainActivity.class);
                            actualizarDatos.putExtra("updateId", id);
                            actualizarDatos.putExtra("updateModelo", modelo);
                            actualizarDatos.putExtra("updateMarca", marca);
                            actualizarDatos.putExtra("updateNoSerie", NoSerie);
                            actualizarDatos.putExtra("updatePlaca", placa);
                            actualizarDatos.putExtra("updatePrecio", precio);
                            actualizarDatos.putExtra("updateAño", año);
                            actualizarDatos.putExtra("updateColor", color);
                            actualizarDatos.putExtra("updateTipo", tipo);

                            listActivityCar.startActivity(actualizarDatos);
                            }

                        if (which == 1) {
                            listActivityCar.eliminarRegistro(position);
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvModelo.setText(
                mcarModelList.get(i).getModelo()
                        + " " + mcarModelList.get(i).getAño()
                        + " " + mcarModelList.get(i).getColor()
        );
    }

    @Override
    public int getItemCount() {
        return mcarModelList.size();
    }
}