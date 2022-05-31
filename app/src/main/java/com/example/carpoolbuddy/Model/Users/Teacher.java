package com.example.carpoolbuddy.Model.Users;

import com.example.carpoolbuddy.Model.Users.User;

import java.util.ArrayList;

public class Teacher extends User {
    private String inSchoolTitle;

    public Teacher(){
        super();
    }

    public Teacher(String uid, String name, String email, String userType, double priceMultiplier, ArrayList ownedVehicle, String inSchoolTitle) {
        super(uid, name, email, userType, priceMultiplier);
        this.inSchoolTitle = inSchoolTitle;
    }

    public String getInSchoolTitle() {
        return inSchoolTitle;
    }

    public void setInSchoolTitle(String inSchoolTitle) {
        this.inSchoolTitle = inSchoolTitle;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "inSchoolTitle='" + inSchoolTitle + '\'' +
                '}';
    }
}
