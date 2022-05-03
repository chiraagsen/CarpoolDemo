package com.example.carpoolbuddy.Model.Vehicle;

public class Helicopter extends Vehicle{
    private int maxAltitude;
    private int maxAirSpeed;
    private String vehicleId;
    private String model;
    private int capacity;
    private double basePrice;
    private String owner;

    public Helicopter(){
        super();
    }

    public Helicopter(String vehicleId, String model , int capacity, double basePrice, String owner,int maxAltitude, int maxAirSpeed) {
        this.maxAltitude = maxAltitude;
        this.maxAirSpeed = maxAirSpeed;
        this.vehicleId = vehicleId;
        this.model = model;
        this.capacity = capacity;
        this.basePrice = basePrice;
        this.owner = owner;
    }

    public int getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    public int getMaxAirSpeed() {
        return maxAirSpeed;
    }

    public void setMaxAirSpeed(int maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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
    public String toString() {
        return "Helicopter{" +
                "maxAltitude=" + maxAltitude +
                ", maxAirSpeed=" + maxAirSpeed +
                ", vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", basePrice=" + basePrice +
                ", owner='" + owner + '\'' +
                '}';
    }
}
