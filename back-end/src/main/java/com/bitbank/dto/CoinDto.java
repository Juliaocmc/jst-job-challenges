package com.bitbank.dto;

import java.util.List;

import com.bitbank.model.Bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinDto {

    public String id;
    public String coinName;
    public Long amountCoins;
}
