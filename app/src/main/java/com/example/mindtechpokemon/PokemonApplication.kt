package com.example.mindtechpokemon

import android.app.Application
import com.example.mindtechpokemon.data.DataRepository
import com.example.mindtechpokemon.network.AppContainer
import com.example.mindtechpokemon.network.PokemonAppContainer

class PokemonApplication : Application() {
    lateinit var container: AppContainer
    lateinit var dataRepository : DataRepository
    override fun onCreate() {
        super.onCreate()
        container = PokemonAppContainer()
        dataRepository = DataRepository()
    }
}