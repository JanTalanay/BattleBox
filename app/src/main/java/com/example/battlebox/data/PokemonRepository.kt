package com.example.battlebox.data

import android.content.Context
import kotlinx.serialization.json.Json

class PokemonRepository (private val context: Context){

    fun loadPokedex(): Map<String, Pokemon>{
        val jsonString = context.assets.open("pokedex.json").bufferedReader().use { it.readText() }
        val json = Json { ignoreUnknownKeys = true }

        return json.decodeFromString<Map<String, Pokemon>>(jsonString)
    }
}