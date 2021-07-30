package com.bitbank.dto;

import java.util.List;

import com.bitbank.model.Account;
import com.bitbank.model.Bank;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BankDto {

    public String id;
    public String name;
    public long agency;
    public List<Account> account;

    

    public BankDto(Bank bank) {
        this.id = bank.getId();
        this.name = bank.getName();
        this.agency = bank.getAgency();
        this.account = bank.getAccount();
    }
    


   
}
