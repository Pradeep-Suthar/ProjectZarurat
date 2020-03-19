package com.example.zarurat1.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
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

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                bottomSheetDialog.setCanceledOnTouchOutside(false);

            }
        });

        return view;


    }


}
