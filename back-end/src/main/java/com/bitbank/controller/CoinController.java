package com.bitbank.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bitbank.dto.CoinDto;
import com.bitbank.model.Coin;
import com.bitbank.server.CoinService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/coin")
@RestController
public class CoinController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public CoinService coinService;

    @GetMapping("")
    public ResponseEntity<List<CoinDto>> findAll() {
        return ResponseEntity.ok(coinService.findAll().stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/{coinId}")
    public ResponseEntity<CoinDto> getUserById(@PathVariable String coinId) {
        var coin = coinService.getById(coinId);
        return ResponseEntity.ok(toDto(coin));
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Coin> save(@RequestBody CoinDto coinDto){
        var coin = modelMapper.map(coinDto, Coin.class);
        coinService.save(coin);
        return ResponseEntity.ok(coin);

    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> delete(@PathVariable String accountId) {
        coinService.delete(accountId);
        return ResponseEntity.ok("Coin successfully deleted!");

    }

    private CoinDto toDto(Coin account) {
        return modelMapper.map(account, CoinDto.class);
    }

}
