package com.example.battlebox.ui.theme.extra

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.battlebox.data.Pokemon


@Composable
fun BattleBoxApp(pokemonList: List<Pokemon>){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar{
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                NavigationBarItem(
                    selected = currentRoute == "pokedex",
                    onClick = { navController.navigate("pokedex")},
                    icon = { Icon(Icons.Default.List, contentDescription = "Pokedex") },
                    label = { Text("Pokedex") }
                )
                NavigationBarItem(
                    selected = currentRoute == "team",
                    onClick = { navController.navigate("team")},
                    icon = { Icon(Icons.Default.List, contentDescription = "Team") },
                    label = { Text("Team") }
                )
                NavigationBarItem(
                    selected = currentRoute == "calc",
                    onClick = { navController.navigate("calc")},
                    icon = { Icon(Icons.Default.List, contentDescription = "Calc") },
                    label = { Text("Calc") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "pokedex",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("pokedex") { PokedexScreen(pokemonList = pokemonList) }
            composable("team") { TeamBuilderScreen() }
            composable("calc") { CalculatorScreen() }
        }
    }
}