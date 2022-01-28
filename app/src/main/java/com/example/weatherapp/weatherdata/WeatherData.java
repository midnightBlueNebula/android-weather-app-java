package com.example.weatherapp.weatherdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class WeatherData {
    private String weather;
    private double degree;
    private String apiKey = "a09bed59da5c3978d688741fc25e6838";
    private String requestStr = "http://api.openweathermap.org/data/2.5/weather";
    private String options;

    public WeatherData(String city) {
        options = "?q=" + city + "&apikey=" + apiKey;
        requestStr += options;
        URL url = null;
        try {
            url = new URL(requestStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection request = null;
        try {
            request = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
            JSONObject jsonObj = (JSONObject) request.getContent();
            JSONArray weatherArr = jsonObj.getJSONArray("weather");
            HashMap<String, String> weatherHash = (HashMap<String, String>) weatherArr.get(0);

            weather = weatherHash.get("main"); /*assign weather state to instance*/

            JSONObject getDegree = jsonObj.getJSONObject("main");

            degree = getDegree.getInt("temp");
            
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public String getWeather() {
        return weather;
    }

    public double getDegree() {
        return degree;
    }
}
