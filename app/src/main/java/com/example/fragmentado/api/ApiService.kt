package com.example.fragmentado.api

import com.example.fragmentado.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>
}