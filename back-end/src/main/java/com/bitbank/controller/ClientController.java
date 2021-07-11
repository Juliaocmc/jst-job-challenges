package com.bitbank.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.domain.model.Client;
import com.bitbank.domain.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
public class ClientController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ClienteRepository clienteRepository;

    @GetMapping("/client")
    public List<Client> listar() {

        return clienteRepository.findAll();
    }
}
