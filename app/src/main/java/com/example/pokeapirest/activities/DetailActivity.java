package com.example.pokeapirest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pokeapirest.R;
import com.example.pokeapirest.model.Pokemon;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mNameTextView;

    private TextView mWeightTextnumber;
    private TextView mHeightTextnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        mImageView= findViewById(R.id.fotoImageView);
        mNameTextView= findViewById(R.id.nameDetailTextView);
        mWeightTextnumber = findViewById(R.id.weightTextnumber);
        mHeightTextnumber = findViewById(R.id.heightTextnumber);

        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            String url = intent.getStringExtra("url");
            int id = intent.getIntExtra("id",0);
            String weight = intent.getStringExtra("weight");
            String height = intent.getStringExtra("height");
            List<String> types = intent.getStringArrayListExtra("types");



            Pokemon pokemon = new Pokemon(name, url, id, weight, height);
            if(pokemon != null){
                Glide.with(this)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemon.getNumber() + ".png")
                        .into(mImageView);
                mNameTextView.setText(name);


            }
        }



    }
}