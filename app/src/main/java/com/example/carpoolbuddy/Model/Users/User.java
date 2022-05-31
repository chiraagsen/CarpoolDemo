package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

public class User {
    private String uid;
    private String name;
    private String email;
    private String UserType;
    private double priceMultiplier;
    private ArrayList ownedVehicle;

    public User(){

    }

    public User(String uid, String name, String email, String userType, double priceMultiplier) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        UserType = userType;
        this.priceMultiplier = priceMultiplier;
        this.ownedVehicle = new ArrayList();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public ArrayList getOwnedVehicle() {
        return ownedVehicle;
    }

    public void setOwnedVehicle(ArrayList ownedVehicle) {
        this.ownedVehicle = ownedVehicle;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", UserType='" + UserType + '\'' +
                ", priceMultiplier=" + priceMultiplier +
                ", ownedVehicle=" + ownedVehicle +
                '}';
    }
}