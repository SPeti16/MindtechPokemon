package com.example.mindtechpokemon.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindtechpokemon.R
import com.example.mindtechpokemon.network.Pokemon
import com.example.mindtechpokemon.ui.AppViewModelProvider
import com.example.mindtechpokemon.ui.navigation.NavigationDestination
import com.example.mindtechpokemon.ui.screens.view_model.HomeViewModel
import com.example.mindtechpokemon.ui.theme.PokemonTopAppBar

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    info: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    //val dimensions = LocalDim.current

    val searchPokemon by viewModel.searchPokemon.collectAsState()
    val selectedType by viewModel.selectedType.collectAsState()
    val expanded by viewModel.expanded.collectAsState()
    val pokemonList by viewModel.selectedPokemon.collectAsState()
    val types by viewModel.types.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            PokemonTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
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
            TextField(
                value = searchPokemon,
                onValueChange = { viewModel.updateSearchPokemon(it) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_search),
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                },
                //placeholder = { Text("") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )
            Spacer(Modifier.height(4.dp))
            Text("Pokemon Types")
            Spacer(Modifier.height(4.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Column {
                    Text(
                        text = if(selectedType=="")"Select..." else selectedType,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.updateExpanded(true) }
                            .padding(16.dp)
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { viewModel.updateExpanded(false)
                            viewModel.updateSelectedType("")}
                    ) {
                        types.results.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(text = type.name) },
                                onClick = {
                                    viewModel.updateSelectedType(type.name)
                                    viewModel.updateExpanded(false)
                                }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(4.dp))
            LazyColumn (modifier = Modifier.fillMaxSize()){
                items(pokemonList.size) { i ->
                    if((searchPokemon=="" || pokemonList[i].name.contains(searchPokemon))&&
                        (selectedType=="" || pokemonList[i].types[0].type.name==selectedType))
                    Row (modifier = Modifier.fillMaxWidth()){
                        Card (modifier = Modifier.weight(3F),
                            onClick = {
                                viewModel.saveData(pokemonList[i])
                                info()
                            }){
                            Row (modifier = Modifier.fillMaxWidth()){
                                Text(modifier = Modifier.weight(1F), text = pokemonList[i].name)
                                Text(modifier = Modifier.weight(1F),text = pokemonList[i].types[0].type.name)
                                Text(modifier = Modifier.weight(1F),text = "-")
                            }
                        }
                        Button(modifier = Modifier.weight(1F), onClick = { /*TODO*/ }) {
                            Text(text = "Catch")
                        }
                    }
                }
            }
        }
    }
}