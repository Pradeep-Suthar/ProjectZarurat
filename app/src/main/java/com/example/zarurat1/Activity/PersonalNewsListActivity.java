package com.example.zarurat1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zarurat1.Adapter.NewsCustomAdapter;
import com.example.zarurat1.Pojo.NewsPojo;
import com.example.zarurat1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PersonalNewsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    ArrayList<NewsPojo> userPojos = new ArrayList<>();

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_news_list);

        String newstype=getIntent().getStringExtra("newsType");

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle(newstype+" News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);



        final String url = "https://newsapi.org/v2/top-headlines?country=in&category="+newstype+"&apiKey=33f92e4321d34c3d91ea41e6df82269c";

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = findViewById(R.id.recyclerviewPresonalNews);
        recyclerView.setHasFixedSize(true);
        progressBar= findViewById(R.id.progressBarPer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        progressBar.setVisibility(View.VISIBLE);

        Log.d("1234", "onCreate: ");
        requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("1234", "onResponse: "+response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("articles");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        NewsPojo newsPojo = new NewsPojo();
                        newsPojo.setName(jsonObject.getJSONObject("source").getString("name"));
                        newsPojo.setTitle(jsonObject.getString("title"));
                        newsPojo.setDescription(jsonObject.getString("description"));
                        newsPojo.setLink(jsonObject.getString("url"));
                        newsPojo.setImgUrl(jsonObject.getString("urlToImage"));
                        newsPojo.setDate(jsonObject.getString("publishedAt"));
                        newsPojo.setContent(jsonObject.getString("content"));
                        userPojos.add(newsPojo);
                    }

                    Log.d("12345", "onResponse: getview ");
                    NewsCustomAdapter newsCustomAdapter = new NewsCustomAdapter(PersonalNewsListActivity.this, userPojos);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(newsCustomAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("1234", "onErrorResponse: "+error.getMessage());

                Toast.makeText(PersonalNewsListActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("1234", "onCreate: 123");
        requestQueue.add(request);

    }
}
