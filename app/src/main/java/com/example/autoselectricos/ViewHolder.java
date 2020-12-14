package com.example.autoselectricos;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvModelo, tvAño, tvColor;
    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
        tvModelo = itemView.findViewById(R.id.tvModelo);
        tvAño = itemView.findViewById(R.id.tvAño);
        tvColor = itemView.findViewById(R.id.tvColor);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}