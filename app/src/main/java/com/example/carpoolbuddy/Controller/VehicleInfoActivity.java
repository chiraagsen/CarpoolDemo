package com.example.carpoolbuddy.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.carpoolbuddy.Model.Vehicle.Vehicle;
import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VehicleInfoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    //added for testing
    private ArrayList<Vehicle> vehiclesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //added for testing
        vehiclesList = new ArrayList<Vehicle>();
    }

    public void testDB(View v) {
        vehiclesList.clear();
        TaskCompletionSource<String> getAllRidesTask = new TaskCompletionSource<>();
        firestore.collection("Vehicle").whereEqualTo("open", true)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        vehiclesList.add(document.toObject(Vehicle.class));
                    }
                    getAllRidesTask.setResult(null);
                }
                else {
                    Log.d("VehiclesInfoActivity", "Error getting documents from db: ", task.getException());
                }
            }
        });
        // when all rides have been retrieved, update RecyclerView
        getAllRidesTask.getTask().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                System.out.println("VEHICLE INFO: " + vehiclesList.toString());
            }
        });
    }

    public void gotoUserProfile(View v) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
}