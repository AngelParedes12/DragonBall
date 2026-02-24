package edu.ucne.dragonball.domain.model

data class planet(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
)