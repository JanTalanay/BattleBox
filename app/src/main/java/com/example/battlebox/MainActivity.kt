package com.example.battlebox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.battlebox.data.PokemonRepository
import com.example.battlebox.ui.theme.BattleBoxTheme
import com.example.battlebox.ui.theme.extra.BattleBoxApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val repository = PokemonRepository(this)
            val pokedex = repository.loadPokedex()
            val pokemonList = pokedex.values.toList()

            setContent {
                BattleBoxTheme{
                    val myPokemonData = pokemonList
                    BattleBoxApp(pokemonList = myPokemonData)
                }
            }

        } catch (e: Exception) {
            Log.e("BATTLEBOX_TEST", "❌ Error loading Pokedex: ${e.message}")
            e.printStackTrace()
        }
    }
}