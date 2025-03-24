package com.example.movies_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app.R;
import com.example.movies_app.model.Movie;
import androidx.annotation.NonNull;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movies; //will be displayed in RecyclerView
    // Constructor: Initializes the adapter with a list of movies
    public MovieAdapter(List<Movie> movies){
        this.movies=movies;

    }

    /**
     * Called when RecyclerView needs a new ViewHolder (row layout).
     * This method inflates the layout for each movie item.
     *
     * @param parent   The parent ViewGroup
     * @param viewType The view type (useful if multiple layouts exist)
     * @return A new MovieViewHolder instance
     */

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // Inflate item_movie.xml and create a View object
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);//that holds this view
    }
    /**
     * Assigns movie data to the views inside ViewHolder.
     * @param holder   The ViewHolder to update
     * @param position The position of the item in the list
     */
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position){
        Movie movie=movies.get(position);
        // Call the bind method in MovieViewHolder to set data
        holder.bind(movie);
    }
    //Calculate total number of items in the RecyclerView.Checks for null list
    @Override
    public int getItemCount(){
        return movies != null? movies.size() : 0;
    }
    // Update movie list dynamically, refresh the RecyclerView
    //* @param newMovies The new list of movies
    public void updateMovies(List<Movie> newMovies){
        this.movies=newMovies;
        // Notify RecyclerView that the data set has changed (forces UI refresh)
        notifyDataSetChanged();
    }

}

