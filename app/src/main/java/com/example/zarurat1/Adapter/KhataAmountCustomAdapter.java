package com.example.zarurat1.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;

import java.util.ArrayList;

public class KhataAmountCustomAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<KhataPojo> arrayList;

    public KhataAmountCustomAdapter(Context context, int resource, ArrayList<KhataPojo> arrayList) {
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

        TextView dateTime = view.findViewById(R.id.tvkhataDateTime);
        TextView description = view.findViewById(R.id.tvDescriptionF);
        TextView textAmountSend = view.findViewById(R.id.tvAmountSend);
        TextView textAmountRecieve = view.findViewById(R.id.tvAmountRecieve);


        KhataPojo khataPojo = arrayList.get(position);
        Log.d("1234", "getView: "+ arrayList.size());
        Log.d("1234", "getView: "+ khataPojo.getDate());
        Log.d("1234", "getView: "+ khataPojo.getDescription());

        dateTime.setText(khataPojo.getDate()+"-"+khataPojo.getTime());
        description.setText(khataPojo.getDescription());

        String amountcheck =khataPojo.getFAmount();

        if(amountcheck.charAt(0)=='-'){
            textAmountRecieve.setText("Rs "+amountcheck.substring(1));
            textAmountRecieve.setTextColor(view.getResources().getColor(R.color.colorRed));
            textAmountSend.setVisibility(View.INVISIBLE);
        }
        else{
            textAmountSend.setText("Rs "+amountcheck.substring(1));
            textAmountSend.setTextColor(view.getResources().getColor(R.color.colorGreenDark));
            textAmountRecieve.setVisibility(View.INVISIBLE);
        }


        return view;

    }

}