package com.example.tutorly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*Class to display all requested sessions*/
public class SessionRequestsActivity extends AppCompatActivity {

    ListView listView;
    private FirebaseAuth mAuth;
    FirebaseUser fUser;
    DatabaseReference databaseSessions;
    List<Session> sessionList;
    RecyclerView recyclerView;
    SessionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_requests);

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        databaseSessions = FirebaseDatabase.getInstance().getReference("sessions"); //reference to sessions saved under user uid

        sessionList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //NOW look up video about adding from firebase

        Query query = databaseSessions.orderByChild("userUid").equalTo(fUser.getUid());

        query.addValueEventListener(valueEventListener);


        /*Code to add bottom navigation bar to the sessions request screen*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item: //Open Profile screen when button is pressed
                                startActivity(new Intent(SessionRequestsActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item: //Open Settings screen when button is pressed
                                startActivity(new Intent(SessionRequestsActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item:  //Open Home screen when button is pressed
                                startActivity(new Intent(SessionRequestsActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item: //Open Scheduled Sessions screen when button is pressed
                                startActivity(new Intent(SessionRequestsActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item: //Open Requested Sessions screen when button is pressed
                                startActivity(new Intent(SessionRequestsActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Session session = snapshot.getValue(Session.class); //When tutors who tutor in specific class are found
                    sessionList.add(session); //Add tutor to tutorList
                }
                //arrayAdapter.notifyDataSetChanged();
                //ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,displayList);
                //listView.setAdapter(arrayAdapter);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //Add exception handling
        }
    };
}
