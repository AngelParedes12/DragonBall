package edu.ucne.dragonball.data.repository

import edu.ucne.dragonball.data.remote.DragonBallApi
import edu.ucne.dragonball.data.remote.Resource
import edu.ucne.dragonball.domain.repository.planetRepository
import edu.ucne.dragonball.domain.model.planet
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val api: DragonBallApi
) : planetRepository {

    override suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Resource<List<planet>> {
        return try {
            if (!name.isNullOrBlank()) {
                val response = api.getPlanetsByName(name)
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.map { it.toDomain() }
                    Resource.Success(data)
                } else {
                    Resource.Error("Error servidor: Código ${response.code()}")
                }
            } else {
                val response = api.getPlanets(page, limit)
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!.items.map { it.toDomain() }
                    Resource.Success(data)
                } else {
                    Resource.Error("Error servidor: Código ${response.code()}")
                }
            }
        } catch (e: Exception) {
            Resource.Error("Error conexión: ${e.localizedMessage}")
        }
    }

    override suspend fun getPlanetDetail(id: Int): Resource<planet> {
        return try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("Error servidor: Código ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Error conexión: ${e.message}")
        }
    }
}