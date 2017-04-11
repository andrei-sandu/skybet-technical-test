package com.example;

class ConversionUtils {

    static Odds convertToFractional(double odds) {
        double fractional = odds - 1.0;
        int numerator = (int) fractional;
        int denominator = 1;
        while(numerator != fractional) {
            numerator = (int) fractional;
            fractional *= 10;
            denominator *= 10;
        }
        int smallerFactor = (numerator < denominator)? numerator : denominator;
        for (int commonFactor = 2; commonFactor < Math.sqrt(smallerFactor); commonFactor++) {
            while (commonFactor % numerator == 0 && commonFactor % denominator == 0) {
                numerator /= commonFactor;
                denominator /= commonFactor;
            }
        }
        try {
            return new Odds(numerator, denominator);
        } catch (InvalidOddsValueException e) {
            return null;
        }
    }

    static double convertToDecimal(Odds odds) {
        int numerator = odds.getNumerator();
        int denominator = odds.getDenominator();
        return 1 + ((double)numerator) / denominator;
    }
}
