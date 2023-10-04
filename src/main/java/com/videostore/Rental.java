package com.videostore;

public class Rental {

    private final Movie movie;
    private final int daysRented;

    public Rental(final Movie movie, final int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public MovieType getMovieType() {
        return movie.getMovieType();
    }

    public String getMovieTitle() {
        return movie.getTitle();
    }

    public double calculateExtraAmount() {
        switch (getMovieType()) {
            case REGULAR:
                if (getDaysRented() > 2) {
                    return (getDaysRented() - 2) * 1.5;
                }
                break;
            case NEW_RELEASE:
                return getDaysRented() * 3;
            case CHILDREN:
                if (getDaysRented() > 3) {
                    return (getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return 0;
    }

    public double calculateTotalAmount() {
        return movie.getBasisAmount() + calculateExtraAmount();
    }
}
