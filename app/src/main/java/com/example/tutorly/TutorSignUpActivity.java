package com.example.tutorly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TutorSignUpActivity extends AppCompatActivity {

    /*Variable Declarations*/
    EditText tutorBioEditText, classTitleEditText, classCodeEditText, classNumberEditText;
    Button addCourseBtn, registerTutorBtn;
    String tutorBio, classTitle, classCode, classNumberString;
    int classNumber;
    Tutor tutor;
    //Course course;
    ListView classList;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_sign_up);

        tutorBioEditText = (EditText) findViewById(R.id.short_tutor_bio);
        classTitleEditText = (EditText) findViewById(R.id.course_title);
        classCodeEditText = (EditText) findViewById(R.id.class_code);
        classNumberEditText = (EditText) findViewById(R.id.class_number);
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

        registerTutorBtn = (Button) findViewById(R.id.RegisterTutorBtn);

        registerTutorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registerTutor();
            }
        });
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

        Course course = new Course(classTitle, classCode, classNumber); //Creating new course object

        arrayList.add(course.getCourseTitle() + " (" + course.getCourseCode()
                +" " + course.getCourseNumber() + ")");

        arrayAdapter.notifyDataSetChanged();

        classTitleEditText.getText().clear();
        classCodeEditText.getText().clear();
        classNumberEditText.getText().clear();

        tutor.addClass(course); //Adding course to tutor object's list of courses
    }
}
