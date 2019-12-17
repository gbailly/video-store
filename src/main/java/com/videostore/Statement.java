package com.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Statement {

    private String name;
    private Vector rentals = new Vector();

    private double totalAmount;
    private int frequentRenterPoints;

    public Statement(final String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
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
        Enumeration rentals = this.rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAMount = 0;
            Rental each = (Rental) rentals.nextElement();

            // determines the amount for each line
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAMount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAMount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAMount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDREN:
                    thisAMount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAMount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

            frequentRenterPoints++;

            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAMount) + "\n";
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
