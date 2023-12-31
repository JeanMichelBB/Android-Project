package com.example.android_project.models;

import androidx.annotation.NonNull;

public class MovieModel {
    private int movieId;
    private String title;
    private String description;
    private String imageUrl;
    private String releaseDate;
    private String rating;
    private String genre;
    private Boolean isAssigned;

    public MovieModel(int movieId,String title, String description, String imageUrl, String releaseDate, String rating, String genre, Boolean isAssigned) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.isAssigned = isAssigned;
    }

    public MovieModel() {}

    public int getMovieId() { return movieId; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() { return imageUrl; }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public Boolean getIsAssigned() { return isAssigned; }

    public void setMovieId(int movieId) { this.movieId = movieId; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsAssigned(Boolean isAssigned) { this.isAssigned = isAssigned; }

    public String toString() {
        return "Movie ID: " + this.movieId + "\n" +
                "Title: " + this.title + "\n" +
                "Description: " + this.description + "\n" +
                "Image URL: " + this.imageUrl + "\n" +
                "Release Date: " + this.releaseDate + "\n" +
                "Rating: " + this.rating + "\n" +
                "Genre: " + this.genre + "\n" +
                "Is Assigned: " + this.isAssigned + "\n";
    }
}
