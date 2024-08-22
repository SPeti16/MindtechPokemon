package com.example.mindtechpokemon.data

import androidx.compose.runtime.mutableStateOf
import com.example.mindtechpokemon.network.Pokemon

class DataRepository {
    private val data = mutableStateOf<Pokemon?>(null)

    fun saveData(data: Pokemon) {
        this.data.value = data
    }

    fun loadData(): Pokemon? {
        return data.value
    }
}