package com.example.weatherapp.weatherdata;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import com.example.weatherapp.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;

public class WeatherData {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private String weather;
    private int degree;
    private final String apiKey = "secret";
    private String requestStr = "https://api.openweathermap.org/data/2.5/weather";
    private String options;

    private ProgressDialog pd;

    public WeatherData(String city) {
        StrictMode.setThreadPolicy(policy);

        options = "?q=" + city + "&apikey=" + apiKey;
        requestStr += options;

        try {
            JSONObject json = readJsonFromUrl(requestStr);
            JSONObject main = (JSONObject) json.get("main");
            degree = (int) Math.round(main.getDouble("temp") - 273.15);

            JSONObject weatherJson = (JSONObject) json.getJSONArray("weather").get(0);
            weather = weatherJson.getString("main");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getWeather() {
        return weather;
    }

    public int getDegree() {
        return degree;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
