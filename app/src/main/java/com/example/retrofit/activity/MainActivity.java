/*
Author: Ali Besharat
Final Project: Movie Search
Date: 12/07/2023
 */

package com.example.retrofit.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.adapter.CustomAdapter;
import com.example.retrofit.model.Movie;
import com.example.retrofit.model.MovieResponse;
import com.example.retrofit.network.GetDataService;
import com.example.retrofit.network.RetrofitClientInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;



public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Intent intent = getIntent();
        if (intent.hasExtra("query")) {
            String query = intent.getStringExtra("query");
            performSearch(query);
        } else {
            loadAllMovies();
        }
        // Create a reference for the RetrofitInstance interface
    }

    private void loadAllMovies() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<MovieResponse> call = service.getMovies();
        call.enqueue(new Callback<MovieResponse>() {

            @Override
            @EverythingIsNonNull
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressDoalog.dismiss();
                generateDataList(response.body().getMovies());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this,
                        "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Ali.Exception", t.getMessage());
            }
        });
    }

    private void performSearch(String query) {
        progressDoalog.show();

        // Create a reference for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<MovieResponse> call = service.searchMovies(query);
        call.enqueue(new Callback<MovieResponse>() {


            @Override
            @EverythingIsNonNull
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressDoalog.dismiss();
                generateDataList(response.body().getMovies());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this,
                        "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Ali.Exception", t.getMessage());
            }
        });
    }

    private void generateDataList(List<Movie> movieList) {
        if (movieList == null || movieList.size() == 0) {
            Toast.makeText(MainActivity.this,
                    "No movies found by that title, please try different title", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        } else {
            this.movieList = movieList;
            recyclerView = findViewById(R.id.customRecyclerView);
            adapter = new CustomAdapter(this, movieList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, MovieDetailPage.class);
            intent.putExtra("movie",  movieList.get(position));
            startActivity(intent);
    }
}