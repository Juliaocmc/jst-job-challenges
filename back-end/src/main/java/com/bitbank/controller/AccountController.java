package com.bitbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.AccountDto;
import com.bitbank.dto.CoinDto;
import com.bitbank.model.Account;
import com.bitbank.model.Coin;
import com.bitbank.server.AccountService;
import com.bitbank.server.ClientService;
import com.bitbank.server.CoinService;

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

@RequestMapping(path = "/account")
@RestController
public class AccountController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public CoinService coinService;

    @Autowired
    public ClientService clientService;

    @Autowired
    public AccountService accountService;

    @GetMapping("")
    public ResponseEntity<List<AccountDto>> findAll() {
        try {
            return ResponseEntity.ok(accountService.findAll().stream().map(this::toDto).collect(Collectors.toList()));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String accountId) {
        try {
            var account = accountService.getById(accountId);
            return ResponseEntity.ok(toDto(account));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<AccountDto>> getAccountByClient(@PathVariable("clientId") String clientId ){       
        try {
            return ResponseEntity.ok(accountService.getAccountByClient(clientId).stream().map(this::toDto).collect(Collectors.toList()));
        } catch (Exception e) {
            return null; 
        }
    }

    @PostMapping("/{accountId}/client/{clientId}")
    public @ResponseBody ResponseEntity<String> depositCoins(@PathVariable("clientId") String clientId, 
    @PathVariable("accountId") String accountId, @RequestBody CoinDto coinDto ){   
        try {
            if(accountService.clientHasAccount(clientId, accountId)){
                var coin = modelMapper.map(coinDto, Coin.class);
                accountService.depositCoins(coin, accountId);
                return ResponseEntity.ok("deposit made successfully!:"+coin.getCoinName()+" Amount :" +coin.getAmountCoins()+" ");
            }
            
            return ResponseEntity.ok("Cliente não tem conta");
        } catch (Exception e) {
            return null;
        }
        
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Account> save(@RequestBody AccountDto accountDto){
        try {
            var account = modelMapper.map(accountDto, Account.class);
            accountService.save(account);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return null;
        }

    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> delete(@PathVariable String accountId) {
        try {
            accountService.delete(accountId);
            return ResponseEntity.ok("Account successfully deleted!");
        } catch (Exception e) {
            return null;
        }

    }

    private AccountDto toDto(Account account) {
        try {
            return modelMapper.map(account, AccountDto.class);
        } catch (Exception e) {
            return null;
        }
    }

}
