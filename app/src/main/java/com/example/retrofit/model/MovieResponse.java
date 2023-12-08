package com.example.retrofit.model;

import java.util.List;

public class MovieResponse {
    private List<Movie> movies;
    private List<Movie> contents;

    public List<Movie> getMovies() {
        if(movies != null) {
            return movies;
        }
        else{
            return contents;
        }
    }
}

