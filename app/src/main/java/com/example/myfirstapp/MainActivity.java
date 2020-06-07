package com.example.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://restcountries.eu/";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("Application save", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Country> countryList = getDataFromCache();

        if(countryList != null){
            showList(countryList);
        } else {
            makeAPIcall();
        }

    }

    private List<Country> getDataFromCache() {
        String countries = sharedPreferences.getString(Constants.KEY_Country_LIST, null);

        if(countries == null){
            return null;
        } else {
            Type listType = new TypeToken<List<Country>>(){}.getType();
            return gson.fromJson(countries, listType);

        }
    }

    private void showList(List<Country> countryList) {
        recyclerView = findViewById(R.id.recycler_view);
        final SwipeRefreshLayout swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefresh.setRefreshing(false);
            }
        });
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(countryList,MainActivity.this);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeAPIcall() {

        CountryAPI placeAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(CountryAPI.class);

        Call<List<Country>> call = placeAPI.getCountryResponse();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Country> countryList = new ArrayList<>(response.body());
                    Toast.makeText(getApplicationContext(), "API SUCCESS", Toast.LENGTH_SHORT).show();
                    saveList(countryList);
                    showList(countryList);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Country> countryList) {
        String jsonString = gson.toJson(countryList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_Country_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }
}