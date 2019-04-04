package com.example.tutorly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PaymentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);



        Button submit = (Button)findViewById(R.id.button6);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ccNum = (EditText) findViewById(R.id.editText3);
                String text = ccNum.getText().toString();
                text = "Pay with card number"+text;
                Intent toAcct = new Intent(getApplicationContext(), AccountActivity.class);
                toAcct.putExtra("com.example.tutorly.cardNum", text);
                startActivity(toAcct);
            }
        });

    }
}
