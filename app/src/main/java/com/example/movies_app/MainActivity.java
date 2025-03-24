package com.example.movies_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app.adapter.MovieAdapter;
import com.example.movies_app.model.Movie;
import com.example.movies_app.util.JsonUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // RecyclerView for displaying the list of movies
    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter; //connects the movie list with RecyclerView
    private List<Movie> movies;//stores movie objects
    private TextView errorTextView;// displlays error messages
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Set the layout file for this activity, calls xml file
        setContentView(R.layout.activity_main);
        // Initialize UI components
        movieRecyclerView=findViewById(R.id.movieRecyclerView);
        errorTextView=findViewById(R.id.errorTextView);
        setupRecyclerView();
        loadMovieData();//from JSON
    }
    //Sets up the RecyclerView with a layout manager(for vertical list display) and adapter.
    private void setupRecyclerView(){
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Improve performance by fixing the RecyclerView size
        movieRecyclerView.setHasFixedSize(true);
        // Initialize adapter with movie list
        adapter=new MovieAdapter(movies);
        movieRecyclerView.setAdapter(adapter);
    }
    private void loadMovieData() {
        try {
            // Retrieve movie list from JSON file using JSONUtils class
            movies = JsonUtils.loadMoviesFromJson(this);
            if (movies == null || movies.isEmpty()) {
                showError("No movies found.");

            } else {
                // Update RecyclerView with loaded movies
                adapter.updateMovies(movies);
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error loading movies", e);
            showError("Failed to load movie data.");
        }
    }
    /**
     * Makes the error message visible, sets error message text
     * and hides the RecyclerView since there's no data to display
     * @param message The error message to be shown.
     */
    private void showError (String message){
            errorTextView.setVisibility(RecyclerView.VISIBLE);
            errorTextView.setText(message);
            movieRecyclerView.setVisibility(View.GONE);
        }
    }

