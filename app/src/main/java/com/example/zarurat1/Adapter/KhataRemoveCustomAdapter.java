package com.example.zarurat1.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;

import java.util.ArrayList;

public class KhataRemoveCustomAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<KhataPojo> arrayList;

    public KhataRemoveCustomAdapter(Context context, int resource, ArrayList<KhataPojo> arrayList) {
        super(context, resource, arrayList);
        Log.d("12345", "NewsCustomAdapter:"+context.toString());
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null,false);
        TextView textKhataName = view.findViewById(R.id.textkhataNameR);
        TextView textKhataEmail = view.findViewById(R.id.textkhataEmailR);
        Button buttonRemove= view.findViewById(R.id.buttonkhataRemove);

        KhataPojo khataPojo = arrayList.get(position);
        textKhataName.setText(khataPojo.getName());
        textKhataEmail.setText(khataPojo.getEmail());

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       /* cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.news_onclick_item, null, false);
                alertDialogBuilder.setView(view);
                AlertDialog dialog=alertDialogBuilder.create();



                dialog.show();
            }
        });*/

        return view;

    }
}

