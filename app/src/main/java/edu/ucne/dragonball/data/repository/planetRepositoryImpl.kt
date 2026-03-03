package edu.ucne.dragonball.data.repository

import edu.ucne.dragonball.data.remote.Resource
import edu.ucne.dragonball.data.remote.DragonBallApi
import edu.ucne.dragonball.data.remote.dto.planetDto
import edu.ucne.dragonball.data.remote.dto.PlanetResponseDto
import edu.ucne.dragonball.domain.model.planet
import edu.ucne.dragonball.domain.repository.planetRepository
import javax.inject.Inject

class planetRepositoryImpl @Inject constructor(
    private val api: DragonBallApi
) : planetRepository {

    override suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Resource<List<planet>> {
        try {
            val response = if (!name.isNullOrBlank()) {
                api.getPlanetsByName(name)
            } else {
                api.getPlanets(page, limit)
            }

            if (response.isSuccessful && response.body() != null) {
                val body = response.body()
                val planetsList: List<planetDto>? = if (!name.isNullOrBlank()) {
                    body as? List<planetDto>
                } else {
                    (body as? PlanetResponseDto)?.items
                }

                return Resource.Success(planetsList?.map { it.toDomain() } ?: emptyList())
            } else {
                return Resource.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Error desconocido")
        }
    }

    override suspend fun getPlanetDetail(id: Int): Resource<planet> {
        return try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("No encontrado")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }
    }
}