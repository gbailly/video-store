package com.videostore;

import org.junit.Before;
import org.junit.Test;

import static com.videostore.MovieType.CHILDREN;
import static com.videostore.MovieType.NEW_RELEASE;
import static com.videostore.MovieType.REGULAR;
import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private static final double DELTA = .001;

    private Statement statement;

    @Before
    public void setUp() {
        statement = new Statement("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        statement.addRental(new Rental(new Movie("The Matrix", NEW_RELEASE), 3));
        statement.execute();
        assertEquals(9.0, statement.getTotalAmount(), DELTA);
        assertEquals(2, statement.getLoyaltyPoints());
    }

    @Test
    public void testDualNewReleaseStatement() {
        statement.addRental(new Rental(new Movie("The Matrix", NEW_RELEASE), 3));
        statement.addRental(new Rental(new Movie("Inception", NEW_RELEASE), 3));
        statement.execute();
        assertEquals(18.0, statement.getTotalAmount(), DELTA);
        assertEquals(4, statement.getLoyaltyPoints());
    }

    @Test
    public void testSingleChildrenStatement() {
        statement.addRental(new Rental(new Movie("Despicable Me", CHILDREN), 3));
        statement.execute();
        assertEquals(1.5, statement.getTotalAmount(), DELTA);
        assertEquals(1, statement.getLoyaltyPoints());
    }

    @Test
    public void testMultipleRegularStatement() {
        statement.addRental(new Rental(new Movie("E.T.", REGULAR), 1));
        statement.addRental(new Rental(new Movie("Pulp Fiction", REGULAR), 2));
        statement.addRental(new Rental(new Movie("Pirates of the Caribbean", REGULAR), 3));
        statement.execute();
        assertEquals(7.5, statement.getTotalAmount(), DELTA);
        assertEquals(3, statement.getLoyaltyPoints());
    }

    @Test
    public void testStatementString() {
        statement.addRental(new Rental(new Movie("E.T.", REGULAR), 1));
        statement.addRental(new Rental(new Movie("Pulp Fiction", REGULAR), 2));
        statement.addRental(new Rental(new Movie("Pirates of the Caribbean", REGULAR), 3));
        assertEquals(
                "Rental Record for Fred\n"
                        + "\tE.T.\t2.0\n"
                        + "\tPulp Fiction\t2.0\n"
                        + "\tPirates of the Caribbean\t3.5\n"
                        + "You owed 7.5\n"
                        + "You earned 3 loyalty points\n",
                statement.execute());
    }
}
