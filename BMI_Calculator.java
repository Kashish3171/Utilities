package com.example.kashishgoyal.utilitytools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMI_Calculator extends AppCompatActivity implements View.OnClickListener{
     private EditText height;
    private EditText weight;
    private Button bmi;
    private TextView result;
    private TextView opinion;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__calculator);
        height=(EditText)findViewById(R.id.height);
        weight=(EditText)findViewById(R.id.weight);
        bmi=(Button)findViewById(R.id.bmi);
        result=(TextView)findViewById(R.id.result);
        opinion=(TextView)findViewById(R.id.opi);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        bmi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==bmi){
            double weight1;
            double height1;
            double bmi1;
            String opinion1="Sorry,not working";
            if(height.getText().toString().equals("")||weight.getText().toString().equals(""))
            {
                Toast.makeText(getApplicationContext(),"No valid values",Toast.LENGTH_SHORT);
            }
            else
            {
                weight1=Double.parseDouble(weight.getText().toString());
                height1=Double.parseDouble(height.getText().toString());
                bmi1=height1*height1;
                bmi1=weight1/bmi1;
                result.setText(String.valueOf(bmi1));
                if(bmi1<18.5){
                    opinion1="Underweight :(";

                }
                else if(bmi1>=18.5 && bmi1<25)
                {
                    opinion1="Normal :)";}
                else if(bmi1>=25)
                {
                    opinion1="Overweight :(";
                }
                opinion.setText(opinion1);
            }
            }
        if(view==back)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        }
    }

