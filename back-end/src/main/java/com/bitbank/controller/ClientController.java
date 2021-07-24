package com.bitbank.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.ClientDto;
import com.bitbank.dto.ClientTestizinho;
import com.bitbank.model.Client;
import com.bitbank.server.ClientService;

import org.modelmapper.ModelMapper;
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
    public ModelMapper modelMapper;

    @Autowired
    public ClientService cs;

    @GetMapping("")
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(cs.findAll().stream().map(this::toDto).collect(Collectors.toList()));
    }
    
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getUserById(@PathVariable String clientId) {   
        var client = cs.getById(clientId);
        return ResponseEntity.ok(toDto(client));
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Client> saveClient(@RequestBody ClientDto clientDto) throws Exception {
        var client = modelMapper.map(clientDto, Client.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        client.setPassword(passwordEncoder.encode(client.getPassword()));        
        cs.save(client);
        return ResponseEntity.ok(client);
        
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> delete(@PathVariable String clientId) {
        cs.delete(clientId);
        return ResponseEntity.ok("Ok");
        
    }


    private ClientDto toDto(Client client){
        return modelMapper.map(client, ClientDto.class);
    }

   
}
