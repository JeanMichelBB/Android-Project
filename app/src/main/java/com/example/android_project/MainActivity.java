package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android_project.data.ApiBroker;
import com.example.android_project.models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultTextView = findViewById(R.id.textView);

        String apiUrl = "https://api.themoviedb.org/3/movie/550?api_key=16338f6f183f5ee2fce0e99d55cfbb5f";

        ApiBroker.fetchData(apiUrl, new ApiBroker.ApiResponseListener() {
            @Override
            public void onApiResponse(String response) throws JSONException {
                JSONObject jsonObject = new JSONObject(response);
                String baseUrl = "http://image.tmdb.org/t/p/w154";
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("overview");
                String imageUrl = baseUrl + jsonObject.getString("poster_path");
                String releaseDate = jsonObject.getString("release_date");
                String rating = jsonObject.getString("vote_average");
                JSONArray genreArray = jsonObject.getJSONArray("genres");
                JSONObject genreObject = genreArray.getJSONObject(0);
                String genre = genreObject.getString("name");
                MovieModel movieModel = new MovieModel(title, description, imageUrl, releaseDate, rating, genre);


                resultTextView.setText(movieModel.toString());
            }
        });
    }
}