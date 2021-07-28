package com.bitbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @JsonIgnoreProperties(value = {"data", "timestamp"}, 
//                       allowGetters = true)
public class CoinApi {
    private String id;
    private String rank;
    private String name;
    private String symbol;
    private String supply;
    private String maxSupply;
    private String marketCapUsd;
    private String volumeUsd24Hr;
    private String priceUsd;
    private String changePercent24Hr;
    private String vwap24Hr;
    private String explorer;
    private String timestamp;
}
    
