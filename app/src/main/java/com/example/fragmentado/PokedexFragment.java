package com.example.fragmentado;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fragmentado.api.ApiService;
import com.example.fragmentado.models.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexFragment extends Fragment {
    private static final String TAG = PokedexFragment.class.getSimpleName();
    private Retrofit retrofit;
    private int pokeNumber = 1;
    private TextView txtPokeName;
    private ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokedex,
                container, false);


        ImageButton btnBack = view.findViewById(R.id.btn_back);
        ImageButton btnNext = view.findViewById(R.id.btn_next);
        img = view.findViewById(R.id.img_pokemon);
        txtPokeName = view.findViewById(R.id.txt_name);

        btnBack.setOnClickListener(v -> {
            if (pokeNumber == 1) {
                return;
            }
            pokeNumber--;
            getData();
        });

        btnNext.setOnClickListener(v -> {
            pokeNumber++;
            getData();
        });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        ApiService service = retrofit.create(ApiService.class);
        Call<Pokemon> responseCall = service.getPokemon(pokeNumber);

        responseCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(@NonNull Call<Pokemon> call, @NonNull Response<Pokemon> response) {
                if(!response.isSuccessful()) {
                    Log.e(TAG, "on Response failed: " + response.code());
                    Log.e(TAG, "on Response failed: " + response.errorBody());
                }
                Pokemon pokemon = response.body();
                assert pokemon != null;
                Log.d(TAG, "poke" + pokemon.getName());
                txtPokeName.setText(pokemon.getName());
                setPokemonImage();
            }

            @Override
            public void onFailure(@NonNull Call<Pokemon> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setPokemonImage() {
        String imageRequest = "https://raw.githubusercontent" +
                ".com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/";
        Glide.with(this)
                .load(imageRequest + pokeNumber + ".gif")
                .into(img);
    }
}