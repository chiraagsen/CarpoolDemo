package com.example.carpoolbuddy.Controller;

import androidx.annotation.NonNull;
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

import com.example.carpoolbuddy.Controller.UserProfileActivity;
import com.example.carpoolbuddy.Model.Users.Alumni;
import com.example.carpoolbuddy.Model.Users.Parent;
import com.example.carpoolbuddy.Model.Users.Student;
import com.example.carpoolbuddy.Model.Users.Teacher;
import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Sign_up extends AppCompatActivity {
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private EditText gradYearField;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.linearLayoutCreateUser);
        userRoleSpinner = findViewById(R.id.spinner2);
        setupSpinner();
    }

    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] userTypes = {"Student", "Teacher", "Alumni", "Parent"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(Sign_up.this,
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
        if(selectedRole.equals("Alumni")) {
            System.out.println("ALUMNI");
            gradYearField = new EditText(this);
            gradYearField.setHint("Graduation year");
            layout.addView(gradYearField);
        }
        //commonFields();
        if(selectedRole.equals("Student")) {
            System.out.println("STUDENT");
            gradYearField = new EditText(this);
            gradYearField.setHint("Graduating year");
            layout.addView(gradYearField);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        nameField = new EditText(this);
        nameField.setHint("Name");
        layout.addView(nameField);
        emailField = new EditText(this);
        emailField.setHint("Email");
        layout.addView(emailField);
        passwordField = new EditText(this);
        passwordField.setHint("Password");
        layout.addView(passwordField);
    }


    public void signUp(View v) {
        String nameString = nameField.getText().toString();
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();

        //if(emailString)

//        System.out.print("Email is"+emailString);
//        System.out.print("Password is"+passwordString);
        mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("SIGN UP", "successfully signed up the user");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            mUser = mAuth.getCurrentUser();
                            userId = mUser.getUid();
                            addUserToDataBase(nameString, emailString);
                        }
                        else {
                            Log.d("SIGN UP", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Sign_up.this,"Sign up failed", Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void addUserToDataBase(String nameString, String emailString){
        if(selectedRole.equals("Alumni")) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            Alumni newUser = new Alumni(userId, nameString, emailString, "Alumni", 0.0, ""+gradYearInt);
            firestore.collection("users").document(userId).set(newUser);
        }
        else if(selectedRole.equals("Student")) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            Student newUser = new Student(userId, nameString, emailString, "Student", 0.0, ""+gradYearInt, null);
            firestore.collection("users").document(userId).set(newUser);
        }
        else if(selectedRole.equals("Parent")) {
            Parent newUser = new Parent(userId, nameString, emailString, "Parent", 0.0, null);
            firestore.collection("users").document(userId).set(newUser);
        }
        else if(selectedRole.equals("Teacher")) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            Teacher newUser = new Teacher(userId, nameString, emailString, "Teacher", 0.0, null);
            firestore.collection("users").document(userId).set(newUser);
        }
    }

    public void logIn(View v) {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
    }
}