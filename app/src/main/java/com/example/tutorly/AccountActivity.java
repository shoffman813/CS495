package com.example.tutorly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profile_item:
                                startActivity(new Intent(AccountActivity.this, profileActivity.class));
                                break;
                            case R.id.settings_item:
                                startActivity(new Intent(AccountActivity.this, AccountActivity.class));
                                break;
                            case R.id.home_item:
                                startActivity(new Intent(AccountActivity.this, MainActivity.class));
                                break;
                            case R.id.scheduled_item:
                                startActivity(new Intent(AccountActivity.this, SessionsScheduledActivity.class));
                                break;
                            case R.id.requested_item:
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

        Button submit = (Button)findViewById(R.id.saveBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText uname = (EditText) findViewById(R.id.Uname);
                String text1 = uname.getText().toString();
                EditText ulname = (EditText) findViewById(R.id.Uname);
                String text2 = ulname.getText().toString();
                Intent toProf = new Intent(getApplicationContext(), profileActivity.class);
                toProf.putExtra("com.example.tutorly.cardNum", text1);
                startActivity(toProf);
            }
        });

        Button payInfo = (Button)findViewById(R.id.button4);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPaymentInfo = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(toPaymentInfo);
            }
        });
    }
}
