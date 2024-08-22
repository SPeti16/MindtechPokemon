package com.example.mindtechpokemon.network

interface PokemonRepository {
    suspend fun getPokemonData(): PokemonData
    suspend fun getTypes(): PokemonData
    suspend fun get1Pokemon(name:String): Pokemon
}

class NetworkPokemonRepository(
    private val pokemonApiService: PokemonApiService
) : PokemonRepository{
    override suspend fun getPokemonData(): PokemonData = pokemonApiService.getData()
    override suspend fun getTypes(): PokemonData = pokemonApiService.getTipes()
    override suspend fun get1Pokemon(name: String): Pokemon = pokemonApiService.get1(name)

}