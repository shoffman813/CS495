package com.example.tutorly;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RequestTutorActivity extends AppCompatActivity {

    TextView tutorNameTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tutor);

        String tutorUid = getIntent().getStringExtra("tutor_uid");
        String tutorName = getIntent().getStringExtra("tutor_name");

        tutorNameTextView = (TextView) findViewById(R.id.request_tutor2);
        tutorNameTextView.setText("with " + tutorName);


    }
}
