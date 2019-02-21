package com.example.tutorly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        Button btn = (Button)findViewById(R.id.ProfileBtn);
        Button btn2 = (Button)findViewById(R.id.AccountBtn);

        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, profileActivity.class));
                }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, AccountActivity.class));
                }
        });
    }
}
