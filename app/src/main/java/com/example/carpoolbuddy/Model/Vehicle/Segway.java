package com.example.carpoolbuddy.Model.Vehicle;

public class Segway extends Vehicle{
    private int range;
    private int weightCapacity;
    private String vehicleId;
    private String model;
    private String owner;
    private int weight;
    private double basePrice;
    private int weightrange;

    public Segway(){
        super();
    }

    public Segway(String vehicleId, String model, String owner, int weight, double basePrice, int weightrange, int weightCapacity) {
        this.range = range;
        this.weightCapacity = weightCapacity;
        this.model = model;
        this.owner = owner;
        this.weight = weight;
        this.basePrice = basePrice;
        this.weightrange = weightrange;
        this.vehicleId = vehicleId;

    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    //public String getVehicleId() {
     //   return vehicleId;
   // }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getWeightrange() {
        return weightrange;
    }

    public void setWeightrange(int weightrange) {
        this.weightrange = weightrange;
    }

    @Override
    public String toString() {
        return "Segway{" +
                "range=" + range +
                ", weightCapacity=" + weightCapacity +
                ", vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", owner='" + owner + '\'' +
                ", weight=" + weight +
                ", basePrice=" + basePrice +
                ", weightrange=" + weightrange +
                '}';
    }
}
