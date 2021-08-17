package com.bitbank.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BankDto {

    public String id;
    public String name;
    public long agency;
    public AccountDto account;

    
   
}
