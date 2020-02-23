package com.example.zarurat1.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zarurat1.Adapter.KhataAmountCustomAdapter;
import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class KhataDetailsActivity extends AppCompatActivity {

    TextView textViewAmount,textViewName;
    ImageView imageViewShare;
    Button btnTake, btnGive,btnSave;
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText editTextAmount,editTextDescription;
    ArrayList<KhataPojo> arrayList = new ArrayList<KhataPojo>();
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerViewAmount;


    int give=0,take=0;
    static int sumAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khata_details);

        Toolbar toolbar1 = findViewById(R.id.toolbarKhataDetails);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("myprf", MODE_PRIVATE);
        String nameUser=sharedPreferences.getString("name",null);
        String mobileUser=sharedPreferences.getString("mobile",null);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("khataInfo").child(nameUser+mobileUser);

        btnTake=findViewById(R.id.btnTake);
        btnGive=findViewById(R.id.btnGive);
        btnSave=findViewById(R.id.btnSave);
        textViewAmount=findViewById(R.id.textFinalAmount);
        editTextAmount=findViewById(R.id.edittextAmount);
        editTextDescription=findViewById(R.id.edittextDescription);
        textViewName=findViewById(R.id.textkhataDetailName);
        imageViewShare=findViewById(R.id.btnShare);
        recyclerViewAmount=findViewById(R.id.recyclerviewAmount);


        recyclerViewAmount.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewAmount.setLayoutManager(layoutManager);


        btnGive.setOnClickListener(clik);
        btnTake.setOnClickListener(clik);

        Intent intent = getIntent();
        String descriptionMain = intent.getStringExtra("description");
        String name = intent.getStringExtra("name");
        String bamount = intent.getStringExtra("bamount");
        String famount = intent.getStringExtra("famount");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String email = intent.getStringExtra("email");
        String mobileNo = intent.getStringExtra("mobileNo");


        textViewName.setText(name);

        imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(give==1||take==1) {
                    save();
                }
                else{
                    Toast.makeText(KhataDetailsActivity.this, "Please select amount type", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadAmountList();

        findSum();

        if(sumAmount<0){
            textViewAmount.setText(""+sumAmount );
            textViewAmount.setTextColor(getResources().getColor(R.color.colorRed));
        }else{
            textViewAmount.setText(""+sumAmount );
            textViewAmount.setTextColor(getResources().getColor(R.color.colorGreenDark));
        }

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateFAmount(sumAmount);

    }

    public void findSum() {
        Intent intent = getIntent();
        String mobileNo = intent.getStringExtra("mobileNo");
        String bamt=intent.getStringExtra("bamount");
        if(bamt.charAt(0) == '-'){
            sumAmount=-Integer.parseInt(bamt.substring(1));
            Log.d("1234", "1-.findSum: "+sumAmount);

        }else if(bamt.charAt(0)== '+'){
            sumAmount=Integer.parseInt(bamt.substring(1));
            Log.d("1234", "2+.findSum: "+sumAmount);
        }

        reference.child(mobileNo).child("amountList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren() ){
                    KhataPojo khataPojo = dataSnapshot1.getValue(KhataPojo.class);
                    String amt = khataPojo.getFAmount();
                    Log.d("1234", "data: "+amt);
                    if(amt.charAt(0) == '-'){
                        sumAmount=sumAmount-Integer.parseInt(amt.substring(1));
                        Log.d("1234", "3-.for loop: "+sumAmount);

                    }else if(amt.charAt(0)== '+'){
                        sumAmount=sumAmount+Integer.parseInt(amt.substring(1));
                        Log.d("1234", "4+.for loop: "+sumAmount);
                    }
                }
                Log.d("1234", "5+.for loop: "+sumAmount);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateFAmount(int x) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(x<0){
            map.put("famount", "-"+x);

        }else{
            map.put("famount", "+"+x);
        }
        Intent intent = getIntent();
        final String mobileNo = intent.getStringExtra("mobileNo");
        reference.child(mobileNo).updateChildren(map);
    }

    public void loadAmountList() {
        Intent intent = getIntent();
        String mobileNo = intent.getStringExtra("mobileNo");
        final String descriptionMain = intent.getStringExtra("description");
        String name = intent.getStringExtra("name");
        final String bamount = intent.getStringExtra("bamount");
        String famount = intent.getStringExtra("famount");
        final String date = intent.getStringExtra("date");
        final String time = intent.getStringExtra("time");
        String email = intent.getStringExtra("email");


        reference.child(mobileNo).child("amountList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                Log.d("1234", "onDataChange: ref ");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("1234", "onDataChange: for " + dataSnapshot1);
                    KhataPojo khataPojo = dataSnapshot1.getValue(KhataPojo.class);
                    arrayList.add(khataPojo);
                }
                Log.d("1234", "onDataChange: " + arrayList.size());
                adapter = new KhataAmountCustomAdapter(KhataDetailsActivity.this, arrayList);
                recyclerViewAmount.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(KhataDetailsActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public View.OnClickListener clik = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnGive:
                    btnGive.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    btnTake.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    give=1;
                    take=0;
                    break;
                case R.id.btnTake:
                    btnGive.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    btnTake.setBackgroundColor(getResources().getColor(R.color.colorGreenDark));
                    take=1;
                    give=0;
                    break;
            }
        }
    };


    public void save() {
        final ProgressDialog progressDialog = new ProgressDialog(KhataDetailsActivity.this);
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

        String dateTime=day+""+month+""+hr+""+min;

        final String amount = editTextAmount.getText().toString();
        final String description = editTextDescription.getText().toString();


        if (amount.length() == 0) {
            editTextAmount.setError("please enter minimum base amount");
            progressDialog.cancel();
            editTextAmount.requestFocus();

        } else {
            Intent intent = getIntent();
            String mobileNo = intent.getStringExtra("mobileNo");

            KhataPojo khataPojo = new KhataPojo();
            khataPojo.setDescription(description);
            khataPojo.setTime(time);
            khataPojo.setDate(date);
            if (take == 1) {
                khataPojo.setFAmount("+" + amount);
            }
            if (give == 1) {
                khataPojo.setFAmount("-" + amount);
            }
            khataPojo.setDate(date);
            khataPojo.setTime(time);
            khataPojo.setDescription(description);
            reference.child(mobileNo).child("amountList").child(dateTime).setValue(khataPojo);
            progressDialog.cancel();
            Toast.makeText(KhataDetailsActivity.this, "Save Successfully", Toast.LENGTH_SHORT).show();
            editTextAmount.setText(null);
            editTextDescription.setText(null);

        }
    }
}
