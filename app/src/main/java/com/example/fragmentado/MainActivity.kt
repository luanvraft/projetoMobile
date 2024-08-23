package com.example.fragmentado


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPokedex: Button = findViewById(R.id.btn_pokedex)
        val btnMaps: Button = findViewById(R.id.btn_Local)

        btnPokedex.setOnClickListener {
            val intentPokedexScreen = Intent(this, PokedexActivity::class.java)
            startActivity(intentPokedexScreen)
        }
        btnMaps.setOnClickListener {
            val intentMapsScreen = Intent(this, LocalizacaoActivity::class.java)
            startActivity(intentMapsScreen)
        }
    }
}