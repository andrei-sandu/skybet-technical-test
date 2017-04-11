package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DecimalBetsController {

    private static final String BETTING_SERVICE_URI = "http://skybettechtestapi.herokuapp.com";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public ResponseEntity<List<DecimalBet>> getAvailable() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BETTING_SERVICE_URI + "/available", String.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(responseEntity.getStatusCode());
        }
        String json = responseEntity.getBody();
        FractionalBet[] fractionalBets;
        try {
            fractionalBets = mapper.readValue(json, FractionalBet[].class);
            ArrayList<DecimalBet> decimalBetsList = new ArrayList<>();
            for (FractionalBet fractionalBet : fractionalBets) {
                decimalBetsList.add(fractionalBet.convertOdds());
            }
            return new ResponseEntity<>(decimalBetsList, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/bets", method = RequestMethod.POST)
    public ResponseEntity<DecimalBet> postBets(@RequestBody DecimalStake stake) {
        ResponseEntity<String> responseEntity= restTemplate.postForEntity(BETTING_SERVICE_URI + "/bets", stake.convertOdds(), String.class);
        if(responseEntity.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(responseEntity.getStatusCode());
        }
        String json = responseEntity.getBody();
        try {
            FractionalBet fractionalBet = mapper.readValue(json, FractionalBet.class);
            return new ResponseEntity<>(fractionalBet.convertOdds(), HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
