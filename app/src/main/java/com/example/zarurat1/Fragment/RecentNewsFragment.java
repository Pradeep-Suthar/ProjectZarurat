package com.example.zarurat1.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class RecentNewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<NewsPojo> userPojos = new ArrayList<>();
    public static final String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=33f92e4321d34c3d91ea41e6df82269c";
    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_recent_news, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewRecentNews);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);


        Log.d("1234", "onCreate: ");
        requestQueue = Volley.newRequestQueue(this.getContext());

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
                    NewsCustomAdapter newsCustomAdapter = new NewsCustomAdapter(getContext(), userPojos);
                    recyclerView.setAdapter(newsCustomAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("1234", "onErrorResponse: "+error.getMessage());

                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("1234", "onCreate: 123");
        requestQueue.add(request);

        return view;
    }

}
