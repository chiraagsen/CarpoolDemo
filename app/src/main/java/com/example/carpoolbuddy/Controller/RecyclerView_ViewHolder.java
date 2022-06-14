package com.example.carpoolbuddy.Controller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.R;

public class RecyclerView_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    RecyclerView_Adapter.OnNoteListener onNoteListener;

    protected TextView modelTextView;
    protected TextView vehicleTypeTextView;
    protected TextView capacityTextView;
    protected TextView basePriceTextView;

    public RecyclerView_ViewHolder(@NonNull View itemView, RecyclerView_Adapter.OnNoteListener onNoteListener) {
        super(itemView);
        this.onNoteListener = onNoteListener;

        modelTextView = itemView.findViewById(R.id.VehicleType);
        vehicleTypeTextView = itemView.findViewById(R.id.Model);
        capacityTextView = itemView.findViewById(R.id.Capacity);
        basePriceTextView = itemView.findViewById(R.id.BasePrice);

        itemView.setOnClickListener(this);
    }

    public String getModelTextView() {
        return modelTextView.getText().toString();
    }

    public void setModelTextView(String text) {
        modelTextView.setText(text);
    }

    public String getVehicleTypeTextView() {
        return vehicleTypeTextView.getText().toString();
    }

    public void setVehicleTypeTextView(String text) {
        vehicleTypeTextView.setText(text);
    }

    public String getCapacityTextView() {
        return capacityTextView.getText().toString();
    }

    public void setCapacityTextView(String text) {
        capacityTextView.setText(text);
    }

    public TextView getBasePriceTextView() {
        return basePriceTextView;
    }

    public void setBasePriceTextView(String text) {
        basePriceTextView.setText(text);
    }

    @Override
    public void onClick(View v) {
        onNoteListener.onNoteClick(getAdapterPosition());
    }
}
