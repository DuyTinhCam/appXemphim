package com.example.app_rapchieuphim.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_rapchieuphim.Adapter.FilmListAdapter;
import com.example.app_rapchieuphim.Domain.ListFilm;
import com.example.app_rapchieuphim.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewMovies, adapterUpComing;
    private RecyclerView recyclerViewNewmovies, recyclerViewUpcoming;
    private RequestQueue mRequestQueue;
    private  StringRequest mStringRequest,mStringRequest2;
    private ProgressBar loading1,loading2;

//    public MainActivity1(RecyclerView.Adapter adapterNewMovies) {
//        this.adapterNewMovies = adapterNewMovies;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sendRequest1();
        RecyclerView.Adapter adapterNewMovie = this.adapterNewMovies;
        sendRequest2();
    }

    private void sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response,ListFilm.class);
            adapterNewMovies = new FilmListAdapter(items);
            recyclerViewNewmovies.setAdapter(adapterNewMovies);
        }, error -> {
            Log.i("uilover", "sendRequest1: "+error.toString());
            loading1.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest);
    }
    private void sendRequest2() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response,ListFilm.class);
            adapterUpComing = new FilmListAdapter(items);
            recyclerViewUpcoming.setAdapter(adapterUpComing);
        }, error -> {
            Log.i("uilover", "sendRequest2: "+error.toString());
            loading2.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest2);
    }

    private void initView() {
        recyclerViewNewmovies = findViewById(R.id.view1);
        recyclerViewNewmovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpcoming = findViewById(R.id.view2);
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);
    }
}