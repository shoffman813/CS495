package com.example.tutorly;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*Class for the account screen*/
public class AccountActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView fullNameTextView, verifiedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
        fullNameTextView = (TextView) findViewById(R.id.full_name_profile);
        verifiedTextView = (TextView) findViewById(R.id.verified_account);

        Button btn = (Button)findViewById(R.id.logout_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Button to logout of app
                finish();
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class); //Opens login screen upon start
                startActivity(intent);
            }
        });

        loadUserInformation();

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



        if(getIntent().hasExtra("com.example.tutorly.cardNum")){
            TextView tv =(TextView) findViewById(R.id.PaymentInfo);
            String cardnum = getIntent().getExtras().getString("com.example.tutorly.cardNum");
            tv.setText(cardnum);
        }

       /* Button submit = (Button)findViewById(R.id.button4); //Saves information to account screen
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstName = (EditText) findViewById(R.id.enterFirstName);
                String text1 = firstName.getText().toString(); //Saving entered account info
                EditText lastName = (EditText) findViewById(R.id.enterLastName);
                String text2 = lastName.getText().toString(); //Saving entered account info
                Intent toProf = new Intent(getApplicationContext(), profileActivity.class);
                toProf.putExtra("com.example.tutorly.cardNum", text1);
                startActivity(toProf); //Opens profile to display saved account information
            }
        });

        Button payInfo = (Button)findViewById(R.id.saveBtn); //Opens the new Payment Information activity screen
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPaymentInfo = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(toPaymentInfo);
            }
        }); */
    }

    private void loadUserInformation() {

        final FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            if (user.getDisplayName() != null) {
                fullNameTextView.setText(user.getDisplayName());
            }
            if(user.isEmailVerified()) {
                verifiedTextView.setText("Email Verified");
            } else {
                SpannableString content = new SpannableString("Email Not Verified (Click to Verify)");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                verifiedTextView.setText(content);
                verifiedTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AccountActivity.this, getString(R.string.verification_email_sent), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }


    }
}
