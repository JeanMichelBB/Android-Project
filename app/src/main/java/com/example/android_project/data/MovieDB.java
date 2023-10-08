package com.example.android_project.data;

import com.example.android_project.models.MovieModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MovieDB {
    private DatabaseReference mDatabase;

    public MovieDB() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("movies");
    }

    public void getAllMovies(final ValueEventListener listener) {
        mDatabase.addListenerForSingleValueEvent(listener);
    }

    public void assignMovie(MovieModel movie)
    {
        mDatabase.child(String.valueOf(movie.getMovieId())).setValue(movie);
    }

    public void unassignMovie(MovieModel movie)
    {
        mDatabase.child(String.valueOf(movie.getMovieId())).removeValue();
    }
}