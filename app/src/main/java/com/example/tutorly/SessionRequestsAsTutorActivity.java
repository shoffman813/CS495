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
import android.widget.Button;

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

public class SessionRequestsAsTutorActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser fUser;
    DatabaseReference databaseSessions;
    RecyclerView recyclerView;
    SessionAsTutorAdapter adapter;
    List<Session> sessionList;
    Button viewAsStudentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_requests_as_tutor);

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();

        sessionList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSessionRequestsAsTutor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SessionAsTutorAdapter(this, sessionList);
        recyclerView.setAdapter(adapter);

        databaseSessions = FirebaseDatabase.getInstance().getReference("sessions"); //reference to sessions saved under user uid

        Query query = databaseSessions.orderByChild("tutorUid").equalTo(fUser.getUid());

        query.addValueEventListener(valueEventListener);

        viewAsStudentBtn = (Button) findViewById(R.id.viewAsStudentBtn);
        viewAsStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionRequestsAsTutorActivity.this, SessionRequestsActivity.class);
                startActivity(intent);
            }
        });

        /*Code to add bottom navigation bar to the sessions request screen*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_10);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item: //Open Profile screen when button is pressed
                                startActivity(new Intent(SessionRequestsAsTutorActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item: //Open Settings screen when button is pressed
                                startActivity(new Intent(SessionRequestsAsTutorActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item:  //Open Home screen when button is pressed
                                startActivity(new Intent(SessionRequestsAsTutorActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item: //Open Scheduled Sessions screen when button is pressed
                                startActivity(new Intent(SessionRequestsAsTutorActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item: //Open Requested Sessions screen when button is pressed
                                startActivity(new Intent(SessionRequestsAsTutorActivity.this, SessionRequestsActivity.class));
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
                    sessionList.add(session); //Add session to sessionList
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //Add exception handling
        }
    };
}
