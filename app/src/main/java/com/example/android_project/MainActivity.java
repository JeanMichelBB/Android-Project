package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_project.data.ApiBroker;
import com.example.android_project.models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultTextView = findViewById(R.id.textView);

        String apiUrl = "https://api.themoviedb.org/3/movie/550?api_key=16338f6f183f5ee2fce0e99d55cfbb5f";

        ApiBroker.fetchData(apiUrl, new ApiBroker.ApiResponseListener() {
            @Override
            public void onApiResponse(String response) {
                resultTextView.setText(response);
            }
        });
    }
}