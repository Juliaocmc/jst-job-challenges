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
        try {
            return ResponseEntity.ok(coinService.findAll().stream().map(this::toDto).collect(Collectors.toList()));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{coinId}")
    public ResponseEntity<CoinDto> getUserById(@PathVariable String coinId) {
        try {
            var coin = coinService.getById(coinId);
            return ResponseEntity.ok(toDto(coin));
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Coin> save(@RequestBody CoinDto coinDto){
        try {
            var coin = modelMapper.map(coinDto, Coin.class);
            coinService.save(coin);
            return ResponseEntity.ok(coin);
        } catch (Exception e) {
            return null;
        }

    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> delete(@PathVariable String accountId) {
        try {
            coinService.delete(accountId);
            return ResponseEntity.ok("Coin successfully deleted!");
        } catch (Exception e) {
            return null;
        }

    }

    private CoinDto toDto(Coin account) {
        try {
            return modelMapper.map(account, CoinDto.class);
        } catch (Exception e) {
            return null;
        }
    }

}
