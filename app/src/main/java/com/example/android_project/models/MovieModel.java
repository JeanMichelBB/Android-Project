package com.example.android_project.models;

import androidx.annotation.NonNull;

public class MovieModel {
    private String title;
    private String description;
    private String imageUrl;
    private String releaseDate;
    private String rating;
    private String genre;
    private Boolean assigned;

    public MovieModel(String title, String description, String imageUrl, String releaseDate, String rating, String genre) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
    }

    public MovieModel(String title, String description, String imageUrl, String releaseDate, String rating, String genre, Boolean assigned) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.assigned = assigned;
    }

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

    public Boolean getAssigned() {
        return assigned;
    }

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

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public String toString() {
        return "Title: " + this.title + "\n" +
                "Description: " + this.description + "\n" +
                "Image URL: " + this.imageUrl + "\n" +
                "Release Date: " + this.releaseDate + "\n" +
                "Rating: " + this.rating + "\n" +
                "Genre: " + this.genre + "\n" +
                "Assigned: " + this.assigned + "\n";
    }
}
