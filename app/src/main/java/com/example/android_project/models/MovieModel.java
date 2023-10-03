package com.example.android_project.models;

public class MovieModel {
    private String title;
    private String director;
    private String description;
    private String imageUrl;
    private String releaseDate;
    private String rating;
    private String genre;
    private String duration;

    public MovieModel(String title, String director, String description, String imageUrl, String releaseDate, String rating, String genre, String duration) {
        this.title = title;
        this.director = director;
        this.description = description;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
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

    public String getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
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

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
