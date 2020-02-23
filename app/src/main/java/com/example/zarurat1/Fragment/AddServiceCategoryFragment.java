package com.example.zarurat1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zarurat1.Activity.CoachingService_Activity;
import com.example.zarurat1.Activity.FoodService_Activity;
import com.example.zarurat1.Activity.JobService_Activity;
import com.example.zarurat1.Activity.LivingService_Activity;
import com.example.zarurat1.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class AddServiceCategoryFragment extends Fragment {
    ImageView imageViewLivingService,imageViewfoodService,imageViewCoachingService,imageViewJobService;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_service_category, container, false);
        imageViewLivingService=view.findViewById(R.id.image_LivingService);
        imageViewfoodService=view.findViewById(R.id.image_foodservice);
        imageViewCoachingService=view.findViewById(R.id.image_CoachingService);
        imageViewJobService=view.findViewById(R.id.image_JobService);

        imageViewLivingService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),LivingService_Activity.class);
                startActivity(intent);
            }
        });

        return inflater.inflate(R.layout.fragment_add_service_category, container, false);
    }




}
