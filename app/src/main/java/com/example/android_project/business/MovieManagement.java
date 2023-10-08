package com.example.android_project.business;

import com.example.android_project.data.ApplicationDB;
import com.example.android_project.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieManagement {
    private ApplicationDB applicationDB;
    public MovieManagement(){
        this.applicationDB = new ApplicationDB();
    }
    public ArrayList<MovieModel> getAllMovies(){
        return applicationDB.getAllMovies();
    }
    public void assignMovie(MovieModel movie){
        applicationDB.AssignMovie(movie);
    }
    public void unassignMovie(MovieModel movie){
        applicationDB.UnassignMovie(movie);
    }
}
