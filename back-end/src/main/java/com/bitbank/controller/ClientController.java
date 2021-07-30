package com.bitbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.BankDto;
import com.bitbank.dto.ClientDto;
import com.bitbank.dto.ClientResumeDto;
import com.bitbank.model.Account;
import com.bitbank.model.Bank;
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
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("")
    public ResponseEntity<List<ClientResumeDto>> findAll() {
        return ResponseEntity.ok(cs.findAll().stream().map(this::resume).collect(Collectors.toList()));
    }
    
    @GetMapping("/{clientId}")
    public ClientDto getUserById(@PathVariable String clientId) {   
        var client = cs.getById(clientId);
        var bank = bs.getListBankByClient(clientId);
        var bankDto =  bank.stream().map(BankDto::new).collect(Collectors.toList());
        var clientDto = toDto(client);
        clientDto.setListBank(bankDto);

        return clientDto;
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Client> saveClient(@RequestBody ClientDto clientDto){
        var client = modelMapper.map(clientDto, Client.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        client.setPassword(passwordEncoder.encode(client.getPassword()));        
        cs.save(client);
        return ResponseEntity.ok(client);
        
    }

    @PutMapping("/{clientId}/account/{accountId}")
    public @ResponseBody ResponseEntity<String> addAccount(@PathVariable("clientId") String clientId, @PathVariable("accountId") String accountId){
        var client = cs.getById(clientId);
        var account = as.getById(accountId);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        client.setAccount(accountList);
        cs.save(client);
        return ResponseEntity.ok("Client account successfully registered");
    }

    // @PutMapping("/{clientId}/bank/{bankId}")
    // public @ResponseBody ResponseEntity<String> addBank(@PathVariable("clientId") String clientId, @PathVariable("bankId") String bankId){
    //     var client = cs.getById(clientId);
    //     var bank = bs.getById(bankId);
    //     List<Bank> bankList = new ArrayList<>();
    //     bankList.add(bank);
    //     client.setBank(bankList);
    //     cs.save(client);
    //     return ResponseEntity.ok("Client bank successfully registered");
    // }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> delete(@PathVariable String clientId) {
        cs.delete(clientId);
        return ResponseEntity.ok("Client successfully deleted!");
        
    }


    private ClientDto toDto(Client client){
        return modelMapper.map(client, ClientDto.class);
    }

    private ClientResumeDto resume(Client client){
        return modelMapper.map(client, ClientResumeDto.class);
    }

    public static List<BankDto> bankConvert(List<Bank> bank) {
        return bank.stream().map(BankDto::new).collect(Collectors.toList());
    }
    
    public static List<BankDto> converter(List<Bank> bank) {
        return bank.stream().map(banks -> {
                return new BankDto(banks);
            }).collect(Collectors.toList());
    }
   
}
