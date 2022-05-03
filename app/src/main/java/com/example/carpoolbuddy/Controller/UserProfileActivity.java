package com.example.carpoolbuddy.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carpoolbuddy.R;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
    public void signUp (View v){
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
        finish();
    }
    public void logOut (View v){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
    public void VehicleInfo (View v){
        Intent intent = new Intent(this, VehicleInfoActivity.class);
        startActivity(intent);
        finish();
    }
    public void VehicleProf (View v){
        Intent intent = new Intent(this, VehicleProfileActivity.class);
        startActivity(intent);
        finish();
    }
    public void AddVehicle (View v){
        Intent intent = new Intent(this, AddVehicleActivity.class);
        startActivity(intent);
        finish();
    }
}