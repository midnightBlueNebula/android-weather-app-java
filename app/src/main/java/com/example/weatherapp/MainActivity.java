package com.example.weatherapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.weatherdata.WeatherData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        WeatherData data = new WeatherData("Ankara");
        binding.setData(data);

        SearchView search = findViewById(R.id.searchCity);
        ImageView image = findViewById(R.id.weatherIcon);
        TextView city = findViewById(R.id.cityName);



        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                city.setText(query);
                WeatherData data = new WeatherData(query);
                binding.setData(data);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}