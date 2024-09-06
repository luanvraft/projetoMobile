package com.example.fragmentado

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArmazenarPokemonActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_armazenar_pokemon)

        dbHelper = DBHelper(this)

        val pokeName: EditText = findViewById(R.id.poke_name)
        val pokeType1: EditText = findViewById(R.id.poke_type1)
        val pokeType2: EditText = findViewById(R.id.poke_type2)
        val btnSalvar: Button = findViewById(R.id.btn_salvar)
        val btnArmazenamento: Button = findViewById(R.id.btn_armazenamento)
        val btnBack: ImageButton = findViewById(R.id.btn_voltar)



        btnBack.setOnClickListener {
            finish()
        }

        btnSalvar.setOnClickListener {
            val name: String = pokeName.text.toString()
            val type1: String = pokeType1.text.toString()
            var type2: String = pokeType2.text.toString()

            if (type2.isEmpty()) type2 = "Sem 2º tipo"
            dbHelper.insertData(name, type1, type2)
        }
        btnArmazenamento.setOnClickListener {
            updateUI()
        }
    }

    private fun updateUI() {
        val respostas = dbHelper.getAllData()
        val resultado = StringBuilder()
        for (resp in respostas) {
            resultado.append(
                "Nome do Pokémon: ${resp.name} " +
                        "\n1º tipo do Pokémon: ${resp.type1} " +
                        "\n2º tipo do Pokémon: ${resp.type2}\n\n"
            )
        }
        val txtPokemonArmazem: TextView = findViewById(R.id.txt_pokemonArmazem)
        txtPokemonArmazem.text = resultado.toString()
    }
}