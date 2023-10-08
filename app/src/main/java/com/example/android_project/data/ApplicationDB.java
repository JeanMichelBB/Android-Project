package com.example.android_project.data;

import android.content.OperationApplicationException;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.android_project.business.Authentication;
import com.example.android_project.models.MovieModel;
import com.example.android_project.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDB {
    private DatabaseReference mDatabase;
    private UserDbListener userDbListener;
//    private MovieDbListener movieDbListener;
    public ApplicationDB()
    {
        this.userDbListener = null;
//        this.movieDbListener = null;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void InsertNewUser(UserModel newUser)
    {
        mDatabase.child("users").child(newUser.getUserId()).setValue(newUser);
    }

    public void getUserById(String uId)
    {
        final UserModel user = null;
        mDatabase.child("users").child(uId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());

                    if(userDbListener != null)
                    {
                        userDbListener.onGetUserByIdFail(task.getException().getMessage());
                    }

                } else {
                    UserModel user = task.getResult().getValue(UserModel.class);

                    if(userDbListener != null)
                    {
                        userDbListener.onGetUserByIdSuccess(user);
                    }
                }
            }
        });
    }

    public ApplicationDB onUserDbListener(UserDbListener userDbListener)
    {
        this.userDbListener = userDbListener;
        return this;
    }

    public interface UserDbListener
    {
        public void onGetUserByIdSuccess(UserModel model);
        public void onGetUserByIdFail(String message);
    }

    public void AssignMovie(MovieModel movie)
    {
        mDatabase.child("movies").child(String.valueOf(movie.getMovieId())).setValue(movie);
    }

    public void UnassignMovie(MovieModel movie)
    {
        mDatabase.child("movies").child(String.valueOf(movie.getMovieId())).removeValue();
    }

//    public ApplicationDB onMovieDbListener(MovieDbListener movieDbListener)
//    {
//        this.movieDbListener = movieDbListener;
//        return this;
//    }

//    public interface MovieDbListener
//    {
//        public void onAssignMovieSuccess();
//        public void onAssignMovieFail(String message);
//        public void onDeleteMovieSuccess();
//        public void onDeleteMovieFail(String message);
//    }

    public ArrayList<MovieModel> getAllMovies()
    {
        final ArrayList<MovieModel> movies = new ArrayList<MovieModel>();
        mDatabase.child("movies").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful())
                {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else
                {
                    for(DataSnapshot movie : task.getResult().getChildren())
                    {
                        movies.add(movie.getValue(MovieModel.class));
                    }
                }
            }
        });
        return movies;
    }
}
