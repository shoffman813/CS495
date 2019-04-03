package com.example.tutorly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SessionRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_requests);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item:
                                startActivity(new Intent(SessionRequestsActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item:
                                startActivity(new Intent(SessionRequestsActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item:
                                startActivity(new Intent(SessionRequestsActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item:
                                startActivity(new Intent(SessionRequestsActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item:
                                startActivity(new Intent(SessionRequestsActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }
}
