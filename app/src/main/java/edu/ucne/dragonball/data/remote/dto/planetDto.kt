package edu.ucne.dragonball.data.remote.dto

import edu.ucne.dragonball.domain.model.planet

data class PlanetResponseDto(
    val items: List<planetDto>
)

data class planetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
) {
    fun toDomain() = planet(
        id,
        name,
        isDestroyed,
        description,
    )
}