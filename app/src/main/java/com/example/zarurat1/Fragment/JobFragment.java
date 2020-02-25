package com.example.zarurat1.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zarurat1.R;

public class JobFragment extends Fragment {
    FloatingActionButton addbutton;
    LinearLayout linearLayoutbottomsheet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_job, container, false);
        addbutton=view.findViewById(R.id.fab);
        linearLayoutbottomsheet=view.findViewById(R.id.bottom_sheet);

        final BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(linearLayoutbottomsheet);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        return view;


    }


}
