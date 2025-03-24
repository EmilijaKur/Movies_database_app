package com.example.movies_app.model;

public class Movie {
    private String title;
    private int year;
    private String genre;
    private String posterResource;
    // Constructor + validation of fields (if-else) and setting default values
    public Movie(String title, int year, String genre, String posterResource){
        this.title=(title != null) ? title: "Unknown title";
        this.year=(year>1800 && year<2100) ? year: 0;
        this.genre=(genre != null) ? genre : "Unknown genre";
        this.posterResource=(posterResource !=null) ? posterResource: "default_poster";

    }
    //Getters
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPosterResource() {
        return posterResource;
    }
}
