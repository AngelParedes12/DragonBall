package edu.ucne.dragonball.presentation.tareas.details

import edu.ucne.dragonball.domain.model.planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: planet? = null,
    val error: String? = null
)