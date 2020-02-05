package com.example.zarurat1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zarurat1.Activity.FullNewsActivity;
import com.example.zarurat1.Pojo.NewsPojo;
import com.example.zarurat1.R;

import java.util.ArrayList;

public class NewsCustomAdapter extends RecyclerView.Adapter<NewsCustomAdapter.ViewHolder>{

        private ArrayList<NewsPojo> values;
        private Context context;

        public NewsCustomAdapter(Context context, ArrayList<NewsPojo> myDataset) {
            values = myDataset;
            this.context = context;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.news_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final NewsPojo myPojo = values.get(position);
            holder.name.setText(myPojo.getName());
            holder.title.setText(myPojo.getTitle());
            holder.date.setText(myPojo.getDate());
            Glide.with(context).load(myPojo.getImgUrl()).into(holder.imageNews);
            holder.cardViewNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, FullNewsActivity.class);
                    intent.putExtra("title",myPojo.getTitle());
                    intent.putExtra("name",myPojo.getName());
                    intent.putExtra("content",myPojo.getContent());
                    intent.putExtra("description",myPojo.getDescription());
                    intent.putExtra("date",myPojo.getDate());
                    intent.putExtra("imgUrl",myPojo.getImgUrl());
                    intent.putExtra("link",myPojo.getLink());
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
    public TextView title;
    public TextView date;
    public ImageView imageNews;
    public CardView cardViewNews;
    public ViewHolder(View v) {
        super(v);
        name = v.findViewById(R.id.textName);
        title = v.findViewById(R.id.textTitle);
        date = v.findViewById(R.id.textDate);
        imageNews = v.findViewById(R.id.imageNews);
        cardViewNews = v.findViewById(R.id.cardViewNews);


    }
}


}