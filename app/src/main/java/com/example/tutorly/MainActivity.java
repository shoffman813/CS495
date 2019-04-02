package com.example.tutorly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        Button btn = (Button)findViewById(R.id.ProfileBtn);
        Button btn2 = (Button)findViewById(R.id.AccountBtn);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, profileActivity.class));
                }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, AccountActivity.class));
                }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item:
                                startActivity(new Intent(MainActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item:
                                startActivity(new Intent(MainActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item:
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item:
                                startActivity(new Intent(MainActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item:
                                startActivity(new Intent(MainActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });

    }
}
