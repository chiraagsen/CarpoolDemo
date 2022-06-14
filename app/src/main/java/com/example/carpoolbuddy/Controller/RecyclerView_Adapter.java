package com.example.carpoolbuddy.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.Model.Vehicle.Vehicle;
import com.example.carpoolbuddy.R;

import java.util.ArrayList;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_ViewHolder> {

    private ArrayList<Vehicle> vehicleList;
    private OnNoteListener onNoteListener;

    public RecyclerView_Adapter(ArrayList<Vehicle> vehicleList, OnNoteListener onNoteListener) {
        this.vehicleList = vehicleList;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public RecyclerView_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);

        RecyclerView_ViewHolder holder = new RecyclerView_ViewHolder(myView, onNoteListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_ViewHolder holder, int position) {
        holder.setModelTextView(vehicleList.get(position).getModel());
        holder.setVehicleTypeTextView(vehicleList.get(position).getVehicleType());
        holder.setCapacityTextView("Capacity: "+vehicleList.get(position).getCapacity());
        holder.setBasePriceTextView("€"+vehicleList.get(position).getBasePrice());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
