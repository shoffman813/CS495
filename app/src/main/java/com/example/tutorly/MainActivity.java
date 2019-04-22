package com.example.tutorly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*The home search screen for user to find a tutor*/
public class MainActivity extends AppCompatActivity {

    /*Variable Declarations*/
    FirebaseAuth mAuth;
    EditText classCodeEditText, classNumberEditText;
    Button searchBtn;
    String classCode, classNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        classCodeEditText = (EditText) findViewById(R.id.class_number_main);
        classNumberEditText = (EditText) findViewById(R.id.class_number_main);
        searchBtn = (Button) findViewById(R.id.searchTutorsBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass data to TutorSearchListActivity
                Intent intent = new Intent(MainActivity.this, TutorSearchListActivity.class); //start search list activity
                intent.putExtra("class_code", classCode);
                intent.putExtra("class_number", classNumber);
                startActivity(intent);
            }
        });

        /*Code to add bottom navigation bar to the Main screen*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item: //Open Profile screen when button is pressed
                                startActivity(new Intent(MainActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item: //Open Settings screen when button is pressed
                                startActivity(new Intent(MainActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item: //Open Home screen when button is pressed
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item: //Open Scheduled Sessions screen when button is pressed
                                startActivity(new Intent(MainActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item: //Open Requested Sessions screen when button is pressed
                                startActivity(new Intent(MainActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });

    }

    /*Overriding onStart to send user to login screen if not logged in*/
    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
