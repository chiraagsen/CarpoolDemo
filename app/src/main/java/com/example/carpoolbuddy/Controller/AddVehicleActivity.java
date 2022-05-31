package com.example.carpoolbuddy.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.carpoolbuddy.Model.Users.User;
import com.example.carpoolbuddy.Model.Vehicle.Car;
import com.example.carpoolbuddy.Model.Vehicle.Helicopter;
import com.example.carpoolbuddy.Model.Vehicle.Bicycle;
import com.example.carpoolbuddy.Model.Vehicle.Segway;
import com.example.carpoolbuddy.Model.Vehicle.Vehicle;
import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddVehicleActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText model;
    private EditText capacity;
    private EditText basePrice;
    private EditText extraField;
    private EditText weight;
    private EditText weightCapacity;
    private EditText maxAirSpeed;
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
            extraField = new EditText(this);
            extraField.setHint("Bicycle type");
            weightCapacity = new EditText(this);
            weightCapacity.setHint("Weight capacity");
            weight = new EditText(this);
            weight.setHint("Weight");
            layout.addView(extraField);
            layout.addView(weight);
            layout.addView(weightCapacity);
        }
        else if(selectedRole.equals("Car")) {

            extraField = new EditText(this);
            extraField.setHint("Range");
            layout.addView(extraField);
        }
        else if(selectedRole.equals("Segway")) {

            extraField = new EditText(this);
            extraField.setHint("Range");
            weightCapacity = new EditText(this);
            weightCapacity.setHint("Weight capacity");
            weight = new EditText(this);
            weight.setHint("weight");
            layout.addView(extraField);
            layout.addView(weightCapacity);
            layout.addView(weight);
        }
        else if(selectedRole.equals("Helicopter")) {

            extraField = new EditText(this);
            extraField.setHint("Max altitude");
            maxAirSpeed = new EditText(this);
            maxAirSpeed.setHint("Max air speed");
            layout.addView(extraField);
            layout.addView(maxAirSpeed);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        model = new EditText(this);
        model.setHint("Model");
        layout.addView(model);
        capacity = new EditText(this);
        capacity.setHint("capacity");
        layout.addView(capacity);
        basePrice = new EditText(this);
        basePrice.setHint("Base Price");
        layout.addView(basePrice);
    }

    public void createVehicle(View v) {

        //generate + get new key
        DocumentReference newRideRef = firestore.collection("Vehicle").document();
        String vehicleID = newRideRef.getId();

        //make new vehicle according to selected vehicle type
        Vehicle newVehicle = null;


/*
        //check which type of vehicle was created
        if(selectedType.equals("Car")) {
            int capacityInt = Integer.parseInt(capacityField.getText().toString());
            newVehicle = new Car(5, ownerString, modelString, 5,vehicleId, );
        }
        else if(selectedType.equals("Bicycle")) {
            int capacityInt = Integer.parseInt(capacityField.getText().toString());
            newVehicle = new Bicycle(ownerString, modelString, capacityInt, vehicleId, basePriceDouble);
        }
        else if(selectedType.equals("Helicopter")) {
            newVehicle = new Helicopter(ownerString, modelString, 2, vehicleId, basePriceDouble);
        }

        //add the new vehicle to the database
        newRideRef.set(newVehicle);

        */
        //get data from form
        String modelString  = model.getText().toString();
        String capacityString = capacity.getText().toString();
        double basePriceV = Double.parseDouble(basePrice.getText().toString());



        String owner = mAuth.getCurrentUser().getUid();

        ArrayList<User> riderUids = new ArrayList<>();

        if(selectedRole.equals("Car")) {
            owner = mAuth.getCurrentUser().getUid();
            int capacity = Integer.parseInt(capacityString);
            basePriceV = Double.parseDouble(basePrice.getText().toString());
            newVehicle = new Car(vehicleID, modelString, capacity, basePriceV, owner);

            // firestore.collection("vehicles").document(vehicleID).set(newVehicle);
        }

        else if(selectedRole.equals("Segway")) {
            int weightCapacityInt = Integer.parseInt(weightCapacity.getText().toString());
            int weightInt = Integer.parseInt(weight.getText().toString());
            int range = Integer.parseInt(extraField.getText().toString());
            newVehicle = new Segway(vehicleID, modelString, owner, weightInt, basePriceV, range, weightCapacityInt);
        }

        else if(selectedRole.equals("Helicopter")) {

            String maxHeightString = extraField.getText().toString();
            int maxHeightInt = Integer.parseInt(maxHeightString);
            int capacity = Integer.parseInt(capacityString);
            int maxAirSpeedInt = Integer.parseInt(maxAirSpeed.getText().toString());
            newVehicle = new Helicopter(vehicleID, modelString,capacity, basePriceV, owner, maxHeightInt, maxAirSpeedInt);
        }
        else if (selectedRole.equals("Bicycle")) {
            String bikeType = extraField.getText().toString();
            int capacity = Integer.parseInt(capacityString);
            int weightInt = Integer.parseInt(weight.getText().toString());
            int weightCapacityInt = Integer.parseInt(weightCapacity.getText().toString());
            newVehicle = new Bicycle(owner, modelString, capacity, vehicleID, basePriceV, bikeType, weightInt, weightCapacityInt);
        }

        newRideRef.set(newVehicle);
    }
}