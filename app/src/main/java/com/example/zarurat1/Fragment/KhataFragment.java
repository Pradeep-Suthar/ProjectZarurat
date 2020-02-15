package com.example.zarurat1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zarurat1.Activity.AddKhataMemberActivity;
import com.example.zarurat1.Activity.RemoveKhataMemberActivity;
import com.example.zarurat1.Pojo.NewsPojo;
import com.example.zarurat1.R;

import java.util.ArrayList;

public class KhataFragment extends Fragment {
    CardView cardViewAddKhata, cardViewRemoveKhata;
    RecyclerView recyclerViewKhata;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<NewsPojo> userPojos = new ArrayList<>();

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



        return view;
    }

}
