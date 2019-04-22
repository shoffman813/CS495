package com.example.tutorly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*The screen to hold the list of tutor search results*/
public class TutorSearchListActivity extends AppCompatActivity {

    /*Declaring Variables*/
    String classCode, classNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_search_list);

        classCode = getIntent().getStringExtra("class_code");
        classNumber= getIntent().getStringExtra("class_number");
    }
}
