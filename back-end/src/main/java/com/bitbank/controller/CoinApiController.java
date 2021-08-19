package com.bitbank.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.bitbank.model.CoinApi;
import com.bitbank.server.CoinApiService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping(path = "/coinapi")
@RestController
public class CoinApiController {

    @Autowired
    CoinApiService coinApiService;
    
    @GetMapping(value = "")
    public CoinApi[] findAllCoinsApi() throws IOException {
        try {
            return  coinApiService.findAllCoinsApi();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/{coinName}")
    public CoinApi findCoinApiByName(@PathVariable String coinName)throws IOException {
        try {
            return  coinApiService.findCoinApiByName(coinName);
        } catch (Exception e) {
            return null;
        }
        
    
    
    }
}
