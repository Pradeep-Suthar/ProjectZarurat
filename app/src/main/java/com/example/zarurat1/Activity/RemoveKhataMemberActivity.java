package com.example.zarurat1.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zarurat1.Adapter.KhataRemoveCustomAdapter;
import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveKhataMemberActivity extends AppCompatActivity {
    ListView khataRemoveListview;
    ArrayList<KhataPojo> khataPojos = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_khata_member);

        khataRemoveListview=findViewById(R.id.recyclerviewKhata1);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("khataInfo");


        getDataofKhata();

        KhataRemoveCustomAdapter customAdapter = new KhataRemoveCustomAdapter(this, R.layout.khataremove_layout,khataPojos);
        khataRemoveListview.setAdapter(customAdapter);


    }

    public void getDataofKhata() {
        SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
        final String name1=sharedPreferences.getString("name",null);
        final String mobile1=sharedPreferences.getString("mobile",null);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                khataPojos.clear();
                Log.d("1234", "onDataChange: ref ");
                for (DataSnapshot dataSnapshot1:dataSnapshot.child(name1+mobile1).getChildren()){
                    Log.d("1234", "onDataChange: for "+dataSnapshot1);
                    KhataPojo khataPojo = dataSnapshot1.getValue(KhataPojo.class);
                    khataPojos.add(khataPojo);
                }
                Log.d("1234", "onDataChange: "+khataPojos.size());
                KhataRemoveCustomAdapter customAdapter = new KhataRemoveCustomAdapter(RemoveKhataMemberActivity.this, R.layout.khataremove_layout,khataPojos);
                khataRemoveListview.setAdapter(customAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RemoveKhataMemberActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
