package com.example.carpoolbuddy.Model.Users;

import com.example.carpoolbuddy.Model.Users.User;

import java.util.ArrayList;

public class Alumni extends User {
    private String graduateYear;

    public Alumni(String uid, String nameString, String emailString, int gradYearInt){
        super();
    }

    public Alumni(String uid, String name, String email, String userType, double priceMultiplier, ArrayList ownedVehicle, String graduateYear) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicle);
        this.graduateYear = graduateYear;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    @Override
    public String toString() {
        return "Alumni{" +
                "graduateYear='" + graduateYear + '\'' +
                '}';
    }
}