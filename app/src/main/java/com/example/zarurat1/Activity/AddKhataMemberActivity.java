package com.example.zarurat1.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zarurat1.Fragment.KhataFragment;
import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.regex.Pattern;

public class AddKhataMemberActivity extends AppCompatActivity {

    EditText editTextName,editTextMobile,editTextEmail,editTextAmount,editTextDescription;
    Button buttonGive,buttonTake, buttonSave;
    int give=0,take=0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    static final Pattern s1 = Pattern.compile("^[A-Za-z\\s]+$");
    static final Pattern s3 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    static final Pattern s4 = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?"        // +<digits><sdd>*
            + "(\\([0-9]+\\)[\\- \\.]*)?"   // (<digits>)<sdd>*
            + "([0-9][0-9\\- \\.]+[0-9])");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_khata_member);


        Toolbar toolbar1 = findViewById(R.id.toolbarKhataAdd);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Add Member");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("khataInfo");

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        editTextName = findViewById(R.id.khataMembername);
        editTextMobile = findViewById(R.id.khataMembermobile);
        editTextEmail = findViewById(R.id.khataMemberEmail);
        editTextAmount = findViewById(R.id.baseprice);
        buttonGive = findViewById(R.id.buttonGive);
        buttonTake = findViewById(R.id.buttonTake);
        buttonSave = findViewById(R.id.buttonSave);
        editTextDescription = findViewById(R.id.KhataDescription);

        buttonGive.setOnClickListener(clik);
        buttonTake.setOnClickListener(clik);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(give==1||take==1) {
                    save();
                }
                else{
                    Toast.makeText(AddKhataMemberActivity.this, "Please select amount type", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public View.OnClickListener clik = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonGive:
                    buttonGive.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    buttonTake.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    give=1;
                    take=0;
                    break;
                case R.id.buttonTake:
                    buttonGive.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    buttonTake.setBackgroundColor(getResources().getColor(R.color.colorGreenDark));
                    take=1;
                    give=0;
                    break;
            }
        }
    };

    public void save() {
        final ProgressDialog progressDialog = new ProgressDialog(AddKhataMemberActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int hr = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        final String time = hr + ":" + min;
        final String date = day + "/" + (month + 1) + "/" + year;

        final String name=editTextName.getText().toString();
        final String mobile=editTextMobile.getText().toString();
        final String email=editTextEmail.getText().toString();
        final String amount=editTextAmount.getText().toString();
        final String description=editTextDescription.getText().toString();


        SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
        final String name1=sharedPreferences.getString("name",null);
        final String mobile1=sharedPreferences.getString("mobile",null);

        if (name.length() == 0) {
            editTextName.setError("please enter name");
            editTextName.requestFocus();
            progressDialog.cancel();
        }else if (!s1.matcher(name).matches()) {
            editTextName.setError("Please enter valid name");
            progressDialog.cancel();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("please enter valid email address");
            progressDialog.cancel();
            editTextEmail.requestFocus();
        } else if (mobile.length() < 10) {
            editTextMobile.setError("please enter 10 digit mobile number");
            progressDialog.cancel();
            editTextMobile.requestFocus();
        } else if (!s4.matcher(mobile).matches()) {
            editTextMobile.setError("Please enter valid phone number");
            progressDialog.cancel();
        }else if (amount.length()==0) {
            editTextAmount.setError("please enter minimum base amount");
            progressDialog.cancel();
            editTextAmount.requestFocus();
        }else{
            Query emailQuery=databaseReference.child(name1+mobile1).orderByChild("email").equalTo(email);
            Log.d("12345", "getSignUp:email query enter ");
            emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getChildrenCount()>0){
                        editTextEmail.setError("Email Already Khata Exist!");
                        progressDialog.cancel();
                        editTextEmail.requestFocus();
                    }
                    else{
                        KhataPojo khataPojo = new KhataPojo();
                        khataPojo.setEmail(email);
                        khataPojo.setName(name);
                        khataPojo.setMoble_no(mobile);
                        if(take==1){
                            khataPojo.setBAmount("+"+amount);
                            khataPojo.setFAmount("+"+amount);
                        }
                        if(give==1){
                            khataPojo.setBAmount("-"+amount);
                            khataPojo.setFAmount("-"+amount);
                        }
                        khataPojo.setDate(date);
                        khataPojo.setTime(time);
                        khataPojo.setDescription(description);
                        databaseReference.child(name1+mobile1).child(mobile).setValue(khataPojo);
                        progressDialog.cancel();
                        Toast.makeText(AddKhataMemberActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddKhataMemberActivity.this, KhataFragment.class);
                        startActivity(intent);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }




    }
}
