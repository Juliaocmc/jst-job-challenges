package com.bitbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.BankDto;
import com.bitbank.model.Account;
import com.bitbank.model.Bank;
import com.bitbank.server.AccountService;
import com.bitbank.server.BankService;

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
    public ModelMapper modelMapper;

    @Autowired
    public BankService bs;

    @Autowired
    public AccountService as;

    @GetMapping("")
    public ResponseEntity<List<BankDto>> findAll() {
        return ResponseEntity.ok(bs.findAll().stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<BankDto> getUserById(@PathVariable String bankId) {
        var bank = bs.getById(bankId);
        return ResponseEntity.ok(toDto(bank));
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Bank> save(@RequestBody BankDto bankDto){
        var bank = modelMapper.map(bankDto, Bank.class);
        bs.save(bank);
        return ResponseEntity.ok(bank);

    }

    @PutMapping("/{bankId}/{accountId}")
    public @ResponseBody ResponseEntity<String> addAccount(@PathVariable("bankId") String bankId,@PathVariable("accountId") String accountId){
        var bank = bs.getById(bankId);
        var account = as.getById(accountId);
        List<Account> listAccount = new ArrayList<>();
        listAccount.add(account);
        bank.setAccount(listAccount);
        bs.save(bank);
        return ResponseEntity.ok("account successfully registered with the bank");

    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<String> delete(@PathVariable String bankId) {
        bs.delete(bankId);
        return ResponseEntity.ok("Back successfully deleted!");

    }

    private BankDto toDto(Bank bank) {
        return modelMapper.map(bank, BankDto.class);
    }

}
