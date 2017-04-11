package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OddsTest {

    private Odds odds;
    @Before
    public void setUp() throws Exception {
        odds = new Odds(2, 3);
    }

    @Test
    public void testSetDenominator() throws Exception {
        odds.setDenominator(1);
        assertThat(odds.getDenominator(), is(1));
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testSetDenominatorRejectsInvalidOdds() throws Exception {
        odds.setDenominator(-12);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testSetDenominatorRejectsInvalidOddsAtBoundary() throws Exception {
        odds.setDenominator(0);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testConstructorRejectsInvalidDenominator() throws Exception {
        odds = new Odds(2, -12);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testConstructorRejectsInvalidDenominatorAtBoundary() throws Exception {
        odds = new Odds(2, 0);
    }

    @Test
    public void testSetNumerator() throws Exception {
        odds.setNumerator(1);
        assertThat(odds.getNumerator(), is(1));
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testSetNumeratorRejectsInvalidOdds() throws Exception {
        odds.setNumerator(-12);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testSetNumeratorRejectsInvalidOddsAtBoundary() throws Exception {
        odds.setNumerator(0);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testConstructorRejectsInvalidNumerator() throws Exception {
        odds = new Odds(-12, 3);
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testConstructorRejectsInvalidNumeratorAtBoundary() throws Exception {
        odds = new Odds(0, 3);
    }
}