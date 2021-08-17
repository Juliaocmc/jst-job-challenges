package com.bitbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.BankDto;
import com.bitbank.mapper.BankMapper;
import com.bitbank.model.Account;
import com.bitbank.model.Bank;
import com.bitbank.server.AccountService;
import com.bitbank.server.BankService;
import com.bitbank.server.ClientService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/bank")
@RestController
public class BankController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public BankMapper bankMapper;

    @Autowired
    public BankService bankService;

    @Autowired
    public ClientService clientService;

    @Autowired
    public AccountService accountService;

    @GetMapping("")
    public ResponseEntity<List<BankDto>> findAll() {
        List<Bank> bankList = bankService.findAll();
        return ResponseEntity.ok(bankMapper.mapperListBankDtoForPojo(bankList));
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<BankDto> getUserById(@PathVariable String bankId) {
        var bank = bankService.getById(bankId);
        return ResponseEntity.ok(bankMapper.toDto(bank));
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Bank> save(@RequestBody BankDto bankDto){
        var bank = bankMapper.toPojo(bankDto);
        bankService.save(bank);
        return ResponseEntity.ok(bank);

    }

    @PutMapping("/{bankId}/client/{clientId}")
    public @ResponseBody ResponseEntity<String> addAccount(@PathVariable("bankId") String bankId,@PathVariable("clientId") String clientId){
        var bank = bankService.getById(bankId);
        var client = clientService.getById(clientId);
        var account = bankService.linkClientToAccount(bank, client);  
        return ResponseEntity.ok("Account Nº " +account.getNumber()+" successfully registered with the Bank " +bank.getName()+ 
                                " Agency Nº" +bank.getAgency()+ " in the name of " +client.getName() );

    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<String> delete(@PathVariable String bankId) {
        bankService.delete(bankId);
        return ResponseEntity.ok("Back successfully deleted!");

    }

 

}
