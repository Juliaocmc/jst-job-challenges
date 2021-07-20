package com.bitbank.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.domain.model.Client;
import com.bitbank.domain.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
public class ClientController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ClienteRepository clienteRepository;

    @GetMapping("/client")
    public List<Client> findAll() {
        return clienteRepository.findAll();
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getUserById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        var client = clienteRepository.getById(id);
        return ResponseEntity.ok(client);
        
    }

    @PostMapping("/client")
    public @ResponseBody ResponseEntity<Client> saveClient(@RequestBody Client client) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var newClient = new Client();
        newClient.setPassword(passwordEncoder.encode(client.getPassword()));
        newClient.setAdmin(client.isAdmin());
        newClient.setCpf(client.getCpf());  
        newClient.setId(client.getId());
        newClient.setName(client.getName());
        newClient.setUsername(client.getUsername());   
        newClient.setEmail(client.getEmail());
        clienteRepository.save(newClient);
        return ResponseEntity.ok(newClient);
        
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        var client = clienteRepository.getById(id);
        clienteRepository.deleteById(client.getId());
        return ResponseEntity.ok("Ok");
        
    }
}
