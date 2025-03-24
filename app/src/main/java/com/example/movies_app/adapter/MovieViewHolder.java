package com.example.movies_app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app.R;
import com.example.movies_app.model.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    // declaring UI components
    private ImageView posterImageView;
    private TextView titleTextView, yearTextView, genreTextView;

    // Constructor
    public MovieViewHolder(@NonNull View itemView){
        super(itemView); // Call superclass constructor to associate this ViewHolder with the itemView
        // Initialize views in the movie item layout
        titleTextView=itemView.findViewById(R.id.titleTextView);
        yearTextView = itemView.findViewById(R.id.yearTextView);
        genreTextView = itemView.findViewById(R.id.genreTextView);
        posterImageView = itemView.findViewById(R.id.posterImageView);
    }
    // Method to bind the movie(object) data to the views
    public void bind(Movie movie){
        // Set text values from the movie object
        titleTextView.setText(movie.getTitle());
        yearTextView.setText(String.valueOf(movie.getYear()));// Convert int year to String
        genreTextView.setText(movie.getGenre());
        // Set poster image (set default placeholder if poster is missing)
        // Load movie poster image dynamically by retrieving its resourceID
        int posterResId=itemView.getContext().getResources()
                .getIdentifier(movie.getPosterResource(), "drawable", itemView.getContext().getPackageName());
        posterImageView.setImageResource(posterResId !=0 ? posterResId : R.drawable.default_poster);


    }


}
