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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

/*The sign up screen for a new user*/
public class SignUpActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private FirebaseAuth mAuth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
       // progressBar = new ProgressBar(SignUpActivity.this,null,android.R.attr.progressBarStyleLarge);

        editTextFirstName = (EditText) findViewById(R.id.first_name_register);
        editTextLastName = (EditText) findViewById(R.id.last_name_register);
        editTextEmail = (EditText) findViewById(R.id.email_register);
        editTextPassword = (EditText) findViewById(R.id.password_register);
        //Add variable for college spinner!!

        Button btn = (Button)findViewById(R.id.RegisterBtn); //Button to submit registration

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //A button to view the session history screen
                registerUser();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

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

        //All validations are passed

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            //user is successfully registered and logged in
                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class); //start main activity
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            SignUpActivity.this.finish();
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
}
