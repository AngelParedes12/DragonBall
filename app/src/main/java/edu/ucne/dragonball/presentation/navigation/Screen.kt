package edu.ucne.dragonball.presentation.navigation

import kotlinx.serialization.Serializable
sealed class Screen {
    @Serializable
    data object PlanetList : Screen()

    @Serializable
    data class PlanetDetail(val id: Int) : Screen()
}