package com.example.tutorly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SessionsScheduledActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions_scheduled);

        Button btn = (Button)findViewById(R.id.SessionHistoryBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SessionsScheduledActivity.this, SessionHistoryActivity.class));
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item:
                                startActivity(new Intent(SessionsScheduledActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item:
                                startActivity(new Intent(SessionsScheduledActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item:
                                startActivity(new Intent(SessionsScheduledActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item:
                                startActivity(new Intent(SessionsScheduledActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item:
                                startActivity(new Intent(SessionsScheduledActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }
}
