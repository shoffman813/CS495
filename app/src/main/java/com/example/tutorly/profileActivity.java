package com.example.tutorly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        final EditText editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setVisibility(View.GONE);

        final EditText editTextUniversity = (EditText) findViewById(R.id.editTextUniversity);
        editTextUniversity.setVisibility(View.GONE);

        final EditText editTextBio = (EditText) findViewById(R.id.editTextBio);
        editTextBio.setVisibility(View.GONE);

        final CalendarView calendarView1 = (CalendarView) findViewById(R.id.calendarView1);
        calendarView1.setVisibility(View.GONE);


        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setVisibility(View.VISIBLE);
                editTextUniversity.setVisibility(View.VISIBLE);
                editTextBio.setVisibility(View.VISIBLE);
                calendarView1.setVisibility(View.VISIBLE);

            }
        });
    }
}
