package com.example.carpoolbuddy.Model.Users;

import com.example.carpoolbuddy.Model.Users.User;

import java.util.ArrayList;

public class Student extends User {
    private String graduatingYear;
    private ArrayList parentUIDs;

    public Student(){
        super();
    }

    public Student(String uid, String name, String email, String userType, double priceMultiplier, ArrayList ownedVehicle, String graduatingYear, ArrayList parentUIDs) {
        super(uid, name, email, userType, priceMultiplier);
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }

    public String getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(String graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public ArrayList getParentUIDs() {
        return parentUIDs;
    }

    public void setParentUIDs(ArrayList parentUIDs) {
        this.parentUIDs = parentUIDs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "graduatingYear='" + graduatingYear + '\'' +
                ", parentUIDs=" + parentUIDs +
                '}';
    }
}