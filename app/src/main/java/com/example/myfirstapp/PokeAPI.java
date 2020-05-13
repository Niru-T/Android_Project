package com.example.myfirstapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeAPI {
    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();

    @GET("/api/v2/ability")
    Call<RestPokemonResponse> getAbility();
}