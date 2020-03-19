package com.example.zarurat1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zarurat1.Activity.PersonalNewsListActivity;
import com.example.zarurat1.R;

public class PersonlizedNewsFragment extends Fragment {
    CardView cardViewEntertainment,cardViewBuisness,cardViewSports,cardViewHealth,cardViewScience,cardViewTechnology;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_personlized_news, container, false);
        cardViewBuisness=view.findViewById(R.id.cardBuisness);
        cardViewEntertainment=view.findViewById(R.id.cardEntertainment);
        cardViewScience=view.findViewById(R.id.cardScience);
        cardViewHealth=view.findViewById(R.id.cardHealth);
        cardViewSports=view.findViewById(R.id.cardSports);
        cardViewTechnology=view.findViewById(R.id.cardTechnology);

        cardViewTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalNewsListActivity.class);
                intent.putExtra("newsType","Technology");
                startActivity(intent);

            }
        });

        cardViewSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalNewsListActivity.class);
                intent.putExtra("newsType","Sports");
                startActivity(intent);

            }
        });


        cardViewHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalNewsListActivity.class);
                intent.putExtra("newsType","Health");
                startActivity(intent);
            }
        });


        cardViewScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalNewsListActivity.class);
                intent.putExtra("newsType","Science");
                startActivity(intent);
            }
        });


        cardViewEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalNewsListActivity.class);
                intent.putExtra("newsType","Entertainment");
                startActivity(intent);
            }
        });


        cardViewBuisness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalNewsListActivity.class);
                intent.putExtra("newsType","Business");
                startActivity(intent);
            }
        });
        return view;
    }

}
