package com.example.zarurat1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;

import java.util.ArrayList;

public class KhataAmountCustomAdapter extends RecyclerView.Adapter<KhataAmountCustomAdapter.ViewHolder>{

    private ArrayList<KhataPojo> values;
    private Context context;
    private View v;

    public KhataAmountCustomAdapter(Context context, ArrayList<KhataPojo> myDataset) {
        values = myDataset;
        this.context = context;
    }


    @Override
    public KhataAmountCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.khataamountdetail_layout, parent, false);
        KhataAmountCustomAdapter.ViewHolder vh = new KhataAmountCustomAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(KhataAmountCustomAdapter.ViewHolder holder, final int position) {
        final KhataPojo myPojo = values.get(position);
        holder.description.setText(myPojo.getDescription());
        String data_time=myPojo.getDate()+" "+myPojo.getTime();
        holder.dateTime.setText(data_time);
        String amountcheck =myPojo.getBAmount();

        if(amountcheck.charAt(0)=='-'){
            holder.textAmountred.setText("Rs "+amountcheck.substring(1));
            holder.textAmountred.setTextColor(v.getResources().getColor(R.color.colorRed));
            holder.textAmountGreen.setVisibility(View.INVISIBLE);
        }
        else{
            holder.textAmountGreen.setText("Rs "+amountcheck.substring(1));
            holder.textAmountGreen.setTextColor(v.getResources().getColor(R.color.colorGreenDark));
            holder.textAmountred.setVisibility(View.INVISIBLE);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dateTime;
        public TextView description;
        public TextView textAmountred;
        public TextView textAmountGreen;
        public ViewHolder(View v) {
            super(v);
            dateTime = v.findViewById(R.id.textkhataDateTime);
            description = v.findViewById(R.id.textkhataDescriptionF);
            textAmountred=v.findViewById(R.id.textkhataAmountRed);
            textAmountGreen = v.findViewById(R.id.textkhataAmountGreen);

        }
    }


}