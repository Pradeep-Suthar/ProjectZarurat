package com.example.zarurat1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zarurat1.Activity.LivingServiceProviderActivity;
import com.example.zarurat1.R;


public class AddServiceCategoryFragment extends Fragment {
    CardView cardViewLiving,cardViewFood,cardViewCoaching,cardViewJob;

Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_service_category, container, false);
     cardViewLiving=view.findViewById(R.id.carviewLivingServices);



        cardViewLiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LivingServiceProviderActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }




}
