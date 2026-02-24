package edu.ucne.dragonball.domain.repository

import edu.ucne.dragonball.data.remote.Resource
import edu.ucne.dragonball.domain.model.planet


interface planetRepository {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Resource<List<planet>>

    suspend fun getPlanetDetail(id: Int): Resource<planet>
}