package com.bitbank.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.bitbank.model.CoinApi;
import com.bitbank.model.Data;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RequestMapping(path = "/coinapi")
@RestController
public class CoinApiController {
    
    @GetMapping(value = "")
    public CoinApi[] findAllCoinsApi() throws IOException {
        
    URL url = new URL("https://api.coincap.io/v2/assets");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Accept-Encoding", "deflate");

    int status = con.getResponseCode();
    BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
    }
    in.close();
    con.disconnect();

    JsonParser parser = new JsonParser();
    JsonObject rootObj = parser.parse(content.toString()).getAsJsonObject();
    JsonArray locObj = rootObj.getAsJsonArray("data");
    

    Gson gson = new Gson();
    return gson.fromJson(locObj, CoinApi[].class);  
    }

    @GetMapping(value = "/{coinName}")
    public CoinApi findCoinApiByName(@PathVariable String coinName)throws IOException {
        
    URL url = new URL("https://api.coincap.io/v2/assets/"+coinName);
    
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Accept-Encoding", "deflate");

    int status = con.getResponseCode();
    BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
    }
    in.close();
    con.disconnect();

    JsonParser parser = new JsonParser();
    JsonObject rootObj = parser.parse(content.toString()).getAsJsonObject();
    JsonObject locObj = rootObj.getAsJsonObject("data");
    

    Gson gson = new Gson();
    return gson.fromJson(locObj, CoinApi.class);
    
    }
}
