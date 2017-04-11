package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class Bet implements OddsConverter {
    @SuppressWarnings("unused")
    private int bet_id;

    Bet(@JsonProperty("bet_id") int bet_id) {
        this.bet_id = bet_id;
    }
}
