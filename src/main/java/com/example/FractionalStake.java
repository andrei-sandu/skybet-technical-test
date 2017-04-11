package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
class FractionalStake extends Bet {

    @Override
    public String toString() {
        return "FractionalStake{" +
                "bet_id=" + bet_id +
                ", odds=" + odds +
                ", stake=" + stake +
                '}';
    }

    private int bet_id;
    private Odds odds;
    private double stake;

    @JsonCreator
    FractionalStake(@JsonProperty("bet_id") int bet_id, @JsonProperty("odds") Odds odds, @JsonProperty("stake") double stake) {
        super(bet_id);
        this.bet_id = bet_id;
        this.odds = odds;
        this.stake = stake;
    }

    @Override
    public Bet convertOdds() {
        // TODO: method is never used for the purpose of this task, so it has not been implemented
        return null;
    }

    // GETTERS AND SETTERS
   public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }
}
