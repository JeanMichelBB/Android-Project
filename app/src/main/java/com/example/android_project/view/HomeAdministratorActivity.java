package com.example.android_project.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.android_project.R;
import com.example.android_project.business.MovieManagement;
import com.example.android_project.data.ApiBroker;
import com.example.android_project.models.MovieModel;
import com.example.android_project.models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeAdministratorActivity extends AppCompatActivity {

    UserModel administrator;
    TextView txtWelcome;
    ListView getListViewMovies;
    ArrayList<MovieModel> movieList;
    ListView listViewMovies;
    ArrayList<Integer> assignedMoviesIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_administrator);
        administrator = (UserModel) getIntent().getSerializableExtra("AUTH_USER");
        listViewMovies = findViewById(R.id.listViewMovies);
        txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome " + administrator.getFirstName() + "!");
        getAssignedMovies();
    }

    private void getAssignedMovies() {
        MovieManagement movieManagement = new MovieManagement();
        movieManagement.getAllMovies(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assignedMoviesIds = new ArrayList<Integer>();
                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
                    MovieModel movie = movieSnapshot.getValue(MovieModel.class);
                    assignedMoviesIds.add(movie.getMovieId());
                }
                getAPIMovies();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG", "Error getting Movies from Firebase");
            }
        });
    }

    private void getAPIMovies () {
        String API_URL = "https://my-json-server.typicode.com/JeanMichelBB/Android-Project/db";
        ApiBroker.fetchData(API_URL, new ApiBroker.ApiResponseListener() {
            @Override
            public void onApiResponse(String response) throws JSONException {
                String baseUrl = "http://image.tmdb.org/t/p/w154";
                JSONObject jsonObject = new JSONObject(response);
                JSONArray movies = jsonObject.getJSONArray("movies");
                movieList = new ArrayList<MovieModel>();
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject movie = movies.getJSONObject(i);
                    int movieId = movie.getInt("id");
                    String title = movie.getString("title");
                    String description = movie.getString("overview");
                    String imageUrl = baseUrl + movie.getString("poster_path");
                    String releaseDate = movie.getString("release_date");
                    String rating = movie.getString("vote_average");
                    rating = String.valueOf(Float.parseFloat(rating) / 2);
                    JSONArray genreArray = movie.getJSONArray("genres");
                    String genre = "";
                    for (int j = 0; j < genreArray.length(); j++) {
                        genre += genreArray.getString(j) + (j == genreArray.length() - 1 ? "" : ", ");
                    }
                    boolean isAssigned = assignedMoviesIds.contains(movieId);
                    MovieModel movieModel = new MovieModel(movieId, title, description, imageUrl, releaseDate, rating, genre, isAssigned);
                    movieList.add(movieModel);
                    System.out.println(movieModel.toString());
                }

                MovieAdapter adapter = new MovieAdapter(HomeAdministratorActivity.this, movieList, true);
                listViewMovies.setAdapter(adapter);
            }
        });
    }
}