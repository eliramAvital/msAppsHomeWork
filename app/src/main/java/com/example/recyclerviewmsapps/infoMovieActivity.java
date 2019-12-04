package com.example.recyclerviewmsapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class infoMovieActivity extends AppCompatActivity {
    TextView tvTitle,tvRating,tvRelease,tvGenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_movie);

        tvTitle = findViewById(R.id.tvTitle);
        tvRelease = findViewById(R.id.tvRelease);
        tvRating = findViewById(R.id.tvRating);
        tvGenre = findViewById(R.id.tvGenre);
        String movieName = getIntent().getExtras().getString("titleName");
        String ratingMovie = getIntent().getExtras().getString("rating");
        String movieGenre = getIntent().getExtras().getString("genreName");
        String yearRelease = getIntent().getExtras().getString("release");
        tvTitle.setText("Movie name: " + movieName);
        tvRating.setText("Rating: " +ratingMovie);
        tvGenre.setText("Kind: " +movieGenre);
        tvRelease.setText("Year: " +yearRelease);
    }
}
