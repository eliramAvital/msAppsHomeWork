package com.example.recyclerviewmsapps;

import java.util.Collections;

public class Movie implements Comparable<Movie>{
    private String title;
    private String image;
    private String  rating;
    private String releaseYear;
    private String genre;

    public Movie() {
    }

    public Movie(String title, String image, String rating, String releaseYear, String genre) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    public int compareTo(Movie movie) {
        return getReleaseYear().compareTo(movie.releaseYear);
    }
}
