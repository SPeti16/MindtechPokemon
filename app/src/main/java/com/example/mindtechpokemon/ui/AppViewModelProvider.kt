package com.example.mindtechpokemon.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mindtechpokemon.PokemonApplication
import com.example.mindtechpokemon.ui.screens.view_model.HomeViewModel
import com.example.mindtechpokemon.ui.screens.view_model.InfoViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(gameApplication().container, gameApplication().dataRepository)
        }
        initializer {
            InfoViewModel(gameApplication().container, gameApplication().dataRepository)
        }
    }
}


fun CreationExtras.gameApplication(): PokemonApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokemonApplication)