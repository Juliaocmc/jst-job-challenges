package com.bitbank.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.bitbank.model.CoinExchange;
import com.bitbank.server.CoinExchangeService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping(path = "/exchange")
@RestController
public class CoinExchangeController {

    @Autowired
    CoinExchangeService coinExchangeService;
    
    @GetMapping(value = "")
    public CoinExchange getBrlValue() throws IOException{   
        try {
            
            return coinExchangeService.getBrlValue();
        } catch (Exception e) {
            return null;
        }
    }

       
    
    
    
}
