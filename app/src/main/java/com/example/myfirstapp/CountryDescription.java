package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDescription extends AppCompatActivity {
    private TextView capitalView;
    private TextView regionView;
    private TextView populationView;
    private TextView alpha3CodeView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_page);
        Intent pageInformation = getIntent();
        capitalView = findViewById(R.id.capital);
        regionView = findViewById(R.id.region);
        populationView = findViewById(R.id.population);
        alpha3CodeView = findViewById(R.id.alpha3Code);

        capitalView.setText("Capital : " + pageInformation.getStringExtra("capital"));
        regionView.setText("Continent : " + pageInformation.getStringExtra("region"));
        populationView.setText("There is " + pageInformation.getStringExtra("population") + " humans in this country !");
        alpha3CodeView.setText("Country code : " + pageInformation.getStringExtra("alpha3Code"));

    }
}
