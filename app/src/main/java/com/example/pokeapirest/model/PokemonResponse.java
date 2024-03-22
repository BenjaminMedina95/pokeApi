package com.example.pokeapirest.model;

import java.util.ArrayList;

public class PokemonResponse {
    private ArrayList<Pokemon> results;
    private int idCounter = 1;

    public ArrayList<Pokemon> getResults() {
        return results;
    }


    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

    public void asignarIDs() {
        for (Pokemon pokemon: results){
            pokemon.setId(idCounter++);
        }
        }
    }






