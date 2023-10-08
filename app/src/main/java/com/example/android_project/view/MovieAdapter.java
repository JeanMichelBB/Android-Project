package com.example.android_project.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.android_project.MainActivity;
import com.example.android_project.R;
import com.example.android_project.business.MovieManagement;
import com.example.android_project.data.ApiBroker;
import com.example.android_project.models.MovieModel;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter {
    ArrayList<MovieModel> movieList = new ArrayList<>();
    MovieManagement movieManagement = new MovieManagement();
    Context context;
    private boolean isTeacher;

    public MovieAdapter(Context context, ArrayList<MovieModel> movieList, boolean isTeacher) {
        super(context, R.layout.movie_item, movieList);
        this.movieList = movieList;
        this.context = context;
        this.isTeacher = isTeacher;
    }

    public static class ViewHolder{
        TextView movieTitleText, movieDescriptionText, movieReleaseDateText, movieGenreText;
        RatingBar movieRatingBar;
        ImageView imageViewMovie;
        ToggleButton toggleAssignMovie;
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
            movieViewHolder.imageViewMovie = convertView.findViewById(R.id.movieImage);
            movieViewHolder.toggleAssignMovie = convertView.findViewById(R.id.toggleAssignMovie);

            convertView.setTag(movieViewHolder);
        } else {
            movieViewHolder = (ViewHolder) convertView.getTag();
        }
        movieViewHolder.movieTitleText.setText(movie.getTitle());
        movieViewHolder.movieDescriptionText.setText(movie.getDescription());
        movieViewHolder.movieReleaseDateText.setText(movie.getReleaseDate());
        movieViewHolder.movieGenreText.setText(movie.getGenre());
        movieViewHolder.movieRatingBar.setRating(Float.parseFloat(movie.getRating()));

        String imageUrl = movie.getImageUrl();
        ApiBroker.fetchImage(imageUrl, new ApiBroker.ImageResponseListener() {
            @Override
            public void onImageResponse(Bitmap image) {
                movieViewHolder.imageViewMovie.setImageBitmap(image);
            }
        });

        if (isTeacher) {
            movieViewHolder.toggleAssignMovie.setVisibility(View.VISIBLE);
            movieViewHolder.movieDescriptionText.setVisibility(View.GONE);
            movieViewHolder.toggleAssignMovie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        System.out.println("Movie assigned" + movie.getTitle());
                        movieManagement.assignMovie(movie);
                    } else {
                        System.out.println("Movie unassigned" + movie.getTitle());
                        movieManagement.unassignMovie(movie);
                    }
                }
            });
        } else {
            movieViewHolder.toggleAssignMovie.setVisibility(View.GONE);
            movieViewHolder.movieDescriptionText.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
