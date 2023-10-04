package com.videostore;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    private double totalAmount;
    private int frequentRenterPoints;

    public Statement(final String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
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
            double thisAMount = 0;

            // determines the amount for each line
            switch (rental.getMoviePriceCode()) {
                case Movie.REGULAR:
                    thisAMount += 2;
                    if (rental.getDaysRented() > 2) {
                        thisAMount += (rental.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAMount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDREN:
                    thisAMount += 1.5;
                    if (rental.getDaysRented() > 3) {
                        thisAMount += (rental.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

            frequentRenterPoints++;

            if (rental.getMoviePriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            result += "\t" + rental.getMovieTitle() + "\t" + String.valueOf(thisAMount) + "\n";
            totalAmount += thisAMount;
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
