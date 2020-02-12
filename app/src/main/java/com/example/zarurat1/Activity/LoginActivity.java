package com.example.zarurat1.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zarurat1.Pojo.UserInfoPojo;
import com.example.zarurat1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextView textViewSignup;
    Button buttonSignin, buttonUser, buttonService;
    EditText editTextEmail, editTextPwd;
    int flag = 0;
    int modeselect=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String mode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewSignup=findViewById(R.id.textClickLogin);
        buttonSignin=findViewById(R.id.btnsignin);
        buttonUser=findViewById(R.id.buttonUser);
        buttonService=findViewById(R.id.buttonServices);
        editTextEmail=findViewById(R.id.loginemail);
        editTextPwd=findViewById(R.id.loginPwd);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(mode);

        buttonUser.setOnClickListener(clik);
        buttonService.setOnClickListener(clik);

        SharedPreferences sharedPreferences1=getSharedPreferences("myprf",MODE_PRIVATE);
        Boolean status=sharedPreferences1.getBoolean("loginStatus",false);
        if(status==true){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ModeActivity.class);
                startActivity(intent);
            }
        });

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                setLogin();
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
                    mode="userInfo";
                    modeselect=1;
                    Toast.makeText(getBaseContext(), "User mode Selected", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonServices:
                    buttonService.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    buttonUser.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mode="serviceProviderInfo";
                    modeselect=1;
                    Toast.makeText(getBaseContext(), "Services mode Selected", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void setLogin() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final String email=editTextEmail.getText().toString();
        final String password=editTextPwd.getText().toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren() ){
                    UserInfoPojo userPojo=dataSnapshot1.getValue(UserInfoPojo.class);
                    String ids=userPojo.getId();
                    String names=userPojo.getName();
                    String emails=userPojo.getEmail();
                    String mobiles=userPojo.getMoble_no();
                    String dob=userPojo.getDob();
                    String pwds=userPojo.getPwd();
                    String urls=userPojo.getImg();
                    if(email.equals(emails)&&password.equals(pwds)){
                        SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("id",ids);
                        editor.putString("name",names);
                        editor.putString("email",emails);
                        editor.putString("mobile",mobiles);
                        editor.putString("pwd",pwds);
                        editor.putString("pin",dob);
                        editor.putString("imgUrl",urls);
                        editor.putBoolean("loginStatus",true);
                        editor.commit();
                        flag=1;
                        break;
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Login Error" ,Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                }
                if(modeselect==0){
                    Toast.makeText(LoginActivity.this, "Please Select Login Type", Toast.LENGTH_SHORT).show();
                }
                if(flag==1 && modeselect==1) {
                    progressDialog.cancel();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
