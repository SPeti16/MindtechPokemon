package com.example.mindtechpokemon.network

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon?limit=100000&offset=0")
    suspend fun getData(): PokemonData
    @GET("type?limit=100000&offset=0")
    suspend fun getTipes(): PokemonData

    @GET("pokemon/{name}")
    suspend fun get1(@Path("name") name:String): Pokemon
}
