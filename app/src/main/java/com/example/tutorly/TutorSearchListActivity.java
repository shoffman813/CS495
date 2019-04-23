package com.example.tutorly;

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
public class TutorSearchListActivity extends AppCompatActivity {

    /*Declaring Variables*/
    //String classCode, classNumber;
    RecyclerView recyclerView;
    TutorAdapter adapter;
    List<Tutor> tutorList;
    List<Course> uidList;
    DatabaseReference dbTutors, dbCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_search_list);

        String classCode = getIntent().getStringExtra("class_code");
        String classNumber = getIntent().getStringExtra("class_number");

        tutorList = new ArrayList<>();
        uidList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //For recycler view, not elements inside it
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TutorAdapter(this, tutorList);
        recyclerView.setAdapter(adapter);

        dbTutors = FirebaseDatabase.getInstance().getReference("tutors"); //Getting reference to Tutors node
        dbCourses = FirebaseDatabase.getInstance().getReference("classes"); //Getting reference to Classes node

        Query query1 = dbCourses.orderByChild("courseCodeAndNum").equalTo(classCode + classNumber);

        query1.addValueEventListener(valueEventListener2);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          // tutorList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Tutor tutor = snapshot.getValue(Tutor.class);
                    tutorList.add(tutor);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Course course = snapshot.getValue(Course.class);
                    if(course != null) {
                        Query query2 = dbTutors.orderByChild("uid").equalTo(course.getUid()); //Doesn't work yet
                        query2.addValueEventListener(valueEventListener);
                    }
                    //uidList.add(course);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
