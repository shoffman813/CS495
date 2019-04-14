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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A login screen that offers login via email/password. test
 */
public class LoginActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";

    FirebaseAuth mAuth;
    EditText editTextEmail;
    EditText editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.email_login);
        editTextPassword = (EditText) findViewById(R.id.password_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button btn = (Button)findViewById(R.id.login_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Button to login to app
                userLogin();
            }
        });

        TextView textViewRegister = (TextView) findViewById(R.id.textViewRegister); //For new user going to sign up page

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //A button to send user to registration screen
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                LoginActivity.this.finish();
            }
        });

    }

    private void userLogin() {

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

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful() ) {
                    //User has successfully logged in, save this information
                    // We need an Editor object to make preference changes.
                    SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0); // 0 - for private mode
                    SharedPreferences.Editor editor = settings.edit();

                    //Set "hasLoggedIn" to true
                    editor.putBoolean("hasLoggedIn", true);

                    // Commit the edits!
                    editor.commit();

                    //Go to main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

