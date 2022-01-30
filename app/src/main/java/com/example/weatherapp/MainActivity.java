package com.example.weatherapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.weatherdata.WeatherAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView search = findViewById(R.id.searchCity);
        ImageView image = findViewById(R.id.weatherIcon);
        TextView city = findViewById(R.id.cityName);
        TextView degree = findViewById(R.id.degree);

        city.setText("ankara");
        WeatherAdapter.adapter("ankara", degree, image);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                city.setText(query);
                WeatherAdapter.adapter(query, degree, image);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}