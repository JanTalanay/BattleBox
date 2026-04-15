package com.example.battlebox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.battlebox.data.PokemonRepository
import com.example.battlebox.ui.theme.BattleBoxTheme
import com.example.battlebox.ui.theme.extra.BattleBoxApp
import com.example.battlebox.ui.theme.extra.PokedexList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val repository = PokemonRepository(this)
            val pokedex = repository.loadPokedex()
            val pokemonList = pokedex.values.toList()

            setContent {
                BattleBoxTheme{
                    BattleBoxApp(pokemonList = pokemonList)
//                    Surface(
//                        modifier = Modifier.fillMaxSize(),
//                        color = MaterialTheme.colorScheme.background
//                    ) {
//                        val pokemonList = pokedex.values.toList()
//                        PokedexList(pokemonList = pokemonList)
//                    }
                }
            }

        } catch (e: Exception) {
            Log.e("BATTLEBOX_TEST", "❌ Error loading Pokedex: ${e.message}")
            e.printStackTrace()
        }
    }
}