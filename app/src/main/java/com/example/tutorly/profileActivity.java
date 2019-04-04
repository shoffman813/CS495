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


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item:
                                startActivity(new Intent(profileActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item:
                                startActivity(new Intent(profileActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item:
                                startActivity(new Intent(profileActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item:
                                startActivity(new Intent(profileActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item:
                                startActivity(new Intent(profileActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });

    }
}
