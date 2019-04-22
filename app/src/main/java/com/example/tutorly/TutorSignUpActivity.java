package com.example.tutorly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class TutorSignUpActivity extends AppCompatActivity {

    /*Variable Declarations*/
    EditText tutorBioEditText, tutorPayRateEditText, classTitleEditText, classCodeEditText, classNumberEditText;
    Button addCourseBtn, registerTutorBtn;
    String tutorBio, payRate, classTitle, classCode, classNumberString;
    int classNumber;
    Tutor tutor;
    ListView classList;
    ArrayList<String> arrayList; //Holds course description that tutor enters
    ArrayAdapter<String> arrayAdapter;
    private FirebaseAuth mAuth;
    FirebaseUser fUser;
    DatabaseReference databaseUsers, databaseTutors, databaseCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_sign_up);

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users"); //reference to user database
        databaseTutors = FirebaseDatabase.getInstance().getReference("tutors"); //reference to tutor database
        databaseCourses = FirebaseDatabase.getInstance().getReference("classes"); //Reference to classes database

        tutorBioEditText = (EditText) findViewById(R.id.short_tutor_bio);
        classTitleEditText = (EditText) findViewById(R.id.course_title);
        classCodeEditText = (EditText) findViewById(R.id.class_code);
        classNumberEditText = (EditText) findViewById(R.id.class_number);
        tutorPayRateEditText = (EditText) findViewById(R.id.pay_rate);
        tutor = new Tutor();

        classList = (ListView) findViewById(R.id.class_list);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        classList.setAdapter(arrayAdapter);

        addCourseBtn = (Button)findViewById(R.id.AddCourseBtn); //Button to add a course to list
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //When tutor clicks button to sign up
                addCourse();
            }
        });

        //Add onitemclicklistener so if tutor clicks a list item, it is deleted
        //from the Tutor object and the listView screen
        //Also add transparent X to listview item

        registerTutorBtn = (Button) findViewById(R.id.RegisterTutorBtn);
        registerTutorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTutor();
            }
        });
    }

    private void registerTutor() {
        payRate = tutorPayRateEditText.getText().toString().trim(); //Saving tutor pay rate to string
        tutorBio = tutorBioEditText.getText().toString().trim(); //Saving tutor bio to string

        /*Validating information*/
        if(tutorBio.isEmpty()) { //No bio has been entered
            tutorBioEditText.setError("Field is required");
            tutorBioEditText.requestFocus();
            return;
        }

        if(payRate.isEmpty()) { //No pay rate has been entered
            tutorPayRateEditText.setError("Field is required");
            tutorPayRateEditText.requestFocus();
            return;
        }

        if(arrayList.isEmpty()) { //No courses have been entered
            classTitleEditText.setError("You must enter at least one class");
            classTitleEditText.requestFocus();
            return;
        }

        tutor.setShortBio(tutorBio); //Saving description to tutor object
        tutor.setPayRate(payRate); //Saving pay rate to tutor object

        FirebaseUser fUser = mAuth.getCurrentUser(); //Getting current user
        final String currentUID = fUser.getUid(); //Getting user uid

        tutor.setUID(currentUID);

        tutor.name = fUser.getDisplayName();
        tutor.setEmail(fUser.getEmail());

       // tutor.setFirstName(databaseUsers.child(currentUID).child("firstName").getValue(String.class);

        databaseTutors.child(currentUID).setValue(tutor); //Saving tutor to database under FireBase uid

        databaseUsers.child(currentUID).child("isTutor").setValue(1); //Changing isTutor value for user to 1

        Toast.makeText(TutorSignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

        TutorSignUpActivity.this.finish(); //So user cannot return to sign up screen
        Intent intent = new Intent(TutorSignUpActivity.this, AccountActivity.class); //start account activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent); //Open the account screen, user is now registered as a tutor
    }

    /*Adds a course to a tutor's list of courses and displays in a list on the registration screen*/
    private void addCourse() {
        /*Saving course information in strings*/
        classTitle = classTitleEditText.getText().toString().trim();
        classCode = classCodeEditText.getText().toString().trim();
        classNumberString = classNumberEditText.getText().toString().trim();

        /*Validating course information*/
        if(classTitle.isEmpty()) { //If tutor did not enter class title
            classTitleEditText.setError("Class title is required");
            classTitleEditText.requestFocus();
            return;
        }

        if(classCode.isEmpty()) { //If tutor did not enter class code
            classCodeEditText.setError("Class code is required");
            classCodeEditText.requestFocus();
            return;
        }

        if(classNumberString.isEmpty()) {
            classNumberEditText.setError("Class number is required");
            classNumberEditText.requestFocus();
            return;
        }

        classNumber = Integer.parseInt(classNumberString); //Converting class number to integer

        Course course = new Course(classTitle, classCode, classNumber, fUser.getUid()); //Creating new course object

        course.setCourseCodeAndNum();

        String id = databaseCourses.push().getKey();

        databaseCourses.child(id).setValue(course);

        arrayList.add(course.getCourseTitle() + " (" + course.getCourseCode()
                +" " + course.getCourseNumber() + ")");

        arrayAdapter.notifyDataSetChanged();

        classTitleEditText.getText().clear();
        classCodeEditText.getText().clear();
        classNumberEditText.getText().clear();

        tutor.addClass(course); //Adding course to tutor object's list of courses
    }
}
