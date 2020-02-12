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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        buttonUser.setOnClickListener(clik);
        buttonService.setOnClickListener(clik);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public View.OnClickListener clik = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonUser:
                    buttonUser.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    buttonService.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getBaseContext(), "User mode Selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonServices:
                    buttonService.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    buttonUser.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getBaseContext(), "Services mode Selected", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
