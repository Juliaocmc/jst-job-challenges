package com.bitbank.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.bitbank.model.CoinApi;
import com.bitbank.model.Data;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RequestMapping(path = "/coinapi")
@RestController
public class CoinApiController {
    
    @GetMapping(value = "")
    public void getCoins() throws IOException {
        
    // RestTemplate template = new RestTemplate();
    URL url = new URL("https://api.coincap.io/v2/assets/bitcoin");
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
    // .getAsJsonObject("name");
    // var result = template.getForEntity(in.toString(), Data.class);


    System.out.println(locObj.get("name"));
    System.out.println(locObj.get("priceUsd"));
    Gson gson = new Gson();
    CoinApi coinApi = gson.fromJson(locObj, CoinApi.class);
    System.out.println(coinApi.getName());

    
    // RestTemplate template2 = new RestTemplateBuilder().rootUri("https://api.coincap.io/v2/assets").defaultHeader("Accept-Encoding", "gzip")
    // .defaultHeader("Content-Type", "application/json").build();
    // Gson gson2 = new Gson();
    // Data coinApi2 = gson.fromJson(template2.toString(), Data.class);
    // System.out.println(coinApi2.getData().getName());
    // RestTemplate template2 = new RestTemplate();
    // // https://api.coincap.io/v2/assets/bitcoin
    // UriComponents uri = UriComponentsBuilder.newInstance()
    // .scheme("https")
    // .host("api.coincap.io")
    // .path("v2/assets")
    // .queryParam("id", "bitcoin")
    // .
    // .build();
    // Gson gson2 = new Gson();
    // Data coinApi2 = gson2.fromJson(uri.toUriString(), Data.class);
    // System.out.println(coinApi2.getData().getName());

   

    //'Content-Type' : 'application/json; charset=utf-8'   
    
    // We encourage clients to use compression via the Accept-Encoding header.

    // Accept-Encoding: gzip or Accept-Encoding: deflate
    
    // System.out.println(result);
    }
}
