package com.example.tutorly;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*Class for the account screen*/
public class AccountActivity extends AppCompatActivity {

    /*Variable Declarations*/
    FirebaseAuth mAuth;
    TextView fullNameTextView, verifiedTextView, emailTextView, phoneTextView;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();

        /*Assigning TextView fields to their variables*/
        fullNameTextView = (TextView) findViewById(R.id.full_name_profile);
        verifiedTextView = (TextView) findViewById(R.id.verified_account);
        emailTextView = (TextView) findViewById(R.id.email_account);
        phoneTextView = (TextView) findViewById(R.id.phone_number_account);

        Button becomeATutorBtn = (Button) findViewById(R.id.becomeATutorBtn); //Button for user to become a tutor

        becomeATutorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //When Become a Tutor Button is pressed
                //add logic: if button pressed & user already tutor, make toast "You are already a tutor"
                Intent intent = new Intent(AccountActivity.this, TutorSignUpActivity.class);
                startActivity(intent);
            }
        });

        Button btn = (Button)findViewById(R.id.logout_button); //Button to log out of app

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut(); //Signs out FireBase user
                AccountActivity.this.finish();
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class); //Opens login screen
                startActivity(intent);
            }
        });

        loadUserInformation(); //Loads and displays the user's information

        /*Creates the bottom navigation toolbar and links to other activities*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item: //Open Profile screen when button is pressed
                                startActivity(new Intent(AccountActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item: //Open Settings screen when button is pressed
                                startActivity(new Intent(AccountActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item: //Open Home screen when button is pressed
                                startActivity(new Intent(AccountActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item: //Open Scheduled Sessions screen when button is pressed
                                startActivity(new Intent(AccountActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item: //Open Requested Sessions screen when button is pressed
                                startActivity(new Intent(AccountActivity.this, SessionRequestsActivity.class));
                                break;
                        }
                        return true;
                    }
                });

        //???
        if(getIntent().hasExtra("com.example.tutorly.cardNum")){
            TextView tv =(TextView) findViewById(R.id.PaymentInfo);
            String cardnum = getIntent().getExtras().getString("com.example.tutorly.cardNum");
            tv.setText(cardnum);
        }

        Button payInfo = (Button)findViewById(R.id.creditCardInfoBtn); //Opens the PaymentInfoActivity screen
        payInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPaymentInfo = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(toPaymentInfo); //Opens PaymentInfoActivity
            }
        });
    }

    /*Loads user information from FireBase to AccountActivity screen*/
    private void loadUserInformation() {

        final FirebaseUser user = mAuth.getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users"); //Creating reference to users database

        if(user != null) {
            if (user.getDisplayName() != null) { //If user display name has name, display it
                fullNameTextView.setText(user.getDisplayName()); //If user exists and has name, display it
            }

            if (user.getDisplayName() == null) { //If displayName is null, retrieve name from database
                databaseUsers.child(user.getUid()).child("fullName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        fullNameTextView.setText(snapshot.getValue().toString());
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
            if(user.isEmailVerified()) {
                verifiedTextView.setText("Email Verified"); //If a user has verified their email
            } else {
                SpannableString content = new SpannableString("Email Not Verified (Click to Verify)");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                verifiedTextView.setText(content);
                verifiedTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //If a user clicks to verify their email
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() { //Send email verification
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AccountActivity.this, getString(R.string.verification_email_sent), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
            databaseUsers.addValueEventListener(new ValueEventListener() { //Displaying a user's email
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) { //Display email from FireBase user field
                    emailTextView.setText(user.getEmail());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) { //No email found
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }
    }
}
