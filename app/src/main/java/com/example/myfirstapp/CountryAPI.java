package com.example.myfirstapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryAPI {
    @GET("/rest/v2/all")
    Call<List<Country>> getCountryResponse();

}