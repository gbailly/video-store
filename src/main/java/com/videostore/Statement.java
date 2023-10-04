package com.videostore;

import java.util.ArrayList;
import java.util.List;

import static com.videostore.MovieType.NEW_RELEASE;

public class Statement {

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    private double totalAmount;
    private int frequentRenterPoints;

    public Statement(final String name) {
        this.name = name;
    }

    public void addRental(final Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public String execute() {
        init();
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental : rentals) {
            final double totalAmount = rental.calculateTotalAmount();

            frequentRenterPoints++;

            if (rental.getMovieType() == NEW_RELEASE && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            result += "\t" + rental.getMovieTitle() + "\t" + String.valueOf(totalAmount) + "\n";
            this.totalAmount += totalAmount;
        }

        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";

        return result;
    }

    private void init() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }
}
