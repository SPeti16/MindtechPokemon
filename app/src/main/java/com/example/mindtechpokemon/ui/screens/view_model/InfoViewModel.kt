package com.example.mindtechpokemon.ui.screens.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mindtechpokemon.data.DataRepository
import com.example.mindtechpokemon.network.AppContainer
import com.example.mindtechpokemon.network.Pokemon
import com.example.mindtechpokemon.network.PokemonData
import com.example.mindtechpokemon.ui.screens.InfoDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class InfoViewModel (private val container: AppContainer, private val dataRepository: DataRepository) : ViewModel() {

    private val _pokemonData = MutableStateFlow(Pokemon.empty())
    val pokemonData: StateFlow<Pokemon> = _pokemonData.asStateFlow()
    fun updatePokemonData(data: Pokemon) = _pokemonData.update { data }

    private val _catch = MutableStateFlow(false)
    val catch: StateFlow<Boolean> = _catch.asStateFlow()
    fun updateCatch(data: Boolean) = _catch.update { data }

    fun loadPokemonData(){
        dataRepository.loadData()?.let { updatePokemonData(it) }
    }

    fun getAbilities():String{
        var text = ""
        pokemonData.value.abilities.forEach {
            if(text!=""){
                text += "\n"
            }
            text += it.ability.name
        }
        return text
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}