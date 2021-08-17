package com.bitbank.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    public String id;
    public Long number;
    public CoinDto coin;

}
