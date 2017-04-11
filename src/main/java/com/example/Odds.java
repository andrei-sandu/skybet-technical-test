package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class Odds {

    @JsonProperty("numerator")
    private int numerator;
    @JsonProperty("denominator")
    private int denominator;

    @Override
    public String toString() {
        return "Odds{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    @JsonCreator
    Odds(@JsonProperty("numerator") int numerator, @JsonProperty("denominator") int denominator) throws InvalidOddsValueException {
        if (numerator <= 0 || denominator <= 0) {
            throw new InvalidOddsValueException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    int getDenominator() {
        return denominator;
    }

    int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) throws InvalidOddsValueException {
        if(numerator <= 0) {
            throw new InvalidOddsValueException();
        }
        this.numerator = numerator;
    }

    public void setDenominator(int denominator) throws InvalidOddsValueException {
        if(denominator <= 0) {
            throw new InvalidOddsValueException();
        }
        this.denominator = denominator;
    }
}
