package com.example.android_project.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android_project.R;
import com.example.android_project.business.Authentication;
import com.example.android_project.business.MovieManagement;
import com.example.android_project.models.MovieModel;
import com.example.android_project.models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeUserActivity extends AppCompatActivity {
    TextView txtWelcome;
    ListView listView;
    UserModel user;
    MovieManagement movieManagement = new MovieManagement();
    ArrayList<MovieModel> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        user = (UserModel) getIntent().getSerializableExtra("AUTH_USER");
        txtWelcome = findViewById(R.id.txtWelcome);
        listView = findViewById(R.id.listView);

        txtWelcome.setText("Welcome " + user.getFullName());

        ValueEventListener moviesListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movieList = new ArrayList<MovieModel>();
                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
                    MovieModel movie = movieSnapshot.getValue(MovieModel.class);
                    movieList.add(movie);
                }
                MovieAdapter adapter = new MovieAdapter(HomeUserActivity.this, movieList, false);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG", "Error getting Movies");
            }
        };

        movieManagement.getAllMovies(moviesListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Authentication.logout();
        Log.d("DEBUG", "User Loggout");
    }
}