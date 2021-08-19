package com.bitbank.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinApi extends BaseModel{   
    private String id;
    private String name;
    private String priceUsd;

}
    
