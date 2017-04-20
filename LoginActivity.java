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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button signin;
    private EditText email1;
    private EditText password1;
    private TextView signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email1 = (EditText)findViewById(R.id.email);
        password1=(EditText)findViewById(R.id.password);
        signin=(Button)findViewById(R.id.signin);
        signup=(TextView)findViewById(R.id.signup);
        progressDialog = new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }
    private void userLogin(){
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
        progressDialog.setMessage("Signing In...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                    if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                    }
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view==signin)
        {
            userLogin();
        }
        if(view==signup)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
