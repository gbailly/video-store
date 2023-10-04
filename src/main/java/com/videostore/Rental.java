package com.videostore;

public class Rental {

    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public int getMoviePriceCode() {
        return movie.getPriceCode();
    }

    public String getMovieTitle() {
        return movie.getTitle();
    }
}
