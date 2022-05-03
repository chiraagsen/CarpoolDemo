package com.example.carpoolbuddy.Model.Vehicle;

import java.util.ArrayList;

public class Car extends Vehicle{
    private int range;
    private String model;
    private int capacity;
    private double basePrice;
    private String owner;
    private String vehicleID;

    public Car(){
        super();
    }

    public Car(String vehicleID, String model, int capacity, double basePrice, String owner, int range) {
        this.range = range;
        this.model = model;
        this.capacity = capacity;
        this.basePrice = basePrice;
        this.owner = owner;
        this.vehicleID = vehicleID;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getVehicleID() {
        return vehicleID;
    }

    @Override
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    @Override
    public String toString() {
        return "Car{" +
                "range=" + range +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", basePrice=" + basePrice +
                ", owner='" + owner + '\'' +
                ", vehicleID='" + vehicleID + '\'' +
                '}';
    }
}
