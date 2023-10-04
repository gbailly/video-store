package com.videostore;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    private double totalAmount;
    private int loyaltyPoints;

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

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public String execute() {
        init();
        String result = "Rental Record for " + getName() + "\n";
        for (final Rental rental : rentals) {
            final double totalAmount = rental.calculateTotalAmount();

            loyaltyPoints += rental.calculateLoyaltyPoints();

            result += "\t" + rental.getMovieTitle() + "\t" + String.valueOf(totalAmount) + "\n";
            this.totalAmount += totalAmount;
        }

        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(loyaltyPoints) + " loyalty points\n";

        return result;
    }

    private void init() {
        totalAmount = 0;
        loyaltyPoints = 0;
    }
}
