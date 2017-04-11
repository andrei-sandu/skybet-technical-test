package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DecimalBetTest {

    private DecimalBet decimalBet;

    @Before
    public void setUp() throws Exception {
        decimalBet = new DecimalBet(20, "event", "name", 12.0, null, null);
    }

    @Test
    public void testSetOdds() throws Exception {
        decimalBet.setOdds(11.0);
        assertThat(decimalBet.getOdds(), is(11.0));
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testSetOddsRejectsInvalidOdds() throws Exception {
        decimalBet.setOdds(0.5);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testSetOddsRejectsInvalidOddsAtBoundary() throws Exception {
        decimalBet.setOdds(1.0);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testConstructorRejectsInvalidOdds() throws Exception {
        double invalidOdds = 0.5;
        decimalBet = new DecimalBet(20, "event", "name", invalidOdds, null, null);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testConstructorRejectsInvalidOddsAtBoundary() throws Exception {
        double invalidOdds = 1.0;
        decimalBet = new DecimalBet(20, "event", "name", invalidOdds, null, null);
    }
}