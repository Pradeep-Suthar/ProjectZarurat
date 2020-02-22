package com.example.zarurat1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zarurat1.Activity.KhataDetailsActivity;
import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;

import java.util.ArrayList;
import java.util.Random;

public class KataPersonCustomAdapter extends RecyclerView.Adapter<KataPersonCustomAdapter.ViewHolder>{

    private ArrayList<KhataPojo> values;
    private Context context;
    private View v;

    public KataPersonCustomAdapter(Context context, ArrayList<KhataPojo> myDataset) {
        values = myDataset;
        this.context = context;
    }


    @Override
    public KataPersonCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.khatadetail_layout, parent, false);
        KataPersonCustomAdapter.ViewHolder vh = new KataPersonCustomAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(KataPersonCustomAdapter.ViewHolder holder, final int position) {
        final KhataPojo myPojo = values.get(position);
        holder.name.setText(myPojo.getName());
        holder.email.setText(myPojo.getEmail());
        String amountcheck =myPojo.getFAmount();

        if(amountcheck.charAt(0)=='-'){
            holder.amount.setText("Rs "+amountcheck.substring(1));
            holder.amount.setTextColor(v.getResources().getColor(R.color.colorRed));
        }
        else{
            holder.amount.setText("Rs "+amountcheck.substring(1));
            holder.amount.setTextColor(v.getResources().getColor(R.color.colorGreenDark));
        }

        int[] color_array=v.getResources().getIntArray(R.array.color_array);
        int randomcolor= color_array[new Random().nextInt(color_array.length)];

        holder.imageViewText.setText(String.valueOf(myPojo.getName().charAt(0)));

        GradientDrawable gradientDrawable= (GradientDrawable) holder.imageViewText.getBackground().getCurrent();
        gradientDrawable.setColor(randomcolor);


        holder.cardViewKhataPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, KhataDetailsActivity.class);
                intent.putExtra("description",myPojo.getDescription());
                intent.putExtra("date",myPojo.getDate());
                intent.putExtra("name",myPojo.getName());
                intent.putExtra("time",myPojo.getTime());
                intent.putExtra("famount",myPojo.getFAmount());
                intent.putExtra("bamount",myPojo.getBAmount());
                intent.putExtra("email",myPojo.getEmail());
                intent.putExtra("mobileNo",myPojo.getMoble_no());
                context.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView amount;
        public TextView email;
        public TextView imageViewText;
        public CardView cardViewKhataPerson;
        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.textkhataName);
            amount = v.findViewById(R.id.textkhataAmount);
            email = v.findViewById(R.id.textkhataEmail);
            imageViewText=v.findViewById(R.id.imageKhataName);
            cardViewKhataPerson = v.findViewById(R.id.cardViewKhataPerson);


        }
    }


}