package com.example.carpoolbuddy.Model.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehicle implements Serializable {
    private String owner;
    private String model;
    private int capacity;
    private String vehicleID;
    private ArrayList ridersUIDS;
    private boolean open;
    private String vehicleType;
    private double basePrice;

    public Vehicle(){

    }

    public Vehicle(String owner, String model, int capacity, String vehicleID, ArrayList ridersUIDS, boolean open, String vehicleType, double basePrice) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.ridersUIDS = ridersUIDS;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public ArrayList getRidersUIDS() {
        return ridersUIDS;
    }

    public void setRidersUIDS(ArrayList ridersUIDS) {
        this.ridersUIDS = ridersUIDS;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "owner='" + owner + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", vehicleID='" + vehicleID + '\'' +
                ", ridersUIDS=" + ridersUIDS +
                ", open=" + open +
                ", vehicleType='" + vehicleType + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}