package com.example.tutorly;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/*Screen where the user can login to app*/
public class LoginActivity extends AppCompatActivity {

    /*Variable Declarations*/
    FirebaseAuth mAuth;
    EditText editTextEmail;
    EditText editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        /*Connecting EditText variables to screen text boxes*/
        editTextEmail = (EditText) findViewById(R.id.email_login);
        editTextPassword = (EditText) findViewById(R.id.password_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button btn = (Button)findViewById(R.id.login_button); //Button to log in user

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Button to login to app
                userLogin(); //Verifies user credentials and logs in user
            }
        });

        TextView textViewRegister = (TextView) findViewById(R.id.textViewRegister); //For new user going to sign up page

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //A button to send user to registration screen
                LoginActivity.this.finish();
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class)); //Opens SignUpActivity
            }
        });

    }

    /*Verifies user credentials and logs in user*/
    private void userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        /*Validating email and password*/

        if(email.isEmpty()) { //Email field is empty
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { //If entered email is a real email
            editTextEmail.setError("Please enter valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)) { //Password field is empty
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) { //Password entered is less than 6 characters
            editTextPassword.setError("Minimum length of password is 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        /*All validations are passed*/

        progressBar.setVisibility(View.VISIBLE); //Show the loading wheel while verifying user credentials

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE); //Upon completion of verification, hide loading wheel
                if(task.isSuccessful() ) {
                    finish(); //So user can't return to login screen after logging in

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent); //Open MainActivity
                }
                else { //Login Failed
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /*Overriding onStart to go to MainActivity if user is already logged in*/
    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null) { //If user is already logged in
            finish();
            startActivity(new Intent(this, MainActivity.class)); //Go to MainActivity
        }
    }
}

