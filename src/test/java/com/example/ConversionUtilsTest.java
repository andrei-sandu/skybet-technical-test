package com.example;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConversionUtilsTest {

    @Test
    public void testConvertToFractional() throws Exception {
        Odds result = ConversionUtils.convertToFractional(11.0);
        assertThat(result.getNumerator(), is(10));
        assertThat(result.getDenominator(), is(1));
    }

    @Test
    public void convertToDecimal() throws Exception {
        Odds odds = new Odds(10, 1);
        assertThat(ConversionUtils.convertToDecimal(odds), is(11.0));
    }

}