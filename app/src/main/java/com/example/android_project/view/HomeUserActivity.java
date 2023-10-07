package com.example.android_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android_project.R;
import com.example.android_project.business.Authentication;
import com.example.android_project.models.MovieModel;
import com.example.android_project.models.UserModel;

import java.util.ArrayList;

public class HomeUserActivity extends AppCompatActivity {
    TextView txtWelcome;
    ListView listView;
    UserModel user;

    ArrayList<MovieModel> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        user = (UserModel) getIntent().getSerializableExtra("AUTH_USER");
        txtWelcome = findViewById(R.id.txtWelcome);
        listView = findViewById(R.id.listView);

        txtWelcome.setText("Welcome " + user.getFullName());

        // retrieve movies from Database

//        MovieAdapterUser adapter = new MovieAdapterUser(HomeUserActivity.this, movieList);
//        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Authentication.logout();
        Log.d("DEBUG", "User Loggout");
    }
}