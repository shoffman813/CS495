package com.example.tutorly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*The sign up screen for a new user*/
public class SignUpActivity extends AppCompatActivity {

    /*Variable Declarations*/
    EditText editTextFirstName; //Where user enters first name
    EditText editTextLastName; //Where user enters last name
    private EditText editTextEmail; //Where user enters email
    private EditText editTextPassword; //Where user enters password
    private FirebaseAuth mAuth;
    Spinner collegeSpinner; //Where user selects college
    Button btn; //button to submit registration
    ProgressBar progressBar;
    DatabaseReference databaseUsers;

    User user;
    String firstName, lastName, email, college, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users"); //reference to user database

        /*Assigning information variables to display fields*/
        editTextFirstName = (EditText) findViewById(R.id.first_name_register);
        editTextLastName = (EditText) findViewById(R.id.last_name_register);
        collegeSpinner = (Spinner) findViewById(R.id.colleges_spinner);
        editTextEmail = (EditText) findViewById(R.id.email_register);
        editTextPassword = (EditText) findViewById(R.id.password_register);

        btn = (Button)findViewById(R.id.RegisterBtn); //Button to submit registration

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //When user clicks button to sign up
                registerUser(); //Register the user
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    /*Registers a user with FireBase*/
    private void registerUser() {
        /*Saving all information to strings*/
        email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        firstName = editTextFirstName.getText().toString().trim();
        lastName = editTextLastName.getText().toString().trim();
        college = collegeSpinner.getSelectedItem().toString();

        //Validating email and password

        if(email.isEmpty()) { //Email field is empty
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { //If entered email is not a real email
            editTextEmail.setError("Please enter valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)) {  //Password field is empty
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) { //Password is shorter than 6 characters
            editTextPassword.setError("Minimum length of password is 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(firstName)) { //First name field is empty
            Toast.makeText(this, "You must enter your full name", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(lastName)) { //Last name field is empty
            Toast.makeText(this, "You must enter your full name", Toast.LENGTH_SHORT).show();
        }

        /*All validations are passed*/

        progressBar.setVisibility(View.VISIBLE); //Show the loading wheel

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE); //Upon completion of task, hide the loading wheel
                        if(task.isSuccessful()) { //user is successfully registered and logged in
                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                            SignUpActivity.this.finish(); //So user cannot return to sign in screen
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class); //start main activity
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            FirebaseUser fUser = mAuth.getCurrentUser(); //Saving unique UID to use

                            id = databaseUsers.push().getKey(); //ID generated uniquely
                            user = new User(id, firstName, lastName, email, college, 0, 0, fUser.getUid()); //Create new instance of user class
                            databaseUsers.child(fUser.getUid()).setValue(user); //Sets user info to be a child of the user's FireBase id

                            startActivity(intent); //Open the main screen, user is registered and logged in
                        }

                        else { //User account could not be created
                            if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "Could not register, please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
}
