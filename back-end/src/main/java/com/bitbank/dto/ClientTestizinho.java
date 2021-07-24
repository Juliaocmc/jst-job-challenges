package com.bitbank.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTestizinho implements Serializable {

    public String id;
    public String name;
    public String login;
    public String email;
}
