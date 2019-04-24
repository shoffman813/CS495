package com.example.tutorly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*The screen to hold the list of tutor search results*/
public class TutorSearchListActivity extends AppCompatActivity implements TutorAdapter.OnTutorListener {

    /*Declaring Variables*/
    RecyclerView recyclerView;
    TutorAdapter adapter;
    List<Tutor> tutorList;
    DatabaseReference dbTutors, dbCourses;
    String tutorUid, tutorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_search_list);

        String classCode = getIntent().getStringExtra("class_code"); //Saved from MainActivity
        String classNumber = getIntent().getStringExtra("class_number"); //Saved from MainActivity

        tutorList = new ArrayList<>(); //List of tutors to save search results in

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //For recycler view, not elements inside it
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TutorAdapter(this, tutorList, this);
        recyclerView.setAdapter(adapter); //Set Tutor adapter for recycler view

        dbTutors = FirebaseDatabase.getInstance().getReference("tutors"); //Getting reference to Tutors node
        dbCourses = FirebaseDatabase.getInstance().getReference("classes"); //Getting reference to Classes node

        Query query1 = dbCourses.orderByChild("courseCodeAndNum").equalTo(classCode + classNumber); //Finding tutors with same class code + number

        query1.addValueEventListener(valueEventListener2); //First query the course node

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Tutor tutor = snapshot.getValue(Tutor.class); //When tutors who tutor in specific class are found
                    tutorList.add(tutor); //Add tutor to tutorList
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //Add exception handling
        }
    };

    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Course course = snapshot.getValue(Course.class); //Finding course from course database
                    if(course != null) {
                        Query query2 = dbTutors.orderByChild("uid").equalTo(course.getUid()); //Querying tutors with UIDs attached to courses
                        query2.addValueEventListener(valueEventListener); //Now query the tutors
                    }
                }
                adapter.notifyDataSetChanged(); //Update display
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //Add exception handling
        }
    };

    @Override
    public void onTutorClick(int position) { //Method for when a tutor card is clicked on
        Tutor tutor = tutorList.get(position); //Get tutor at correct recycler position
        tutorUid = tutor.getUID();
        tutorName = tutor.name;

        Intent intent = new Intent(this, RequestTutorActivity.class);
        intent.putExtra("tutor_uid", tutorUid); //Sending tutor uid to RequestTutorActivity
        intent.putExtra("tutor_name", tutorName); //Sending tutor name to RequestTutorActivity
        startActivity(intent); //Open RequestTutor screen
    }

}
