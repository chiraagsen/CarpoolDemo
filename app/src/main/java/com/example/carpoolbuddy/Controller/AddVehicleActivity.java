package com.example.carpoolbuddy.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carpoolbuddy.Model.Users.User;
import com.example.carpoolbuddy.Model.Vehicle.Car;
import com.example.carpoolbuddy.Model.Vehicle.Helicopter;
import com.example.carpoolbuddy.Model.Vehicle.Bicycle;
import com.example.carpoolbuddy.Model.Vehicle.Segway;
import com.example.carpoolbuddy.Model.Vehicle.Vehicle;
import com.example.carpoolbuddy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

public class AddVehicleActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText modelEditText;
    private EditText capacityEditText;
    private EditText basePriceEditText;
    private EditText maxAltitudeEditText;
    private EditText rangeEditText;
    private EditText weightEditText;
    private EditText weightCapacityEditText;
    private EditText maxAirSpeedEditText;
    private EditText bicycleTypeEditText;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String vid;
    private static int vidGenerator = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.activity_add_vehicle);
        userRoleSpinner = findViewById(R.id.spinner);
        setupSpinner();
        vid = "" + vidGenerator;
        vidGenerator++;
    }

    private void setupSpinner() {
        String[] userTypes = {"Car", "Segway", "Helicopter", "Bicycle"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AddVehicleActivity.this,
                android.R.layout.simple_spinner_item, userTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addFields() {
        commonFields();
        if(selectedRole.equals("Bicycle")) {

            bicycleTypeEditText = new EditText(this);
            bicycleTypeEditText.setHint("Bicycle type");

            weightCapacityEditText = new EditText(this);
            weightCapacityEditText.setHint("Weight capacity");

            weightEditText = new EditText(this);
            weightEditText.setHint("Weight");

            layout.addView(bicycleTypeEditText);
            layout.addView(weightEditText);
            layout.addView(weightCapacityEditText);
        }
        else if(selectedRole.equals("Car")) {

            rangeEditText = new EditText(this);
            rangeEditText.setHint("Range");

            layout.addView(rangeEditText);
        }
        else if(selectedRole.equals("Segway")) {

            rangeEditText = new EditText(this);
            rangeEditText.setHint("Range");

            weightCapacityEditText = new EditText(this);
            weightCapacityEditText.setHint("Weight capacity");

            layout.addView(rangeEditText);
            layout.addView(weightCapacityEditText);
        }
        else if(selectedRole.equals("Helicopter")) {

            maxAltitudeEditText = new EditText(this);
            maxAltitudeEditText.setHint("Max altitude");

            maxAirSpeedEditText = new EditText(this);
            maxAirSpeedEditText.setHint("Max air speed");

            layout.addView(maxAltitudeEditText);
            layout.addView(maxAirSpeedEditText);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        modelEditText = new EditText(this);
        modelEditText.setHint("Model");
        layout.addView(modelEditText);
        capacityEditText = new EditText(this);
        capacityEditText.setHint("capacity");
        layout.addView(capacityEditText);
        basePriceEditText = new EditText(this);
        basePriceEditText.setHint("Base Price");
        layout.addView(basePriceEditText);
    }

    public void createVehicle(View v) {
        //generate + get new key
        DocumentReference newRideRef = firestore.collection("Vehicle").document();
        String vehicleID = newRideRef.getId();

        //make new vehicle according to selected vehicle type
        Vehicle newVehicle = null;

        //get data from form
        String modelString  = modelEditText.getText().toString();
        int capacityData = Integer.parseInt(capacityEditText.getText().toString());
        double basePriceV = Double.parseDouble(basePriceEditText.getText().toString());
        String ownerId = mAuth.getCurrentUser().getUid();
        ArrayList<User> riderUids = new ArrayList<>();

        if(selectedRole.equals("Car")) {
            int range = Integer.parseInt(rangeEditText.getText().toString());
            newVehicle = new Car(modelString, capacityData, basePriceV, vehicleID,ownerId,range);
        }

        else if(selectedRole.equals("Segway")) {
            int weightCapacity = Integer.parseInt(weightCapacityEditText.getText().toString());
            int range = Integer.parseInt(rangeEditText.getText().toString());
            newVehicle = new Segway(modelString, capacityData, basePriceV, vehicleID, ownerId, range, weightCapacity);
        }

        else if(selectedRole.equals("Helicopter")) {

            String maxHeightString = maxAltitudeEditText.getText().toString();
            int maxHeightInt = Integer.parseInt(maxHeightString);
            int maxAirSpeedInt = Integer.parseInt(maxAirSpeedEditText.getText().toString());
            newVehicle = new Helicopter(modelString, capacityData, basePriceV, vehicleID, ownerId, maxHeightInt, maxAirSpeedInt);
        }
        else if (selectedRole.equals("Bicycle")) {
            String bicycleType = bicycleTypeEditText.getText().toString();
            int weightInt = Integer.parseInt(weightEditText.getText().toString());
            int weightCapacityInt = Integer.parseInt(weightCapacityEditText.getText().toString());
            newVehicle = new Bicycle(modelString, capacityData, basePriceV, vehicleID, ownerId, bicycleType, weightInt, weightCapacityInt);
        }
        else{
            Toast.makeText(AddVehicleActivity.this,"Something went wrong", Toast.LENGTH_LONG).show();
        }

        newRideRef.set(newVehicle);
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void gotoUserProfile(View v) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
}