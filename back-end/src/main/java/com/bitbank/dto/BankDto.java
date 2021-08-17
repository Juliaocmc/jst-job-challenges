package com.bitbank.dto;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BankDto {

    public String id;
    public String name;
    public long agency;
    public List<AccountDto> accountList;

    
   
}
