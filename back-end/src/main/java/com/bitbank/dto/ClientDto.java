package com.bitbank.dto;

import java.util.List;

import com.bitbank.dao.BankDao;
import com.bitbank.model.Bank;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ClientDto {
    
    public String id;
    public String name;
    public String login;
    public Long cpf;
    public String email;
    public String password;
    public List<BankDto> listBank;

    
    
}
