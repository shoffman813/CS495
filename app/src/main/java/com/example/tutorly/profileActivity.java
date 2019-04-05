package com.example.tutorly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*Adds bottom navigation bar to profile screen*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item: //Open Profile screen when button is pressed
                                startActivity(new Intent(profileActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item: //Open Settings screen when button is pressed
                                startActivity(new Intent(profileActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item: //Open Home screen when button is pressed
                                startActivity(new Intent(profileActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item: //Open Scheduled Sessions screen when button is pressed
                                startActivity(new Intent(profileActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item: //Open Requested Sessions screen when button is pressed
                                startActivity(new Intent(profileActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });

    }
}
