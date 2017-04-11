package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

public class DecimalStakeTest {

    private DecimalStake decimalBet;

    @Before
    public void setUp() throws Exception {
        decimalBet = new DecimalStake(anyInt(), 12.0, anyDouble());
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
        decimalBet = new DecimalStake(anyInt(), 0.5, anyDouble());
    }


    @Test(expected = InvalidOddsValueException.class)
    public void testConstructorRejectsInvalidOddsAtBoundary() throws Exception {
        decimalBet = new DecimalStake(anyInt(), 1.0, anyDouble());
    }

}