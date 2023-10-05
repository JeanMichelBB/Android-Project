package com.example.android_project.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_project.R;
import com.example.android_project.models.MovieModel;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter {
    ArrayList<MovieModel> movieList = new ArrayList<>();
    Context context;

    public MovieAdapter(Context context, ArrayList<MovieModel> movieList) {
        super(context, R.layout.movie_item, movieList);
        this.movieList = movieList;
        this.context = context;
    }

    public static class ViewHolder{
        TextView movieTitleText, movieDescriptionText, movieReleaseDateText, movieGenreText;
        RatingBar movieRatingBar;
        ImageView imageViewMovie;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder movieViewHolder;
        MovieModel movie = movieList.get(position);

        if (convertView == null) {
            movieViewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.movie_item, parent, false);

            movieViewHolder.movieTitleText = convertView.findViewById(R.id.movieTitle);
            movieViewHolder.movieDescriptionText = convertView.findViewById(R.id.movieDescription);
            movieViewHolder.movieReleaseDateText = convertView.findViewById(R.id.movieReleaseDate);
            movieViewHolder.movieGenreText = convertView.findViewById(R.id.movieGenres);
            movieViewHolder.movieRatingBar = convertView.findViewById(R.id.movieRating);

            convertView.setTag(movieViewHolder);
        } else {
            movieViewHolder = (ViewHolder) convertView.getTag();
        }
        movieViewHolder.movieTitleText.setText(movie.getTitle());
        movieViewHolder.movieDescriptionText.setText(movie.getDescription());
        movieViewHolder.movieReleaseDateText.setText(movie.getReleaseDate());
        movieViewHolder.movieGenreText.setText(movie.getGenre());
        movieViewHolder.movieRatingBar.setRating(Float.parseFloat(movie.getRating()));

        return convertView;
    }
}
