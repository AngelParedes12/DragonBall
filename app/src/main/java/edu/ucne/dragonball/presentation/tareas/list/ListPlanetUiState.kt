package edu.ucne.dragonball.presentation.tareas.list

import edu.ucne.dragonball.domain.model.planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<planet> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)