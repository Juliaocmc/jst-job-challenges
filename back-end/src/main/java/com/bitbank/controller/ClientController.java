package com.bitbank.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.model.Client;
import com.bitbank.server.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping(path = "/client")
@RestController
public class ClientController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ClientService cs;

    @GetMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Client> findAll() {
        return cs.findAll();
    }
    
    @GetMapping("/{clientId}")
    public ResponseEntity<Optional<Client>> getUserById(@PathVariable String clientId) {
        var client = cs.findById(clientId);
        return ResponseEntity.ok(client);
        
    }

    @PostMapping("/")
    public @ResponseBody ResponseEntity<Client> saveClient(@RequestBody Client client) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var newClient = new Client();
        newClient.setPassword(passwordEncoder.encode(client.getPassword()));
        newClient.setAdmin(client.isAdmin());
        newClient.setCpf(client.getCpf());  
        newClient.setId(client.getId());
        newClient.setName(client.getName());
        newClient.setUsername(client.getUsername());   
        newClient.setEmail(client.getEmail());
        cs.save(newClient);
        return ResponseEntity.ok(newClient);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String clientId) {
        cs.delete(clientId);
        return ResponseEntity.ok("Ok");
        
    }
}
