package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;

@JsonInclude(JsonInclude.Include.NON_NULL)
class DecimalBet extends Bet {

    @Override
    public String toString() {
        String nullableProperties = stake == null? "" : ", stake=" + stake + ", transaction_id=" + transaction_id;
        return "DecimalBet{" +
                "bet_id=" + bet_id +
                ", event='" + event + '\'' +
                ", name='" + name + '\'' +
                ", odds=" + odds +
                nullableProperties +
                '}';
    }

    private int bet_id;
    private String event;
    private String name;
    private double odds;
    private Double stake;
    private Long transaction_id;

    @JsonCreator
    DecimalBet(@JsonProperty("bet_id") int bet_id, @JsonProperty("event") String event, @JsonProperty("name") String name,
               @JsonProperty("odds") double odds, @Nullable Double stake, @Nullable Long transaction_id) throws InvalidOddsValueException {
        super(bet_id);
        if(odds <= 1.0) {
            throw new InvalidOddsValueException();
        }
        this.event = event;
        this.name = name;
        this.odds = odds;
        this.stake = stake;
        this.transaction_id = transaction_id;
    }

    @Override
    public FractionalBet convertOdds() {
        return new FractionalBet(
                this.bet_id,
                this.event,
                this.name,
                ConversionUtils.convertToFractional(this.odds),
                this.stake,
                this.transaction_id);
    }

    // GETTERS AND SETTERS
    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) throws InvalidOddsValueException {
        if(odds <= 1.0) {
            throw new InvalidOddsValueException();
        }
        this.odds = odds;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }
}
