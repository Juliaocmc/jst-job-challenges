package com.bitbank.dto;

import java.util.List;

import com.bitbank.model.Bank;

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
    public List<Bank> listBank;
}
