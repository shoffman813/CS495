package com.example.tutorly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/*Class to hold a record of a user's currently scheduled sessions*/
public class SessionsScheduledActivity extends AppCompatActivity {

    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions_scheduled);

        Button btn = (Button)findViewById(R.id.SessionHistoryBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //A button to view the session history screen
                startActivity(new Intent(SessionsScheduledActivity.this, SessionHistoryActivity.class));
            }
        });

        /*Populating the sessions scheduled screen with mock data for the demo*/
/*        Session session1 = new Session("John Smith", 4, 12, 5, 30, "Gorgas Library" );
        Session session2 = new Session("Jane Doe", 4, 13, 2, 15, "Rodgers Library" );
        Session session3 = new Session("Thanos Jones", 4, 26, 6, 30, "McLure Library" );
        Session session4 = new Session("Jane Doe", 5, 1, 4, 45, "Gorgas Library" );
        Session session5 = new Session("Jimmy Walker", 5, 2, 3, 30, "McLure Library" );

        listView2 = (ListView) findViewById(R.id.scheduled_list);

        ArrayList<String> arrayList = new ArrayList<>();    */

        /*Adding the session descriptions to the ArrayList*/
/*        arrayList.add("Scheduled session with " + session1.getTutorName() + " at " + session1.getMeetingHour()
                +":" + session1.getMeetingMinute() + " at " + session1.getMeetingLocation());
        arrayList.add("Scheduled session with " + session2.getTutorName() + " at " + session2.getMeetingHour()
                +":" + session2.getMeetingMinute() + " at " + session2.getMeetingLocation());
        arrayList.add("Scheduled session with " + session3.getTutorName() + " at " + session3.getMeetingHour()
                +":" + session3.getMeetingMinute() + " at " + session3.getMeetingLocation());
        arrayList.add("Scheduled session with " + session4.getTutorName() + " at " + session4.getMeetingHour()
                +":" + session4.getMeetingMinute() + " at " + session4.getMeetingLocation());
        arrayList.add("Scheduled session with " + session5.getTutorName() + " at " + session5.getMeetingHour()
                +":" + session5.getMeetingMinute() + " at " + session5.getMeetingLocation());    */

        /*Using ArrayAdapter to populate ListView with session data*/
/*        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView2.setAdapter(arrayAdapter);   */

        /*Code to add bottom navigation bar to the sessions request screen*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item: //Open Profile screen when button is pressed
                                startActivity(new Intent(SessionsScheduledActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item: //Open Settings screen when button is pressed
                                startActivity(new Intent(SessionsScheduledActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item: //Open Home screen when button is pressed
                                startActivity(new Intent(SessionsScheduledActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item: //Open Scheduled Sessions screen when button is pressed
                                startActivity(new Intent(SessionsScheduledActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item: //Open Requested Sessions screen when button is pressed
                                startActivity(new Intent(SessionsScheduledActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }
}
