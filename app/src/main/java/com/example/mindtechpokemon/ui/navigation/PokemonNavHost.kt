package com.example.mindtechpokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mindtechpokemon.network.Pokemon
import com.example.mindtechpokemon.ui.screens.HomeDestination
import com.example.mindtechpokemon.ui.screens.HomeScreen
import com.example.mindtechpokemon.ui.screens.InfoDestination
import com.example.mindtechpokemon.ui.screens.InfoScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun PokemonNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                info = {
//                    val json = Json.encodeToString(it)
//                    navController.currentBackStackEntry?.savedStateHandle?.set(
//                        key = InfoDestination.infoArg,
//                        value = json
//                    )
                    navController.navigate(InfoDestination.route)
                       },
            )
        }
        composable(route = InfoDestination.route) {
            InfoScreen(
                back = { navController.popBackStack() }
            )
        }
    }
}