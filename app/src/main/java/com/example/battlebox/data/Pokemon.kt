package com.example.battlebox.data

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val num: Int = -1,
    val name: String = "Unknown",
    val types: List<String> = emptyList(),
    val baseStats: BaseStats? = null
)

@Serializable
data class BaseStats(
    val hp: Int = 0,
    val atk: Int = 0,
    val def: Int = 0,
    val spa: Int = 0,
    val spd: Int = 0,
    val spe: Int = 0
)