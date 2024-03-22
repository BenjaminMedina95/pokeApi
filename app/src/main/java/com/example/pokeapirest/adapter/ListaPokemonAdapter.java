package com.example.pokeapirest.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokeapirest.R;
import com.example.pokeapirest.activities.DetailActivity;
import com.example.pokeapirest.model.Pokemon;

import java.util.ArrayList;
import java.util.List;


public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder>{

        private List<Pokemon> dataset;
        private Context context;


        public ListaPokemonAdapter (Context context ) {
            this.context=context;
            dataset = new ArrayList<>();
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false );

            ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d("Debug", "onClick() method is called");
                    int position = viewHolder.getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Pokemon pokemon = dataset.get(position);
                        try{

                            context.startActivity(pokemon.getIntent(context));

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            // Manejar la excepción apropiadamente
                        }
                    }


                }
            } );
            return viewHolder;


        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Pokemon p = dataset.get(position);
            // Configura el nombre del pókemon en el textView
            holder.nombreTextView.setText(p.getName());
            //Configura en Id del pokemon en el textview
            holder.idTextView.setText("#" + p.getId());
            //Carga la imagen del pokemon en el ImageView
            Glide.with(context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + p.getNumber() + ".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.fotoImageView);
        }

        @Override
        public int getItemCount() {

            return dataset.size();
        }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
            dataset.addAll(listaPokemon);
            notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView fotoImageView;
            private TextView nombreTextView;
            private TextView idTextView;

            private CardView cardView;



            public ViewHolder(@NonNull View itemView) {

                super(itemView);
                fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
                nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
                cardView = (CardView) itemView.findViewById(R.id.cardView);
                idTextView= (TextView) itemView.findViewById(R.id.idTextView);

            }


        }
    }



