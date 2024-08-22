package com.example.mindtechpokemon.ui.screens.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindtechpokemon.data.DataRepository
import com.example.mindtechpokemon.network.AppContainer
import com.example.mindtechpokemon.network.Pokemon
import com.example.mindtechpokemon.network.PokemonAppContainer
import com.example.mindtechpokemon.network.PokemonData
import com.example.mindtechpokemon.network.PokemonResults
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(private val container: AppContainer, private val dataRepository: DataRepository) : ViewModel() {

    private val  emptyPokemonData = PokemonData(0,"","", listOf(PokemonResults("","")))

    private val _types = MutableStateFlow(emptyPokemonData)
    val types: StateFlow<PokemonData> = _types.asStateFlow()
    fun updateTypes(data: PokemonData) = _types.update { data }

    private val _pokemonData = MutableStateFlow(emptyPokemonData)
    val pokemonData: StateFlow<PokemonData> = _pokemonData.asStateFlow()
    fun updatePokemonData(data: PokemonData) = _pokemonData.update { data }

    val emptyPokemon = emptyList<Pokemon>()
    private val _selectedPokemon = MutableStateFlow(emptyPokemon)
    val selectedPokemon: StateFlow<List<Pokemon>> = _selectedPokemon.asStateFlow()
    fun updateSelectedPokemon(pokemon: List<Pokemon>) = _selectedPokemon.update {
        pokemon
    }

    private val _searchPokemon = MutableStateFlow("")
    val searchPokemon: StateFlow<String> = _searchPokemon.asStateFlow()
    fun updateSearchPokemon(pokemon: String) = _searchPokemon.update { pokemon }

    private val _selectedType = MutableStateFlow("")
    val selectedType: StateFlow<String> = _selectedType.asStateFlow()
    fun updateSelectedType(type: String) = _selectedType.update { type }

    private val _expanded = MutableStateFlow(false)
    val expanded: StateFlow<Boolean> = _expanded.asStateFlow()
    fun updateExpanded(type: Boolean) = _expanded.update { type }

    init {
        getPokemonData()
        getTipes()
    }

    private fun getPokemonData() {
        viewModelScope.launch {
            try {
                updatePokemonData(container.pokemonRepository.getPokemonData())
                getPokemon()
            }catch (e: IOException){
                updatePokemonData(emptyPokemonData)
            }catch (e: HttpException){
                updatePokemonData(emptyPokemonData)
            }
        }

    }
    private fun getTipes() {
        viewModelScope.launch {
            try {

                updateTypes(container.pokemonRepository.getTypes())
            }catch (e: IOException){
                updateTypes(emptyPokemonData)
            }catch (e: HttpException){
                updateTypes(emptyPokemonData)
            }
        }

    }


    private fun <T> List<T>.selectRandomElements(numberOfElements: Int): List<T> {
        if (numberOfElements >= size) return this

        val shuffledList = this.shuffled()
        return shuffledList.subList(0, numberOfElements)
    }

    private fun getPokemon() {
        val randomElements = pokemonData.value.results.selectRandomElements(10)
        val list = selectedPokemon.value.toMutableList()
        viewModelScope.launch {
            randomElements.forEach {
                try {
                    list.add(container.pokemonRepository.get1Pokemon(it.name))
                } catch (e: IOException) {
                    updateSelectedPokemon(emptyPokemon)
                } catch (e: HttpException) {
                    updateSelectedPokemon(emptyPokemon)
                } catch (e: Exception) {
                    updateSelectedPokemon(emptyPokemon)
                }
            }
            updateSelectedPokemon(list.toList())
        }

    }

    fun saveData(data: Pokemon) {
        dataRepository.saveData(data)
    }

}
