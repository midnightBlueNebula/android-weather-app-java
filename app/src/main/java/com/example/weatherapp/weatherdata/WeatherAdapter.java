package com.example.weatherapp.weatherdata;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.R;

import java.io.IOException;

public class WeatherAdapter {
    public static void adapter(String city, TextView degree, ImageView image) {
        WeatherData data = new WeatherData(city);
        WeatherAdapter.setWeather(data.getWeather(), image);
        WeatherAdapter.setDegree(data.getDegree(), degree);
    }

    private static void setWeather(String weather, ImageView image){
        if(weather == "Clouds"){
            image.setImageResource(R.drawable.ic_cloudy);
        } else {
            image.setImageResource(R.drawable.ic_sunny);
        }
    }

    private static void setDegree(double degree, TextView text){
        String deg = ""+degree+"°C";
        text.setText(deg);
    }
}