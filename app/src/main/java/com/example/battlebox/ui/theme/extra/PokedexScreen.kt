package com.example.battlebox.ui.theme.extra

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.battlebox.data.Pokemon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button

@Composable
fun PokemonCard(pokemon: Pokemon){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            val imageUrl = "file:///android_asset/sprites/pokemon/${pokemon.num}.png"

            AsyncImage(
                model = imageUrl,
                contentDescription = "${pokemon.name} sprite",
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "#${pokemon.num} ${pokemon.name}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = pokemon.types.joinToString(" / "),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun PokedexList(pokemonList: List<Pokemon>){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues( vertical = 8.dp)
    ) {
        val validPokemon = pokemonList.filter { it.num > 0 }.sortedBy { it.num }

        items(validPokemon) { pokemon ->
            PokemonCard(pokemon = pokemon)
        }
    }
}

@Composable
fun PokedexScreen(pokemonList: List<Pokemon>){
    var searchQuery by remember { mutableStateOf("") }

    val filteredList = pokemonList.filter { pokemon ->
        pokemon.name.contains(searchQuery, ignoreCase = true) ||
        pokemon.num.toString().contains(searchQuery)
    }

    Column(modifier = Modifier.fillMaxSize()){
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newText -> searchQuery = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text("Search by Name or Number") },
            singleLine = true
        )
        PokedexList(pokemonList = filteredList)
    }
}

