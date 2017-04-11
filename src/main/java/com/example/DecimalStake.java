package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class DecimalStake extends Bet {

    private int bet_id;
    private double odds;
    private double stake;


    @Override
    public String toString() {
        return "DecimalStake{" +
                "bet_id=" + bet_id +
                ", odds=" + odds +
                ", stake=" + stake +
                '}';
    }

    @JsonCreator
    DecimalStake(@JsonProperty("bet_id") int bet_id, @JsonProperty("odds") double odds,
                 @JsonProperty("stake") double stake) throws InvalidOddsValueException {
        super(bet_id);
        if(odds <= 1.0) {
            throw new InvalidOddsValueException();
        }
        this.odds = odds;
        this.stake = stake;
    }

    @Override
    public FractionalStake convertOdds() {
        return new FractionalStake(this.bet_id, ConversionUtils.convertToFractional(this.odds), this.stake);
    }

    // GETTERS AND SETTERS
    int getBet_id() {
        return bet_id;
    }

    void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    double getOdds() {
        return odds;
    }

    void setOdds(double odds) throws InvalidOddsValueException {
        if(odds <= 1.0) {
            throw new InvalidOddsValueException();
        }
        this.odds = odds;
    }

    double getStake() {
        return stake;
    }

    void setStake(double stake) {
        this.stake = stake;
    }
}
