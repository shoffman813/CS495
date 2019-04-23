package com.example.tutorly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TutorListProfileActivity extends AppCompatActivity {

    TextView tutorName, tutorRate, tutorBio;
    Button requestSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list_profile);

        tutorName = (TextView) findViewById(R.id.tutorName);
        tutorRate = (TextView) findViewById(R.id.tutorRate);

    }
}
