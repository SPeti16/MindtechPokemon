package com.example.mindtechpokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mindtechpokemon.ui.theme.PokemonApp
import com.example.mindtechpokemon.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PokemonApp()
                }
            }
        }
    }
}