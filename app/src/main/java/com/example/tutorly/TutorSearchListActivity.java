package com.example.tutorly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    String classCode, classNumber;
    RecyclerView recyclerView;
    TutorAdapter adapter;
    List<Tutor> tutorList;
    DatabaseReference dbTutors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_search_list);

        classCode = getIntent().getStringExtra("class_code");
        classNumber = getIntent().getStringExtra("class_number");


        tutorList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //For recycler view, not elements inside it
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TutorAdapter(this, tutorList);
        recyclerView.setAdapter(adapter);

        dbTutors = FirebaseDatabase.getInstance().getReference("tutors"); //Getting reference to Tutors node

        Query query = dbTutors.orderByChild("classList/courseCodeAndNum").equalTo(classCode + classNumber); //Doesn't work yet

        query.addListenerForSingleValueEvent(valueEventListener);

        /*The following code is for testing
        Tutor tutor1 = new Tutor();
        tutor1.setShortBio("I'm a third year CS student with experience tutoring in Algebra I and II.");
        tutor1.setFirstName("Sarah");
        tutor1.setLastName("Jones");

        tutorList.add(tutor1);

        Tutor tutor2 = new Tutor();
        tutor2.setShortBio("I'm a fourth year Marketing Major looking to tutor in English");
        tutor2.setFirstName("Dave");
        tutor2.setLastName("Johnson");

        tutorList.add(tutor2);

        End Testing Code*/
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            tutorList.clear();
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

}
