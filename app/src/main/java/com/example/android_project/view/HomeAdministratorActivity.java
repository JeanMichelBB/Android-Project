package com.example.android_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android_project.R;
import com.example.android_project.data.ApiBroker;
import com.example.android_project.models.MovieModel;
import com.example.android_project.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeAdministratorActivity extends AppCompatActivity {

    UserModel administrator;
    TextView txtWelcome;
    ListView getListViewMovies;
    ArrayList<MovieModel> movieList = new ArrayList<>();
    ListView listViewMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_administrator);

        administrator = (UserModel) getIntent().getSerializableExtra("AUTH_USER");

//        txtWelcome = findViewById(R.id.txtWelcome);
//        listViewMovies = findViewById(R.id.listViewMovies);

//        txtWelcome.setText("Welcome " + administrator.getFirstName() );

        String apiUrl = "https://my-json-server.typicode.com/JeanMichelBB/Android-Project/db";

        ApiBroker.fetchData(apiUrl, new ApiBroker.ApiResponseListener() {
            @Override
            public void onApiResponse(String response) throws JSONException {
                String baseUrl = "http://image.tmdb.org/t/p/w154";
                JSONObject jsonObject = new JSONObject(response);
                JSONArray movies = jsonObject.getJSONArray("movies");
                // iterate through movies
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject movie = movies.getJSONObject(i);
                    String title = movie.getString("title");
                    String description = movie.getString("overview");
                    String imageUrl = baseUrl + movie.getString("poster_path");
                    String releaseDate = movie.getString("release_date");
                    String rating = movie.getString("vote_average");
                    JSONArray genreArray = movie.getJSONArray("genres");
                    String genre = "";
                    for (int j = 0; j < genreArray.length(); j++) {
                        genre += genreArray.getString(j) + (j == genreArray.length() - 1 ? "" : ", ");
                    }
                    MovieModel movieModel = new MovieModel(title, description, imageUrl, releaseDate, rating, genre);
                    movieList.add(movieModel);
                    System.out.println(movieModel.toString());
                }
//                MovieAdapter adapter = new MovieAdapter(HomeAdministratorActivity.this, movieList);
//                listViewMovies.setAdapter(adapter);
            }
        });
    }
}