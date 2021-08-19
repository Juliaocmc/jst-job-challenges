package com.bitbank.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.bitbank.model.CoinExchange;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.stereotype.Service;

@Service
public class CoinExchangeService {
    

    public CoinExchange getBrlValue() throws IOException {
        try {
            URL url = new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/usd/brl.json");
            
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
            
            
            Gson gson = new Gson();
            return gson.fromJson(rootObj, CoinExchange.class);
        } catch (Exception e) {
            return null;
        }
    
    }
}
