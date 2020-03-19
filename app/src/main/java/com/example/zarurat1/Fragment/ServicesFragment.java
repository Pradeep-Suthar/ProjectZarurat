package com.example.zarurat1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zarurat1.Activity.CoachingServicesActivity;
import com.example.zarurat1.Activity.FoodServicesActivity;
import com.example.zarurat1.Activity.LivingServiceProviderActivity;
import com.example.zarurat1.Activity.NearByServicesActivity;
import com.example.zarurat1.R;

public class ServicesFragment extends Fragment {
    CardView cardViewLiving, cardViewFood, cardViewCoaching,cardViewNearby;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_services, container, false);
        cardViewCoaching=view.findViewById(R.id.cardCoaching);
        cardViewFood=view.findViewById(R.id.cardFood);
        cardViewLiving=view.findViewById(R.id.cardLiving);
        cardViewNearby=view.findViewById(R.id.cardNearby);

        cardViewNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NearByServicesActivity.class);
                intent.putExtra("ServiceType","Near By");
                startActivity(intent);
            }
        });

        cardViewLiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LivingServiceProviderActivity.class);
                intent.putExtra("ServiceType","Living");
                startActivity(intent);
            }
        });

        cardViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FoodServicesActivity.class);
                intent.putExtra("ServiceType","Food and Mess");
                startActivity(intent);
            }
        });

        cardViewCoaching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CoachingServicesActivity.class);
                intent.putExtra("ServiceType","Coaching");
                startActivity(intent);
            }
        });


        return view;
    }

}
