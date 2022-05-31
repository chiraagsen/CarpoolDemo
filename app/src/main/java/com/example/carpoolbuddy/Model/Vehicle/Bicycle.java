package com.example.carpoolbuddy.Model.Vehicle;

import java.util.ArrayList;

public class Bicycle extends Vehicle {
    private String bicycleType;
    private String owner;
    private int capacity;
    private String vehicleID;
    private double basePrice;
    private int weight;
    private int weightCapacity;

    public Bicycle(){
    }
    //String owner, String model, String capacity, String vehicleType, String basePrice, String vehicleID, String ownerUID,String bicycleType, String weightCapacity
    public Bicycle(String owner, String model, int capacity, String vehicleID, double basePrice, String bicycleType, int weight, int weightCapacity) {
        //super(owner, model, capacity, basePrice, vehicleID);
        this.bicycleType = bicycleType;
        this.owner = owner;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.basePrice = basePrice;
        this.weight = weight;
        this.weightCapacity = weightCapacity;
    }

    public String getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
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
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "bicycleType='" + bicycleType + '\'' +
                ", owner='" + owner + '\'' +
                ", capacity=" + capacity +
                ", vehicleID='" + vehicleID + '\'' +
                ", basePrice=" + basePrice +
                ", weight=" + weight +
                ", weightCapacity=" + weightCapacity +
                '}';
    }
}


