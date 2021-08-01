package com.bitbank.dto;

import java.util.List;

import com.bitbank.model.Coin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    public String id;
    public Long number;
    public Coin coin;

}
