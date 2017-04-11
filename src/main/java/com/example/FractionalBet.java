package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
class FractionalBet extends Bet {

    private Long transaction_id;
    private Double stake;

    @JsonCreator
    FractionalBet(@JsonProperty("bet_id") int bet_id, @JsonProperty("event") String event, @JsonProperty("name") String name,
                  @JsonProperty("odds") Odds odds, @JsonProperty("stake") Double stake, @JsonProperty("transaction_id") Long transaction_id) {
        super(bet_id);
        this.event = event;
        this.name = name;
        this.odds = odds;
        this.stake = stake;
        this.transaction_id = transaction_id;
    }

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

    @Override
    public DecimalBet convertOdds() {
        try {
            return new DecimalBet(
                    this.bet_id,
                    this.event,
                    this.name,
                    ConversionUtils.convertToDecimal(this.odds),
                    this.stake,
                    this.transaction_id
            );
        } catch (InvalidOddsValueException e) {
            return null;
        }
    }

    private int bet_id;
    private String event;
    private String name;
    private Odds odds;

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

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }
}
