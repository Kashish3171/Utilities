package com.example.kashishgoyal.utilitytools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView email1;
    private ImageView logout;
    private ImageView bmi;
    private ImageView qrcode;
    private ImageView compass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();

        email1 = (TextView) findViewById(R.id.email);
        email1.setText("Welcome" + user.getEmail());
        logout = (ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(this);
        bmi = (ImageView) findViewById(R.id.bmi);
        bmi.setOnClickListener(this);
        qrcode=(ImageView)findViewById(R.id.qr);
        qrcode.setOnClickListener(this);
        compass1=(ImageView)findViewById(R.id.compass);
        compass1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == bmi) {
            finish();
            startActivity(new Intent(getApplicationContext(), BMI_Calculator.class));

        }
        if(view==qrcode)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),QR_Code_Scanner.class));

        }
        if(view==compass1)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),compass.class));
        }


    }
}