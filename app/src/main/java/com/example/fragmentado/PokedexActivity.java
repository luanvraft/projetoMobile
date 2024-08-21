package com.example.fragmentado;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fragmentado.api.ApiService;
import com.example.fragmentado.models.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexActivity extends AppCompatActivity {
    public static final String TAG = PokedexActivity.class.getSimpleName();
    private Retrofit retrofit;
    private int pokeNumber = 1;
    private TextView txtPokeName;
    private ImageView img;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        ImageButton btnBack = findViewById(R.id.btn_back);
        ImageButton btnNext = findViewById(R.id.btn_next);
        img = findViewById(R.id.img_pokemon);
        txtPokeName = findViewById(R.id.txt_name);

        btnBack.setOnClickListener(v -> {
            if(pokeNumber == 1) {
                return;
            }
            pokeNumber--;
            getData();
        });

        btnNext.setOnClickListener(v -> {
            if(pokeNumber == 649) {
                return;
            }
            pokeNumber++;
            getData();
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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
