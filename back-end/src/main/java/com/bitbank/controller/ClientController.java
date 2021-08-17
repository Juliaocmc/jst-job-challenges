package com.bitbank.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.BankBalanceDto;
import com.bitbank.dto.ClientDto;
import com.bitbank.dto.ClientResumeDto;
import com.bitbank.mapper.BankMapper;
import com.bitbank.mapper.ClientMapper;
import com.bitbank.model.Client;
import com.bitbank.server.AccountService;
import com.bitbank.server.BankService;
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
    public AccountService as;

    @Autowired
    public BankService bs;

    @Autowired
    public ClientService cs;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    public BankMapper bankMapper;

    @GetMapping("")
    public ResponseEntity<List<ClientResumeDto>> findAll() {
        var clientList = cs.findAll();
        return ResponseEntity.ok(clientMapper.toResumeDtoList(clientList));
    }
    
    @GetMapping("/{clientId}")   
    public ClientDto getUserById(@PathVariable String clientId) {         
        return cs.relationshipBankClient(clientId);
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Client> saveClient(@RequestBody ClientDto clientDto){
        var client = modelMapper.map(clientDto, Client.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        client.setPassword(passwordEncoder.encode(client.getPassword()));        
        cs.save(client);
        return ResponseEntity.ok(client);
        
    }


    @GetMapping("/{clientId}/account/{accountId}")
    public @ResponseBody ResponseEntity<List<BankBalanceDto>> getBankBalanceByAccount(@PathVariable("clientId") String clientId, @PathVariable("accountId") String accountId) throws IOException{
        var client = cs.getById(clientId);
        var account = as.getById(accountId);
        var bankBalance = as.getBankBalanceByAccount(client, account);
        return ResponseEntity.ok(bankBalance);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> delete(@PathVariable String clientId) {
        cs.delete(clientId);
        return ResponseEntity.ok("Client successfully deleted!");
        
    }


   



  
     }