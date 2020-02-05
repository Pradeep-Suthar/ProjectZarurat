package com.example.zarurat1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zarurat1.R;

public class FullNewsActivity extends AppCompatActivity {

    ImageView newsImage;
    TextView textViewTitle,textViewDate,textViewName,textViewContent, textViewDescription,textViewLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        Toolbar toolbar1 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);


        textViewContent=findViewById(R.id.newsContent);
        textViewTitle=findViewById(R.id.newstitle);
        textViewDate=findViewById(R.id.textNewsDate);
        textViewName=findViewById(R.id.textNewsName);
        textViewDescription=findViewById(R.id.newsdescription);
        textViewLink=findViewById(R.id.newsLink);
        newsImage=findViewById(R.id.newsimage);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String date=getIntent().getStringExtra("date");
        String name=getIntent().getStringExtra("name");
        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("description");
        String content=getIntent().getStringExtra("content");
        String imgurl=getIntent().getStringExtra("imgUrl");
        String link=getIntent().getStringExtra("link");


        textViewContent.setText("ARTICLE :"+content);
        textViewDate.setText(date);
        textViewDescription.setText("DESCRIPTION :"+description);
        textViewName.setText(name);
        textViewTitle.setText(title);

        Glide.with(this).load(imgurl).into(newsImage);

        textViewLink.setText("WEB LINK :"+link);
        Linkify.addLinks(textViewLink,Linkify.WEB_URLS);

        //textViewLink.setText(Html.fromHtml("Here is <a href=<a href="+"'"+link+"'"+">News Link</a>"));

       // textViewLink.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
    }
}
