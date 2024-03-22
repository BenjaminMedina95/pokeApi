package com.example.pokeapirest.model;

import android.content.Context;
import android.content.Intent;

import com.example.pokeapirest.activities.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private int number;
    private String name;
    private String url;
    private int id;
    private String weight;
    private String height;

    //private List<String> types;

    public int getId() {
        return id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }




    public void setId(int id) {
        this.id = id;
    }

    public Pokemon(String name, String url, int id, String weight, String height) {

        this.name = name;
        this.url = url;
        this.id= id;
        this.weight= weight;
        this.height= height;




    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public int getNumber() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Intent getIntent(Context context){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("url", url);
        intent.putExtra("id", id);
        intent.putExtra("weight", weight);
        intent.putExtra("height", height);



        return intent;
    }
}
