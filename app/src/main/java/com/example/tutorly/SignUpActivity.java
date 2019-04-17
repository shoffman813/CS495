package com.example.tutorly;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*The sign up screen for a new user*/
public class SignUpActivity extends AppCompatActivity {

    EditText editTextFirstName;
    EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;
    Spinner collegeSpinner;
    Button btn;
    ProgressBar progressBar;
    DatabaseReference databaseUsers;

    String firstName, lastName, email;
    String college;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        editTextFirstName = (EditText) findViewById(R.id.first_name_register);
        editTextLastName = (EditText) findViewById(R.id.last_name_register);
        collegeSpinner = (Spinner) findViewById(R.id.colleges_spinner);
        editTextEmail = (EditText) findViewById(R.id.email_register);
        editTextPassword = (EditText) findViewById(R.id.password_register);

        btn = (Button)findViewById(R.id.RegisterBtn); //Button to submit registration

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //A button to view the session history screen
                registerUser();
                addInformationToUser();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void registerUser() {
        email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        firstName = editTextFirstName.getText().toString().trim();
        lastName = editTextLastName.getText().toString().trim();
        college = collegeSpinner.getSelectedItem().toString();

        //Validating email and password

        if(email.isEmpty()) {
            //email is empty
            //Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { //If entered email is a real email
            editTextEmail.setError("Please enter valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            //password is empty
            //Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError("Minimum length of password is 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "You must enter your full name", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "You must enter your full name", Toast.LENGTH_SHORT).show();
        }

        //All validations are passed

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            //user is successfully registered and logged in
                            SignUpActivity.this.finish();
                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class); //start main activity
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }

                        else {
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

    private void addInformationToUser() {
        String id = databaseUsers.push().getKey(); //ID generated uniquely

        User user = new User(id, firstName, lastName, email, college);

        databaseUsers.child(id).setValue(user);
    }
}
