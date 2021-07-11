package com.bitbank.controller;

import com.bitbank.domain.model.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping("/clients")
    public Client listar() {
        Client client = new Client();
        client.setCpf(1L);
        return client;
    }
}
