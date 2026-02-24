package edu.ucne.dragonball.presentation.tareas.list

sealed interface ListPlanetEvent {
    data class UpdateFilterName(val name: String) : ListPlanetEvent
    data object Search : ListPlanetEvent
}