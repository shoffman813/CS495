package com.example.tutorly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.RecentlyNonNull;

/*Screen where a user can request a tutoring session with a tutor*/
public class RequestTutorActivity extends AppCompatActivity {

    TextView tutorNameTextView;
    EditText meetingLocationEditText, sessionStartEditText, sessionStartAmOrPmEditText, sessionEndEditText,
        sessionEndAmOrPmEditText, sessionMessageEditText;
    CalendarView calendar;
    Button requestTutoringSessionBtn;
    int meetingMonth, meetingDay, meetingYear, isChanged;
    String meetingLocation, sessionStart, startAmOrPm, sessionEnd, endAmOrPm, sessionMessage;
    private FirebaseAuth mAuth;
    FirebaseUser fUser;
    DatabaseReference databaseTutorSessions, databaseUserSessions;

    Session session;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tutor);

        isChanged = 0;
        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        databaseTutorSessions = FirebaseDatabase.getInstance().getReference("tutorSessions"); //reference to sessions saved under tutor uid
        databaseUserSessions = FirebaseDatabase.getInstance().getReference("userSessions"); //reference to sessions saved under user uid

        String tutorName = getIntent().getStringExtra("tutor_name"); //Saved from TutorSearchListActivity

        tutorNameTextView = (TextView) findViewById(R.id.request_tutor2);
        tutorNameTextView.setText("with " + tutorName); //Display tutor name on the screen

        calendar = (CalendarView) findViewById(R.id.calendar);
        meetingLocationEditText = (EditText) findViewById(R.id.meetingLocationEditText);
        sessionStartEditText = (EditText) findViewById(R.id.sessionStartEditText);
        sessionStartAmOrPmEditText = (EditText) findViewById(R.id.sessionStartAmOrPmEditText);
        sessionEndEditText = (EditText) findViewById(R.id.sessionEndEditText);
        sessionEndAmOrPmEditText = (EditText) findViewById(R.id.sessionEndAmOrPmEditText);
        sessionMessageEditText = (EditText) findViewById(R.id.sessionRequestMessageEditText);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { //When the calendar date is changed
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                meetingMonth = month;
                meetingDay = dayOfMonth;
                meetingYear = year;
                isChanged = 1;
            }
        });

        requestTutoringSessionBtn = (Button) findViewById(R.id.requestTutorBtn);
        requestTutoringSessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSession();
            }
        });


    }

    private void addSession() {

        String tutorUid = getIntent().getStringExtra("tutor_uid"); //Saved from TutorSearchListActivity

        meetingLocation = meetingLocationEditText.getText().toString().trim();
        sessionStart = sessionStartEditText.getText().toString().trim();
        startAmOrPm = sessionStartAmOrPmEditText.getText().toString().trim();
        sessionEnd = sessionEndEditText.getText().toString().trim();
        endAmOrPm = sessionEndAmOrPmEditText.getText().toString().trim();
        sessionMessage = sessionMessageEditText.getText().toString().trim();

        String tutorName2 = getIntent().getStringExtra("tutor_name"); //Saved from TutorSearchListActivity

        if(isChanged == 0) {
            Toast.makeText(getApplicationContext(), "You must select a date", Toast.LENGTH_SHORT).show();
            return;
        }

        if(meetingLocation.isEmpty()) {
            meetingLocationEditText.setError("Field is required");
            meetingLocationEditText.requestFocus();
            return;
        }

        if(sessionStart.isEmpty()) {
            sessionStartEditText.setError("Field is required");
            sessionStartEditText.requestFocus();
            return;
        }

        if(startAmOrPm.isEmpty()) {
            sessionStartAmOrPmEditText.setError("Field is required");
            sessionStartAmOrPmEditText.requestFocus();
            return;
        }

        if(sessionEnd.isEmpty()) {
            sessionEndEditText.setError("Field is required");
            sessionEndEditText.requestFocus();
            return;
        }

        if(endAmOrPm.isEmpty()) {
            sessionEndAmOrPmEditText.setError("Field is required");
            sessionEndAmOrPmEditText.requestFocus();
            return;
        }

        if(sessionMessage.isEmpty()) {
            sessionMessageEditText.setError("Field is required");
            sessionMessageEditText.requestFocus();
            return;
        }

        /*All validations are passed*/

        session.setTutorName(tutorName2);
        session.setUserName(fUser.getDisplayName());
        session.setMeetingDay(meetingDay);
        session.setMeetingMonth(meetingMonth);
        session.setMeetingYear(meetingYear);
        session.setMeetingStartTime(sessionStart + " " + startAmOrPm);
        session.setMeetingEndTime(sessionEnd + " " + endAmOrPm);
        session.setMeetingLocation(meetingLocation);
        session.setSessionRequestMessage(sessionMessage);

        session.setUserUid(fUser.getUid());
        session.setTutorUid(tutorUid);

        session.isRequested = true;
        session.isConfirmed = false;
        session.isDenied = false;

        databaseTutorSessions.child(tutorUid).setValue(session);
        databaseUserSessions.child(fUser.getUid()).setValue(session);

        Toast.makeText(this, "Session Requested Successfully", Toast.LENGTH_SHORT).show();

        RequestTutorActivity.this.finish(); //So user cannot return to request tutor activity screen
        Intent intent = new Intent(RequestTutorActivity.this, MainActivity.class); //start account activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent); //Open the main screen, session has been requested

    }
}
