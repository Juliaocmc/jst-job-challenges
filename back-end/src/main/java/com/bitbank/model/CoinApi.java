package com.bitbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @JsonIgnoreProperties(value = {"data", "timestamp"}, 
//                       allowGetters = true)
public class CoinApi {   
    private String name;
    private String priceUsd;

}
    
