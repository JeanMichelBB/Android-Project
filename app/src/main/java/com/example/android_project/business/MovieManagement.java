package com.example.android_project.business;

import com.example.android_project.data.ApplicationDB;
import com.example.android_project.data.MovieDB;
import com.example.android_project.models.MovieModel;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieManagement {
    private MovieDB movieDB;
    public MovieManagement(){
        this.movieDB = new MovieDB();
    }
    public void assignMovie(MovieModel movie){
        movieDB.assignMovie(movie);
    }
    public void unassignMovie(MovieModel movie){
        movieDB.unassignMovie(movie);
    }
    public void getAllMovies(final ValueEventListener listener) {
        movieDB.getAllMovies(listener);
    }
}
