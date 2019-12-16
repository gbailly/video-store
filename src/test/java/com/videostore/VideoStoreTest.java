package com.videostore;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Matrix", Movie.NEW_RELEASE), 3));
        assertEquals("Rental Record for Fred\n\tThe Matrix\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", customer.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Matrix", Movie.NEW_RELEASE), 3));
        customer.addRental(new Rental(new Movie("Inception", Movie.NEW_RELEASE), 3));
        assertEquals("Rental Record for Fred\n\tThe Matrix\t9.0\n\tInception\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", customer.statement());
    }

    @Test
    public void testSingleChildrenStatement() {
        customer.addRental(new Rental(new Movie("Despicable Me", Movie.CHILDREN), 3));
        assertEquals("Rental Record for Fred\n\tDespicable Me\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", customer.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
        customer.addRental(new Rental(new Movie("E.T.", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("Pulp Fiction", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Pirates of the Caribbean", Movie.REGULAR), 3));
        assertEquals("Rental Record for Fred\n\tE.T.\t2.0\n\tPulp Fiction\t2.0\n\tPirates of the Caribbean\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customer.statement());
    }
}
