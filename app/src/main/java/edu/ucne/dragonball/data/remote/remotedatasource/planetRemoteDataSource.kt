package edu.ucne.dragonball.data.remote.remotedatasource

import edu.ucne.dragonball.data.remote.DragonBallApi
import edu.ucne.dragonball.data.remote.dto.PlanetResponseDto
import edu.ucne.dragonball.data.remote.dto.planetDto
import retrofit2.HttpException
import javax.inject.Inject

class planetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Result<PlanetResponseDto> {
        return try {
            if (!name.isNullOrBlank()) {
                val response = api.getPlanetsByName(name)
                if (!response.isSuccessful) {
                    return Result.failure(Exception("Error de red ${response.code()}"))
                }
                Result.success(PlanetResponseDto(items = response.body() ?: emptyList()))
            } else {
                val response = api.getPlanets(page, limit)
                if (!response.isSuccessful) {
                    return Result.failure(Exception("Error de red ${response.code()}"))
                }
                Result.success(response.body()!!)
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<planetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}