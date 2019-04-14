package com.example.tutorly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*Class for the account screen*/
public class AccountActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button btn = (Button)findViewById(R.id.logout_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Button to login to app
                SharedPreferences preferences = getSharedPreferences(AccountActivity.PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();

                Intent intent = new Intent(AccountActivity.this, MainActivity.class); //Opens login screen upon start
                startActivity(intent);
                AccountActivity.this.finish();
            }
        });

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

        Button submit = (Button)findViewById(R.id.button4); //Saves information to account screen
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
        });
    }
}
