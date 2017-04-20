package com.example.kashishgoyal.utilitytools;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signup;
    private EditText email1;
    private EditText password1;
    private TextView signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        signup = (Button)findViewById(R.id.signup);
        email1 =(EditText) findViewById(R.id.email);
        password1=(EditText) findViewById(R.id.password);
        signin=(TextView)findViewById(R.id.signin);
        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private void registerUser(){
        String email = email1.getText().toString().trim();
        String password = password1.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please Enter your Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please Enter your Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){

                  Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                  finish();
                  startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
              }else{

                  Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
              }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==signup){
            registerUser();
        }
        if(view==signin){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}

