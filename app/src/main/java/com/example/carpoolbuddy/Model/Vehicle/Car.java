package com.example.carpoolbuddy.Model.Vehicle;

import java.util.ArrayList;

public class Car extends Vehicle {

    private int range;
    /*
        public Car() {
        }


       public Car(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, int range) {
            //super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice);
            this.range = range;

        }
    */
    public Car(String model, int capacity, double basePrice, String vehicleID, String ownerId, int range){
        super(model, capacity, "Car", basePrice, vehicleID, ownerId);
        this.range =range;
        this.setVehicleType("Car");
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Car{" +
                "range=" + range +
                '}';
    }
}