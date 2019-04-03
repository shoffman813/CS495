package com.example.tutorly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        final EditText editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setVisibility(View.GONE);

        final EditText editTextUniversity = (EditText) findViewById(R.id.editTextUniversity);
        editTextUniversity.setVisibility(View.GONE);

        final EditText editTextBio = (EditText) findViewById(R.id.editTextBio);
        editTextBio.setVisibility(View.GONE);

        final CalendarView calendarView1 = (CalendarView) findViewById(R.id.calendarView1);
        calendarView1.setVisibility(View.GONE);


        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setVisibility(View.VISIBLE);
                editTextUniversity.setVisibility(View.VISIBLE);
                editTextBio.setVisibility(View.VISIBLE);
                calendarView1.setVisibility(View.VISIBLE);

            }
        });

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
