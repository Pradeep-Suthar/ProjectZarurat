package com.example.zarurat1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zarurat1.R;

public class LoginActivity extends AppCompatActivity {
    TextView textViewSignup;
    Button buttonSignin, buttonUser, buttonService;
    EditText editTextEmail, editTextPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewSignup=findViewById(R.id.textClickLogin);
        buttonSignin=findViewById(R.id.btnsignin);

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
