package com.example.retrofit.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.retrofit.R;
import com.example.retrofit.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailPage extends AppCompatActivity {
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_page);

        progressDoalog = new ProgressDialog(MovieDetailPage.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Intent intent = getIntent();
        if(intent.hasExtra("movie")){
            Movie movie = intent.getParcelableExtra("movie");
            ImageView imageView = findViewById(R.id.imageView);
            Picasso.get().load(movie.getPoster_path()).into(imageView);
            TextView overView = findViewById(R.id.overView);
            overView.setText(movie.getOverview());
            progressDoalog.dismiss();
        }
        else {
            Toast.makeText(MovieDetailPage.this,
                    "No movie found", Toast.LENGTH_SHORT).show();
        }
    }
}
