package com.bitbank.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.bitbank.model.CoinApi;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.stereotype.Service;

@Service
public class CoinApiService {
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
    

    public CoinApi findCoinApiByName(String coinName) throws IOException {
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
