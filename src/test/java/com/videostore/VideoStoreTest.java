package com.videostore;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {

    private static final double DELTA = .001;

    private Statement statement;

    @Before
    public void setUp() {
        statement = new Statement("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        statement.addRental(new Rental(new Movie("The Matrix", Movie.NEW_RELEASE), 3));
        statement.execute();
        assertEquals(9.0, statement.getTotalAmount(), DELTA);
        assertEquals(2, statement.getFrequentRenterPoints());
    }

    @Test
    public void testDualNewReleaseStatement() {
        statement.addRental(new Rental(new Movie("The Matrix", Movie.NEW_RELEASE), 3));
        statement.addRental(new Rental(new Movie("Inception", Movie.NEW_RELEASE), 3));
        statement.execute();
        assertEquals(18.0, statement.getTotalAmount(), DELTA);
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrenStatement() {
        statement.addRental(new Rental(new Movie("Despicable Me", Movie.CHILDREN), 3));
        statement.execute();
        assertEquals(1.5, statement.getTotalAmount(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatement() {
        statement.addRental(new Rental(new Movie("E.T.", Movie.REGULAR), 1));
        statement.addRental(new Rental(new Movie("Pulp Fiction", Movie.REGULAR), 2));
        statement.addRental(new Rental(new Movie("Pirates of the Caribbean", Movie.REGULAR), 3));
        statement.execute();
        assertEquals(7.5, statement.getTotalAmount(), DELTA);
        assertEquals(3, statement.getFrequentRenterPoints());
    }

    @Test
    public void testStatementString() {
        statement.addRental(new Rental(new Movie("E.T.", Movie.REGULAR), 1));
        statement.addRental(new Rental(new Movie("Pulp Fiction", Movie.REGULAR), 2));
        statement.addRental(new Rental(new Movie("Pirates of the Caribbean", Movie.REGULAR), 3));
        assertEquals(
                "Rental Record for Fred\n"
                        + "\tE.T.\t2.0\n"
                        + "\tPulp Fiction\t2.0\n"
                        + "\tPirates of the Caribbean\t3.5\n"
                        + "You owed 7.5\n"
                        + "You earned 3 frequent renter points\n",
                statement.execute());
    }
}
