package com.example.mindtechpokemon.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mindtechpokemon.R
import com.example.mindtechpokemon.network.Pokemon
import com.example.mindtechpokemon.ui.AppViewModelProvider
import com.example.mindtechpokemon.ui.navigation.NavigationDestination
import com.example.mindtechpokemon.ui.screens.view_model.HomeViewModel
import com.example.mindtechpokemon.ui.screens.view_model.InfoViewModel
import com.example.mindtechpokemon.ui.theme.PokemonTopAppBar
import kotlinx.serialization.json.Json

object InfoDestination : NavigationDestination {
    const val infoArg = "info"
    override val route = "info/{$infoArg}"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    back: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InfoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    //val dimensions = LocalDim.current

    val pokemon by viewModel.pokemonData.collectAsState()
    if(pokemon.name == ""){
        viewModel.loadPokemonData()
    }
    val catch by viewModel.catch.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            PokemonTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = true,
                navigateUp = back
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (pokemon.name == "") {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            } else {
                AsyncImage(
                    modifier = Modifier,
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(pokemon.sprites.front_default)
                        .crossfade(true)
                        .build(),
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.pokemon),
                    placeholder = painterResource(R.drawable.pokemon),
                )
                Spacer(Modifier.height(4.dp))
                Row {
                    Text(modifier = Modifier.weight(1F), text = "Name")
                    Text(modifier = Modifier.weight(1F), text = pokemon.name)
                }
                Row {
                    Text(modifier = Modifier.weight(1F), text = "Weight")
                    Text(modifier = Modifier.weight(1F), text = "${pokemon.weight} kg")
                }
                Row {
                    Text(modifier = Modifier.weight(1F), text = "Height")
                    Text(modifier = Modifier.weight(1F), text = "${pokemon.height} m")
                }
                Row {
                    Text(modifier = Modifier.weight(1F), text = "Abilities")
                    Text(modifier = Modifier.weight(1F), text = viewModel.getAbilities())
                }
                Row {
                    Text(modifier = Modifier.weight(1F), text = "Status")
                    Text(modifier = Modifier.weight(1F), text = if (!catch) "-" else "Catched")
                }
                Spacer(Modifier.height(4.dp))
                Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.updateCatch(!catch)}) {
                    Text(if (catch) "Release" else "Catch")
                }
            }
        }
    }
}