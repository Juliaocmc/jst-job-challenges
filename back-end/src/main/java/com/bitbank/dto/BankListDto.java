package com.bitbank.dto;

import java.util.List;

import com.bitbank.model.Account;
import com.bitbank.model.Bank;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BankListDto {

    public String id;
    public String name;
    public long agency;
    public List<Account> account;

   
    public BankListDto(String id, String name, long agency, List<Account> account) {
        this.id = id;
        this.name = name;
        this.agency = agency;
        this.account = account;
    }


    public BankListDto(Bank banks) {
        this.id = banks.getId();
        this.name = banks.getName();
        this.agency = banks.getAgency();
        this.account = banks.getAccount();
    }

    
   
}
