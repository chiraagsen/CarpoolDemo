package com.example.carpoolbuddy.Controller;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carpoolbuddy.Model.Vehicle.Vehicle;
import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class VehicleProfileActivity extends AppCompatActivity implements View.OnClickListener
{

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private TextView model;
    private TextView owner;
    private TextView capacity;
    private TextView price;
    private Vehicle vehicle;
    private Button reserve_button;
    private TextView owned_text;
    private TextView bookedUIDs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        if(getIntent().hasExtra("Vehicle")) {
            vehicle = getIntent().getParcelableExtra("Vehicle");
            Log.d("Vehicle Activity", "onCreate: "+ vehicle.toString());

            model = findViewById(R.id.textViewModel);
            owner = findViewById(R.id.textViewOwner);
            capacity = findViewById(R.id.textViewCapacity);
            price = findViewById(R.id.textViewPrice);
            bookedUIDs = findViewById(R.id.textViewBookedUids);
            reserve_button =findViewById(R.id.reserveButton);

            model.setText("Model: "+vehicle.getModel());
            owner.setText("Owner: "+vehicle.getOwnerUID());
            capacity.setText("Capacity: "+String.valueOf(vehicle.getCapacity()));
            price.setText("Base price: "+String.valueOf(vehicle.getBasePrice()));
            bookedUIDs.setText("Reserved UIDs: "+vehicle.getRidersUIDs().toString());

            String vehicleOwner = ""+vehicle.getOwnerUID();
            String user = ""+mAuth.getUid();
            System.out.println("Vehicle Owner: " + vehicleOwner + ", User: " + user);
            if(vehicleOwner.equals(user))
            {
                owned_text = findViewById(R.id.ownedTextView);
                owned_text.setText("You own this vehicle. You can choose to close it.");
                reserve_button.setText("Close Vehicle");
            }
            if(vehicle.getRidersUIDs().contains(user))
            {
                reserve_button.setText("Cancel");
            }

        }


    }

    public void reserve()
    {
        //close vehicle if user took last seat available
        if(vehicle.getCapacity() == 1) {
            firestore.collection("Vehicle").document(vehicle.getVehicleID())
                    .update("open", false);
        }

        // update capacity
        firestore.collection("Vehicle").document(vehicle.getVehicleID())
                .update("capacity", vehicle.getCapacity() - 1);


        vehicle.addRidersUIDs(mAuth.getUid());
        firestore.collection("Vehicle").document(vehicle.getVehicleID())
                .update("ridersUIDs", vehicle.getRidersUIDs())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // go back to VehiclesInfoActivity
                        System.out.println("CHECK FOR");
                        Intent intent = new Intent(getApplicationContext(), VehicleInfoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
    public void cancel()
    {//close vehicle if user took last seat available
        if(vehicle.getCapacity() == 0) {
            firestore.collection("Vehicle").document(vehicle.getVehicleID())
                    .update("open", true);
        }

        // update capacity
        firestore.collection("Vehicle").document(vehicle.getVehicleID())
                .update("capacity", vehicle.getCapacity() + 1);

        // deletes user from ridersUIDs
        firestore.collection("Vehicle").document(vehicle.getVehicleID())
                .update("ridersUIDs", FieldValue.arrayRemove(mAuth.getUid()+""))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // go back to VehiclesInfoActivity
                        System.out.println("CHECK FOR");
                        Intent intent = new Intent(getApplicationContext(), VehicleInfoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }

    public void closeVehicle()
    {
        firestore.collection("Vehicle").document(vehicle.getVehicleID()).delete();
        Intent intent = new Intent(getApplicationContext(), VehicleInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(reserve_button.getText().equals("Reserve")) {
            reserve();
        }
        else if(reserve_button.getText().equals("Cancel")) {
            cancel();
        }
        else if(reserve_button.getText().equals("Close Vehicle")) {
            closeVehicle();
        }
    }

}
