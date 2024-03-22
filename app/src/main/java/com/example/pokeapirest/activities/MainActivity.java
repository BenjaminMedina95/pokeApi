package com.example.pokeapirest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pokeapirest.R;
import com.example.pokeapirest.adapter.ListaPokemonAdapter;
import com.example.pokeapirest.model.Pokemon;
import com.example.pokeapirest.model.PokemonResponse;
import com.example.pokeapirest.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;

    private int offset;

    private boolean cargarMas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        //Carga de elementos
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (cargarMas) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Final");

                            cargarMas = false;
                            offset += 40;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });
        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cargarMas = true;
        offset = 0;
        obtenerDatos(offset);
    }
    private void obtenerDatos(int offset){
        PokeapiService service = retrofit.create(PokeapiService.class);
       Call<PokemonResponse> pokemonResponseCall= service.obtenerListaPokemon(40,offset);

       pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
           @Override
           public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
               cargarMas = true;
               if (response.isSuccessful()){
                   PokemonResponse pokemonResponse = response.body();

                   // Asigna IDs a los Pokémon en la respuesta
                   pokemonResponse.asignarIDs();
                   ArrayList<Pokemon> listaPokemon = pokemonResponse.getResults();

                   listaPokemonAdapter.adicionarListaPokemon(listaPokemon);
               }
               else{
                Log.e (TAG, "onResponse: " + response.errorBody());
               }
           }

           @Override
           public void onFailure(Call<PokemonResponse> call, Throwable t) {
               cargarMas = true;
               Log.e(TAG, " onFailure " + t.getMessage());
           }


       });



    }
}