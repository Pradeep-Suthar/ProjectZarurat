package com.example.zarurat1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zarurat1.R;

public class LoginActivity extends AppCompatActivity {
    TextView textViewSignup;
    Button buttonSignin, buttonUser, buttonService;
    EditText editTextEmail, editTextPwd;
    private int buttonState = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewSignup=findViewById(R.id.textClickLogin);
        buttonSignin=findViewById(R.id.btnsignin);
        buttonUser=findViewById(R.id.buttonUser);
        buttonService=findViewById(R.id.buttonServices);

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonState % 2 == 0){
                    buttonUser.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    buttonService.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getBaseContext(), "User mode Selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    buttonUser.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    buttonService.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Toast.makeText(getBaseContext(), "User mode Unselected", Toast.LENGTH_SHORT).show();
                }
                buttonState++;
            }
        });

        buttonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonState % 2 == 0){
                    buttonService.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    buttonUser.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getBaseContext(), "Services mode Selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    buttonService.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    buttonUser.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Toast.makeText(getBaseContext(), "Services mode Selected", Toast.LENGTH_SHORT).show();
                }
                buttonState++;
            }
        });

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ModeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
