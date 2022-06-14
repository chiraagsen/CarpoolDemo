package com.example.carpoolbuddy.Model.Vehicle;

import java.util.ArrayList;

public class Bicycle extends Vehicle {
    private String bicycleType;
    private int weight;
    private int weightCapacity;

    public Bicycle(){
    }
    public Bicycle(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String bicycleType, int weight, int weightCapacity) {
        this.bicycleType=bicycleType;
        this.weight=weight;
        this.weightCapacity=weightCapacity;
    }
    public Bicycle(String model, int capacity, double basePrice, String vehicleID, String ownerId, String bicycleType, int weight, int weightCapacity) {
        super(model, capacity, "Segway", basePrice, vehicleID, ownerId);
        this.bicycleType=bicycleType;
        this.weight=weight;
        this.weightCapacity=weightCapacity;
    }

    public String getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
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
                ", weight=" + weight +
                ", weightCapacity=" + weightCapacity +
                '}';
    }
}


