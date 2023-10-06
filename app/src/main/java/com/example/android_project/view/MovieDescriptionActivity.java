package com.example.android_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android_project.R;

public class MovieDescriptionActivity extends AppCompatActivity {

        TextView movieTitleText, movieDescriptionText, movieReleaseDateText, movieGenreText;
        RatingBar movieRatingBar;
        Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        backButton = findViewById(R.id.backButton);
        movieTitleText = findViewById(R.id.movieTitle);
        movieDescriptionText = findViewById(R.id.movieDescription);
        movieReleaseDateText = findViewById(R.id.movieReleaseDate);
        movieGenreText = findViewById(R.id.movieGenres);
        movieRatingBar = findViewById(R.id.movieRating);

        movieTitleText.setText(getIntent().getStringExtra("title"));
        movieDescriptionText.setText(getIntent().getStringExtra("description"));
        movieReleaseDateText.setText(getIntent().getStringExtra("releaseDate"));
        movieGenreText.setText(getIntent().getStringExtra("genre"));
        movieRatingBar.setRating(getIntent().getFloatExtra("rating", 0));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDescriptionActivity.this, MovieAdapterUser.class);
                startActivity(intent);
            }
        });
    }
}