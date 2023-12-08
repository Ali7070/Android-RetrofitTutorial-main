package com.example.retrofit.network;

import com.example.retrofit.model.Comment;
import com.example.retrofit.model.Movie;
import com.example.retrofit.model.MovieResponse;
import com.example.retrofit.model.RetroPhoto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();

    @GET("/comments")
    Call<List<Comment>> getAllComments();

    @GET("/movies")
    Call<List<Movie>> getAllMovies();

    @GET("/movies")
    Call<MovieResponse> getMovies();

    @GET("/search")
    Call<MovieResponse> searchMovies(@Query("query") String query);

}
