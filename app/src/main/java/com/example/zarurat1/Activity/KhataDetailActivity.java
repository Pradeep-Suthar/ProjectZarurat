package com.example.zarurat1.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class KhataDetailActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    ListView khataDetailListview;
    private ProgressBar progressBar;
    ArrayList<KhataPojo> khataPojos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khata_detail);

        khataDetailListview=findViewById(R.id.khatadetailList);
        progressBar=findViewById(R.id.progressBar);

        Toolbar toolbar1 = findViewById(R.id.toolbarKhataDetails);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Khata Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);


        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
        final String name1=sharedPreferences.getString("name",null);
        final String mobile1=sharedPreferences.getString("mobile",null);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("khataInfo").child(name1+mobile1);


        progressBar.setVisibility(View.VISIBLE);
        loadAmountList();
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

                khataPojos.clear();
                Log.d("1234", "onDataChange: ref ");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("1234", "onDataChange: for " + dataSnapshot1);
                    KhataPojo khataPojo = dataSnapshot1.getValue(KhataPojo.class);
                    khataPojos.add(khataPojo);
                }
                Log.d("1234", "onDataChange: " + khataPojos.size());
                KhataAmountCustomAdapter customAdapter = new KhataAmountCustomAdapter(KhataDetailActivity.this, R.layout.khataamountdetail_layout,khataPojos);
                progressBar.setVisibility(View.GONE);
                khataDetailListview.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(KhataDetailActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
