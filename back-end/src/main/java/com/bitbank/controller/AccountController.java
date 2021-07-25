package com.bitbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.AccountDto;
import com.bitbank.model.Account;
import com.bitbank.model.Coin;
import com.bitbank.server.AccountService;
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
    public AccountService as;

    @GetMapping("")
    public ResponseEntity<List<AccountDto>> findAll() {
        return ResponseEntity.ok(as.findAll().stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getUserById(@PathVariable String accountId) {
        var account = as.getById(accountId);
        return ResponseEntity.ok(toDto(account));
    }

    @PutMapping("/{accountId}/coin/{coinId}")
    public @ResponseBody  ResponseEntity<String> addCoin(@PathVariable("accountId") String accountId,@PathVariable("coinId") String coinId ) {
        var account = as.getById(accountId);
        var coin = coinService.getById(coinId);
        List<Coin> coinsList = new ArrayList<>();
        coinsList.add(coin);
        account.setCoinList(coinsList);
        as.save(account);
        return ResponseEntity.ok("deposito realizado com sucesso");
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Account> save(@RequestBody AccountDto accountDto){
        var account = modelMapper.map(accountDto, Account.class);
        as.save(account);
        return ResponseEntity.ok(account);

    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> delete(@PathVariable String accountId) {
        as.delete(accountId);
        return ResponseEntity.ok("Account successfully deleted!");

    }

    private AccountDto toDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

}
