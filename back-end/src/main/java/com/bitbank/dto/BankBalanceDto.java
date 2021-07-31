package com.bitbank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankBalanceDto {

    public String clientName;
    public Long accountNumber;
    public String coinName;
    public Double bankBalance;
    
}
