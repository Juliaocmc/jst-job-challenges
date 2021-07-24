package com.bitbank.dto;

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
}
