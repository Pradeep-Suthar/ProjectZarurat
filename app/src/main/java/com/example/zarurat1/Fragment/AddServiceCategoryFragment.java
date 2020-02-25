package com.example.zarurat1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zarurat1.Activity.CoachingService_Activity;
import com.example.zarurat1.Activity.FoodService_Activity;
import com.example.zarurat1.Activity.JobService_Activity;
import com.example.zarurat1.Activity.LivingService_Activity;
import com.example.zarurat1.R;

import de.hdodenhof.circleimageview.CircleImageView;


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
                Intent intent=new Intent(getContext(),LivingService_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }




}
