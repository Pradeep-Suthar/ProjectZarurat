package com.example.zarurat1.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zarurat1.Activity.AddKhataMemberActivity;
import com.example.zarurat1.Activity.RemoveKhataMemberActivity;
import com.example.zarurat1.Adapter.KataPersonCustomAdapter;
import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class KhataFragment extends Fragment {
    CardView cardViewAddKhata, cardViewRemoveKhata;
    RecyclerView recyclerViewKhata;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView.Adapter adapter;
    ArrayList<KhataPojo> arrayList = new ArrayList<KhataPojo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_khata, container, false);

        cardViewAddKhata=view.findViewById(R.id.cardAddKhata);
        cardViewRemoveKhata=view.findViewById(R.id.cardRemoveKhata);
        recyclerViewKhata=view.findViewById(R.id.recyclerviewKhata1);

        recyclerViewKhata.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerViewKhata.setLayoutManager(layoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("khataInfo");

        cardViewAddKhata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddKhataMemberActivity.class);
                startActivity(intent);
            }
        });

        cardViewRemoveKhata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RemoveKhataMemberActivity.class);
                startActivity(intent);
            }
        });

        getDataofKhata();


        return view;
    }


    public void getDataofKhata() {
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("myprf",MODE_PRIVATE);
        final String name1=sharedPreferences.getString("name",null);
        final String mobile1=sharedPreferences.getString("mobile",null);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                Log.d("1234", "onDataChange: ref ");
                for (DataSnapshot dataSnapshot1:dataSnapshot.child(name1+mobile1).getChildren()){
                    Log.d("1234", "onDataChange: for "+dataSnapshot1);
                    KhataPojo khataPojo = dataSnapshot1.getValue(KhataPojo.class);
                    arrayList.add(khataPojo);
                }
                Log.d("1234", "onDataChange: "+arrayList.size());
                adapter = new KataPersonCustomAdapter(getActivity(), arrayList);
                recyclerViewKhata.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
