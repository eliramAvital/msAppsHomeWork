package com.example.recyclerviewmsapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String JSON_URL = "https://api.androidhive.info/json/movies.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Movie> theMovie;
    private RecyclerView recyclerView;
    private SQLiteDatabaseHandler db;
    ImageView imageView;
    TextView tvTitle, tvRating,tvRelease,tvGenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theMovie = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        db = new SQLiteDatabaseHandler(this);
        jsonrequset();
        imageView = findViewById(R.id.ivImage);
    }



    private void jsonrequset() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Movie movies = new Movie();
                        movies.setTitle(jsonObject.getString("title"));
                        movies.setRating(jsonObject.getString("rating"));
                        movies.setReleaseYear(jsonObject.getString("releaseYear"));
                        movies.setGenre(jsonObject.getString("genre"));
                        movies.setImage(jsonObject.getString("image"));
                        theMovie.add(movies);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }
                setuprecyclerview(theMovie);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Movie> theMovie) {
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,theMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        saveToDb(theMovie);

    }

    private void saveToDb(List<Movie> theMovie) {
        for (int i = 0; i <theMovie.size(); i++) {
            db.addMovie(theMovie.get(i));
        }
    }


    public void infoMovie(View view){
        Intent intent = new Intent(getBaseContext(),infoMovieActivity.class);
        tvTitle = findViewById(R.id.tvTitle);
        tvRating = findViewById(R.id.tvRating);
        tvGenre = findViewById(R.id.tvGenre);
        tvRelease = findViewById(R.id.tvRelease);
        String movieTitle = tvTitle.getText().toString();
        String movieRating = tvRating.getText().toString();
        String movieGenre = tvGenre.getText().toString();
        String movieRelease = tvRelease.getText().toString();
        intent.putExtra("titleName",movieTitle);
        intent.putExtra("rating", movieRating);
        intent.putExtra("genreName", movieGenre);
        intent.putExtra("release",movieRelease);
        startActivity(intent);
    }
}
