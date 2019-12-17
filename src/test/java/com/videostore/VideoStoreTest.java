package com.videostore;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {

    private static final double DELTA = .001;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Matrix", Movie.NEW_RELEASE), 3));
        customer.statement();
        assertEquals(9.0, customer.getTotalAmount(), DELTA);
        assertEquals(2, customer.getFrequentRenterPoints());
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Matrix", Movie.NEW_RELEASE), 3));
        customer.addRental(new Rental(new Movie("Inception", Movie.NEW_RELEASE), 3));
        customer.statement();
        assertEquals(18.0, customer.getTotalAmount(), DELTA);
        assertEquals(4, customer.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrenStatement() {
        customer.addRental(new Rental(new Movie("Despicable Me", Movie.CHILDREN), 3));
        customer.statement();
        assertEquals(1.5, customer.getTotalAmount(), DELTA);
        assertEquals(1, customer.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(new Movie("E.T.", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("Pulp Fiction", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Pirates of the Caribbean", Movie.REGULAR), 3));
        customer.statement();
        assertEquals(7.5, customer.getTotalAmount(), DELTA);
        assertEquals(3, customer.getFrequentRenterPoints());
    }

    @Test
    public void testStatementString() {
        customer.addRental(new Rental(new Movie("E.T.", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("Pulp Fiction", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Pirates of the Caribbean", Movie.REGULAR), 3));
        assertEquals(
                "Rental Record for Fred\n"
                        + "\tE.T.\t2.0\n"
                        + "\tPulp Fiction\t2.0\n"
                        + "\tPirates of the Caribbean\t3.5\n"
                        + "You owed 7.5\n"
                        + "You earned 3 frequent renter points\n",
                customer.statement());
    }
}
