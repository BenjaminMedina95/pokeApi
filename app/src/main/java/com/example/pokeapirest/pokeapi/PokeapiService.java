package com.example.pokeapirest.pokeapi;

import com.example.pokeapirest.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {
    @GET("pokemon")
    Call<PokemonResponse> obtenerListaPokemon(@Query("limit")int limit, @Query("offset") int offset);
}
